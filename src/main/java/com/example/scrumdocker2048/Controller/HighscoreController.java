/**
 * The HighscoreController class implements the functionality for the Highscore screen.
 */
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

    /**
     * A reference to the score button.
     */
    @FXML
    private Button btnScore;

    /**
     * A reference to the list view for the highscore.
     */
    @FXML
    private ListView<Username> listviewHighscore;

    /**
     * The list of usernames that make up the highscore.
     */
    private ObservableList<Username> list;

    /**
     * Initializes the list of usernames.
     */
    public void initialize() {
        list = Username.getList();
        listviewHighscore.setItems(list);
    }

    /**
     * Handles the event when the games played button is pressed.
     * Sorts the list of usernames based on the number of games played.
     *
     * @param event the action event that triggered this method
     */
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
        listviewHighscore.getItems().setAll(list);
    }


    /**
     * Handles the event when the name button is pressed.
     * Sorts the list of usernames based on their names.
     *
     * @param event the action event that triggered this method
     */
    @FXML
    void btnNamePressed(ActionEvent event) {
        // sort the list by username
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
        listviewHighscore.getItems().setAll(list);
    }

    /**
     * This method is called when the "Score" button is pressed. It sorts the list of Usernames based on their high score.
     * The list is sorted in descending order, so that the user with the highest score is displayed first.
     * The sorted list is then displayed in the list view.
     *
     * @param event the event that triggered the button press
     */
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
        listviewHighscore.getItems().setAll(list);
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
