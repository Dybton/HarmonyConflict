import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class Bipartite {
    private static final boolean WHITE = false;
    private static final boolean BLACK = true;

    private boolean isBipartite; // is the graph bipartite?
    private boolean[] color; // color[v] gives vertices on one side of bipartition
    public boolean[] marked; // marked[v] = true iff v has been visited in DFS
    private Queue<Integer> cycle; // odd-length cycle

    /**
     * Determines whether an undirected graph is bipartite and finds either a
     * bipartition or an odd-length cycle.
     *
     * @param G the graph
     */
    public Bipartite(GraphX G, Bagx B) {
        isBipartite = true;
        color = new boolean[G.V()];
        marked = new boolean[G.V()];

        for (int v = 0; v < G.V() && isBipartite; v++) {
            if (!marked[v]) {
                bfs(G, B, v);
            }
        }
        assert check(G);
    }

    private void bfs(GraphX G, Bagx B, int s) {
        Queue<Integer> q = new Queue<Integer>();
        color[s] = WHITE;
        marked[s] = true;
        q.enqueue(s);

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    color[w] = !color[v];
                    q.enqueue(w);
                }
                if (color[w] == color[v]) {
                    System.out.println(
                            "it is " + B.inWeightedEdges(v, w) + " that " + v + " " + w + " are the same color");
                    {
                        isBipartite = false;
                    }
                    // Here we need to call BagX to see if the
                }
            }
        }
    }

    /**
     * Returns true if the graph is bipartite.
     *
     * @return {@code true} if the graph is bipartite; {@code false} otherwise
     */
    public boolean isBipartite() {
        return isBipartite;
    }

    /**
     * Returns an odd-length cycle if the graph is not bipartite, and
     * {@code null} otherwise.
     *
     * @return an odd-length cycle if the graph is not bipartite
     *         (and hence has an odd-length cycle), and {@code null}
     *         otherwise
     */
    public Iterable<Integer> oddCycle() {
        return cycle;
    }

    private boolean check(GraphX G) {
        // graph is bipartite
        if (isBipartite) {
            for (int v = 0; v < G.V(); v++) {
                for (int w : G.adj(v)) {
                    if (color[v] == color[w]) {
                        System.err.printf("edge %d-%d with %d and %d in same side of bipartition\n", v, w, v, w);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean[] getColor() {
        return color;
    }

}
