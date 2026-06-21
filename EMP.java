import java.sql.*;
import java.util.Scanner;

public class EMP {
static String url = "jdbc:mysql://localhost:3306/name";
static String user = "root";
static String password = "root";

static Scanner sc = new Scanner(System.in);

public static void addEmployee() throws SQLException {

    Connection c = DriverManager.getConnection(url, user, password);

    PreparedStatement p =
            c.prepareStatement("insert into Employee1 values(?,?,?,?,?)");

    System.out.println("Enter Id");
    p.setInt(1, sc.nextInt());

    System.out.println("Enter Name");
    p.setString(2, sc.next());

    System.out.println("Enter Job Role");
    p.setString(3, sc.next());

    System.out.println("Enter Hire Date (yyyy-mm-dd)");
    p.setString(4, sc.next());

    System.out.println("Enter Salary");
    p.setInt(5, sc.nextInt());

    p.executeUpdate();

    System.out.println("Employee Added Successfully");

    c.close();
}

public static void displayEmployees() throws SQLException {

    Connection c = DriverManager.getConnection(url, user, password);

    Statement s = c.createStatement();

    ResultSet rs = s.executeQuery("select * from Employee1");

    System.out.println("ID\tNAME\tJOB_ROLE\tHIRE_DATE\tSALARY");

    while (rs.next()) {

        System.out.println(
                rs.getInt("id") + "\t" +
                rs.getString("name") + "\t" +
                rs.getString("job_role") + "\t" +
                rs.getDate("hire_date") + "\t" +
                rs.getInt("salary"));
    }

    c.close();
}

public static void updateSalary() throws SQLException {

    Connection c = DriverManager.getConnection(url, user, password);

    PreparedStatement p =
            c.prepareStatement("update Employee1 set salary=? where id=?");

    System.out.println("Enter Employee Id");
    int id = sc.nextInt();

    System.out.println("Enter New Salary");
    int salary = sc.nextInt();

    p.setInt(1, salary);
    p.setInt(2, id);

    int rows = p.executeUpdate();

    if (rows > 0)
        System.out.println("Salary Updated Successfully");
    else
        System.out.println("Employee ID Not Found");

    c.close();
}

public static void deleteEmployee() throws SQLException {

    Connection c = DriverManager.getConnection(url, user, password);

    PreparedStatement p =
            c.prepareStatement("delete from Employee1 where id=?");

    System.out.println("Enter Employee Id");
    p.setInt(1, sc.nextInt());

    int rows = p.executeUpdate();

    if (rows > 0)
        System.out.println("Employee Deleted Successfully");
    else
        System.out.println("Employee ID Not Found");

    c.close();
}

public static void displayColumn() throws SQLException {

    Connection c = DriverManager.getConnection(url, user, password);

    System.out.println("Enter Column Name");
    String col = sc.next();

    Statement s = c.createStatement();

    ResultSet rs =
            s.executeQuery("select " + col + " from Employee1");

    while (rs.next()) {
        System.out.println(rs.getString(1));
    }

    c.close();
}

public static void main(String[] args) throws SQLException {

    while (true) {

        System.out.println("\n----- EMPLOYEE MENU -----");
        System.out.println("1. Add Employee");
        System.out.println("2. Display Employee Details");
        System.out.println("3. Update Salary");
        System.out.println("4. Delete Employee");
        System.out.println("5. Display Column Values");
        System.out.println("6. Exit");

        System.out.println("Enter Choice");

        int choice = sc.nextInt();

        switch (choice) {

            case 1:
                addEmployee();
                break;

            case 2:
                displayEmployees();
                break;

            case 3:
                updateSalary();
                break;

            case 4:
                deleteEmployee();
                break;

            case 5:
                displayColumn();
                break;

            case 6:
                System.out.println("Thank You");
                System.exit(0);

            default:
                System.out.println("Invalid Choice");
        }
    }
}
}
