/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sad.yeti;

import com.sad.controllers.YetiController;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author JT
 */
public class YETI extends Application {

    private static final int COUNT_LIMIT = 10;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/sad/scenes/yeti.fxml"));

        Scene mainScene = new Scene(root);

        stage.setTitle("YETI");
        stage.setScene(mainScene);
        stage.setMinWidth(790);
        stage.setMinHeight(360);
        stage.show();
    }

    @Override
    public void init() throws Exception {

        // Perform some heavy lifting (i.e. database start, check for application updates, etc. )
        for (int i = 0; i < COUNT_LIMIT; i++) {
            double progress = (double) i / COUNT_LIMIT;
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
            Thread.sleep(500);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //launch(args);
        LauncherImpl.launchApplication(YETI.class, YetiPreloader.class, args);

    }
    
}
