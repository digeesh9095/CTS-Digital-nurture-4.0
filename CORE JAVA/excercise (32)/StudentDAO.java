import java.sql.*;

public class StudentDAO {
    private final String url = "jdbc:sqlite:students.db";

    // Create table if it doesn't exist (optional setup method)
    public void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS students (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                age INTEGER
            );
        """;

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert a new student record
    public void insertStudent(String name, int age) {
        String sql = "INSERT INTO students(name, age) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            int rows = pstmt.executeUpdate();

            System.out.println(rows + " student(s) inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing student's age by id
    public void updateStudentAge(int id, int newAge) {
        String sql = "UPDATE students SET age = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newAge);
            pstmt.setInt(2, id);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Student ID " + id + " updated successfully.");
            } else {
                System.out.println("Student ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Optional: method to display all students
    public void displayAllStudents() {
        String sql = "SELECT id, name, age FROM students";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("ID | Name    | Age");
            System.out.println("-------------------");
            while (rs.next()) {
                System.out.printf("%2d | %-7s | %3d%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method for quick test
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        dao.createTableIfNotExists();

        // Insert students
        dao.insertStudent("Alice", 22);
        dao.insertStudent("Bob", 19);

        // Display current students
        dao.displayAllStudents();

        // Update student age
        dao.updateStudentAge(1, 23);

        // Display after update
        dao.displayAllStudents();
    }
}