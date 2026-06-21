import java.sql.*;

public class Conn {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/name";
        String user = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            System.out.println("Database connected successfully");

            Statement stmt = conn.createStatement();
            stmt.executeUpdate( "create table Employee1 (" +"id int primary key, " +"name varchar(50), " +"job_role varchar(50), " +"hire_date date, " +"salary int)");

            System.out.println("Table Successfully Created");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}