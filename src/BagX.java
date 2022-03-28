import edu.princeton.cs.algs4.Bag;

public class BagX {
    private final int V;
    private int E;
    private Bag<Integer>[] weightedEdges;

    public BagX(int V) {
        this.V = V;
        this.E = 0;
        weightedEdges = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            weightedEdges[v] = new Bag<Integer>();
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        weightedEdges[v].add(w);
        weightedEdges[w].add(v);
        E++;
    }

    public Iterable<Integer> weightedEdges(int v) {
        return weightedEdges[v];
    }

    public boolean inWeightedEdges(int toSearch, int toFind) {
        for (Integer integer : weightedEdges[toSearch]) {
            if (toFind == integer) {
                return true;
            }
        }
        return false;
    }

}