import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket sock = new Socket("127.0.0.1", 4000);
        System.out.print("Enter the filename: ");

        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
        String filename = keyRead.readLine();

        OutputStream oStream = sock.getOutputStream();
        PrintWriter pWrite = new PrintWriter(oStream, true);
        pWrite.println(filename);

        InputStream iStream = sock.getInputStream();

        BufferedReader socketRead = new BufferedReader(new InputStreamReader(iStream));
        String str;

        while ((str = socketRead.readLine()) != null) {
            System.out.println(str);
        }
        socketRead.close();
        pWrite.close();
        sock.close();
    }
}
