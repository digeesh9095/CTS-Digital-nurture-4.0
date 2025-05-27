import java.time.Duration;
import java.time.Instant;

public class VirtualThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        int totalThreads = 100_000;

        System.out.println("Launching " + totalThreads + " virtual threads...");
        Instant startVirtual = Instant.now();

        Thread[] virtualThreads = new Thread[totalThreads];
        for (int i = 0; i < totalThreads; i++) {
            virtualThreads[i] = Thread.startVirtualThread(() -> {
                System.out.println("Hello from virtual thread: " + Thread.currentThread());
            });
        }

        for (Thread t : virtualThreads) {
            t.join();
        }

        Instant endVirtual = Instant.now();
        System.out.println("Virtual threads completed in: " + Duration.between(startVirtual, endVirtual).toMillis() + " ms");


        // Optional: compare with platform threads (WARNING: heavy on system resources)
        System.out.println("\nLaunching " + totalThreads + " platform threads...");
        Instant startPlatform = Instant.now();

        Thread[] platformThreads = new Thread[totalThreads];
        for (int i = 0; i < totalThreads; i++) {
            platformThreads[i] = new Thread(() -> {
                System.out.println("Hello from platform thread: " + Thread.currentThread());
            });
            platformThreads[i].start();
        }

        for (Thread t : platformThreads) {
            t.join();
        }

        Instant endPlatform = Instant.now();
        System.out.println("Platform threads completed in: " + Duration.between(startPlatform, endPlatform).toMillis() + " ms");
    }
}