package com.example.scrumdocker2048.Controller;

import com.example.scrumdocker2048.Model.Username;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class HighscoreController extends AbstractController {
    @FXML
    private Button btnName;

    @FXML
    private Button btnScore;

    @FXML
    private Button btnGamesPlayed;

    @FXML
    private ListView<Username> listviewHighscore;

    public void initialize() {
        listviewHighscore.setItems(Username.getList());
    }

    @FXML
    void btnGamesPlayedPressed(ActionEvent event) {

    }

    @FXML
    void btnNamePressed(ActionEvent event) {

    }

    @FXML
    void btnScorePressed(ActionEvent event) {

    }

    @FXML
    void btnClosePressed(ActionEvent event) {
        Stage stage = (Stage) btnScore.getScene().getWindow();
        stage.close();
        try {
            StartmenueController c = this.loadFxmlFile("startmenue.fxml", "Spiel",
                    ((Button) event.getSource()).getScene().getWindow(), StartmenueController.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
