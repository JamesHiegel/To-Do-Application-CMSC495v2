/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main extends Application {

    Stage window;
    Scene login, toDoList;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        //login scene
        Label label = new Label("Login Here");
        TextField username = new TextField();
        TextField password = new TextField();
        Button button = new Button();
        button.setText("Log In");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.setScene(toDoList);
            }
        });

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label, username, password, button);
        login = new Scene(layout1, 200, 200);


        Button button2 = new Button("Add to the to Do List");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Event.display("Event", "Description", LocalDate.now());
            }
        });

        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        toDoList = new Scene(layout2, 600, 400);

        window.setTitle("Login");
        window.setScene(login);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public boolean authenticate(String user, String pword) {
        boolean isValid = false;
        if (user.equalsIgnoreCase("sdevadmin")
                && pword.equals("425!pass")) {
            isValid = true;
        }

        return isValid;
    }
}
