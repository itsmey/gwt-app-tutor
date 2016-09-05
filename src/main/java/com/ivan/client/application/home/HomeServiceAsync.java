package com.ivan.client.application.home;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface HomeServiceAsync {
    void connectFileNet(String name, AsyncCallback<Void> asyncCallback);
}