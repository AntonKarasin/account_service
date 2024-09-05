package com.beprog.account.service.utils;

import java.sql.*;

public class DbUtil {
    // здесь нужно реализовать логику соединения с БД
    public static Connection getMyConnection() throws SQLException,
            ClassNotFoundException {

        // Using Oracle
        // You may be replaced by other Database.

        String url = "jdbc:postgresql://localhost:5432/account_service?user=account_service&password=account_service";
        return DriverManager.getConnection(url);
    }
}
