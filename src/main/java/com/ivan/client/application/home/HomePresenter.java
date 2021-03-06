package com.ivan.client.application.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.ivan.client.application.ApplicationPresenter;
import com.ivan.client.place.NameTokens;
import com.ivan.shared.FileNetActions;

import java.util.List;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy>
                           implements HomeUiHandlers {
    interface MyView extends View, HasUiHandlers<HomeUiHandlers> {
        void setOperationStatus(String status);
        void setWorkingDir(String dir);
        void fillGrid(List<String> strings);
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
    public void onReveal() {
        super.onReveal();

        doButtonClick(FileNetActions.SET_WORKING_DIR, "/");
    }

    @Override
    public void doButtonClick(final FileNetActions action, String parameter) {
        HomeServiceAsync homeServiceAsync = GWT.create(HomeService.class);
        AsyncCallback<List<String>> asyncCallback = new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {
                getView().setOperationStatus("An error occurred: " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<String> result) {
                getView().setOperationStatus("Operation performed successfully.");

                String actualWorkingDir = result.remove(0);
                getView().setWorkingDir("Working directory is " + actualWorkingDir);

//                switch (action) {
//                    case SET_WORKING_DIR:
//                    case BROWSE:
//                        getView().fillGrid(result);
//                }
                getView().fillGrid(result);
            }
        };
        getView().setOperationStatus("Performing an operation...");
        homeServiceAsync.doTask(action, parameter, asyncCallback);
    }

}
