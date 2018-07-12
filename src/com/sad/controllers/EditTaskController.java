package com.sad.controllers;

import com.sad.database.DBUtils;
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
import java.util.ResourceBundle;

public class EditTaskController implements Initializable {
    @FXML private DatePicker date;
    @FXML private TextField tag;
    @FXML private RadioButton sendEmail;
    @FXML private TextArea descr;
    @FXML private ComboBox priority;
    @FXML private ComboBox tasktype;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.setValue(YetiController.publicLocalEvent.getDate());
        descr.setText(YetiController.publicLocalEvent.getDescription());
        if(YetiController.publicLocalEvent.getPersonal()==true)  tasktype.setValue("Personal");
        else tasktype.setValue("Professional");
        tag.setText(YetiController.publicLocalEvent.getTag());

        switch (YetiController.publicLocalEvent.getPriority()) {
            case 3:
                priority.setValue("Low");
                break;
            case 2:
                priority.setValue("Medium");
                break;
            default:
                priority.setValue("High");
                break;
        }
    }

    @FXML
    private void editEvent(ActionEvent event ) {
        //create local event
        YetiController.publicLocalEvent.setDate(date.getValue());
        YetiController.publicLocalEvent.setDescription(descr.getText());
        YetiController.publicLocalEvent.setPersonal(tasktype.getValue().toString().equalsIgnoreCase("Personal"));
        switch (priority.getValue().toString()) {
            case "High":
                YetiController.publicLocalEvent.setPriority(1);
                break;
            case "Medium":
                YetiController.publicLocalEvent.setPriority(2);
                break;
            default:
                YetiController.publicLocalEvent.setPriority(3);
                break;
        }
        YetiController.publicLocalEvent.setTag(tag.getText());
        YetiController.publicLocalEvent.setNotify(sendEmail.isSelected()?"Y":"N");

        //add event to database
        DBUtils.updateTask(YetiController.publicLocalEvent);

        if (sendEmail.isSelected()) {
            EmailController.sendEmailNotification(YetiController.publicLocalEvent);
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
}
