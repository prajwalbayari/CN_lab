import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        Scanner scanner = new Scanner(System.in);

        try {
            socket = new DatagramSocket(9876);

            System.out.println("Server is running. Type messages to send to the client(Type \"Exit\" to close the server):");

            while (true) {
                System.out.print("Enter message: ");
                String message = scanner.nextLine();

                byte[] buffer = message.getBytes();

                InetAddress clientAddress = InetAddress.getByName("localhost");
                int clientPort = 9875;

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);

                socket.send(packet);

                if(message.equals("Exit")){
                    System.out.println("Closing the server");
                    break;
                }

                System.out.println("Message sent to the client.\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            scanner.close();
        }
    }
}
