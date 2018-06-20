package com.sad.controllers;

import com.sad.utils.Security;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML Label signup_banner_snup;
    @FXML Label uname_label_snup;
    @FXML TextField uname_snup;
    @FXML Label pfield_label_snup;
    @FXML PasswordField pfield_snup;
    @FXML Button snup_button_snup;
    @FXML Button snup_cancel_button_snup1;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void signUp_handleButtonAction(ActionEvent event) {

        //create an account

        //send account details to database

        //authenticate
        boolean status=false;

        try {
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:derby:YETI;create=true");

            String sql = "insert into users values (next value for users_seq, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uname_snup.getText());
            pstmt.setString(2, Security.md5Hash(pfield_snup.getText()));

            status = pstmt.execute();
            status = true;
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        if (status) {

            // setting stage to normal YETI application
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
    }

    @FXML
    private void cancel_signUpHandleButtonAction(ActionEvent event) {

        // setting stage back to normal YETI application
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
