import java.util.List;
import java.util.stream.Collectors;

// Define the record
record Person(String name, int age) {}

public class RecordExample {
    public static void main(String[] args) {
        // Create a list of Person records
        List<Person> people = List.of(
            new Person("Alice", 22),
            new Person("Bob", 17),
            new Person("Charlie", 19),
            new Person("Diana", 15)
        );

        // Print all people
        System.out.println("All people:");
        people.forEach(System.out::println);

        // Filter people age 18 or older
        List<Person> adults = people.stream()
                                    .filter(p -> p.age() >= 18)
                                    .collect(Collectors.toList());

        // Display filtered list
        System.out.println("\nAdults (age 18+):");
        adults.forEach(System.out::println);
    }
}