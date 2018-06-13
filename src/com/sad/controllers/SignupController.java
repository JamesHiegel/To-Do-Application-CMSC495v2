package com.sad.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    Label signup_banner_snup;
    @FXML
    Label uname_label_snup;
    @FXML
    TextField uname_snup;
    @FXML
    Label pfield_label_snup;
    @FXML
    PasswordField pfield_snup;
    @FXML
    Button snup_button_snup;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void signUp_handleButtonAction(ActionEvent event) {

        //create an account

        //send account details to database

        //authenticate

        // setting stage to normal YETI application
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/sad/scenes/yeti.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
