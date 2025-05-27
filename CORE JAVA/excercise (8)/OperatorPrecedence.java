public class OperatorPrecedence {
    public static void main(String[] args) {
        // Expression combining multiple operators
        int result1 = 10 + 5 * 2;
        int result2 = (10 + 5) * 2;
        int result3 = 20 - 4 / 2 + 3;

        // Display the results
        System.out.println("Result 1 (10 + 5 * 2): " + result1); // 10 + (5 * 2) = 20
        System.out.println("Result 2 ((10 + 5) * 2): " + result2); // (10 + 5) * 2 = 30
        System.out.println("Result 3 (20 - 4 / 2 + 3): " + result3); // 20 - (4 / 2) + 3 = 21

        // Explanation
        System.out.println("\nExplanation:");
        System.out.println("- Multiplication (*) and division (/) have higher precedence than addition (+) and subtraction (-).");
        System.out.println("- Parentheses () are used to override the default precedence.");
    }
}