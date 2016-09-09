package com.ivan.client.application.home;

import com.gwtplatform.mvp.client.UiHandlers;
import com.ivan.shared.FileNetActions;

public interface HomeUiHandlers extends UiHandlers {
    void doButtonClick(FileNetActions action, String parameter);
}