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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MFAController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    Button proceed_button_mfa;
    @FXML
    TextField otp_tfield_mfa;
    @FXML
    Label pin_banner_mfa;

    @FXML
    private void MFA_ProceedOnButtonAction(ActionEvent event) {

        String get_otp = otp_tfield_mfa.getText();
        int hitcount = 0;

        Stage stage = null;
        Parent root = null;


            if (get_otp.equals( EmailController.getSEND() )) {
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                try {
                    root = null;
                    root = FXMLLoader.load( getClass().getResource( "/com/sad/scenes/yeti.fxml" ) );
                } catch (java.io.IOException e) {
                    e.printStackTrace();

                }

            } else {
                JOptionPane.showMessageDialog( null, "Please enter a valid PIN!","Alert!",JOptionPane.ERROR_MESSAGE,null);
                hitcount++;
            }
            Scene scene = new Scene( root );
            stage.setScene( scene );
        }
    }







