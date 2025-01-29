import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            // Create a server socket at port 9876
            serverSocket = new ServerSocket(9876);
            System.out.println("Server is running and waiting for connections...");

            while (true) {
                // Accept client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                // Create input and output streams
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read the file name from the client
                String fileName = in.readLine();
                System.out.println("Client requested file: " + fileName);

                // Check if the file exists
                File file = new File(fileName);
                if (file.exists() && file.isFile()) {
                    // Read the file contents
                    BufferedReader fileReader = new BufferedReader(new FileReader(file));
                    String line;

                    // Send file contents line by line
                    while ((line = fileReader.readLine()) != null) {
                        out.println(line);
                    }

                    fileReader.close();
                    System.out.println("File sent to client.");
                } else {
                    // File not found
                    out.println("Error: File not found.");
                    System.out.println("Requested file not found.");
                }

                // Close client connection
                clientSocket.close();
                System.out.println("Client connection closed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
