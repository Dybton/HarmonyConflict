import java.io.BufferedReader;
import java.io.InputStreamReader;

import edu.princeton.cs.algs4.Graph;

public class App {
    public static void main(String[] args) throws Exception {
        boolean debug = true;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split("\\s+");

        int n = Integer.parseInt(size[0]); // Nodes
        int m = Integer.parseInt(size[1]); // Edges

        GraphX graph = new GraphX(n);
        Bagx bagx = new Bagx(n);

        int i = 0;
        while (i < m) {
            String[] edgeLine = br.readLine().split("\\s+");
            int v = Integer.parseInt(edgeLine[0]); // endpoint 1
            int u = Integer.parseInt(edgeLine[1]); // endpoint 2
            int c = Integer.parseInt(edgeLine[2]); // 0 if harmony edge, 1 if conflict edge
            if (c > 1) {
                throw new Exception("c can only be 0 or 1");
            } else {
                graph.addEdge(v, u);
                if (c == 0) {
                    bagx.addEdge(v, u);
                }
                i++;
            }
        }
        Bipartite bipartite = new Bipartite(graph, bagx);

        if (debug) {
            System.out.println("isBipartite " + bipartite.isBipartite());
        }

        if (debug) {
            System.out.println("inWeightedEdges " + bagx.inWeightedEdges(2, 3));
        }
    }

}

// Test (first graph is bipartite, second is not) evaluate to false

// 8 7
// 0 1 0
// 1 2 0
// 2 3 0
// 2 4 0
// 2 5 0
// 2 6 0
// 2 7 0

// 7 7
// 0 1 1
// 1 2 1
// 2 3 1
// 3 0 1
// 4 5 0
// 5 6 0
// 6 4 0

// Test both conflict and harmony is bipartite - should evaluate to true
// 8 8
// 0 1 1
// 1 2 1
// 2 3 1
// 3 0 1
// 4 5 0
// 5 6 0
// 6 7 0
// 7 4 0

// 7 7
// 0 1 1
// 1 2 1
// 2 3 0
// 3 0 1
// 4 5 0
// 5 6 0
// 6 4 0