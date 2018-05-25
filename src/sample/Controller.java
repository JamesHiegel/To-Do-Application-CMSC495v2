package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import java.net.URL;
import javafx.fxml.Initializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        datePicker.setValue( LocalDate.now() );

    }

    @FXML
    Button addbutton;
    @FXML
    TextArea descriptionTextArea;
    @FXML
    DatePicker datePicker;
    @FXML
    ListView<LocalEvent> eventList;

    ObservableList<LocalEvent> list = FXCollections.observableArrayList();

    @FXML
    private void addEvent(Event event) {

        list.add( new LocalEvent( datePicker.getValue(), descriptionTextArea.getText() ) );
        eventList.setItems( list );
        refresh();

    }

    private void refresh() {
        datePicker.setValue( LocalDate.now() );
        descriptionTextArea.setText( null );

    }

}
