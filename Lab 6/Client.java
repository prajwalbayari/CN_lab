import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws Exception {
        BufferedReader inFromUser=new BufferedReader(new InputStreamReader(System.in));

        DatagramSocket clientSock=new DatagramSocket();
        InetAddress IPAddress=InetAddress.getByName("localhost");
        byte[] sendData=new byte[1024];
        byte[] recieveData=new byte[1024];

        System.out.println("Enter the string in lowercase so that you recieve message in uppercase from the server: ");
        String sentence=inFromUser.readLine();
        sendData=sentence.getBytes();

        DatagramPacket sendPacket=new DatagramPacket(sendData, sendData.length,IPAddress,9876);
        clientSock.send(sendPacket);

        DatagramPacket recievPacket=new DatagramPacket(recieveData, recieveData.length);
        clientSock.receive(recievPacket);

        String result=new String(recievPacket.getData());
        System.out.println("FROM SERVER: "+result);
        clientSock.close();
    }
}
