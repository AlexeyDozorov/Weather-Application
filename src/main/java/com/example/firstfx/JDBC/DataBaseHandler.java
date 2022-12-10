package com.example.firstfx.JDBC;

import com.example.firstfx.Constants.Configs;
import com.example.firstfx.Constants.Const;

import java.sql.*;

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    public void sighnUpUser(Users users){
        String insert = "INSERT INTO " + Const.USER_TABLE + "("
                + Const.USER_FIRSTNAME + "," + Const.USER_LASTNAME + "," + Const.USER_LOGIN + ","
                + Const.USER_PASSOWRD + "," + Const.USER_LOCATION + "," + Const.USER_GENDER + ")"
                + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, users.getFirstname());
            prSt.setString(2, users.getLastname());
            prSt.setString(3, users.getLogin());
            prSt.setString(4, users.getPassword());
            prSt.setString(5, users.getLocation());
            prSt.setString(6, users.getGender());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet getUser(Users users){
        ResultSet resSet = null;
        String select = "SELECT * FROM "+ Const.USER_TABLE + " WHERE "
                + Const.USER_LOGIN + "=? AND " + Const.USER_PASSOWRD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, users.getLogin());
            prSt.setString(2, users.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }
}
