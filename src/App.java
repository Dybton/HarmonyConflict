import java.io.BufferedReader;
import java.io.InputStreamReader;
import edu.princeton.cs.algs4.Graph;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split("\\s+");

        int n = Integer.parseInt(size[0]); // Nodes
        int m = Integer.parseInt(size[1]); // Edges

        Graph conflictGraph = new Graph(n);
        Graph harmonyGraph = new Graph(n);

        int i = 0;
        while (i < m) {
            String[] edgeLine = br.readLine().split("\\s+");
            // Splits the graph into two. First one is conflict, second is harmonious.
            // How do we split this?
            int v = Integer.parseInt(edgeLine[0]); // endpoint 1
            int u = Integer.parseInt(edgeLine[1]); // endpoint 2
            int c = Integer.parseInt(edgeLine[2]); // 0 if harmony edge, 1 if conflict edge
            if (c > 1) {
                throw new Exception("c can only be 0 or 1");
            } else if (c == 1) {
                conflictGraph.addEdge(v, u);
                i++;
            } else {
                harmonyGraph.addEdge(v, u);
                i++;
            }
        }

        Bipartite conflict = new Bipartite(conflictGraph);
        Bipartite harmony = new Bipartite(harmonyGraph);

        if (conflict.isBipartite())
            System.out.println(harmony.isBipartite());
    }

}

// Test (first graph is bipartite, second is not) evaluate to false

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

// First create the the conflict graph
// Then push all harmony edges into an array
// [1,2,3,4,5,6]
// I take them two by two and check if they have the same color
// If that is not the case for even one, then break.
