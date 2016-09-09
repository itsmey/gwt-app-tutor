package com.ivan.client.application.home;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.ivan.shared.FileNetActions;

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
        FileNetActions action = FileNetActions.BROWSE;

        if (vwfRadio.getValue()) action = FileNetActions.BROWSE;
        if (cwfRadio.getValue()) action = FileNetActions.SET_WORKING_DIR;
        if (crfRadio.getValue()) action = FileNetActions.CREATE_FOLDER;
        if (crdRadio.getValue()) action = FileNetActions.CREATE_DOCUMENT;
        if (dlfRadio.getValue()) action = FileNetActions.DELETE_FOLDER;
        if (dldRadio.getValue()) action = FileNetActions.DELETE_DOCUMENT;

        getUiHandlers().doButtonClick(action, nameTextBox.getText());
    }



}
