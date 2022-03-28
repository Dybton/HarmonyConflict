import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split("\\s+");

        int n = Integer.parseInt(size[0]); // Nodes
        int m = Integer.parseInt(size[1]); // Edges

        GraphX graph = new GraphX(n);

        int i = 0;
        while (i < m) {
            String[] edgeLine = br.readLine().split("\\s+");
            int v = Integer.parseInt(edgeLine[0]); // endpoint 1
            int u = Integer.parseInt(edgeLine[1]); // endpoint 2
            int c = Integer.parseInt(edgeLine[2]); // 0 if harmony edge, 1 if conflict edge
            if (c > 1) {
                throw new Exception("c can only be 0 or 1");
            } else {
                graph.addEdge(v, u, c);
                i++;
            }
        }
        Bipartite bipartite = new Bipartite(graph);
        bipartite.checkIfBipartite();
    }

}