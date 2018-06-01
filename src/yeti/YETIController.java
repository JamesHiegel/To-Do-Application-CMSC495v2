/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeti;

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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author JT
 */
public class YETIController implements Initializable {
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //datePicker.setValue( LocalDate.now() );
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> { 
            SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm");
            lblDate.setText(df.format(new Date()));
    }),
         new KeyFrame(Duration.seconds(1))
    );
    clock.setCycleCount(Animation.INDEFINITE);
    clock.play();

    }

    @FXML
    Button btnAddItem;
    @FXML
    Button btnDeleteItem;
    @FXML
    TextArea descriptionTextArea;
    @FXML
    DatePicker datePicker;
    @FXML
    ListView<LocalEvent> lvPersonalList;
    @FXML
    ListView<LocalEvent> lvProfessionalList;
    @FXML
    Label lblDate;
    @FXML
    ImageView settings;

    ObservableList<LocalEvent> olLocalEvent = FXCollections.observableArrayList();

    @FXML
    private void addEvent(Event event) {

        //olLocalEvent.add( new LocalEvent( datePicker.getValue(), descriptionTextArea.getText() ) );
        olLocalEvent.add( new LocalEvent( datePicker.getValue(), "Dummy" ) );
        lvPersonalList.setItems( olLocalEvent );
        refresh();

    }

    private void refresh() {
        datePicker.setValue( LocalDate.now() );

    }
    
}
