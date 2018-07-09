package com.sad.controllers;

import com.sad.database.DBUtils;
import com.sad.yeti.LocalEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.apache.derby.impl.sql.execute.CurrentDatetime;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateTaskController  implements Initializable {

    @FXML private DatePicker date;
    @FXML private TextField tag;
    @FXML private RadioButton sendEmail;
    @FXML private Button btnAddEvent;
    @FXML private Button btnCancelEvent;
    @FXML private TextArea descr;
    @FXML private ComboBox priority;
    @FXML private ComboBox tasktype;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.setValue(LocalDate.now());
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
        LocalEvent le = new LocalEvent();
        le.setDate(date.getValue());
        le.setDescription(descr.getText());
        le.setPersonal(tasktype.getValue().toString().equalsIgnoreCase("Personal"));
        switch (priority.getValue().toString()) {
            case "High":
                le.setPriority(1);
                break;
            case "Medium":
                le.setPriority(2);
                break;
            default:
                le.setPriority(3);
                break;
        }
        le.setTag(tag.getText());
        le.setNotify(sendEmail.isSelected()?"Y":"N");

        //add event to database
        DBUtils.addTask(le);

        if (sendEmail.isSelected()) {
            EmailController.sendEmailNotification(le);

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

}
