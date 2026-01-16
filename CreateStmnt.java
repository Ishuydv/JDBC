import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateStmnt {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/college";
        String username = "root";
        String password = "1234";
        String query = "insert into students (id,full_name,age) values (5,'Atul',22)";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement stmt = connection.createStatement();
            int rowsAffected = stmt.executeUpdate(query);

            if (rowsAffected > 0) {
                System.out.println("Insert successfull " + rowsAffected + " rows affected");
            }

            System.out.println("Connected to database successfully");
            stmt.close();
            connection.close();
            System.out.println("Connection Closed Successfully");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
