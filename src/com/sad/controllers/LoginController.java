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
import java.net.URLConnection;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController  implements Initializable {


    private static int netavail;
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
    Label acc_label_error;
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
        int user_id = 0;
        int hitcnt = 0;
        boolean status = false;

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
            Connection conn = null;
            conn = DriverManager.getConnection( "jdbc:derby:YETI;create=true" );

            String sql = "select us_id from users  where us_userName = ?";

            PreparedStatement stmt = conn.prepareStatement( sql );
            stmt.setString( 1, login_getUserName );

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user_id = rs.getInt( 1 );
            }

            if (user_id > 0) {
                String sql2 = "select us_id from users where us_id = ? and us_password = ?";

                stmt = conn.prepareStatement( sql2 );
                stmt.setInt( 1, user_id );
                stmt.setString( 2, Security.md5Hash( login_pfield_retrieve ) );
                stmt.executeQuery();

                ResultSet rs2 = stmt.executeQuery();
                while (rs2.next()) {
                    hitcnt++;
                }
                // Set to true if userid/password match
                if (hitcnt > 0) {
                    status = true;
                    System.out.println( "Login succeeded!" );
                } else {
                    acc_label_error.setText( "Login failed, please try again!" );
                    System.out.println( "Login failed!" );
                }
            } else {
                acc_label_error.setText( "Login failed, please try again!" );
            }
        } catch (SQLException ex) {
            System.out.println( "ERROR: " + ex.getMessage() );
        }
        if (status) {
                //check for internet
            netIsAvailable();
               if (netavail == 1) {
                   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    Parent root = null;
                    try {
                        root = FXMLLoader.load( getClass().getResource( "/com/sad/scenes/MFA.fxml" ) );
                        EmailController.generateAndSendEmail( login_getUserName );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Scene scene = new Scene( root );
                    stage.setScene( scene );

                } else {
                    if (netavail == 0) {
                        // setting stage back to normal YETI application
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Parent root = null;
                        try {
                            root = FXMLLoader.load( getClass().getResource( "/com/sad/scenes/yeti.fxml" ) );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Scene scene = new Scene( root );
                        stage.setScene( scene );
                    }
                }

        }
    }




    @FXML
    private void signUp_handleButtonAction(ActionEvent event) {

        //what happens after the button is clicked

        //do we use a string connected to a different class?
        String login_getUserName = uname_login.getText();
        String login_pfield_retrieve = pfield_login.getText();


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


    private void netIsAvailable() {

        try {
            URL url = new URL("https://www.geeksforgeeks.org/");
            URLConnection connection = url.openConnection();
            connection.connect();

            System.out.println("Internet Connection Successful");
            this.netavail = 1;
        }
        catch (Exception e) {
            System.out.println("Internet Not Connected");
            this.netavail = 0;
        }


    }




}
