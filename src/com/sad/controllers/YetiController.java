/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sad.controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import com.sad.yeti.LocalEvent;
import java.time.Month;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author JT
 */
public class YetiController implements Initializable {

    @FXML private Label lblPersonalList;
    @FXML private Label lblProfessionalList;
    @FXML private Label lblCopyright;
    @FXML private Button btnAddItem;
    @FXML private Button btnDeleteItem;
    @FXML private Label lblDate;
    @FXML private ImageView settings;
    @FXML private TableView<LocalEvent> personalTableView;
    @FXML private TableColumn<LocalEvent, Integer> personalPriorityColumn;
    @FXML private TableColumn<LocalEvent, LocalDate> personalDateColumn;
    @FXML private TableColumn<LocalEvent, String> personalItemColumn;
    @FXML private TableView<LocalEvent> professionalTableView;
    @FXML private TableColumn<LocalEvent, Integer> professionalPriorityColumn;
    @FXML private TableColumn<LocalEvent, LocalDate> professionalDateColumn;
    @FXML private TableColumn<LocalEvent, String> professionalItemColumn;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //setup the columns in the table
        personalPriorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        personalDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        personalItemColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        //load dummy data
        personalTableView.setItems(getPersonalEvents());

        //setup the columns in the table
        professionalPriorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        professionalDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        professionalItemColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        //load dummy data
        professionalTableView.setItems(getProfessionalEvents());

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
    private void addItem(ActionEvent event ) {


    }

    @FXML
    private void deleteItem(ActionEvent event) {
    }

    private ObservableList<LocalEvent> getPersonalEvents() {
        ObservableList<LocalEvent> localEvents = FXCollections.observableArrayList();
        localEvents.add(new LocalEvent(1, LocalDate.of(2018, Month.JUNE, 1),"ToDo 1"));
        localEvents.add(new LocalEvent(3, LocalDate.of(2018, Month.JUNE, 1),"ToDo 2"));
        localEvents.add(new LocalEvent(2, LocalDate.of(2018, Month.JUNE, 1),"ToDo 3"));
        localEvents.add(new LocalEvent(3, LocalDate.of(2018, Month.JUNE, 1),"ToDo 4"));
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        return localEvents;
    }
    
    private ObservableList<LocalEvent> getProfessionalEvents() {
        ObservableList<LocalEvent> localEvents = FXCollections.observableArrayList();
        localEvents.add(new LocalEvent(1, LocalDate.of(2018, Month.JUNE, 2),"Professional ToDo 1"));
        localEvents.add(new LocalEvent(3, LocalDate.of(2018, Month.JUNE, 3),"Professional ToDo 2"));
        localEvents.add(new LocalEvent(2, LocalDate.of(2018, Month.JUNE, 4),"Professional ToDo 3"));
        localEvents.add(new LocalEvent(1, LocalDate.of(2018, Month.JUNE, 5),"Professional ToDo 4"));
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        return localEvents;
    }

}
