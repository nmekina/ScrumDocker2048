package com.example.scrumdocker2048.Model;

/**
 * The Position class is used to store the position (x, y) of a cell in the game board.
 */
public class Position {
    private int x;
    private int y;

    /**
     * Compares two positions to see if they are equal.
     * @param pos1 The first position to compare.
     * @param pos2 The second position to compare.
     * @return True if both positions are equal, False otherwise.
     */
    public static boolean comparePositions(Position pos1, Position pos2){
        boolean rv = false;
        if (pos1.getX() == pos2.getX() && pos1.getY() == pos2.getY()){
            rv = true;
        }
        return rv;
    }

    /**
     * Gets the x coordinate of the position.
     * @return The x coordinate of the position.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x coordinate of the position.
     * @param x The new x coordinate of the position.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the y coordinate of the position.
     * @return The y coordinate of the position.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y coordinate of the position.
     * @param y The new y coordinate of the position.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Constructs a new position with the given x and y coordinates.
     * @param x The x coordinate of the position.
     * @param y The y coordinate of the position.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns a string representation of the position in the form of "{x|y}".
     * @return The string representation of the position.
     */
    @Override
    public String toString() {
        return "{" + x + "|"+ y +
                "}";
    }
}
