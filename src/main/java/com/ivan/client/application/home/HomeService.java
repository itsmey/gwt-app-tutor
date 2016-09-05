package com.ivan.client.application.home;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("home")
public interface HomeService extends RemoteService {
    void connectFileNet(String name) throws Exception;
}