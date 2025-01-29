import java.util.*;

public class CRC {

    public static String divide(char[] dividend, char[] divisor) {
        int n = dividend.length, m = divisor.length;
        for (int i = 0; i < n; i++) {
            if (dividend[i] == '1') {
                if (n - i == m)
                    break;

                for (int j = 0; j < m; j++) {
                    dividend[i + j] = divisor[j] == '0' ? dividend[i + j] : dividend[i + j] == '1' ? '0' : '1';
                }
            }
        }
        return new String(dividend).substring(n-m+1);
    }

    public static String encode(String key, String data) {
        return data+divide((data+"0".repeat(key.length()-1)).toCharArray(),key.toCharArray());
    }

    public static boolean decode(String key, String encodedData) {
        return divide(encodedData.toCharArray(), key.toCharArray()).contains("1");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the binary key: ");
        String key = sc.next();

        System.out.println("Enter the binary data: ");
        String data = sc.next();

        System.out.println("Encoded data: " + encode(key, data));

        System.out.println("Enter the binary encoded data: ");
        String encodedData = sc.next();

        if (decode(key, encodedData))
            System.out.println("There is some error in the data!!");
        else
            System.out.println("The data is error free!!");

        sc.close();
    }
}