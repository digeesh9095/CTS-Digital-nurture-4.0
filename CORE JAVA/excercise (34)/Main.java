// File: src/com/example/Main.java
package com.example;

public class Main {
    public static void main(String[] args) {
        System.out.println(StringUtils.greet("Java Single Module"));
    }
}

// File: src/com/example/StringUtils.java
package com.example;

public class StringUtils {
    public static String greet(String name) {
        return "Hello, " + name + "!";
    }
}