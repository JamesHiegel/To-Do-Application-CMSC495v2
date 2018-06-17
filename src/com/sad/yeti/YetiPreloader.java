package com.sad.yeti;

import com.sad.controllers.SplashController;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class YetiPreloader extends Preloader {

    private Stage preloaderStage;
    private Scene scene;

    public YetiPreloader() {

    }

    @Override
    public void init() throws Exception {

        Parent splashRoot = FXMLLoader.load(getClass().getResource("/com/sad/scenes/splash.fxml"));
        scene = new Scene(splashRoot);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.preloaderStage = primaryStage;

        preloaderStage.setScene(scene);
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.show();

    }

    @Override
    public void handleApplicationNotification(Preloader.PreloaderNotification info) {
        if (info instanceof ProgressNotification) {
            //SplashController.label.setText("Loading " + ((ProgressNotification) info).getProgress()*100 + "%");
            //System.out.println("Value# :" + ((ProgressNotification) info).getProgress());
            SplashController.progressBar.setProgress(((ProgressNotification) info).getProgress());
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {

        StateChangeNotification.Type type = info.getType();

        switch (type) {
            case BEFORE_START:
                // Called after init and before start is called
                preloaderStage.hide();
                break;
            default:
                //System.out.println(type);
                break;
        }
    }

}
