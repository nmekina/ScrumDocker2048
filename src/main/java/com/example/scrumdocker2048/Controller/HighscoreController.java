package com.example.scrumdocker2048.Controller;

import com.example.scrumdocker2048.Model.Username;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class HighscoreController extends AbstractController {


    @FXML
    private Button btnScore;

    @FXML
    private ListView<Username> listviewHighscore;

    private ObservableList<Username> list;

    public void initialize() {
        list = Username.getList();
        listviewHighscore.setItems(list);
    }

    @FXML
    void btnGamesPlayedPressed(ActionEvent event) {
        // list sortieren
        ObservableList<Username> gamesPlayed = FXCollections.observableArrayList();
        int highest;
        int index;

        while (list.size() > 0) {
            highest = 0;
            index = 0;
            for (int i = 0; i < list.size(); i++) {
                if (highest < list.get(i).getGamesPlayed()) {
                    highest = list.get(i).getGamesPlayed();
                    index = i;
                }
            }
            gamesPlayed.add(list.get(index));
            list.remove(index);
        }
        list = gamesPlayed;
        listviewHighscore.setItems(list);
    }

    @FXML
    void btnNamePressed(ActionEvent event) {
        // list sortieren
        ObservableList<Username> name = FXCollections.observableArrayList();
        String highest;
        int index;

        while (list.size() > 0) {
            highest = list.get(0).getUsername();
            index = 0;
            for (int i = 0; i < list.size(); i++) {
                if (highest.compareTo(list.get(i).getUsername()) > 0) {
                    highest = list.get(i).getUsername();
                    index = i;
                }
            }
            name.add(list.get(index));
            list.remove(index);
        }
        list = name;
        listviewHighscore.setItems(list);
    }

    @FXML
    void btnScorePressed(ActionEvent event) {
        // list sortieren
        ObservableList<Username> score = FXCollections.observableArrayList();
        int highest;
        int index;

        while (list.size() > 0) {
            highest = 0;
            index = 0;
            for (int i = 0; i < list.size(); i++) {
                if (highest < list.get(i).getHighscore()) {
                    highest = list.get(i).getHighscore();
                    index = i;
                }
            }
            score.add(list.get(index));
            list.remove(index);
        }
        list = score;
        listviewHighscore.setItems(list);
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
