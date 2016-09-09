package com.ivan.client.application.login;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import javax.inject.Inject;

public class LoginView extends ViewWithUiHandlers<LoginUiHandlers> implements LoginPresenter.MyView {
    interface Binder extends UiBinder<Widget, LoginView> {
    }

    @UiField
    TextBox loginTextBox;

    @UiField
    PasswordTextBox passwordTextBox;

    @UiField
    Button connectButton;

    @UiField
    Button showInfoButton;

    @UiField
    Button showDocumentsButton;

    @UiField
    Label loginLabel;

    @UiField
    Label headerLabel;

    @UiField
    Label passwordLabel;

    @UiField
    Label connectionStatusLabel;

    @Inject
    LoginView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("connectButton")
    public void connectButtonClick(ClickEvent event) {
        getUiHandlers().connectButtonClick(loginTextBox.getText(), passwordTextBox.getText());
    }

    @UiHandler("showInfoButton")
    public void showInfoButtonClick(ClickEvent event) {
        getUiHandlers().showInfoButtonClick();
    }

    @UiHandler("showDocumentsButton")
    public void showDocumentsButtonClick(ClickEvent event) {
        getUiHandlers().showDocumentsButtonClick();
    }

    public void setConnectionStatus(String text) {
        connectionStatusLabel.setText(text);
    }
}