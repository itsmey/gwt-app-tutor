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

    public List<String> doTask(FileNetActions action, String parameter) throws Exception {
        List<String> result = new ArrayList<>();

        List<String> containees = new ArrayList<>();

        switch (action) {
            case BROWSE:
                containees.addAll(FileNetManager.getAll());
                break;
            case GET_WORKING_DIR:
                break;
            case SET_WORKING_DIR:
                FileNetManager.setWorkingDir(parameter);
                containees.addAll(FileNetManager.getAll());
                break;
            case CREATE_DOCUMENT:
                FileNetManager.createDocument(parameter);
                break;
            case CREATE_FOLDER:
                FileNetManager.createFolder(parameter);
                break;
            case DELETE_DOCUMENT:
                FileNetManager.deleteDocument(parameter);
                break;
            case DELETE_FOLDER:
                FileNetManager.deleteFolder(parameter);
                break;
        }

        result.add(FileNetManager.getWorkingDir());
        result.addAll(containees);

        return result;
    }
}