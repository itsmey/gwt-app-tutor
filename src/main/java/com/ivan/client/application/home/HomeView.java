package com.ivan.client.application.home;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class HomeView extends ViewWithUiHandlers<HomeUiHandlers> implements HomePresenter.MyView {
    interface Binder extends UiBinder<Widget, HomeView> {
    }

    @UiField
    Label workingDirLabel;

    @UiField
    TextBox nameTextBox;

    @UiField
    Button doButton;

    @UiField
    RadioButton crfRadio;

    @UiField
    RadioButton crdRadio;

    @UiField
    RadioButton dlfRadio;

    @UiField
    RadioButton dldRadio;

    @UiField
    RadioButton vwfRadio;

    @UiField
    RadioButton cwfRadio;

    @Inject
    HomeView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("doButton")
    public void doButtonClick(ClickEvent event) {
        int action_code = 0;

        if (vwfRadio.getValue()) action_code = 1;
        if (cwfRadio.getValue()) action_code = 2;
        if (crfRadio.getValue()) action_code = 3;
        if (crdRadio.getValue()) action_code = 4;
        if (dlfRadio.getValue()) action_code = 5;
        if (dldRadio.getValue()) action_code = 6;

        getUiHandlers().doButtonClick(action_code, nameTextBox.getText());
    }



}
