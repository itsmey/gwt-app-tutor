package com.ivan.client.application.home;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface HomeServiceAsync {
    void  doTask(int code, String parameter, AsyncCallback<List<String>> asyncCallback);
}