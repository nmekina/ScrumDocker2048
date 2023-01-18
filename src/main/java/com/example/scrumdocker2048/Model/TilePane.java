package com.example.scrumdocker2048.Model;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class TilePane extends Pane {

    private int value = 0;
    Label labelValue=new Label();

    private Position position;

    public TilePane(int value){
        setValue(value);
        if (value <= 2048) {
            this.getStyleClass().add("tile_" + value);
        }else {
            this.getStyleClass().add("tile_above");

        }
        labelValue.setText(String.valueOf(value));
        labelValue.getStyleClass().add("tileLabel");
        this.getChildren().add(labelValue);
        }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
        List<TilePane> tilePanes = new ArrayList<>();

        for (int i = 1; i < 10000; i=i*2) {
            tilePanes.add(new TilePane(i));
        }
        for (int i = 0; i < tilePanes.size(); i++) {
            System.out.println(tilePanes.get(i));
        }
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
