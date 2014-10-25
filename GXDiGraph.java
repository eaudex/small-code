
import java.util.*;

// undirected graph
// adjacency-SET implementation
public class GXDiGraph {

	private int V;
	private int E;
	private Map<Integer,Set<Integer>> adjLists;
	private Map<Integer,Integer> inDegrees;

	public GXDiGraph(int v) {
		if(v<0) v=0;
		V = v;
		E = 0;
		adjLists = new HashMap<Integer,Set<Integer>>(v);
		for (int i=0; i<V; ++i)
			adjLists.put(i, new TreeSet<Integer>());

		inDegrees = new HashMap<Integer,Integer>(v);
	}

	public boolean addEdge(int vid, int uid) {
		if (vid<0 || vid>=V || uid<0 || uid>=V)
			return false;

		if (adjLists.get(vid).contains(uid))
			return false;

		adjLists.get(vid).add(uid);
		if (inDegrees.containsKey(uid))
			inDegrees.put(uid, inDegrees.get(uid)+1);
		else
			inDegrees.put(uid, 1);
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
		if (vid<0 || vid>=V)
			return -1;
		if (inDegrees.containsKey(vid))
			return inDegrees.get(vid);
		else
			return 0;
	}

	public void print() {
		System.out.println("----");
		System.out.println("G<V,E> |V|=" + V + " |E|=" + E);
		for (int i=0; i<V; ++i) {
			Set<Integer> adjs = adjLists.get(i);
			System.out.println("Node " + i + "\t->\t" + adjs + "\t(out=" + outDegree(i) + " in=" + inDegree(i) + ")");
		}
	}

	// transpose of g
	public static GXDiGraph transpose(GXDiGraph g) {
		GXDiGraph tg = new GXDiGraph(g.V());
		for (int vid=0; vid<g.V(); ++vid) {
			for (Integer uid :  g.adj(vid))
				tg.addEdge(uid, vid);
		}
		return tg;
	}

	// directed graph
	// no self-loop
	// no multi-edge
	public static GXDiGraph genRandomGraph(int v, double p) {
		GXDiGraph g = new GXDiGraph(v);
		if (v<=0 || p<0.0)
			return g;

		for (int i=0; i<g.V(); ++i) {
			for (int j=0; j<g.V(); ++j) {
				if (i == j)
					continue;
				if (Math.random() < p)
					g.addEdge(i, j);
			}
		}
		return g;
	}

	public static void main(String[] args) {

		GXDiGraph g = new GXDiGraph(10);
		g.print();

		GXDiGraph rndG = GXDiGraph.genRandomGraph(10, 0.1);
		rndG.print();

		GXDiGraph.transpose(rndG).print();
	}


}
