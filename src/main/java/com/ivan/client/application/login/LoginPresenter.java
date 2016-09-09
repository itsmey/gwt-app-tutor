package com.ivan.client.application.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.ivan.client.application.ApplicationPresenter;
import com.ivan.client.place.NameTokens;


public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy>
        implements LoginUiHandlers {
    interface MyView extends View, HasUiHandlers<LoginUiHandlers> {
        void setConnectionStatus(String text);
    }

    private static class InfoPopup extends PopupPanel {
        public InfoPopup() {
            super(true);
            VerticalPanel panel = new VerticalPanel();
            panel.add(new Label("This screen is intended to contain some useful information."));
            panel.add(new Label("You may click somewhere outside to close it."));
            setWidget(panel);
        }
    }

    @ProxyStandard
    @NameToken(NameTokens.LOGIN)
    @NoGatekeeper
    interface MyProxy extends ProxyPlace<LoginPresenter> {
    }

    private final PlaceManager placeManager;

    private static boolean isLoggedIn = false;

    @Inject
    LoginPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            PlaceManager placeManager) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);

        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    public void connectButtonClick(String name, String password) {
        LoginServiceAsync loginServiceAsync = GWT.create(LoginService.class);
        AsyncCallback<Void> asyncCallback = new AsyncCallback<Void>() {
            public void onFailure(Throwable caught) {
                setIsLoggedIn(false);
                getView().setConnectionStatus("Connection failure.");
            }

            public void onSuccess(Void result) {
                setIsLoggedIn(true);
                getView().setConnectionStatus("Connection established.");
            }
        };
        getView().setConnectionStatus("Connecting...");
        loginServiceAsync.connectFileNet(name, password, asyncCallback);
    }

    @Override
    public void showInfoButtonClick() {
        final InfoPopup popup = new InfoPopup();;

        popup.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
            public void setPosition(int offsetWidth, int offsetHeight) {
                int left = (Window.getClientWidth() - offsetWidth) / 2;
                int top = (Window.getClientHeight() - offsetHeight) / 2;
                popup.setPopupPosition(left, top);
            }
        });
    }

    @Override
    public void showDocumentsButtonClick() {
        PlaceRequest placeRequest = new PlaceRequest.Builder()
                .nameToken(NameTokens.HOME)
                .build();
        placeManager.revealPlace(placeRequest);
    }

    public static boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public static void setIsLoggedIn(boolean value) {
        isLoggedIn = value;
    }
}
