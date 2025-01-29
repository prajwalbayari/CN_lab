import java.util.*;
import java.math.*;

public class RSA {
    private BigInteger n, e, d;

    public RSA(int bitlength) {
        Random r = new Random();
        BigInteger p = BigInteger.probablePrime(bitlength / 2, r);
        BigInteger q = BigInteger.probablePrime(bitlength / 2, r);
        n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));
        e = BigInteger.probablePrime(bitlength / 2, r);

        while (!phi.gcd(e).equals(BigInteger.ONE) || e.compareTo(phi) >= 0)
            e = BigInteger.probablePrime(bitlength / 2, r);

        d = e.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger ciphertext) {
        return ciphertext.modPow(d, n);
    }

    public static void main(String[] args) {
        int bitLength = 512;

        RSA rsa = new RSA(bitLength);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter message: ");
        BigInteger message = new BigInteger(sc.nextLine().getBytes());

        BigInteger ciphertext = rsa.encrypt(message);
        System.out.print("Encrypted message is: " + ciphertext.toString());

        System.out.println("Enter the ciphertext: ");
        String cipher = sc.nextLine();

        if (!cipher.equals(ciphertext.toString())) {
            System.out.println("Wrong ciphertext");
        } else {
            BigInteger decryptedMessage = rsa.decrypt(new BigInteger(cipher));
            System.out.println("\nDecrypted message is: " + new String(decryptedMessage.toByteArray()));
        }
        sc.close();
    }
}
