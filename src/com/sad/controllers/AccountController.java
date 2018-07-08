/*
 * Author: @Amanda
 * 7/7/18 updated to shut down database
 */
package com.sad.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
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

    @FXML
    private void logout(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource( "/com/sad/scenes/account.fxml" ));

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


    public void logoutButtonHandleOnAction(javafx.event.ActionEvent event) {

        // closing database and setting stage back to login Screen
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = null;
        try {
            System.out.println("Shutting down derby DB");
            DriverManager.getConnection(
                    "jdbc:derby:YETI;shutdown=true");
        }
        catch (SQLException e){
                e.printStackTrace();
            }
           try{ root = FXMLLoader.load(getClass().getResource( "/com/sad/scenes/login.fxml" ));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
                    catch (IOException e) {
                    e.printStackTrace(); }
           }



    // setting stage back to YETI application
    public void cancelButtonHandleOnAction(javafx.event.ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource( "/com/sad/scenes/yeti.fxml" ));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);

    }
}




