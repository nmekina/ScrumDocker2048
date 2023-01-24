package com.example.scrumdocker2048.Controller;

import com.example.scrumdocker2048.Model.Position;
import com.example.scrumdocker2048.Model.TilePane;
import com.example.scrumdocker2048.Model.Username;
import javafx.collections.*;
import com.example.scrumdocker2048.Model.checkConditions;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
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
import java.sql.SQLOutput;
import java.util.*;

public class PlayfieldController extends AbstractController {

    public ColumnConstraints Grridpane;
    public Label labelCurrent;
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
    Username username = new Username();

    UsernameController usernameController = new UsernameController();

    public void initialize() throws IOException {
        this.gridPlayfield.addEventHandler(MouseEvent.MOUSE_RELEASED, startEventhandler);
        insertingTileInPlayfield();
        insertingTileInPlayfield();
        labelCurrent.setText(usernameController.getName());
        labelHighestScore.setText(String.valueOf(username.getHighscore()));
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
            ObservableList<TilePane> tiles = getTiles();
            Position currentPosition;
            Position moveNextPosition = null;
            int currentValue = 0;
            int moveAvailable = 0;
            switch (key) {
                case UP: // row -
                    System.out.println("up");
                    for (int row = 0; row < gridPlayfield.getRowCount(); row++) {
                        for (int col = 0; col < gridPlayfield.getColumnCount(); col++) {
                            currentPosition = new Position(col, row);
                            tiles = getTiles();
                            for (TilePane tile : tiles) {
                                if (Position.comparePositions(currentPosition, tile.getPosition())) {
                                    moveNextPosition = new Position(tile.getPosition().getX() - 1, tile.getPosition().getY());
                                    currentValue = tile.getValue();

                                }
                            }
                            if (moveNextPosition != null) {
                                if (moveNextPosition.getX() >= 0) {
                                    moveAvailable = checkPosIfMoveOn(moveNextPosition, currentValue);
                                    if (moveAvailable == 1) {
                                        System.out.println(moveNextPosition + " 120");
                                        newPlaceTile(currentPosition, moveNextPosition, currentValue);
                                    } else if (moveAvailable == 2){
                                        System.out.println(moveNextPosition + " 124");
                                        newPlaceTile(currentPosition, moveNextPosition, currentValue*2);
                                    }
                                }
                            }
                            moveNextPosition = null;
                            currentValue = 0;
                            moveAvailable = 0;
                        }
                    }
                    break;
                case DOWN:// row +
                    System.out.println("down");
                    for (int row = 0; row < gridPlayfield.getRowCount(); row++) {
                        for (int col = gridPlayfield.getColumnCount(); col >= 0; col--) {
                            currentPosition = new Position(col, row);
                            for (TilePane tile : tiles) {
                                if (Position.comparePositions(currentPosition, tile.getPosition())) {
                                    moveNextPosition = new Position(tile.getPosition().getX() + 1, tile.getPosition().getY());
                                    currentValue = tile.getValue();
                                }
                            }
                            if (moveNextPosition != null) {
                                if (moveNextPosition.getY() < 4) {
                                    moveAvailable = checkPosIfMoveOn(moveNextPosition, currentValue);
                                    if (moveAvailable == 1) {
                                        System.out.println(moveNextPosition + " 150");
                                        newPlaceTile(currentPosition, moveNextPosition, currentValue);
                                    } else if (moveAvailable == 2){
                                        System.out.println(moveNextPosition + " 153");
                                        newPlaceTile(currentPosition, moveNextPosition, currentValue*2);
                                    }
                                }
                            }
                            moveNextPosition = null;
                            currentValue = 0;
                            moveAvailable = 0;
                        }
                    }
                    break;
                case LEFT: // col -
                    System.out.println("left");
                    for (int col = 0; col < gridPlayfield.getColumnCount(); col++) {
                        for (int row = 0; row < gridPlayfield.getRowCount(); row++) {
                            currentPosition = new Position(col, row);
                            for (TilePane tile : tiles) {
                                if (Position.comparePositions(currentPosition, tile.getPosition())) {
                                    moveNextPosition = new Position(tile.getPosition().getX(), tile.getPosition().getY() - 1);
                                    currentValue = tile.getValue();
                                }
                            }
                            if (moveNextPosition != null) {
                                if (moveNextPosition.getY() >= 0) {
                                    moveAvailable = checkPosIfMoveOn(moveNextPosition, currentValue);
                                    if (moveAvailable == 1) {
                                        System.out.println(moveNextPosition + " 179");
                                        newPlaceTile(currentPosition, moveNextPosition, currentValue);
                                    } else if (moveAvailable == 2){
                                        System.out.println(moveNextPosition + " 182");
                                        newPlaceTile(currentPosition, moveNextPosition, currentValue*2);
                                    }
                                }
                            }
                            moveNextPosition = null;
                            currentValue = 0;
                            moveAvailable = 0;
                        }
                    }
                    break;
                case RIGHT: // col +
                    System.out.println("right");
                    for (int col = 0; col < gridPlayfield.getColumnCount(); col++) {
                        for (int row = gridPlayfield.getRowCount(); row >= 0; row--) {
                            currentPosition = new Position(col, row);
                            for (TilePane tile : tiles) {
                                if (Position.comparePositions(currentPosition, tile.getPosition())) {
                                    moveNextPosition = new Position(tile.getPosition().getX(), tile.getPosition().getY() + 1);
                                    currentValue = tile.getValue();
                                }
                            }
                            if (moveNextPosition != null) {
                                if (moveNextPosition.getY() < 4) {
                                    moveAvailable = checkPosIfMoveOn(moveNextPosition, currentValue);
                                    if (moveAvailable == 1) {
                                        System.out.println(moveNextPosition + " 208");
                                        newPlaceTile(currentPosition, moveNextPosition, currentValue);
                                    } else if (moveAvailable == 2){
                                        System.out.println(moveNextPosition + " 211");
                                        newPlaceTile(currentPosition, moveNextPosition, currentValue*2);
                                    }
                                }
                            }
                            moveNextPosition = null;
                            currentValue = 0;
                            moveAvailable = 0;
                        }
                    }
                    break;
                default:
                    System.out.println("no available action");
            }
            try {
                insertingTileInPlayfield();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        private int checkPosIfMoveOn(Position moveNextPosition, int val) {
            ObservableList<TilePane> tiles = getTiles();
            int returnValue = 1;
            for (TilePane tile : tiles) {
                if (Position.comparePositions(moveNextPosition, tile.getPosition())) {
                    returnValue = 2;
                    if (!(tile.getValue() == val)) {
                        returnValue = 0;
                    }
                }
            }
            return returnValue;
        }


        private ObservableList<TilePane> getTiles() {
            ObservableList<Node> children = gridPlayfield.getChildren();
            ObservableList<TilePane> tiles = FXCollections.observableArrayList();
            for (Node child : children) {
                if (child instanceof TilePane) {
                    tiles.add((TilePane) child);
                }
            }
            for (TilePane tile : tiles) {
                Position position = new Position(gridPlayfield.getRowIndex(tile), gridPlayfield.getColumnIndex(tile));
                tile.setPosition(position);

            }
            return tiles;
        }

        private void newPlaceTile(Position position, Position nextPosition, int val) {
            System.out.println(position + " " + nextPosition + "232");
            ObservableList<TilePane> tiles = getTiles();
            List<TilePane> removeTile = new ArrayList<>();
            for (TilePane tile : tiles) {
                if (Position.comparePositions(tile.getPosition(), position)) {
                    removeTile.add(tile);
                }
            }
            gridPlayfield.add(new TilePane(val), nextPosition.getY(), nextPosition.getX());
            for (TilePane tilePane : removeTile) {
                gridPlayfield.getChildren().remove(tilePane);
            }
        }
    }

    /**
     * This private method is responsible for inserting a new TilePane into the playfield grid.
     * A random x and y coordinate is generated, and a check is made to see if the space is already occupied by another TilePane.
     * If the space is not occupied, a new TilePane is created with a random value of 2 or 4.
     * If the value of any TilePane on the playfield is 2048, the game is set to be over.
     * If an IOException is thrown, it is propagated to the calling method.
     *
     * @throws IOException if an input or output exception occurs
     */
    private void insertingTileInPlayfield() throws IOException {
        boolean placeistaken = false;
        boolean over = false;
        ObservableList<Node> children = gridPlayfield.getChildren();
        boolean isCellNull = true;
        int x = (int) (Math.random() * 4);
        int y = (int) (Math.random() * 4);
        TilePane tp = null;
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
                System.out.println(gridPlayfield.getChildren().size());
                if (Math.random() > 0.89) {
                    tp = new TilePane(4);
                } else {
                    tp = new TilePane(2);
                }
                for (Node child1 : children) {
                    if (child1 instanceof TilePane) {
                        if (tilePane.getValue() == 2048) {
                            over = true;
                        }
                        if (gridPlayfield.getRowIndex(child) == y && gridPlayfield.getColumnIndex(child) == x) {
                            placeistaken = true;
                        }


                    }
                }

                if (gridPlayfield.getChildren().size() == 16) {
                    for (int i = 0; i <= 14; i++) {
                        if (children.get(i) instanceof TilePane && children.get(i + 1) instanceof TilePane) {
                            TilePane tile1 = (TilePane) children.get(i);
                            TilePane tile2 = (TilePane) children.get(i + 1);
                            if (tile1.getValue() == (tile2.getValue())) {
                                System.out.println("weiter gehts  277");
                            }

                        }
                    }
                }
       /* over = true;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Alarm");
        alert.setHeaderText("An Error Occurred");
        alert.setContentText("An error has occurred. Please try again later.");
        alert.show();
        */
            }
        }
        if (gridPlayfield.getChildren().size() == 16) {
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
            System.out.println(gridPlayfield.getChildren().size() + "293");
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
