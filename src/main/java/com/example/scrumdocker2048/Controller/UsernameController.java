package com.example.scrumdocker2048.Controller;

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
import javafx.stage.Stage;

import java.io.IOException;

public class UsernameController extends AbstractController {

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private ChoiceBox<String> dropdownUsername;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClose;

    private ObservableList<String> list = FXCollections.observableArrayList();

    public void initialize() {
        this.txtUsername.setPromptText("username");
        this.txtPassword.setPromptText("password");
        for (Username username: Username.getList()) {
            list.add(username.getUsername());
        }
        if (list.size() > 0) {
            this.dropdownUsername.setItems(list);
            dropdownUsername.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue ov, Number value, Number new_value) {
                    txtUsername.setText(dropdownUsername.getItems().get(new_value.intValue()) + "");
                }
            });
        }
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
        boolean check = true;

        for (Username user : Username.getList()) {
            if (txtUsername.getText().equals(user.getUsername())) {
                if (txtPassword.getText().equals(user.getPassword())) {
                    Stage stage = (Stage) btnPlay.getScene().getWindow();
                    stage.close();
                    try {
                        PlayfieldController c = this.loadFxmlFile("playfield.fxml", "Spiel",
                                ((Button) event.getSource()).getScene().getWindow(), PlayfieldController.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                check = false;
            }
        }
        if (check) {
            // Insert new User in Database
            Username username = new Username(txtUsername.getText(), txtPassword.getText());
            username.insertUser();

            Stage stage = (Stage) btnPlay.getScene().getWindow();
            stage.close();
            try {
                PlayfieldController c = this.loadFxmlFile("playfield.fxml", "Spiel",
                        ((Button) event.getSource()).getScene().getWindow(), PlayfieldController.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}