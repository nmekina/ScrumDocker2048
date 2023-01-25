package com.example.scrumdocker2048.Model;

public class Position {
    private int x;
    private int y;


    public static boolean comparePositions(Position pos1, Position pos2){
        boolean rv = false;
        if (pos1.getX() == pos2.getX() && pos1.getY() == pos2.getY()){
            rv = true;
        }
        return rv;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" + x + "|"+ y +
                "}";
    }
}
