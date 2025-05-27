import java.util.concurrent.*;
import java.util.*;

public class CallableExample {
    public static void main(String[] args) {
        // Create a fixed thread pool with 4 threads
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Create a list to hold Future objects
        List<Future<String>> futureList = new ArrayList<>();

        // Submit 5 callable tasks
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            Callable<String> task = () -> {
                Thread.sleep(1000); // Simulate some work
                return "Task " + taskId + " completed by " + Thread.currentThread().getName();
            };

            Future<String> future = executor.submit(task);
            futureList.add(future);
        }

        // Retrieve and print results
        for (Future<String> future : futureList) {
            try {
                String result = future.get(); // Blocks until the result is available
                System.out.println(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // Shut down the executor
        executor.shutdown();
    }
}