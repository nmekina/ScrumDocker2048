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

    /**
     * Loads an FXML file, instantiates a controller object, and shows a new stage.
     * The stage can be set as modal with respect to a specified owner window.
     *
     * @param path              The path to the FXML file
     * @param title             The title of the new stage
     * @param owner             The owner window of the new stage
     * @param classOfController The class of the controller object
     * @param <T>               The type of the controller object
     * @return The instantiated controller object
     * @throws IOException if an I/O error occurs while loading the FXML file
     */
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

    /**
     * Sets the stage of the controller object.
     *
     * @param s The stage to be set
     */
    public void setStage(Stage s) {
        this.stage = s;
    }

}