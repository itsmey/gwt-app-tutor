package com.ivan.client.application;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.annotations.DefaultGatekeeper;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;
import com.ivan.client.application.login.LoginPresenter;

@DefaultGatekeeper
public class LoggedInGatekeeper implements Gatekeeper {

    @Inject
    public LoggedInGatekeeper() {
    }

    @Override
    public boolean canReveal() {
        return LoginPresenter.getIsLoggedIn();
    }
}