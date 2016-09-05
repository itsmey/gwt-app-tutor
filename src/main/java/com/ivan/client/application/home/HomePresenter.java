package com.ivan.client.application.home;

import com.filenet.api.core.Connection;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.exception.ExceptionCode;
import com.filenet.api.exception.EngineRuntimeException;
import com.filenet.api.util.UserContext;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.ivan.client.application.ApplicationPresenter;
import com.ivan.client.place.NameTokens;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy>
                           implements HomeUiHandlers {
    interface MyView extends View, HasUiHandlers<HomeUiHandlers> {
    }

    @ProxyStandard
    @NameToken(NameTokens.HOME)
    interface MyProxy extends ProxyPlace<HomePresenter> {
    }

    @Inject
    HomePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);

        getView().setUiHandlers(this);
    }

    @Override
    public void myOnClick(String name) {
        HomeServiceAsync homeServiceAsync = GWT.create(HomeService.class);
        AsyncCallback<Void> asyncCallback = new AsyncCallback<Void>() {
            public void onFailure(Throwable caught) {
                Window.alert("FAIL, code " + caught.getMessage());
            }

            public void onSuccess(Void result) {
                Window.alert("SUCCESS");
            }
        };
        homeServiceAsync.connectFileNet(name, asyncCallback);
    }
}
