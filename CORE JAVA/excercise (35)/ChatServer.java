import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        final int PORT = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            Thread receive = new Thread(() -> {
                String msg;
                try {
                    while ((msg = in.readLine()) != null) {
                        System.out.println("Client: " + msg);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                }
            });

            Thread send = new Thread(() -> {
                String msg;
                try {
                    while ((msg = userInput.readLine()) != null) {
                        out.println(msg);
                    }
                } catch (IOException e) {
                    System.out.println("Error sending message.");
                }
            });

            receive.start();
            send.start();

            receive.join();
            send.join();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}