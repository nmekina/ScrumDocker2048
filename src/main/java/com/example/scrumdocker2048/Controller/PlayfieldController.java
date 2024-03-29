package com.example.scrumdocker2048.Controller;

import com.example.scrumdocker2048.Model.Highscore;
import com.example.scrumdocker2048.Model.Position;
import com.example.scrumdocker2048.Model.TilePane;
import com.example.scrumdocker2048.Model.Username;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayfieldController extends AbstractController {

    public ColumnConstraints Gridpane;
    public Label labelCurrent;
    public Button btnClose;
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
    public Label labelCurrentHighscore;
    @FXML
    public Label labelHighestScore;
    Username currentUsername = UsernameController.currentUsername;

    public void initialize() throws IOException {
        this.gridPlayfield.addEventHandler(MouseEvent.MOUSE_RELEASED, startEventhandler);
        insertingTileInPlayfield();
        insertingTileInPlayfield();
        labelCurrent.setText(currentUsername.getUsername());
        labelHighestScore.setText(String.valueOf(currentUsername.getHighscore()));
        currentUsername.setCurrentHighscore(0);
        labelCurrentHighscore.setText(String.valueOf(currentUsername.getCurrentHighscore()));
    }

    /**
     *
     * Handles the event when the "Back" button is pressed.
     * Loads the start menu scene from the "startmenue.fxml" file.
     * @param actionEvent the event triggered by pressing the "Back" button
     * @throws RuntimeException if an {@link IOException} occurs while loading the "startmenue.fxml" file
     */
    @FXML
    public void btnBackPressed(ActionEvent actionEvent) {
        try {
            loadFxmlFile("startmenue.fxml", "Startmenue", labelName.getScene().getWindow(), StartmenueController.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * Handles the event when the "New Game" button is pressed.
     * Loads the game scene from the "playfield.fxml" file.
     *@param actionEvent the event triggered by pressing the "New Game" button
     * @throws RuntimeException if an {@link IOException} occurs while loading the "playfield.fxml" file
     */
    @FXML
    public void btnNewGamePressed(ActionEvent actionEvent) {
        try {
            loadFxmlFile("playfield.fxml", "2048 - The Game", labelName.getScene().getWindow(), PlayfieldController.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Close the Stage if the Close button was pressed.
     *
     * @param event
     */
    @FXML
    void btnClosePressed(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public class KeyActionEventHandler implements EventHandler<KeyEvent> {
        /**
         * Handles the event when a button is pressed
         * in each case it iterates over the playfield and moves the tiles if it is available
         * If the number of tiles is less than 32, a new tile is added
         * @param event
         */
        @Override
        public void handle(KeyEvent event) {
            checkLose();
            KeyCode key = event.getCode();
            ObservableList<TilePane> tiles = getTiles();
            Position currentPosition;
            Position moveNextPosition = null;
            Position fallBackPos = null;
            int currentValue = 0;
            int moveAvailable = 1;
            switch (key) {
                case UP: // row -
                    for (int row = 0; row < gridPlayfield.getRowCount(); row++) {
                        for (int col = 0; col < gridPlayfield.getColumnCount(); col++) {
                            currentPosition = new Position(col, row);
                            for (TilePane tile : tiles) {
                                if (Position.comparePositions(currentPosition, tile.getPosition())) {
                                    fallBackPos = currentPosition;
                                    currentValue = tile.getValue();

                                    while (moveAvailable == 1) {
                                        moveNextPosition = new Position(fallBackPos.getX() - 1, fallBackPos.getY());
                                        moveAvailable = checkPosIfMoveOn(moveNextPosition, currentValue);
                                        if (moveAvailable != 0) {
                                            fallBackPos = moveNextPosition;
                                        }
                                    }
                                }
                            }
                            if (fallBackPos != null) {
                                if (moveAvailable == 2) {
                                    currentValue *= 2;
                                }
                                newPlaceTile(currentPosition, fallBackPos, currentValue);
                            }
                            fallBackPos = null;
                            moveNextPosition = null;
                            currentValue = 0;
                            moveAvailable = 1;
                        }
                    }
                    if (gridPlayfield.getChildren().size() < 32) {
                        try {
                            insertingTileInPlayfield();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case DOWN:// row +
                    for (int row = 0; row < gridPlayfield.getRowCount(); row++) {
                        for (int col = gridPlayfield.getColumnCount(); col >= 0; col--) {
                            currentPosition = new Position(col, row);
                            for (TilePane tile : tiles) {
                                if (Position.comparePositions(currentPosition, tile.getPosition())) {
                                    fallBackPos = currentPosition;
                                    currentValue = tile.getValue();

                                    while (moveAvailable == 1) {
                                        moveNextPosition = new Position(fallBackPos.getX() + 1, fallBackPos.getY());
                                        moveAvailable = checkPosIfMoveOn(moveNextPosition, currentValue);
                                        if (moveAvailable != 0) {
                                            fallBackPos = moveNextPosition;
                                        }
                                    }
                                }
                            }
                            if (fallBackPos != null) {
                                if (moveAvailable == 2) {
                                    currentValue *= 2;
                                }
                                newPlaceTile(currentPosition, fallBackPos, currentValue);
                            }
                            fallBackPos = null;
                            moveNextPosition = null;
                            currentValue = 0;
                            moveAvailable = 1;
                        }
                    }
                    if (gridPlayfield.getChildren().size() < 32) {
                        try {
                            insertingTileInPlayfield();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case LEFT: // col -
                    for (int col = 0; col < gridPlayfield.getColumnCount(); col++) {
                        for (int row = 0; row < gridPlayfield.getRowCount(); row++) {
                            currentPosition = new Position(col, row);
                            for (TilePane tile : tiles) {
                                if (Position.comparePositions(currentPosition, tile.getPosition())) {
                                    fallBackPos = currentPosition;
                                    currentValue = tile.getValue();

                                    while (moveAvailable == 1) {
                                        moveNextPosition = new Position(fallBackPos.getX(), fallBackPos.getY() - 1);
                                        moveAvailable = checkPosIfMoveOn(moveNextPosition, currentValue);
                                        if (moveAvailable != 0) {
                                            fallBackPos = moveNextPosition;
                                        }
                                    }
                                }
                            }
                            if (fallBackPos != null) {
                                if (moveAvailable == 2) {
                                    currentValue *= 2;
                                }
                                newPlaceTile(currentPosition, fallBackPos, currentValue);
                            }
                            fallBackPos = null;
                            moveNextPosition = null;
                            currentValue = 0;
                            moveAvailable = 1;
                        }
                    }
                    if (gridPlayfield.getChildren().size() < 32) {
                        try {
                            insertingTileInPlayfield();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case RIGHT: // col +
                    for (int col = 0; col < gridPlayfield.getColumnCount(); col++) {
                        for (int row = gridPlayfield.getRowCount(); row >= 0; row--) {
                            currentPosition = new Position(col, row);
                            for (TilePane tile : tiles) {
                                if (Position.comparePositions(currentPosition, tile.getPosition())) {
                                    fallBackPos = currentPosition;
                                    currentValue = tile.getValue();

                                    while (moveAvailable == 1) {
                                        moveNextPosition = new Position(fallBackPos.getX(), fallBackPos.getY() + 1);
                                        moveAvailable = checkPosIfMoveOn(moveNextPosition, currentValue);
                                        if (moveAvailable != 0) {
                                            fallBackPos = moveNextPosition;
                                        }
                                    }
                                }
                            }
                            if (fallBackPos != null) {
                                if (moveAvailable == 2) {
                                    currentValue *= 2;
                                }
                                newPlaceTile(currentPosition, fallBackPos, currentValue);
                            }
                            fallBackPos = null;
                            moveNextPosition = null;
                            currentValue = 0;
                            moveAvailable = 1;
                        }
                    }
                    if (gridPlayfield.getChildren().size() < 32) {
                        try {
                            insertingTileInPlayfield();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                default:
            }

        }
        /**
         *
         * The checkLose method checks if the game is lost and updates the highscore if necessary.
         * It retrieves all the tiles from the game and loops through each tile to compare its value with other tiles.
         * If there is no match found, the game is lost and the highscore is updated by retrieving the current score,
         * creating a new Highscore object and setting the highscore value to it.
         * Then an alert is displayed to inform the player that the game is lost and ask if they want to play again.
         */
        private void checkLose() {
            ObservableList<TilePane> tilePanes = getTiles();
            boolean checkLose = true;

            if (tilePanes.size() == 16) {
                for (TilePane pane1 : tilePanes) {
                    for (TilePane pane2 : tilePanes) {
                        if (pane1.getValue() == pane2.getValue()) {
                            if (pane1.getPosition().getX() == pane2.getPosition().getX()) {
                                if (pane1.getPosition().getY() == (pane2.getPosition().getY() + 1)) {
                                    checkLose = false;
                                } else if (pane1.getPosition().getY() == (pane2.getPosition().getY() - 1)) {
                                    checkLose = false;
                                }
                            } else if (pane1.getPosition().getY() == pane2.getPosition().getY()) {
                                if (pane1.getPosition().getX() == (pane2.getPosition().getX() + 1)) {
                                    checkLose = false;
                                } else if (pane1.getPosition().getX() == (pane2.getPosition().getX() - 1)) {
                                    checkLose = false;
                                }
                            }
                        }
                    }
                }
                if (checkLose) {
                    // add highscore to database
                    Highscore highscore = new Highscore();
                    System.out.println(Integer.valueOf(labelHighestScore.getText()));
                    highscore.setHighscore(Integer.valueOf(labelHighestScore.getText()));

                    currentUsername.updateHighscore(highscore);

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Verloren!");
                    alert.setHeaderText("Sie haben verloren! Wollen Sie noch einmal spielen?");
                    alert.show();
                }
            }
        }
        /**

         Check the position if move is possible.
         @param moveNextPosition the position to be checked
         @param val the value to be checked
         @return the status of the check: 0 for illegal move, 1 for empty position, 2 for a merge.
         */
        private int checkPosIfMoveOn(Position moveNextPosition, int val) {
            ObservableList<TilePane> tiles = getTiles();
            int returnValue = 1;
            if (moveNextPosition.getX() >= 0 && moveNextPosition.getX() < 4 && moveNextPosition.getY() >= 0 && moveNextPosition.getY() < 4) {
                for (TilePane tile : tiles) {
                    if (Position.comparePositions(moveNextPosition, tile.getPosition())) {
                        returnValue = 2;
                        if (!(tile.getValue() == val)) {
                            returnValue = 0;
                        }
                    }
                }
                if (returnValue == 2) {
                    currentUsername.addOnHighScore(val * 2);
                    if (currentUsername.getCurrentHighscore() >= currentUsername.getHighscore()) {
                        labelHighestScore.setText(String.valueOf(currentUsername.getCurrentHighscore()));
                    }
                    labelCurrentHighscore.setText(String.valueOf(currentUsername.getCurrentHighscore()));
                }
            } else {
                returnValue = 0;
            }
            return returnValue;
        }

        /**
         *
         * Returns a list of {@link TilePane} objects that are currently in the grid playfield.
         * @return An {@link ObservableList} of {@link TilePane} objects
         */
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
        /**
         *
         * This method is used to place a new tile on the playfield with a given value.
         * It first gets the list of all existing tiles in the playfield and adds the tile with the given value at the next position.
         * The tiles present at the current position and next position are removed.
         * @param position current position of the tile
         * @param nextPosition next position of the tile
         * @param val value of the new tile
         */
        private void newPlaceTile(Position position, Position nextPosition, int val) {
            ObservableList<TilePane> tiles = getTiles();
            List<TilePane> removeTile = new ArrayList<>();
            for (TilePane tile : tiles) {
                if (Position.comparePositions(tile.getPosition(), position) || Position.comparePositions(tile.getPosition(), nextPosition)) {
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
            }
        }

        if (placeistaken && !over) {
            insertingTileInPlayfield();
        } else if (!over) {
            gridPlayfield.add(tp, x, y);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Gewonnen!");
            alert.setHeaderText("Sie haben gewonen wollen Sie weiterspielen?");
            alert.show();
        }
    }

    public class StartEventHandler implements EventHandler<MouseEvent> {

        /**
         *
         * Handles the MouseEvent and adds a KeyEvent filter to the scene.
         * This method also removes the MouseEvent handler from the grid playfield and sets the visibility of the start label to false.
         * @param mouseEvent The mouse event to be handled
         */
        @Override
        public void handle(MouseEvent mouseEvent) {
            labelName.getScene().addEventFilter(KeyEvent.KEY_PRESSED, keyActionEventHandler);
            gridPlayfield.removeEventHandler(MouseEvent.MOUSE_RELEASED, startEventhandler);
            labelStartPlay.setVisible(false);
        }
    }
}

