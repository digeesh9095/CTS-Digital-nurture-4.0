class MessagePrinter implements Runnable {
    private String message;

    public MessagePrinter(String message) {
        this.message = message;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(message + " - " + i);
            try {
                Thread.sleep(500); // Sleep for 500ms to better observe interleaving
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
        }
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MessagePrinter("Thread 1 message"));
        Thread thread2 = new Thread(new MessagePrinter("Thread 2 message"));

        thread1.start();
        thread2.start();
    }
}