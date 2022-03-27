import java.io.BufferedReader;
import java.io.InputStreamReader;
import edu.princeton.cs.algs4.Graph;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split("\\s+");

        int n = Integer.parseInt(size[0]); // Nodes
        int m = Integer.parseInt(size[1]); // Edges

        // First create the the conflict graph.
        Graph conflictGraph = new Graph(n);
        Stack harmonyStack = new Stack();

        int i = 0;
        while (i < m) {
            String[] edgeLine = br.readLine().split("\\s+");
            // Splits the graph into two. First one is conflict, second is harmonious.
            int v = Integer.parseInt(edgeLine[0]); // endpoint 1
            int u = Integer.parseInt(edgeLine[1]); // endpoint 2
            int c = Integer.parseInt(edgeLine[2]); // 0 if harmony edge, 1 if conflict edge
            if (c > 1) {
                throw new Exception("c can only be 0 or 1");
            } else if (c == 1) {
                // Add all the edges to the conflict graph
                conflictGraph.addEdge(v, u);
                i++;
            } else {
                // Then push all harmony edges into a stack
                harmonyStack.push(v);
                harmonyStack.push(u);
                i++;
            }
        }

        Bipartite conflict = new Bipartite(conflictGraph);

        if (conflict.isBipartite() && harmonyBipartite(harmonyStack, conflict))
            System.out.println(1);
        else
            System.out.println(0);
    }

    // Function that checks if two colors are the same
    public static boolean harmonyBipartite(Stack S, Bipartite B) {
        boolean output = true;
        while (!S.isEmpty()) {
            // if it's not the case that the value of two nodes next to each other are the
            // same, then return 0. Else return 1.
            int firstNum = (int) S.pop();
            int secondNum = (int) S.pop();
            boolean firstNumColor = B.getColor(firstNum);
            boolean secondNumColor = B.getColor(secondNum);
            if (firstNumColor != secondNumColor) {
                return false;
            }
        }
        return output;

    }

}

// Conflict graph test - should evaluate to 0
// 3 3
// 0 1 1
// 1 2 1
// 2 0 1

// Conflict graph test - should evaluate to 1
// 4 4
// 0 1 1
// 1 2 1
// 2 3 1
// 3 0 1

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

// First create the the conflict graph
// Then push all harmony edges into a stack
// I then pop them two by two and check if they have the same color
// If that is not the case for even one, then break.