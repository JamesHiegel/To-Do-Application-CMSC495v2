/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sad.yeti;

import com.sad.database.DBUtils;
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
        //Parent root = FXMLLoader.load(getClass().getResource("/com/sad/scenes/yeti.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/com/sad/scenes/login.fxml"));


        Scene mainScene = new Scene(root);

        stage.setTitle("YETI");
        stage.setScene(mainScene);
        stage.setResizable(false);
        //stage.setMinWidth(790);
        //stage.setMinHeight(360);
        stage.show();

    }

    @Override
    public void init() throws Exception {

        // Perform some heavy lifting (i.e. database start, check for application updates, etc. )
        if (DBUtils.dbExists()) {
            DBUtils db = new DBUtils();
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(.5));
            System.out.println("DB Exists - Display Tables");
            db.displayTables();
        } else {
            DBUtils db = new DBUtils();
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(.05));
            db.createDB();
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(.20));
            db.createSequences();
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(.35));
            db.createTables();
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(.50));
            db.seedTables();
            System.out.println("DB Does Not Exists - Display Tables");
            db.displayTables();
        }

        for (int i = 6; i < COUNT_LIMIT; i++) {
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
