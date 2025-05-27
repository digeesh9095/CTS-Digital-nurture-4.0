public class BytecodeExample {
    public static void main(String[] args) {
        int x = 7;
        int y = 3;
        int result = multiply(x, y);
        System.out.println("Result: " + result);
    }

    public static int multiply(int a, int b) {
        return a * b;
    }
}