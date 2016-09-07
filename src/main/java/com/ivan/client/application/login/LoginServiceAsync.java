package com.ivan.client.application.login;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {
    void connectFileNet(String name, String password, AsyncCallback<Void> asyncCallback);
}