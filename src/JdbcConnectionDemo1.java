package com.jdbc;


import java.sql.*;

public class JdbcConnectionDemo1 {
    public static void main(String[] args){

        String url = "jdbc:mysql://localhost:3306/dev";
        String user = "root";
        String password = "password";

        String sql = "SELECT * from employees";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null; //RuntimeError

        // load mysql driver in jvm
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //get database connection
            //https://downloads.mysql.com/archives/c-j/
            con = DriverManager.getConnection(url, user,password);

            //write sql query and execute
             stmt = con.createStatement();
             rs = stmt.executeQuery(sql); //RuntimeError

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


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        finally {
            con = null;
            try {
                if(con != null) {
                    con.close(); //Exception in thread "main" java.lang.NullPointerException: Cannot invoke "java.sql.Connection.close()" because "con" is null
                }
                if(rs != null)
                    rs.close();

                if(rs != null) stmt.close();

            } catch (SQLException e) {
                //throw new RuntimeException(e);
            }

        }

    }

}

