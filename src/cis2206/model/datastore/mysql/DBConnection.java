package cis2206.model.datastore.mysql;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Get a connection to the database. DBConnection is based on example code from
 * http://www.journaldev.com/2471/package com.journaldev.jdbc
 *
 * @author Matt Delosa
 * @version 20151015
 *
 */
public class DBConnection {

    public static Connection getConnection() {
        Properties props = new Properties();
        Connection con = null;
        try {
            props.load(new FileInputStream("res/mysql/db.properties"));
            Class.forName(props.getProperty("DB_DRIVER_CLASS"));
            con = DriverManager.getConnection(
                    props.getProperty("DB_URL")
                    + "?user=" + props.getProperty("DB_USERNAME")
                    + "&password=" + props.getProperty("DB_PASSWORD")
                    + "&useSSL=" + props.getProperty("USE_SSL"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("db error in getConnection()");
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) {
        getConnection();
    }
}
