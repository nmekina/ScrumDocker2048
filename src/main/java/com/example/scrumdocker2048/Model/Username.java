package com.example.scrumdocker2048.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Username {
    String username;
    String password;
    Timestamp date;
    Integer highscore;
    Integer gamesPlayed;

    public Username(ResultSet results) throws SQLException {
        this.username = results.getString("name");
        this.password = results.getString("password");
        this.date = results.getTimestamp("date");
        this.highscore = results.getInt("highscore");
        this.gamesPlayed = results.getInt("gamesPlayed");
    }

    public Username(String username, String password, Timestamp date, Integer highscore, Integer gamesPlayed) {
        this.username = username;
        this.password = password;
        this.date = date;
        this.highscore = highscore;
        this.gamesPlayed = gamesPlayed;
    }

    public static ObservableList<Username> getList() {
        ObservableList<Username> list = FXCollections.observableArrayList();
        Username username1 = new Username("Nico", "password", new Timestamp(2000), 999, 1);
        Username username2 = new Username("Leini", "password", new Timestamp(3000), 123, 4);
        list.add(username1);
        list.add(username2);
        /*
        Connection c = Database.getConnection();

        try {
            Statement s = c.createStatement();
            ResultSet results = s.executeQuery("SELECT * FROM t_user u INNER JOIN t_statistics s ON u.userid = s.userid;");

            while (results.next()) {
                list.add(new Username(results));
            }

            results.close();
            s.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
         */
        return list;
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

    @Override
    public String toString() {
        return username;
    }
}
