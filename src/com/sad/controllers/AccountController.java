/*
 * Author: @Amanda
 * 7/7/18 updated to shut down database
 */
package com.sad.controllers;

import com.sad.database.DBUtils;
import com.sad.utils.Security;
import com.sad.yeti.YETI;
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
import java.sql.*;
import java.util.ResourceBundle;

public class AccountController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //method handling happens

    @FXML Button logout_button_account;
    @FXML Button noLogout;
    @FXML PasswordField curPass;
    @FXML PasswordField newPass1;
    @FXML PasswordField newPass2;
    @FXML Label errMsg;


    //method here

    @FXML
    private void logout(javafx.event.ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource( "/com/sad/scenes/account.fxml" ));

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void saveButtonHandleOnAction(ActionEvent event) {

        boolean status = false;

        errMsg.setText("");

        if (newPass1.getText().equals("") || newPass2.getText().equals("") || curPass.getText().equals("")) {
            errMsg.setText("All fields required to change password.");
        } else if (!newPass1.getText().equals(newPass2.getText())) {
            errMsg.setText("New passwords do not match!");
        }

        if (errMsg.getText().isEmpty()) {
            try {
                Connection conn = null;
                conn = DriverManager.getConnection("jdbc:derby:YETI;create=true");

                String sql = "select us_id from users where us_id = ? and us_password = ?";

                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, YETI.getUserID());
                stmt.setString(2, Security.md5Hash(curPass.getText()));

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    status = true;
                }
                if (status) {
                    System.out.println("Login validated, changing password.");
                    sql = "update users set us_password = ? where us_id = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, Security.md5Hash(newPass1.getText()));
                    stmt.setInt(2, YETI.getUserID());
                    stmt.execute();
                    System.out.println("Password changed!");
                } else {
                    errMsg.setText("Invalid password, please try again!");
                }
            } catch (SQLException ex) {
                status = false;
                errMsg.setText("Exception occured!");
                System.out.println("ERROR: " + ex.getMessage());
            }
            if (status) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
    }

    public void logoutButtonHandleOnAction(javafx.event.ActionEvent event) {

        // closing database and setting stage back to login Screen
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = null;
        System.out.println("Shutting down derby DB");
        DBUtils.closeDB();

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
            root = FXMLLoader.load(getClass().getResource( "/com/sad/scenes/yeti.fxml" ));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);

    }
}




