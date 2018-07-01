package com.sad.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //method handling happens

    @FXML
    Button logout_button_account;
    @FXML
    Button noLogout;


    //method here


    public void logoutButtonHandleOnAction(javafx.event.ActionEvent event) {

        // setting stage back to login Screen
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/sad/scenes/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    // setting stage back to YETI application
    public void cancelButtonHandleOnAction(javafx.event.ActionEvent event) {
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






