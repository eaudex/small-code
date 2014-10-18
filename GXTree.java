import java.util.*;
import java.util.Map.*;

class TreeNode {
	private int data;
	private TreeNode left;
	private TreeNode right;
	public TreeNode(int d) {
		data = d;
		left = null;
		right = null;
	}

	public static TreeNode genBST(int n) {
		Random rnd = new Random();
		int[] array = new int[n];
		for (int i=0; i<n; ++i)
			array[i] = rnd.nextInt(100);
		Arrays.sort(array);
		System.out.println(Arrays.toString(array));
		TreeNode root = _genBST(array, 0, array.length-1);
		return root;
	}
	private static TreeNode _genBST(int[] array, int idx, int jdx) {
		if (idx > jdx)
			return null;
		int mdx = idx + (jdx-idx)/2;
		TreeNode root = new TreeNode(array[mdx]);
		root.left = _genBST(array, idx, mdx-1);
		root.right = _genBST(array, mdx+1, jdx);
		return root;
	}

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

	//
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

	//
	public static int width(TreeNode v) {
		GX.Pair<Integer,Integer> window = _width(v, 0);
		System.out.println(window);
		return window.value-window.key+1;
	}

	private static GX.Pair<Integer,Integer> _width(TreeNode v, int w) {
		if (v == null)
			return null;

		int min=w, max=w;
		if (v.left != null) {
			GX.Pair<Integer,Integer> window_left = _width(v.left, w-1);
			min = Math.min(min, window_left.key);
			max = Math.max(max, window_left.value);
		}
		if (v.right != null) {
			GX.Pair<Integer,Integer> window_right = _width(v.right, w+1);
			min = Math.min(min, window_right.key);
			max = Math.max(max, window_right.value);
		}

		return new GX.Pair<Integer,Integer>(min, max);
	}

	public static void printVerticalOrder(TreeNode v) {
		GX.Pair<Integer,Integer> window = _width(v, 0);
		System.out.println(window);
		int min = window.key.intValue();
		int max = window.value.intValue();
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

	public static void main(String[] args) {
		TreeNode root = TreeNode.genBST(10);
		TreeNode.printInOrder(root);
		int depth = TreeNode.depth(root);
		int width = TreeNode.width(root);
		TreeNode.printVerticalOrder(root);
	}

}
