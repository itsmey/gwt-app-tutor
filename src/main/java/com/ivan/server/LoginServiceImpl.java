package com.ivan.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ivan.client.application.login.LoginService;

import javax.servlet.ServletException;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void connectFileNet(String name, String password) throws Exception {
        FileNetManager.connect(name, password, "TAD");
    }
}