package com.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.connection.ConnectionFactory;
import com.jdbc.model.Employee;


public class EmployeeDao {

    public Boolean save(Employee employee) {

        String sql = "insert  into employees (`employeeid`,`lastname`,`firstname`,`email`,`jobtitle`) values (?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int exec = 0;
        Boolean msg = false;
        try {
            // Create Connection
            connection = ConnectionFactory.createConnectionToMySql();

            //Prepare the statement
            preparedStatement = connection.prepareStatement(sql);

            //bind java data to jdbc statement
            preparedStatement.setInt(1, employee.getEmployeeId());//bind 1
            preparedStatement.setString(2, employee.getLastName());// bind 2
            preparedStatement.setString(3, employee.getFirstName());// bind 3
            preparedStatement.setString(4, employee.getEmail());// bind 4
            preparedStatement.setString(5, employee.getJobTitle());// bind 5

            // execute Query
            int count = preparedStatement.executeUpdate();

            if (count > 0) {
                msg = true;
            } else {
                msg = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // release external resources or close connection
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return msg;
    }

    //
    public List<Employee> getAllEmployees() throws SQLException {

        // query
        String sql = "select * from employees";

        // var -employeeList
        List<Employee> employeeList = new ArrayList<Employee>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // Create Connection
            connection = ConnectionFactory.createConnectionToMySql();
            //Prepare the statement
            preparedStatement = connection.prepareStatement(sql);
            // execute Query
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Employee employee = new Employee();

                employee.setEmployeeId(resultSet.getInt("employeeId"));     //employeeId
                employee.setLastName(resultSet.getString("lastName"));      //lastName
                employee.setFirstName(resultSet.getString("firstName"));    //firstName
                employee.setEmail(resultSet.getString("email"));            //email
                employee.setJobTitle(resultSet.getString("jobTitle"));      //jobTitle

                employeeList.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return employeeList;

    }

    public Boolean delete(int id) throws Exception {
        // query
        String sql = "DELETE FROM employees WHERE employeeId = ?";


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        Boolean msg = false;
        try {
            // Create Connection
            connection = ConnectionFactory.createConnectionToMySql();
            //Prepare the statement
            preparedStatement = connection.prepareStatement(sql);

            //bind java data to jdbc statement
            preparedStatement.setInt(1, id);
            // execute Query
            result = preparedStatement.executeUpdate();


            if (result > 0) {
                msg = true;
            } else {
                msg = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return msg;
    }


    public Boolean update(int employeeId, String firstName, String lastName) throws Exception {
        // query
        String sql = "UPDATE employees SET firstName = ?, lastName = ? WHERE employeeId = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        int exec = 0;
        Boolean msg = false;
        try {
            // Create Connection
            conn = ConnectionFactory.createConnectionToMySql();
            //Prepare the statement
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, firstName);// bind 1
            pstmt.setString(2, lastName);// bind 2
            pstmt.setInt(3, employeeId);// bind 3

            exec = pstmt.executeUpdate();

            if (exec > 0) {
                msg = true;
            } else {
                msg = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (conn != null) {
                conn.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return msg;
    }
}