import java.sql.*;

public class JDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost:3306/mysql80";
        String user = "root";
        String password = "root";

        String sql = "SELECT * from employees";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user,password);
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
            System.out.println(rs.getInt("employeeId"));
            System.out.println(rs.getString("lastName"));
            System.out.println(rs.getString("firstName"));
            System.out.println(rs.getString("email"));
            System.out.println(rs.getString("jobTitle"));

        }

        con.close();
    }
}
