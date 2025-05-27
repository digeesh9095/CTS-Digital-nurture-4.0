public class TypeCastingExample {
    public static void main(String[] args) {
        // Declare a double variable with a decimal value
        double myDouble = 9.78;

        // Cast the double to int (explicit casting)
        int myInt = (int) myDouble;
        System.out.println("Double value: " + myDouble);
        System.out.println("Double cast to int: " + myInt);

        // Declare an int variable
        int anotherInt = 42;

        // Cast the int to double (implicit casting)
        double anotherDouble = anotherInt;
        System.out.println("Int value: " + anotherInt);
        System.out.println("Int cast to double: " + anotherDouble);
    }
}