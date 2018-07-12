/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sad.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sad.database.DBUtils;
import com.sad.yeti.YETI;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import com.sad.yeti.LocalEvent;
import java.time.Month;

import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author JT
 */
public class YetiController implements Initializable {

    @FXML private TextField filterField;
    @FXML private Label lblPersonalList;
    @FXML private Label lblProfessionalList;
    @FXML private Label lblCopyright;
    @FXML private Button btnAddItem;
    @FXML private Button btnDeleteItem;
    @FXML private Label lblDate;
    @FXML private ImageView account_icon;
    @FXML private TableView<LocalEvent> personalTableView;
    @FXML private TableColumn<LocalEvent, Integer> personalPriorityColumn;
    @FXML private TableColumn<LocalEvent, LocalDate> personalDateColumn;
    @FXML private TableColumn<LocalEvent, String> personalItemColumn;
    @FXML private TableColumn<LocalEvent, String> personalTagColumn;
    @FXML private TableColumn<LocalEvent, String> personalNotifyColumn;

    @FXML private TableView<LocalEvent> professionalTableView;
    @FXML private TableColumn<LocalEvent, Integer> professionalPriorityColumn;
    @FXML private TableColumn<LocalEvent, LocalDate> professionalDateColumn;
    @FXML private TableColumn<LocalEvent, String> professionalItemColumn;
    @FXML private TableColumn<LocalEvent, String> professionalTagColumn;
    @FXML private TableColumn<LocalEvent, String> professionalNotifyColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //setup the columns in the table
        personalPriorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        personalDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        personalItemColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        personalTagColumn.setCellValueFactory(new PropertyValueFactory<>("tag"));
        personalNotifyColumn.setCellValueFactory(new PropertyValueFactory<>("notify"));

        ObservableList<LocalEvent> personalEvents = getPersonalEvents();
        //personalTableView.setItems(personalEvents);

        FilteredList<LocalEvent>  filteredPersonalEvents = new FilteredList<>(personalEvents, p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredPersonalEvents.setPredicate(LocalEvent -> {
                // If filter text is empty, display all Events
                if (newValue==null || newValue.isEmpty()) {
                    return true;
                }

                // Compare Tag of every LocalEvent with filter text
                String lowerCaseFilter = newValue.toLowerCase();

                if (LocalEvent.getTag().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<LocalEvent> sortedPersonalData = new SortedList<>(filteredPersonalEvents);
        sortedPersonalData.comparatorProperty().bind(personalTableView.comparatorProperty());
        personalTableView.setItems(sortedPersonalData);

        //setup the columns in the table
        professionalPriorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        professionalDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        professionalItemColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        professionalTagColumn.setCellValueFactory(new PropertyValueFactory<>("tag"));
        professionalNotifyColumn.setCellValueFactory(new PropertyValueFactory<>("notify"));

        ObservableList<LocalEvent> professionalEvents = getProfessionalEvents();
        //professionalTableView.setItems(professionalEvents);

        FilteredList<LocalEvent>  filteredProfessionalEvents = new FilteredList<>(professionalEvents, p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredProfessionalEvents.setPredicate(LocalEvent -> {
                // If filter text is empty, display all Events
                if (newValue==null || newValue.isEmpty()) {
                    return true;
                }

                // Compare Tag of every LocalEvent with filter text
                String lowerCaseFilter = newValue.toLowerCase();

                if (LocalEvent.getTag().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<LocalEvent> sortedProfessionalData = new SortedList<>(filteredProfessionalEvents);
        sortedProfessionalData.comparatorProperty().bind(professionalTableView.comparatorProperty());
        professionalTableView.setItems(sortedProfessionalData);

        //datePicker.setValue( LocalDate.now() );
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> { 
            SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm");
            lblDate.setText(df.format(new Date()));
        }),new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        
    }

    @FXML
    private void addItem(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource( "/com/sad/scenes/createTask.fxml" ));

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void deleteItem(ActionEvent event) {
        LocalEvent le = personalTableView.getSelectionModel().getSelectedItem();
        if (le == null) le = professionalTableView.getSelectionModel().getSelectedItem();
        if (le != null) {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle("Deleting Item");
            deleteAlert.setHeaderText("Are you sure you want to delete?");
            deleteAlert.initModality(Modality.APPLICATION_MODAL);
            deleteAlert.getDialogPane().setStyle("-fx-border-color: navy");
            deleteAlert.initStyle(StageStyle.UNDECORATED);
            Optional<ButtonType> option = deleteAlert.showAndWait();
            if(option.get() == ButtonType.OK) {
                System.out.println("Worked!");
                DBUtils.deleteTask(le);
                personalTableView.setItems(getPersonalEvents());
                professionalTableView.setItems(getProfessionalEvents());
            } else {
                System.out.println("Deletion Cancelled");
            }

        } else {
            System.out.println("Nothing Selected");
        }
    }

    /**
     * Author: Amanda updated 7/7/18
     * add scene for settings page with logout option
     *  settings.fxml
     */
    @FXML
    private void settings(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/sad/scenes/settings.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }



    private ObservableList<LocalEvent> getPersonalEvents() {
        ObservableList<LocalEvent> localEvents = FXCollections.observableArrayList();
        localEvents = DBUtils.getTasks(YETI.getUserID(), "Personal");

        return localEvents;
    }
    
    private ObservableList<LocalEvent> getProfessionalEvents() {
        ObservableList<LocalEvent> localEvents = FXCollections.observableArrayList();
        localEvents = DBUtils.getTasks(YETI.getUserID(), "Professional");

        return localEvents;
    }


    //controller for what happens when the ICON is clicked.





}
