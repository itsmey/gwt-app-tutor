package com.ivan.client.application.home;

import com.gwtplatform.mvp.client.UiHandlers;

public interface HomeUiHandlers extends UiHandlers {
    void doButtonClick(int code, String parameter);
}