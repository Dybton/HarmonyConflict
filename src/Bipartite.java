
import edu.princeton.cs.algs4.Queue;

public class Bipartite {
    private static final boolean WHITE = false;

    private boolean isBipartite = true; // is the graph bipartite?
    private boolean[] color; // color[v] gives vertices on one side of bipartition
    public boolean[] marked; // marked[v] = true iff v has been visited in DFS
    private Queue<Integer> cycle; // odd-length cycle

    /**
     * Determines whether an undirected graph is bipartite and finds either a
     * bipartition or an odd-length cycle.
     *
     * @param graph the graph
     */
    public Bipartite(GraphX graph) {
        isBipartite = true;
        color = new boolean[graph.V()];
        marked = new boolean[graph.V()];

        for (int v = 0; v < graph.V() && isBipartite; v++) {
            if (!marked[v]) {
                bfs(graph, v);
            }
        }
    }

    private void bfs(GraphX G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        color[s] = WHITE;
        marked[s] = true;
        q.enqueue(s);

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    if (G.isHarmonyEdge(v, w) == true)
                        color[w] = color[v];
                    if (G.isHarmonyEdge(v, w) == false)
                        color[w] = !color[v];
                    marked[w] = true;
                    q.enqueue(w);
                } else if ((G.isHarmonyEdge(v, w) == false) && color[w] == color[v]
                        || (G.isHarmonyEdge(v, w) == true) && color[w] != color[v]) {
                    isBipartite = false;
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

    public void checkIfBipartite() {
        if (isBipartite == false)
            System.out.println(0);
        else
            System.out.println(1);
    }
}