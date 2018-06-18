package com.sad.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MFAcontroller  implements Initializable {

    @FXML
    Button proceed_button_mfa;
    @FXML
    TextField otp_tfield_mfa;
    @FXML
    Label pin_banner_mfa;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
