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

        //when this scene is initialized, the only thing we need to ensure as well, is the login button is ready to go!


        //reference the handler
       // login_button_login.setOnAction( this::login_handleButtonAction );


    }

    @FXML
    private void login_handleButtonAction(ActionEvent event) {

        //what happens after the button is clicked

        //do we use a string connected to a different class?
        String login_getUserName = uname_login.getText();

        /**
         How do we get the password and verify it? this is important and an aspect of security.
         Everything that must happen after the button is clicked, must be typed here, including scene changes and etc.
         */

        String login_pfield_retrieve = pfield_login.getText();


        //this is where we check where the uname & passwords match or not

        try {
            /**
             String buffer_getunamefromdb = dbclass.getunamefromdb (class methods getters)
             String buffer_getpnamefromdb = dbclass.getpnamefromdb (class methods getters)
             if(login_getUsername.equals(buffer_getnamefromdb) && login_pfield_retrieve.equals(buffer_getpnamefromdb)  ){
             //Authenticate here and redirect to next scene
             }else{
             //else show wrong password prompt
             //else redirect to unsuccessfull login screen after 3 attempts
             }
             */

        } catch (Exception e) {

            //Exception can be changed accordingly
            e.printStackTrace();
        }

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

    @FXML
    private void signUp_handleButtonAction(ActionEvent event) {

        //what happens after the button is clicked

        //do we use a string connected to a different class?
        String login_getUserName = uname_login.getText();

        /**
         How do we get the password and verify it? this is important and an aspect of security.
         Everything that must happen after the button is clicked, must be typed here, including scene changes and etc.
         */

        String login_pfield_retrieve = pfield_login.getText();


        //this is where we check where the uname & passwords match or not

        try {
            /**
             String buffer_getunamefromdb = dbclass.getunamefromdb (class methods getters)
             String buffer_getpnamefromdb = dbclass.getpnamefromdb (class methods getters)

             if(login_getUsername.equals(buffer_getnamefromdb) && login_pfield_retrieve.equals(buffer_getpnamefromdb)  ){

             //Authenticate here and redirect to next scene

             }else{

             //else show wrong password prompt

             //else redirect to unsuccessfull login screen after 3 attempts


             }
             */

        } catch (Exception e) {

            //Exception can be changed accordingly
            e.printStackTrace();
        }

        // setting stage to normal YETI application
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/sad/scenes/signup.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);

    }




}
