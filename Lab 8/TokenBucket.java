import java.util.*;
public class TokenBucket {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter bucket capacity for tokens: ");
        int size=sc.nextInt();

        System.out.print("Enter token generation rate: ");
        int rate=sc.nextInt();

        System.out.print("Enter the number of packets: ");
        int numPackets=sc.nextInt();

        System.out.print("Enter the size of the packets: ");
        int[] packets=new int[numPackets];

        for(int i=0;i<numPackets;i++)
            packets[i]=sc.nextInt();

        int tokens=0,sent=0;

        System.out.println("\nPacket Size\tTokens available\tSent\tTokens remaining\tStatus");

        for(int packet:packets){
            tokens=Math.min((tokens+rate), size);

            if(packet<=tokens){
                tokens-=packet;
                sent=packet;
                System.out.println("\t"+packet+"\t\t"+(tokens+packet)+"\t\t"+sent+"\t\t"+tokens+"\t\tAccepted");
            }else{
                System.out.println("\t"+packet+"\t\t"+tokens+"\t\t"+sent+"\t\t"+tokens+"\t\tDropped");
            }

            sent=0;
        }

        sc.close();
    }
}
