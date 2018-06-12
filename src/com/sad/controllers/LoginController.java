package com.sad.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import javafx.event.*;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController  implements Initializable {


    @FXML
    Label banner_login_login;
    @FXML
    Label uname_label_login;
    @FXML
    TextField uname_login;
    @FXML
    Label pwrd_label_login;
    @FXML
    PasswordField pfield_login;
    @FXML
    Button login_button_login;
    @FXML
    Label noacc_label_login;
    @FXML
    Button snup_button_login;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //reference the handler
        login_button_login.setOnAction( this::login_handleButtonAction );


    }

    private void login_handleButtonAction(ActionEvent event) {

        //what happens after the button is clicked

    }






}
