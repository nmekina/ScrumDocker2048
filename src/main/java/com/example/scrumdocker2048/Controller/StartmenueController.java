package com.example.scrumdocker2048.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class StartmenueController extends AbstractController {

    @FXML
    private Label labelName;

    /**
     * This method is called when the "Highscore" option in the menu bar is clicked.
     * It loads the highscore screen by calling the loadFxmlFile method.
     *
     * @param event The ActionEvent that triggers this method.
     */
    @FXML
    void MenueBarHighscoreClicked(ActionEvent event) {
        try {
            HighscoreController c = this.loadFxmlFile("highscore.fxml", "highscore",
                    labelName.getScene().getWindow(), HighscoreController.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when the "Start Game" button is clicked.
     * It loads the username screen by calling the loadFxmlFile method.
     *
     * @param actionEvent The ActionEvent that triggers this method.
     */
    public void ButtonStartGameClicked(ActionEvent actionEvent) throws IOException {
        try {
            UsernameController u = this.loadFxmlFile("username.fxml", "Spiel",
                    ((Button) actionEvent.getSource()).getScene().getWindow(), UsernameController.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
