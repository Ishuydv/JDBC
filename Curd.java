import java.sql.*;
import java.util.Scanner;

public class Curd {

    static void updateData(Statement statement, Scanner sc) throws SQLException {
        System.out.print("Enter id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        String query = "update students set age = " + age + " where id = " + id + ";";
        int rowsAffected = statement.executeUpdate(query);
        System.out.println(rowsAffected + " rows affected");
    }

    static void showData(Statement statement, Scanner sc) throws SQLException {
        System.out.print("Enter id: ");
        int id = sc.nextInt();
        String query = "SELECT * FROM students WHERE id = " + id;
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {   // Move cursor to first row
            id = rs.getInt("id");
            String name = rs.getString("full_name");
            int age = rs.getInt("age");
            System.out.printf("ID: %d | Name: %s | Age: %d%n", id, name, age);
        } else {
            System.out.println("No student found with ID: " + id);
        }
    }

    static void deleteRow(Statement statement, Scanner sc) throws SQLException {
        System.out.print("Enter Id :");
        int id = sc.nextInt();
        String query = "delete from students where id = " + id + ";";
        int rowsAffected = statement.executeUpdate(query);
        if (rowsAffected > 0) {
            System.out.println(rowsAffected + " rows effected.");
            System.out.println("Row deleted successfully...");
        } else {
            System.out.println("No record found with given ID.");
        }
    }

    static void addRow(Statement statement, Scanner sc) throws SQLException {
        System.out.print("Enter Id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        String query = "insert into students (id,full_name,age) values (" + id + "," + "'" + name + "'" + "," + age + ");";
        int rowsAffected = statement.executeUpdate(query);
        if (rowsAffected > 0) System.out.println(rowsAffected + " rows affected.");
        System.out.println("Insertion successful.");
    }

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/college";
        String username = "root";
        String password = "1234";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement stmt = connection.createStatement();
            Scanner sc = new Scanner(System.in);
            int choice;

            do {
                System.out.println("""
                        Enter 1 to add row
                              2 to delete row
                              3 to show data
                              4 to update
                              0 to exit
                        """);
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addRow(stmt, sc);
                        break;
                    case 2:
                        deleteRow(stmt, sc);
                        break;
                    case 3:
                        showData(stmt, sc);
                        break;
                    case 4:
                        updateData(stmt, sc);
                        break;
                    case 0:
                        System.out.println("Exiting");
                        break;
                    default:
                        System.out.println("Invalid input");
                }
                System.out.println("------------------------------------------------------------------------");
                System.out.println();

            } while (choice != 0);

            sc.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
