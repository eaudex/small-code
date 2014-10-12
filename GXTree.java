import java.util.*;

class TreeNode {
	public int data;
	public TreeNode left;
	public TreeNode right;
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

	public static void printPreOrder(TreeNode root) {
		System.out.println("[PreOrderTree]");
		_printPreOrder(root, 0);
	}
	public static void _printPreOrder(TreeNode v, int depth) {
		if (v == null) {
			for (int d=0; d<depth; ++d)
				System.out.print("\t");
			System.out.println("_");
			return;
		}

		for (int d=0; d<depth; ++d)
			System.out.print("\t");
		System.out.println(v.data);
		_printPreOrder(v.left, depth+1);
		_printPreOrder(v.right, depth+1);
	}

}

public class GXTree {

	public static void main(String[] args) {
		TreeNode root = TreeNode.genBST(10);
		TreeNode.printPreOrder(root);
	}

}
