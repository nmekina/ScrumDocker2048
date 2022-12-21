package com.example.scrumdocker2048.Controller;

import com.example.scrumdocker2048.Model.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SettingsController extends AbstractController {

    public Label labelColorPickerPlayfield;
    public Label labelColorPickerTiles;
    @FXML
    private Button btnSave;

    @FXML
    private Button btnHighscore;

    @FXML
    private Label label_headline;

    Settings settings = new Settings();

    public SettingsController() throws FileNotFoundException {
    }

    /**
     * Der Initialize befüllt alle labels, buttons etc.
     * mit den gewünschten Inhalten.
     */
    public void initialize() {

        label_headline.setText("Settings");
        btnHighscore.setText("Highscore");
        btnSave.setText("Save");
        labelColorPickerPlayfield.setText("Color for Playfield");
        labelColorPickerTiles.setText("Color for Tiles");

    }

    /**
     * @param event
     * @throws IOException schaltet auf Highscore.fxml um
     */
    @FXML
    void btnHighscoreClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnHighscore.getScene().getWindow();
        stage.close();
        try {
            StartmenueController c = this.loadFxmlFile("startmenue.fxml", "Spiel",
                    ((Button) event.getSource()).getScene().getWindow(), StartmenueController.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param event
     * @throws IOException schaltet auf Startmenue.fxml um
     */
    @FXML
    void btnSaveClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
        try {
            StartmenueController c = this.loadFxmlFile("startmenue.fxml", "Spiel",
                    ((Button) event.getSource()).getScene().getWindow(), StartmenueController.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * speichert difficulty, Musik und volume
     */
    private void save() {
    }

    /**
     * speichert difficulty
     */


}
