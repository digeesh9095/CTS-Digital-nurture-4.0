import java.util.HashMap;
import java.util.Scanner;

public class StudentMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> studentMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        // Add entries
        System.out.println("Enter student ID and name. Type -1 as ID to stop.");

        while (true) {
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (id == -1) {
                break;
            }

            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            studentMap.put(id, name);
        }

        // Retrieve entry
        System.out.print("\nEnter an ID to search for a student name: ");
        int searchId = scanner.nextInt();

        if (studentMap.containsKey(searchId)) {
            System.out.println("Student name: " + studentMap.get(searchId));
        } else {
            System.out.println("No student found with ID " + searchId);
        }

        scanner.close();
    }
}