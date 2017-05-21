/**
 * Created by Krietallo on 5/21/2017.
 */
public class Main {


    public static void main (String[] args) {
        int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        Dijkstra t = new Dijkstra();
        for(int i = 0 ; i < t.V ;i++)
        {
            System.out.println("Vertex ID " + i);

            System.out.println("Node   Distance from Source");

            t.dijkstra_algorithm(graph, i);
            System.out.println();
            System.out.println();
        }




    }
}
