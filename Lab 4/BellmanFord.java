import java.util.*;

public class BellmanFord {
    private static int n, graph[][];

    public static void bellmanFord(int src) {
        int dist[] = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0;
        for (int k = 0; k < n ; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] != 0 && dist[i] != Integer.MAX_VALUE && dist[i] + graph[i][j] < dist[j])
                        dist[j] = dist[i] + graph[i][j];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] != 0 && dist[i] != Integer.MAX_VALUE && dist[i] + graph[i][j] < dist[j]) {
                    System.out.println("Negative weight cycle detected!!\n");
                    return;
                }
            }
        }

        printSolution(dist);
    }

    public static void printSolution(int[] dist) {
        System.out.println("Vertex\t Distance from source");
        for (int i = 0; i < n; i++)
            System.out.println(i + "\t" + ((dist[i]) == Integer.MAX_VALUE ? "Infinity" : dist[i]));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of vertices: ");
        n = sc.nextInt();

        System.out.println("Enter the weight matrix of the graph (use 0 for no edges): ");
        graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter the source vertex: ");
        int src = sc.nextInt();

        bellmanFord(src);

        sc.close();
    }
}
