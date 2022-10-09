package com.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String username = "root";
    private static final String password = "root";
    private static final String database_url = "jdbc:mysql://localhost:3306/mysql80";

    public static Connection createConnectionToMySql() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(database_url,username,password);
        return connection;
    }

}