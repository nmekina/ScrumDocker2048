package com.example.scrumdocker2048;

import com.example.scrumdocker2048.Controller.AbstractController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AbstractController.class.getResource("splashview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("2048!");
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}