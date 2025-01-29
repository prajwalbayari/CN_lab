import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket servSock=new DatagramSocket(9876);
        System.out.println("Server is ready for the connection!");

        byte[] recieveData=new byte[1024];
        byte[] sendData=new byte[1024];

        DatagramPacket recievePacket=new DatagramPacket(recieveData, recieveData.length);
        servSock.receive(recievePacket);

        String sentence=new String(recievePacket.getData());
        System.out.println("RECIEVED: "+sentence);

        InetAddress IPAddress=recievePacket.getAddress();
        int port=recievePacket.getPort();

        String capitallizeSentence=sentence.toUpperCase();

        sendData=capitallizeSentence.getBytes();
        DatagramPacket sendPacket=new DatagramPacket(sendData, sendData.length,IPAddress,port);
        servSock.send(sendPacket);

        servSock.close();
    }
}
