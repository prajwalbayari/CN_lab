import java.util.*;

public class LeakyBucket {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter bucket capacity: ");
        int capacity=sc.nextInt();

        System.out.println("Enter output rate (packets per second): ");
        int outputRate=sc.nextInt();

        System.out.println("Enter the number of packets: ");
        int numPackets=sc.nextInt();

        int[] packetSizes=new int[numPackets];
        System.out.println("Enter the packet sizes: ");
        for(int i=0;i<numPackets;i++)
            packetSizes[i]=sc.nextInt();

        int curSize=0;

        System.out.println("\nPacket size\tBucket size\tSent\tRemaining\tStatus");

        for(int packetSize :packetSizes){
            if(packetSize+curSize<=capacity){
                curSize+=packetSize;
                System.out.println(packetSize+"\t\t"+curSize+"\t\t"+Math.min(outputRate, curSize)+"\t\t"+Math.max(0,curSize-outputRate)+"\t\tAccepted");
            }else{
                System.out.println(packetSize+"\t\t"+curSize+"\t\t"+Math.min(outputRate, curSize)+"\t\t"+Math.max(0,curSize-outputRate)+"\t\tDropped");
            }
            curSize=Math.max(0, curSize-outputRate);
        }
        sc.close();
    }
}
