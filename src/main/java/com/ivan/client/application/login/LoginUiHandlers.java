package com.ivan.client.application.login;

import com.gwtplatform.mvp.client.UiHandlers;

public interface LoginUiHandlers extends UiHandlers {
    void connectButtonClick(String name, String password);
    void showInfoButtonClick();
    void showDocumentsButtonClick();
}