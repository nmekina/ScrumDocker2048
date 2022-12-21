package com.example.scrumdocker2048.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Username {
    String username;
    String password;
    Date date;
    Integer highscore;
    Integer gamesPlayed;

    public Username(ResultSet results) throws SQLException {
        this.username = results.getString("name");
        this.password = results.getString("password");
        this.date = results.getDate("timestamp");
    }

    public Username(String username, String password, Date date) {
        this.username = username;
        this.password = password;
        this.date = date;
    }

    public static ObservableList<Username> getList() {
        ObservableList<Username> list = FXCollections.observableArrayList();
        Connection c = Database.getConnection();

        try {
            Statement s = c.createStatement();
            ResultSet results = s.executeQuery("SELECT * FROM t_user u;");

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
            PreparedStatement statement = c.prepareStatement("INSERT INTO t_user (name, password, timestamp) VALUES ('" + this.username + "', '" + this.password + "', '" + this.date + "');");
            statement.executeUpdate();
            System.out.println("User wurde zu Datenbank hinzugefuegt!");
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        this.gamesPlayed = gamesPlayed;
    }

    @Override
    public String toString() {
        return username + " - " + highscore + " - " + gamesPlayed;
    }
}
