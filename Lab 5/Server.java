import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket servSock = new ServerSocket(4000);
        System.out.println("Server ready for connection!!");

        Socket sock = servSock.accept();
        System.out.println("Connection successfull and waiting for client request!");

        InputStream iStream = sock.getInputStream();
        BufferedReader filRead = new BufferedReader(new InputStreamReader(iStream));

        String filename = filRead.readLine();
        BufferedReader contentRead = new BufferedReader(new FileReader(filename));

        OutputStream oStream = sock.getOutputStream();
        PrintWriter pWrite = new PrintWriter(oStream, true);

        String str;
        while ((str = contentRead.readLine()) != null)
            pWrite.println(str);

        sock.close();
        servSock.close();
        pWrite.close();
        filRead.close();
        contentRead.close();
    }
}