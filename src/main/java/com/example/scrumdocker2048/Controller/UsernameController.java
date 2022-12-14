package com.example.scrumdocker2048.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class UsernameController extends AbstractController {

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private ChoiceBox<?> dropdownUsername;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClose;

    public void initialize() {
        txtUsername.setPromptText("username");
        txtPassword.setPromptText("password");
    }

    @FXML
    void btnBackPressed(ActionEvent event) {
        Stage stageclose = (Stage) btnBack.getScene().getWindow();
        stageclose.close();
        try {
            HelloController c = this.loadFxmlFile("hello-view.fxml", "Spiel",
                    ((Button) event.getSource()).getScene().getWindow(), HelloController.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnClosePressed(ActionEvent event) {

    }

    @FXML
    void btnPlayPressed(ActionEvent event) {

    }

    @FXML
    void dropdownUsernameExited(DragEvent event) {

    }
}
