import java.net.*;

public class Client {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(9875);

            System.out.println("Client is running and waiting for messages from the server...");

            while (true) {
                byte[] buffer = new byte[1024];

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                socket.receive(packet);

                String receivedMessage = new String(packet.getData(), 0, packet.getLength());

                if(receivedMessage.equals("Exit")){
                    System.out.println("Connection closed by server!");
                    break;
                }

                System.out.println("Message from server: " + receivedMessage+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
