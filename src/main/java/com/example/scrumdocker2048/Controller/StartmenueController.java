package com.example.scrumdocker2048.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class StartmenueController extends AbstractController {

    @FXML
    private Label labelName;

    @FXML
    private MenuItem MenueBarSettings;

    @FXML
    private MenuItem MenueBarHelp;

    @FXML
    private MenuItem MenueBarHighscore;

    @FXML
    private Button startgamebtn;

    @FXML
    void MenueBarHelpClicked(ActionEvent event) {
        try {
            HelpController c = this.loadFxmlFile("help.fxml", "Help",
                    labelName.getScene().getWindow(), HelpController.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void MenueBarHighscoreClicked(ActionEvent event) {
        try {
            HighscoreController c = this.loadFxmlFile("highscore.fxml", "highscore",
                    labelName.getScene().getWindow(), HighscoreController.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void MenueBarSettingsClicked(ActionEvent event) {
        try {
            SettingsController c = this.loadFxmlFile("settings.fxml", "Settings",
                    labelName.getScene().getWindow(), SettingsController.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ButtonStartGameClicked(ActionEvent actionEvent) throws IOException {
        try {
             UsernameController u = this.loadFxmlFile("username.fxml", "Spiel",
                    ((Button) actionEvent.getSource()).getScene().getWindow(), UsernameController.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
