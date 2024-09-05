package chatapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        System.out.println("I am the server, I am waiting...");

        try (ServerSocket serverSocket = new ServerSocket(9806)) {
            Socket clientSocket = serverSocket.accept(); // Wait for a client to connect
            System.out.println("Client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                // Echo the message back to the client
                out.println("Server: " + inputLine);
            }

            System.out.println("All done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
