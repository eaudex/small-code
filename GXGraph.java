
import java.util.*;

// undirected graph
// adjacency-SET implementation
public class GXGraph {

	private int V;
	private int E;
	private Map<Integer,Set<Integer>> adjLists;

	public GXGraph(int v) {
		if(v<0) v=0;
		V = v;
		E = 0;
		adjLists = new HashMap<Integer,Set<Integer>>(v);
		for (int i=0; i<V; ++i)
			adjLists.put(i, new TreeSet<Integer>());
	}

	public boolean addEdge(int vid, int uid) {
		if (vid<0 || vid>=V || uid<0 || uid>=V)
			return false;

		if (adjLists.get(vid).contains(uid) && adjLists.get(uid).contains(vid))
			return false;

		adjLists.get(vid).add(uid);
		adjLists.get(uid).add(vid);
		E += 1;
		return true;
	}

	// number of nodes
	public int V() {
		return V;
	}
	// number of edges
	public int E() {
		return E;
	}

	// adjacent nodes of vid
	public Iterable<Integer> adj(int vid) {
		if (vid<0 || vid>=V)
			return new TreeSet<Integer>();
		return adjLists.get(vid);
	}

	// degree of vid
	public int degree(int vid) {
		return outDegree(vid);
	}
	// out-degree of vid
	public int outDegree(int vid) {
		if (vid<0 || vid>=V)
			return -1;
		return adjLists.get(vid).size();
	}
	// in-degree of vid
	public int inDegree(int vid) {
		return outDegree(vid);
	}

	public void print() {
		System.out.println("----");
		System.out.println("G<V,E> |V|=" + V + " |E|=" + E);
		for (int i=0; i<V; ++i) {
			Set<Integer> adjs = adjLists.get(i);
			System.out.println("Node " + i + "\t->\t" + adjs + "\t(deg=" + degree(i) + ")");
		}
	}

	// un-directed
	// no self-loop
	// no multi-edge
	public static GXGraph genRandomGraph(int v, double p) {
		GXGraph g = new GXGraph(v);
		if (v<=0 || p<=0.0)
			return g;

		for (int i=0; i<g.V(); ++i) {
			for (int j=i+1; j<g.V(); ++j) {
				if (Math.random() < p)
					g.addEdge(i, j);
			}
		}
		return g;
	}

	public static void main(String[] args) {
		GXGraph gx = new GXGraph(10);
		gx.print();

		GXGraph rndG = GXGraph.genRandomGraph(10, 0.3);
		rndG.print();
	}

}

