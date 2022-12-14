package com.example.scrumdocker2048.Controller;

import com.example.scrumdocker2048.Model.Username;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    private ChoiceBox<Username> dropdownUsername;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClose;

    ObservableList<Username> list = FXCollections.observableArrayList();

    public void initialize() {
        txtUsername.setPromptText("username");
        txtPassword.setPromptText("password");
        dropdownUsername.setItems(list);
    }

    @FXML
    void btnBackPressed(ActionEvent event) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
        try {
            StartmenueController c = this.loadFxmlFile("startmenue.fxml", "Spiel",
                    ((Button) event.getSource()).getScene().getWindow(), StartmenueController.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnClosePressed(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Stage stage = (Stage) btnPlay.getScene().getWindow();
        stage.close();
        try {
            HelloController c = this.loadFxmlFile("hello-view.fxml", "Spiel",
                    ((Button) event.getSource()).getScene().getWindow(), HelloController.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void dropdownUsernameExited(DragEvent event) {

    }
}
