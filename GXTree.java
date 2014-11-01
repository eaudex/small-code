import java.util.*;
import java.util.Map.*;

class TreeNode {
	public int data;
	public TreeNode left;
	public TreeNode right;
	public TreeNode(int d) {
		data = d;
		left = null;
		right = null;
	}

	// gen random binary search tree
	public static TreeNode genRandomBST(int n) {
		Random rnd = new Random();
		int[] array = new int[n];
		for (int i=0; i<n; ++i)
			array[i] = rnd.nextInt(100);
		Arrays.sort(array);
		System.out.println(Arrays.toString(array));
		TreeNode root = _genRandomBST(array, 0, array.length-1);
		return root;
	}
	private static TreeNode _genRandomBST(int[] array, int idx, int jdx) {
		if (idx > jdx)
			return null;
		int mdx = idx + (jdx-idx)/2;
		TreeNode root = new TreeNode(array[mdx]);
		root.left = _genRandomBST(array, idx, mdx-1);
		root.right = _genRandomBST(array, mdx+1, jdx);
		return root;
	}

	// print a tree in in-order manner with proper indentation
	public static void printInOrder(TreeNode root) {
		System.out.println("[InOrderTree]");
		_printInOrder(root, 0);
	}
	private static void _printInOrder(TreeNode v, int depth) {
		if (v == null) {
			for (int d=0; d<depth; ++d)
				System.out.print("\t");
			System.out.println("_");
			return;
		}

		_printInOrder(v.left, depth+1);

		for (int d=0; d<depth; ++d)
			System.out.print("\t");
		System.out.println(v.data);

		_printInOrder(v.right, depth+1);
	}

	// find tree depth (defined as # horizontal levels)
	public static int depth(TreeNode v) {
		int d = _depth(v, 1);
		System.out.println("[TreeDepth] " + d);
		return d;
	}
	private static int _depth(TreeNode v, int d) {
		if (v == null)
			return 0;

		int max_depth = d;
		if (v.left != null)
			max_depth = Math.max(max_depth, _depth(v.left, d+1));
		if (v.right != null)
			max_depth = Math.max(max_depth, _depth(v.right, d+1));

		return max_depth;
	}

	// find tree width (defined as # vertical levels)
	public static int width(TreeNode v) {
		GX.Pair<Integer,Integer> window = _width(v, 0);
		int w = window.getValue()-window.getKey()+1;
		System.out.println("[TreeWidth] " + w);
		return w;
	}
	private static GX.Pair<Integer,Integer> _width(TreeNode v, int w) {
		if (v == null)
			return null;

		int min=w, max=w;
		if (v.left != null) {
			GX.Pair<Integer,Integer> window_left = _width(v.left, w-1);
			min = Math.min(min, window_left.getKey());
			max = Math.max(max, window_left.getValue());
		}
		if (v.right != null) {
			GX.Pair<Integer,Integer> window_right = _width(v.right, w+1);
			min = Math.min(min, window_right.getKey());
			max = Math.max(max, window_right.getValue());
		}

		return new GX.Pair<Integer,Integer>(min, max);
	}

	// print a tree in vertical order
	public static void printVerticalOrder(TreeNode v) {
		GX.Pair<Integer,Integer> window = _width(v, 0);
		System.out.println("[VerticalOrder] window=" + window);
		int min = window.getKey().intValue();
		int max = window.getValue().intValue();
		for (int i=min; i<=max; ++i) {
			System.out.print("[" + i + "]");
			_printVerticalOrder(v, 0, i);
			System.out.println();
		}
	}
	private static void _printVerticalOrder(TreeNode v, int w, int pos) {
		if (v == null)
			return;

		if (w == pos)
			System.out.print(" " + v.data);
		_printVerticalOrder(v.left, w-1, pos);
		_printVerticalOrder(v.right, w+1, pos);
	}
}


public class GXTree {

	public static void serialize(TreeNode root, LinkedList<String> out) {
		if (root == null) {
			out.offer("_");
			return;
		}

		out.offer(String.valueOf(root.data));
		serialize(root.left, out);
		serialize(root.right, out);
	}

	public static TreeNode deserialize(LinkedList<String> in) {
		if (in.size()<=0 || in.peek().equals("_")) {
			in.poll();
			return null;
		}

		TreeNode n = new TreeNode(Integer.parseInt(in.poll()));
		n.left = deserialize(in);
		n.right = deserialize(in);
		return n;
	}

	public static void main(String[] args) {
		TreeNode root = TreeNode.genRandomBST(10);
		TreeNode.printInOrder(root);
		int depth = TreeNode.depth(root);
		int width = TreeNode.width(root);
		TreeNode.printVerticalOrder(root);

		// serialize & deserialize
		LinkedList<String> out = new LinkedList<String>();
		serialize(root, out);
		System.out.println(out);

		TreeNode root2 = deserialize(out);
		root2.printInOrder(root2);
	}

}
