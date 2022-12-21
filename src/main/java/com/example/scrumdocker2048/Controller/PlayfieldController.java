package com.example.scrumdocker2048.Controller;

import com.example.scrumdocker2048.Model.Position;
import com.example.scrumdocker2048.Model.TilePane;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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








    public void initialize(){
        this.gridPlayfield.addEventHandler(MouseEvent.MOUSE_RELEASED, startEventhandler);
    }


    @FXML
    public void btnBackPressed(ActionEvent actionEvent) {
        try {
           loadFxmlFile( "startmenue.fxml",
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
            loadFxmlFile( "playfield.fxml",
                    "2048 - The Game",
                    labelName.getScene().getWindow(),
                    PlayfieldController.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public class KeyActionEventHandler implements EventHandler<KeyEvent>{

        @Override
        public void handle(KeyEvent event) {
            KeyCode key = event.getCode();
            switch (key) {
                case UP:
                    System.out.println("up");
                    break;
                case DOWN:
                    System.out.println("down");
                    break;
                case LEFT:
                    System.out.println("left");
                    break;
                case RIGHT:
                    System.out.println("right");
                    break;
                default:
                    System.out.println("no available action");
                    break;
            }
            TilePane tp = new TilePane(4);
            int x = (int) (Math.random() * 3);
            int y = (int) (Math.random() * 3);
            gridPlayfield.add(tp, x, y);

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
