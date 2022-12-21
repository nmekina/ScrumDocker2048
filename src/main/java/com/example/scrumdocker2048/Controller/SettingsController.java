package com.example.scrumdocker2048.Controller;

import com.example.scrumdocker2048.Model.ChangeScene;
import com.example.scrumdocker2048.Model.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SettingsController {

    @FXML
    private Button btn_save;

    @FXML
    private Button btn_highscore;

    @FXML
    private Label label_headline;


    final ToggleGroup rb_difficulty = new ToggleGroup();
    final ToggleGroup rb_music = new ToggleGroup();
    Settings settings = new Settings();

    public SettingsController() throws FileNotFoundException {
    }

    /**
     * Der Initialize befüllt alle labels, buttons etc.
     * mit den gewünschten Inhalten.
     */
    public void initialize() {

        label_headline.setText("Settings");
        btn_highscore.setText("Highscore");
        btn_save.setText("Save");

    }

    /**
     * @param event
     * @throws IOException schaltet auf Highscore.fxml um
     */
    @FXML
    void btn_highscore_click(ActionEvent event) throws IOException {
        save();
        ChangeScene.ChangeSceneNow("Highscore", btn_highscore);
    }


    /**
     * @param event
     * @throws IOException schaltet auf Startmenue.fxml um
     */
    @FXML
    void btn_save_click(ActionEvent event) throws IOException {
        save();
        ChangeScene.ChangeSceneNow("Startmenue", btn_save);
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
