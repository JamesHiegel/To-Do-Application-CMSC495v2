package com.sad.controllers;

import com.sad.yeti.LocalEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

public class CreateTaskController  implements Initializable {

    @FXML private DatePicker date;
    @FXML private TextField tag;
    @FXML private RadioButton sendEmail;
    @FXML private Button btnAddEvent;
    @FXML private Button btnCancelEvent;
    @FXML private ComboBox priority;
    @FXML private ComboBox personal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        personal.getItems().addAll("personal","professional");
        priority.getItems().addAll("important", "not important");
    }

    @FXML
    private void cancelEvent(ActionEvent event ) {

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
    private void addEvent(ActionEvent event ) {
        //create local event
       // LocalEvent newEvent = new LocalEvent(1, LocalDate.of(2018, Month.JUNE, 1),description.getText(),true);

        //add event to database


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
