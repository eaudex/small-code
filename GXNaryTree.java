
import java.util.*;

class GXNaryTreeNode {

	public int data;
	public LinkedList<GXNaryTreeNode> children;

	public GXNaryTreeNode(int d) {
		data = d;
		children = new LinkedList<GXNaryTreeNode>();
	}


	public static void genRandomNaryTree(int n) {
	}

}

public class GXNaryTree {

	public static void serialize(GXNaryTreeNode root, LinkedList<String> out) {
		if (root == null) {
			out.offer("_");
			return;
		}

		out.offer(String.valueOf(root.data));
		for (GXNaryTreeNode c : root.children) {
			serialize(c, out);
		}
		out.offer("_");
	}

	public static GXNaryTreeNode deserialize(LinkedList<String> in) {
		if (in.size()<=0 || in.peek().equals("_")) {
			in.poll();
			return null;
		}

		GXNaryTreeNode n = new GXNaryTreeNode(Integer.parseInt(in.poll()));
		while ( ! in.peek().equals("_")) {
			GXNaryTreeNode c = deserialize(in);
			if (c != null)
				n.children.offer(c);
		}
		return n;
	}

	public static void GXNaryTreeNode(String[] args) {
	}


}


