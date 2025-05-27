import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSingleProgram {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:students.db";

        try {
            // Load the SQLite JDBC driver (not always necessary with newer JDBC)
            Class.forName("org.sqlite.JDBC");

            try (Connection conn = DriverManager.getConnection(url);
                 Statement stmt = conn.createStatement()) {

                // Create students table if it doesn't exist
                String createTableSQL = """
                    CREATE TABLE IF NOT EXISTS students (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        age INTEGER
                    );
                """;
                stmt.execute(createTableSQL);

                // Insert sample data
                String insertDataSQL = """
                    INSERT INTO students (name, age) VALUES
                    ('Alice', 22),
                    ('Bob', 19),
                    ('Charlie', 20)
                    ON CONFLICT DO NOTHING;
                """;
              
                stmt.executeUpdate("DELETE FROM students"); 
                stmt.executeUpdate("INSERT INTO students (name, age) VALUES ('Alice', 22)");
                stmt.executeUpdate("INSERT INTO students (name, age) VALUES ('Bob', 19)");
                stmt.executeUpdate("INSERT INTO students (name, age) VALUES ('Charlie', 20)");

                // Query the table
                ResultSet rs = stmt.executeQuery("SELECT id, name, age FROM students");

                // Display results
                System.out.println("ID | Name    | Age");
                System.out.println("-------------------");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    System.out.printf("%2d | %-7s | %3d%n", id, name, age);
                }

                rs.close();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database error.");
            e.printStackTrace();
        }
    }
}