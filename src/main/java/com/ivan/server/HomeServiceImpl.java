package com.ivan.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ivan.client.application.home.HomeService;
import com.ivan.shared.FileNetActions;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;

public class HomeServiceImpl extends RemoteServiceServlet implements HomeService {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public List<String> doTask(FileNetActions action, String parameter) {
        List<String> strings = new ArrayList<>();

        strings.add("TEST" + action + parameter);

        return strings;
    }
}