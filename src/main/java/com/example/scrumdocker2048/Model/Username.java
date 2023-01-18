package com.example.scrumdocker2048.Model;

import com.example.scrumdocker2048.Controller.PasswordHasher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Username {
    private Integer id;
    private String username;
    private String password;
    private Integer highscore;
    private Integer gamesPlayed;

    public Username(ResultSet results) throws SQLException {
        this.id = results.getInt("u.userid");
        this.username = results.getString("name");
        this.password = results.getString("password");
        this.highscore = results.getInt("highscore");
        this.gamesPlayed = results.getInt("gamesPlayed");
    }

    public Username(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static ObservableList<Username> getList() {
        ObservableList<Username> list = FXCollections.observableArrayList();
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

    public void insertUser() {
        Connection c = Database.getConnection();

        try {
            PreparedStatement statement = c.prepareStatement("INSERT INTO t_user (name, password) VALUES ('" + this.username + "', '" + PasswordHasher.hashPassword(this.password) + "');");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateHighscore(Highscore highscore) {
        Connection c = Database.getConnection();

        try {
            PreparedStatement statement;
            if (this.highscore != 0) {
                this.gamesPlayed += 1;
                if (highscore.getHighscore() > this.highscore) {
                    statement = c.prepareStatement("UPDATE t_statistics SET highscore = " + this.highscore + ", gamesPlayed = " + this.gamesPlayed + " WHERE userid = " + this.id + ";");
                } else {
                    statement = c.prepareStatement("UPDATE t_statistics SET gamesPlayed = " + this.gamesPlayed + " WHERE userid = " + this.id + ";");
                }
            } else {
                statement = c.prepareStatement("INSERT INTO t_statistics(userid, highscore, gamesPlayed) VALUES (" + this.id + ", " + this.highscore + ", " + this.gamesPlayed + ");");
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getHighscore() {
        return highscore;
    }

    public void setHighscore(Integer highscore) {
        this.highscore = highscore;
    }

    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(Integer gamesPlayed) {
        this.gamesPlayed += 1;
    }

    @Override
    public String toString() {
        return username + " - " + highscore + " - " + gamesPlayed;
    }
}
