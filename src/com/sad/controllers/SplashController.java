package com.sad.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashController  implements Initializable {

    @FXML private Label lblProgress;
    @FXML private ProgressBar pbProgress;

    public static Label label;
    public static ProgressBar progressBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label = lblProgress;
        progressBar = pbProgress;
    }

}
