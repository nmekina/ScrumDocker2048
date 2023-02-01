package com.example.scrumdocker2048.Model;

/**
 * The Highscore class is used to store and manage the highest score in the game.
 */
public class Highscore {
    /**
     * saves the highest score.
     */
    private Integer highscore;

    /**
     * Gets the current highest score.
     * @return The current highest score.
     */
    public Integer getHighscore() {
        return highscore;
    }

    /**
     * Sets the current highest score.
     * @param highscore The new highest score.
     */
    public void setHighscore(Integer highscore) {
        this.highscore = highscore;
    }
}
