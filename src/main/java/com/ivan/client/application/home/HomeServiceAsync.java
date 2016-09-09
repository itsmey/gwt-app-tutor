package com.ivan.client.application.home;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ivan.shared.FileNetActions;

import java.util.List;

public interface HomeServiceAsync {
    void  doTask(FileNetActions action, String parameter, AsyncCallback<List<String>> asyncCallback);
}