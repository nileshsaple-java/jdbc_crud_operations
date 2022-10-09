import java.sql.*;

public class JDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/mysql80";
        String user = "root";
        String password = "root";

        Class.forName("com.mysql.cj.jdbc.Driver");


        // create a connection to the database
        Connection conn = DriverManager.getConnection(url, user, password);

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from employees");
        while (rs.next())
            System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
        conn.close();

        System.out.println(conn);

    }
}
