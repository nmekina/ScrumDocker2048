package com.example.scrumdocker2048.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public abstract class AbstractController {
    private Stage stage = null;

    public <T extends AbstractController> T loadFxmlFile(String path, String title, Window owner, Class<T> classOfController) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AbstractController.class.getResource(path));
        Parent scene = fxmlLoader.load();

        T controller = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(scene));
        stage.setTitle(title);

        controller.setStage(stage);

        if (owner != null) {
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(owner);
        }
        Stage closestage = (Stage) owner;
        closestage.close();
        stage.show();

        return controller;
    }

    public void setStage(Stage s) {
        this.stage = s;
    }

}