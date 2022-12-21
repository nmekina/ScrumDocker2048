package com.example.scrumdocker2048.Controller;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class StartmenueController extends AbstractController {

    public Button startgamebtn;

    public void settingsbtnac(ActionEvent actionEvent) {
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
