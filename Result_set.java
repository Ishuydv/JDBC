import java.sql.*;

public class Result_set {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/companydb";
        String username = "root";
        String password = "1234";
        String query = "select * from employees;";

//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            System.out.println("+----+------------+----------------------+-------------+--------+");
            System.out.printf("| %-2s | %-10s | %-20s | %-11s | %-6s |\n", "ID", "Name", "Email", "Department", "Salary");
            System.out.println("+----+------------+----------------------+-------------+--------+");
            while (rs.next()) {

                // inside ResultSet loop
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String dpt = rs.getString("department");
                int salary = rs.getInt("salary");

                System.out.printf("| %-2d | %-10s | %-20s | %-11s | %-6d |\n", id, name, email, dpt, salary);
            }
            // after loop ends
            System.out.println("+----+------------+----------------------+-------------+--------+");

            rs.close();
            statement.close();
            connection.close();
            System.out.println("Connection closed successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
