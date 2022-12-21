package com.example.scrumdocker2048.Model;

import com.example.scrumdocker2048.Controller.StartmenueController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Klasse fürs wechseln der Scene in das mitegegebene fxml-Fenster
 */
public class ChangeScene {

    /**
     * wechelt die Scene
     *
     * @param game   => Name des Fensters inn welches gewechselt werden soll
     * @param node => Button, um die alte Stage schließen zu können
     * @throws IOException
     */
    public static void ChangeSceneNow(String game, Node node) throws IOException {
        Stage stage = new Stage();

        Stage stageclose = (Stage) node.getScene().getWindow();
        stageclose.close();

        final FXMLLoader fxmlLoader = new FXMLLoader();
        URL u = StartmenueController.class.getResource(game + ".fxml");

        fxmlLoader.setLocation(u);
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle(game);
        stage.setScene(scene);
        stage.show();
    }
}
