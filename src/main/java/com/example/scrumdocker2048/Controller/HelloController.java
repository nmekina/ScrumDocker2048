package com.example.scrumdocker2048.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController extends AbstractController{
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}