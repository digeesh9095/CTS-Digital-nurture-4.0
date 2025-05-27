import java.sql.*;

public class JdbcTransactionExample {
    private static final String URL = "jdbc:sqlite:bank.db";

    public static void createAccountsTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS accounts (
                id INTEGER PRIMARY KEY,
                name TEXT NOT NULL,
                balance DOUBLE NOT NULL
            );
        """;

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);

            // Insert initial data if table is empty
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM accounts");
            if (rs.next() && rs.getInt(1) == 0) {
                stmt.executeUpdate("INSERT INTO accounts (id, name, balance) VALUES (1, 'Alice', 1000.00)");
                stmt.executeUpdate("INSERT INTO accounts (id, name, balance) VALUES (2, 'Bob', 500.00)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void transferMoney(int fromAccountId, int toAccountId, double amount) {
        String debitSQL = "UPDATE accounts SET balance = balance - ? WHERE id = ? AND balance >= ?";
        String creditSQL = "UPDATE accounts SET balance = balance + ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement debitStmt = conn.prepareStatement(debitSQL);
             PreparedStatement creditStmt = conn.prepareStatement(creditSQL)) {

            // Begin transaction
            conn.setAutoCommit(false);

            // Debit money from source account
            debitStmt.setDouble(1, amount);
            debitStmt.setInt(2, fromAccountId);
            debitStmt.setDouble(3, amount);
            int rowsDebited = debitStmt.executeUpdate();

            if (rowsDebited != 1) {
                throw new SQLException("Insufficient funds or source account not found.");
            }

            // Credit money to destination account
            creditStmt.setDouble(1, amount);
            creditStmt.setInt(2, toAccountId);
            int rowsCredited = creditStmt.executeUpdate();

            if (rowsCredited != 1) {
                throw new SQLException("Destination account not found.");
            }

            // Commit transaction
            conn.commit();
            System.out.println("Transfer successful: " + amount + " from account " + fromAccountId + " to account " + toAccountId);

        } catch (SQLException e) {
            System.out.println("Transfer failed: " + e.getMessage());
            try {
                // Rollback transaction on failure
                Connection conn = DriverManager.getConnection(URL);
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
        }
    }

    public static void displayBalances() {
        String sql = "SELECT id, name, balance FROM accounts";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Account balances:");
            System.out.println("ID | Name  | Balance");
            System.out.println("-----------------------");
            while (rs.next()) {
                System.out.printf("%2d | %-5s | %.2f%n", rs.getInt("id"), rs.getString("name"), rs.getDouble("balance"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createAccountsTable();
        System.out.println("Before transfer:");
        displayBalances();

        transferMoney(1, 2, 200.00);

        System.out.println("\nAfter transfer:");
        displayBalances();
    }
}