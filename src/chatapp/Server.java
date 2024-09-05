package chatapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        System.out.println("I am the server, I am waiting...");

        try (ServerSocket serverSocket = new ServerSocket(9806)) // First, create a server socket with port 9806
        {
            Socket clientSocket = serverSocket.accept(); // Create a socket and wait for a client to connect
            System.out.println("Client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
            // BufferedReader for reading a string from the input stream
            // InputStreamReader to convert bytes to characters
            // getInputStream retrieves the input stream from the socket to read incoming data from the client

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            // PrintWriter for sending data to the client in a character-based output stream
            // getOutputStream retrieves the output stream from the socket to send data to the client
            // true enables auto-flush after each print/write operation, ensuring data is sent immediately
            //auto-flush used for :
            //Immediate Feedback: In network applications like a chat server, enabling auto-flush ensures that messages are sent and received without delay, providing immediate feedback to the user.
           // Prevents Data Loss: If the program crashes or the socket is closed unexpectedly, auto-flush ensures that all data in the buffer is sent before the program terminates, reducing the risk of data loss.
            String inputLine;
            // While loop continues to read messages from the client until the client disconnects or the stream is closed
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
