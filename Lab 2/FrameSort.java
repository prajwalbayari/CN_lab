import java.util.*;

public class FrameSort{
    static class Frame{
        int FrameNumber;
        String data;

        Frame(int num,String data){
            this.FrameNumber=num;
            this.data=data;
        }
    }

    public static void sort(List<Frame> frames){
        for(int i=0;i<frames.size()-1;i++){
            for(int j=0;j<frames.size()-i-1;j++){
                if(frames.get(j).FrameNumber>frames.get(j+1).FrameNumber){
                    Frame temp=frames.get(j);
                    frames.set(j,frames.get(j+1));
                    frames.set(j+1,temp);
                }
            }
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the total number of frames: ");
        int n=sc.nextInt();
        List<Frame> frames=new ArrayList<>();
        System.out.println("Enter the content of each packet");
        for(int i=0;i<n;i++){
            String data=sc.next();
            frames.add(new Frame(i+1,data));
        }

        Collections.shuffle(frames);

        System.out.println("Frame's order during arrival at destination:");
        for(int i=0;i<n;i++)
            System.out.print(frames.get(i).data+" ");
        System.out.println("/n");

        sort(frames);
            
        System.out.println("Frame's order after sorting: ");
        for(int i=0;i<n;i++)
            System.out.print(frames.get(i).data+" ");
        System.out.println("/n");
        
        sc.close();
    }
}