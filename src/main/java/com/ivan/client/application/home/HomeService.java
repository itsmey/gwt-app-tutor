package com.ivan.client.application.home;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("home")
public interface HomeService extends RemoteService {
    List<String> doTask(int code, String parameter);
}