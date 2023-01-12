package com.example.scrumdocker2048.Controller;

import com.example.scrumdocker2048.Model.Position;
import com.example.scrumdocker2048.Model.TilePane;
import com.example.scrumdocker2048.Model.checkConditions;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.util.ConcurrentModificationException;

public class PlayfieldController extends AbstractController {

    public ColumnConstraints Grridpane;
    KeyActionEventHandler keyActionEventHandler = new KeyActionEventHandler();
    StartEventHandler startEventhandler = new StartEventHandler();
    @FXML
    public Label labelStartPlay;

    @FXML
    public GridPane gridPlayfield;
    @FXML
    public Button btnBack;
    @FXML
    public Button btnNewGame;
    @FXML
    public Label labelName;
    @FXML
    public Label labelHighscore;
    @FXML
    public Label labelHighestScore;


    public void initialize() {
        this.gridPlayfield.addEventHandler(MouseEvent.MOUSE_RELEASED, startEventhandler);
    }


    @FXML
    public void btnBackPressed(ActionEvent actionEvent) {
        try {
            loadFxmlFile("startmenue.fxml",
                    "Startmenue",
                    labelName.getScene().getWindow(),
                    StartmenueController.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void btnNewGamePressed(ActionEvent actionEvent) {
        try {
            loadFxmlFile("playfield.fxml",
                    "2048 - The Game",
                    labelName.getScene().getWindow(),
                    PlayfieldController.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public class KeyActionEventHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            KeyCode key = event.getCode();
            switch (key) {
                case UP -> System.out.println("up");
                case DOWN -> System.out.println("down");
                case LEFT -> System.out.println("left");
                case RIGHT -> System.out.println("right");
                default -> System.out.println("no available action");
            }
            try {
                insertingTileInPlayfield();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void insertingTileInPlayfield() throws IOException {
        boolean placeistaken = false;
        boolean over = false;
        ObservableList<Node> children = gridPlayfield.getChildren();
        boolean isCellNull = true;
        int x = (int) (Math.random() * 4);
        int y = (int) (Math.random() * 4);
        TilePane tp = null;
        try {
            if (Math.random() > 0.89) {
                tp = new TilePane(4);
            } else {
                tp = new TilePane(2);
            }
            for (Node child : children) {
                if (child instanceof TilePane tilePane) {
                    if (tilePane.getValue() == 2048) {
                        over = true;
                    }
                    if (gridPlayfield.getRowIndex(child) == y && gridPlayfield.getColumnIndex(child) == x) {
                        placeistaken = true;
                    }
                }
            }

        } catch (StackOverflowError sfe) {
            over = true;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alarm");
            alert.setHeaderText("An Error Occurred");
            alert.setContentText("An error has occurred. Please try again later.");
            alert.show();
        }

        if (placeistaken && !over) {
            insertingTileInPlayfield();
        } else if (!over) {
            gridPlayfield.add(tp, x, y);
        } else {
            System.out.println("Gewonnen!");
        }
    }


    public class StartEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent mouseEvent) {
            labelName.getScene().addEventFilter(KeyEvent.KEY_PRESSED, keyActionEventHandler);
            gridPlayfield.removeEventHandler(MouseEvent.MOUSE_RELEASED, startEventhandler);
            labelStartPlay.setVisible(false);
        }
    }
}
