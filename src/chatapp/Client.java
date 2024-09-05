package chatapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        System.out.println("I am the client, I will send a message.");

        try (Socket socket = new Socket("localhost", 9806); 
             // Create a socket and connect to the server at localhost on port 9806
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             // BufferedReader to read the user's input from the console
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             // PrintWriter to send data to the server via the socket's output stream
             // true enables auto-flush, ensuring the message is sent immediately
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
             // BufferedReader to receive data from the server via the socket's input stream

            System.out.println("Send a message:");
            String userMessage = userInput.readLine();
            // Read a message from the user's input

            out.println(userMessage);
            // Send the user's message to the server

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
                // Print the server's response to the console
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
            // Handle the case where the server host cannot be determined
        } catch (IOException e) {
            e.printStackTrace();
            // Handle any I/O errors, such as connection issues
        }
    }
}
