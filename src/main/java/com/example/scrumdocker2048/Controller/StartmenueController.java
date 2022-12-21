package com.example.scrumdocker2048.Controller;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class StartmenueController extends AbstractController {

    public Button startgamebtn;
    public MenuItem settingsbtn;
    public Label labelName;

    public void settingsbtnac(ActionEvent actionEvent) {
        try {
            SettingsController c = this.loadFxmlFile("settings.fxml", "Settings",
                    labelName.getScene().getWindow(), SettingsController.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void helpbtnac(ActionEvent actionEvent) {

    }

    public void highscorebtnac(ActionEvent actionEvent) {
    }

    public void startgamebtnac(ActionEvent actionEvent) throws IOException {
        try {
             UsernameController u = this.loadFxmlFile("username.fxml", "Spiel",
                    ((Button) actionEvent.getSource()).getScene().getWindow(), UsernameController.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
