import java.util.*;

public class RED {
    
    public static class RandomEarlyDetection{
        private double minThreshold,maxDP,maxThreshold;
        private int queueSize,currentQueue;

        public RandomEarlyDetection(double minThr,double maxThr,double maxDP,int qs){
            this.minThreshold=minThr;
            this.maxThreshold=maxThr;
            this.maxDP=maxDP;
            this.queueSize=qs;
            this.currentQueue=0;
        }

        public boolean enqueuePacket(){
            if(currentQueue>=queueSize){
                System.out.println("Packet dropped(Queue full)");
                return false;
            }
            double drop=calculateDropProbability();
            if(drop>=0 && shouldDrop(drop)){
                System.out.println("Packet dropped (RED)");
                return false;
            }
            currentQueue++;
            System.out.println("Packet enqueued current queue size: "+currentQueue);
            return true;
        }

        private double calculateDropProbability(){
            if(currentQueue<minThreshold)
                return 0.0;
            if(currentQueue>=maxThreshold)
                return 1.0;
            return maxDP*((currentQueue-minThreshold)/(maxThreshold-minThreshold));
        }

        private boolean shouldDrop(double p){
            Random r=new Random();
            return r.nextDouble()<p;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the minimum threshold: ");
        double minThreshold=sc.nextDouble();
        System.out.println("Enter the maximum threshold: ");
        double maxThreshold=sc.nextDouble();
        System.out.println("Enter the maximum drop probability: ");
        double maxDP=sc.nextDouble();
        System.out.println("Enter the queue size:");
        int qs=sc.nextInt();

        RandomEarlyDetection red=new RandomEarlyDetection(minThreshold, maxThreshold, maxDP, qs);

        System.out.println("Enter the number of packets: ");
        int n=sc.nextInt();

        for(int i=0;i<n;i++)
            red.enqueuePacket();

        sc.close();
    }
}
