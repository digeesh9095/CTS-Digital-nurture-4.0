import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ReflectionDemo {
    public static void main(String[] args) {
        try {
            // Load the class dynamically
            Class<?> clazz = Class.forName("TargetClass");

            // Print all declared methods and their parameters
            System.out.println("Methods in " + clazz.getName() + ":");
            for (Method method : clazz.getDeclaredMethods()) {
                System.out.print("Method: " + method.getName() + "(");
                Parameter[] parameters = method.getParameters();
                for (int i = 0; i < parameters.length; i++) {
                    System.out.print(parameters[i].getType().getSimpleName() + " " + parameters[i].getName());
                    if (i < parameters.length - 1) System.out.print(", ");
                }
                System.out.println(")");
            }

            // Create an instance of the class
            Object obj = clazz.getDeclaredConstructor().newInstance();

            // Invoke a method by name
            Method sayHelloMethod = clazz.getDeclaredMethod("sayHello", String.class);
            sayHelloMethod.invoke(obj, "Reflection World");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// Target class to reflect upon
class TargetClass {
    public void sayHello(String name) {
        System.out.println("Hello, " + name + "!");
    }

    public int add(int a, int b) {
        return a + b;
    }
}