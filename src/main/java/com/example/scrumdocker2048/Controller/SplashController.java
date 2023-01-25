package com.example.scrumdocker2048.Controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SplashController {
    public ImageView view;
    public javafx.scene.layout.AnchorPane AnchorPane;
    public javafx.scene.layout.AnchorPane ap;

    private boolean splashonscreen = true;

    /**
     * Image des Splash wird gesetzt
     */
    public void initialize() {
        Image image = new Image("https://apprecs.org/gp/images/app-icons/300/74/game2048.a2048game.twozerofoureight.jpg");
        view.setImage(image);
        splash();
    }

    /**
     * Splashscreen wird aufgerufen und bleibt fuer 2 Sekunden
     */
    private void splash() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1999);
                } catch (Exception e) {
                    System.out.println(e);
                }
                Platform.runLater(new Runnable() {


                    @Override
                    public void run() {
                        try {
                            AnchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("startmenue.fxml")));
                            Stage stage = new Stage();
                            Scene scene = new Scene(AnchorPane);
                            scene.setFill(Color.TRANSPARENT);
                            stage.initStyle(StageStyle.TRANSPARENT);
                            stage.setTitle("Splashscreen");
                            stage.setScene(scene);
                            stage.setMinHeight(400);
                            stage.setMinWidth(255);
                            stage.show();
                            ap.getScene().getWindow().hide();
                        } catch (IOException ex) {
                            Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        }.start();

    }
}
