package com.example.scrumdocker2048.Controller;

import com.example.scrumdocker2048.Model.Database;
import com.example.scrumdocker2048.Model.Username;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public void initialize() {
        this.txtUsername.setPromptText("username");
        this.txtPassword.setPromptText("password");
        this.dropdownUsername.setItems(Username.getList());
        dropdownUsername.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            // if items of the list are changed
            public void changed(ObservableValue ov, Number value, Number new_value) {
                // text for the label to the selected item
                txtUsername.setText(dropdownUsername.getItems().get(new_value.intValue()) + "");
            }
        });
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
        if ((!txtUsername.getText().equals("")) && (!txtPassword.getText().equals(""))) {
            Stage stage = (Stage) btnPlay.getScene().getWindow();
            stage.close();
            try {
                HelloController c = this.loadFxmlFile("hello-view.fxml", "Spiel",
                        ((Button) event.getSource()).getScene().getWindow(), HelloController.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}