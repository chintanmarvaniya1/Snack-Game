package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class establishConnection {
    public Connection connect;
    public Statement sta;
    public establishConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/Hotel_Management_System", "root", "chintan@26");
            sta = connect.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new establishConnection();
    }
}
