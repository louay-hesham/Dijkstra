public class Dijkstra {
    static int V ;

    public static String doDijkstra (int[][] graph) {
        V = graph.length;
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < V ;i++)
        {
            sb.append("Vertex ID ").append(i).append("\n");
            sb.append("Node   Distance from Source\n");
            dijkstra_algorithm(graph, i, sb);
            sb.append("\n\n");
        }
        return sb.toString();
    }

    private static int minDistance(int dist[], Boolean sptSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    private static void printSolution(int dist[], StringBuilder sb) {

        for (int i = 0; i < V; i++)
            sb.append(i).append(" \t\t ").append(dist[i]).append("\n");
    }

    private static void dijkstra_algorithm(int graph[][], int src, StringBuilder sb) {
        int dist[] = new int[V];

        Boolean sptSet[] = new Boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {

            int u = minDistance(dist, sptSet);

            sptSet[u] = true;


            for (int v = 0; v < V; v++)

                if (!sptSet[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        printSolution(dist, sb);
    }
}