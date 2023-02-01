package com.example.scrumdocker2048.Model;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;


/**
 * Class to represent a tile in the game of 2048. This class extends the JavaFX
 * Pane class and holds the value of the tile and its position.
 */
public class TilePane extends Pane {
    /**
     * The value of the tile.
     */
    private int value = 0;


    /**
     * Label to display the value of the tile.
     */
    Label labelValue = new Label();

    /**
     * The position of the tile on the game board.
     */
    private Position position;

    /**
     * Constructor to initialize the value of the tile and its style based on the value.
     *
     * @param value the value of the tile.
     */
    public TilePane(int value) {
        setValue(value);
        if (value <= 2048) {
            this.getStyleClass().add("tile_" + value);
        } else {
            this.getStyleClass().add("tile_above");
        }

        labelValue.setText(String.valueOf(value));
        if (value < 10) {
            labelValue.getStyleClass().add("tileLabel_10");
        } else if (value < 100) {
            labelValue.getStyleClass().add("tileLabel_100");
        } else if (value < 1000) {
            labelValue.getStyleClass().add("tileLabel_1000");
        } else {
            labelValue.getStyleClass().add("tileLabel_10000");
        }

        this.getChildren().add(labelValue);
    }

    /**
     * Get the value of the tile.
     *
     * @return the value of the tile.
     */
    public int getValue() {
        return value;
    }

    /**
     * Set the value of the tile.
     *
     * @param value the value of the tile.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Get the position of the tile on the game board.
     *
     * @return the position of the tile.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Set the position of the tile on the game board.
     *
     * @param position the position of the tile.
     */
    public void setPosition(Position position) {
        this.position = position;
    }
}
