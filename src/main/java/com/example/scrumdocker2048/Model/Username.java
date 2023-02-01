package com.example.scrumdocker2048.Model;

import com.example.scrumdocker2048.Controller.PasswordHasher;
import com.example.scrumdocker2048.Controller.UsernameController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * This class represents a user in the game of 2048.
 * It contains data members such as the user's id, username, password, highscore, and games played.
 * The class has methods to retrieve a list of all users, insert a new user into the database,
 * and update a user's highscore in the database.
 */
public class Username {
    private int id;
    private String username;
    private String password;
    private int highscore;
    private int gamesPlayed;

    private int currentHighscore = 0;

    // instance of the username controller class
    UsernameController usernameController = new UsernameController();

    /**
     * Constructor to initialize a user from a result set obtained from a database query.
     *
     * @param results - the result set obtained from a database query
     * @throws SQLException - thrown if there is an error accessing the result set
     */
    public Username(ResultSet results) throws SQLException {
        this.id = results.getInt("userid");
        this.username = results.getString("name");
        this.password = results.getString("password");
        this.highscore = results.getInt("highscore");
        this.gamesPlayed = results.getInt("gamesPlayed");
    }

    /**
     * Constructor to initialize a user with a username and password.
     *
     * @param username - the user's username
     * @param password - the user's password
     */
    public Username(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Default constructor
     */
    public Username() {

    }

    /**
     * Method to retrieve the user's highscore from the database.
     *
     * @return - the user's highscore
     */
    public Integer getHighscore() {
        // get connection to database
        Connection c = Database.getConnection();

        try {
            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT highscore AS highscore FROM t_statistics INNER JOIN t_user tu on t_statistics.userid = tu.userid WHERE name = '" + usernameController.getName() + "';");
            if (resultSet.next()) {
                setHighscore(resultSet.getInt("highscore"));
            }
            resultSet.close();
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highscore;
    }

    /**
     * Method to retrieve a list of all users from the database.
     *
     * @return - an observable list of all users
     */
    public static ObservableList<Username> getList() {
        // create an observable list to hold the users
        ObservableList<Username> list = FXCollections.observableArrayList();
        // get connection to database
        Connection c = Database.getConnection();

        try {
            Statement s = c.createStatement();
            ResultSet results = s.executeQuery("SELECT * FROM t_user u LEFT OUTER JOIN t_statistics s on u.userid = s.userid;");

            while (results.next()) {
                list.add(new Username(results));
            }

            results.close();
            s.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Inserts a new user into the t_user table in the database. The username and password are
     * passed as parameters.
     */
    public void insertUser() {
        Connection c = Database.getConnection();

        try {
            PreparedStatement statement = c.prepareStatement("INSERT INTO t_user (name, password) VALUES ('" + this.username + "', '" + PasswordHasher.hashPassword(this.password) + "');");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertStats() {
        Connection c = Database.getConnection();

        try {
            PreparedStatement statement2 = c.prepareStatement("INSERT INTO t_statistics (userid, highscore, gamesPlayed) VALUES (" + this.id + ", 0, 0);");
            statement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the highscore and games played for a user in the t_statistics table in the database.
     * If the user has not played any games before, a new record is inserted into the table.
     * If the user has a previous highscore, their games played counter is incremented and their
     * highscore is updated if the new highscore is higher than their previous one.
     *
     * @param highscore The new highscore to be set for the user.
     */
    public void updateHighscore(Highscore highscore) {
        Connection c = Database.getConnection();

        try {
            PreparedStatement statement;
            this.gamesPlayed += 1;
            statement = c.prepareStatement("UPDATE t_statistics SET highscore = " + this.highscore + ", gamesPlayed = " + this.gamesPlayed + " WHERE userid = " + this.id + ";");
            statement = c.prepareStatement("UPDATE t_statistics SET gamesPlayed = " + this.gamesPlayed + " WHERE userid = " + this.id + ";");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The new username to be set for the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The new username to be set for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the gamesPlayed of the user.
     *
     * @return The gamesPlayed of the user.
     */
    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Sets the gamesPlayed of the user.
     *
     * @param gamesPlayed The new username to be set for the user.
     */
    public void setGamesPlayed(Integer gamesPlayed) {
        this.gamesPlayed += 1;
    }

    /**
     * Sets the highscore of the user.
     *
     * @param highscore The new username to be set for the user.
     */
    public void setHighscore(Integer highscore) {
        this.highscore = highscore;
    }

    /**
     * Returns a string representation of the Username in the form of "username - highscore - gamesPlayed".
     *
     * @return The string representation of the Username.
     */
    @Override
    public String toString() {
        return username + " - " + highscore + " - " + gamesPlayed;
    }

    /**
     * Returns the CurrentHighscore of the user.
     *
     * @return The CurrentHighscore of the user.
     */
    public int getCurrentHighscore() {
        return currentHighscore;
    }

    /**
     * Sets the currentHighscore of the user.
     *
     * @param val The highscore to add to the currentHighscore.
     */
    public void addOnHighScore(int val) {
        this.currentHighscore += val;
    }
}
