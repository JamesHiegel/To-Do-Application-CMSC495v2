package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Event {

    public static void display(String title, String message, LocalDate date) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label("Please fill details in for " + title);
        Label description = new Label("Description: ");
        TextField descriptionInput = new TextField();
        Label eventDate = new Label ("Date: ");
        DatePicker picker = new DatePicker();
        picker.setValue(date);


        Button save = new Button ("Save");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //save event information

                window.close();
            }
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, description, descriptionInput, eventDate, picker,save);

        layout.setAlignment(Pos.CENTER);

        Scene scene =  new Scene (layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
