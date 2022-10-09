package com.jdbc.application;


import com.jdbc.model.Employee;
import com.jdbc.dao.EmployeeDao;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Main{
    public static void main(String[] args) throws SQLException, Exception {

        Scanner scan = new Scanner(System.in);
        EmployeeDao employeeDao = new EmployeeDao();


        int choice;
        while (true) {
            System.out.println(" **** Employee Database  **** ");
            System.out.println("\n Choose one of the options below:\n\n" +
                    "1 - List All Employees\n" +
                    "2 - Create Employee \n" +
                    "3 - Delete Employee \n" +
                    "4 - Edit Employee \n" +
                    "5 - Exit Program \n");

            // entry choose
            System.out.print("Enter your choice: ");
            choice = scan.nextInt();

            switch (choice) {
                case 1:
                    List<Employee> employeeList = employeeDao.getAllEmployees();

                    for (Employee emp : employeeList) {
                        /*System.out.println(" employeeId: " + emp.getEmployeeId());
                        System.out.println(" lastName: " + emp.getLastName());
                        System.out.println(" firstName: " + emp.getFirstName());
                        System.out.println(" email: " + emp.getEmail());
                        System.out.println(" jobTitle: " + emp.getJobTitle() + "\n");*/

                        System.out.println(emp.getEmployeeId() +" :: "+
                                            emp.getLastName() +" :: "+
                                            emp.getFirstName() +" :: "+
                                            emp.getEmail() +" :: "+
                                            emp.getJobTitle());
                    }
                    System.out.println("------------------------------------------------------\n");
                    break;

                case 2:
                    Employee employee = new Employee();

                    System.out.print("Enter EmployeeID : ");
                    employee.setEmployeeId(scan.nextInt());

                    System.out.print("Enter Last Name: ");
                    employee.setLastName(scan.next());

                    System.out.print("Enter First Name: ");
                    employee.setFirstName(scan.next());

                    System.out.print("Enter Email ID: ");
                    employee.setEmail(scan.next());

                    System.out.print("Enter Job Title: ");
                    employee.setJobTitle(scan.next());

                    Boolean isEmployeeSaved = employeeDao.save(employee);
                    if (isEmployeeSaved) {
                        System.out.println("\n Employee created Successfully");
                    } else {
                        System.out.println("\n\n Error occurred while creating Employee \n Check that the information entered is correct.\n");
                    }
                    System.out.println("------------------------------------------------------");
                    break;

                case 3:
                    // var
                    int id;

                    System.out.print(" Enter the Employee ID you want to delete: ");
                    int empId = scan.nextInt();

                    boolean isEmployeeDeleted = employeeDao.delete(empId);

                    if (isEmployeeDeleted) {
                        System.out.println("\n Employee deleted Successfully");
                    } else {
                        System.out.println("\n\n Error occurred while deleting Employee \n Check that the information entered is correct.\n");
                    }
                    System.out.println("------------------------------------------------------");
                    break;

                case 4:
                    System.out.print("Enter the Employee ID you want to Update: ");
                    int empID = scan.nextInt();

                    System.out.print("Enter the New first Name: ");
                    String fName = scan.next();

                    System.out.print("Enter the New Last Name: ");
                    String lName = scan.next();

                    boolean isEmployeeUpdated = employeeDao.update(empID, fName, lName);

                    if (isEmployeeUpdated) {
                        System.out.println("\n Employee updated Successfully");
                    } else {
                        System.out.println("\n\n Error occurred while updating Employee \n Check that the information entered is correct.\n");
                    }
                    System.out.println("------------------------------------------------------");
                    break;

                case 5:
                    System.out.println("\n\n Finished Program\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n\n Invalid Option. Try again\n");
            }

        }
    }
}