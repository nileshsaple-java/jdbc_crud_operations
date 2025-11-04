package com.jdbc;


import java.sql.*;

public class JdbcConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost:3306/dev";
        String user = "root";
        String password = "password";

        String sql = "SELECT * from employees";

        // load mysql driver in jvm
        Class.forName("com.mysql.cj.jdbc.Driver");

        //get database connection
        //https://downloads.mysql.com/archives/c-j/
        Connection con = DriverManager.getConnection(url, user,password);

        //write sql query and execute
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        /**
         * employeeId int PK
         * lastName varchar(50)
         * firstName varchar(50)
         * email varchar(100)
         * jobTitle varchar(50)
         */
        while(rs.next()){
            System.out.println(rs.getInt("employeeNumber"));
            System.out.println(rs.getString("lastName"));
            System.out.println(rs.getString("firstName"));
            System.out.println(rs.getString("email"));
            System.out.println(rs.getString("jobTitle"));

        }

        con.close();


    }

}
