package com.ivan.client.application.home;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.ivan.shared.FileNetActions;

import java.util.List;

public class HomeView extends ViewWithUiHandlers<HomeUiHandlers> implements HomePresenter.MyView {
    interface Binder extends UiBinder<Widget, HomeView> {
    }

    @UiField
    Label workingDirLabel;

    @UiField
    Label operationStatusLabel;

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

    @UiField
    Grid browseGrid;

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

    public void setOperationStatus(String status) {
        workingDirLabel.setText(status);
    }

    public void setWorkingDir(String dir) {
        workingDirLabel.setText(dir);
    }

    public void fillGrid(List<String> strings) {
        browseGrid.clear();

        for (int i = 0; i < strings.size(); i++) {
            browseGrid.setText(i, 0, strings.get(i));
        }
    }

}
