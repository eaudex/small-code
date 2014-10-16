

public class GXMath {

	// compute sqrt(n) using binary search
	public static double sqrt(double n) {
		if (n < 0.0)
			return -1.0;
		else if (n == 0.0)
			return 0.0;
		else if (n == 1.0)
			return 1.0;

		double left=0.0, right=n;
		double root=0.0, root2=root*root;
		while (Math.abs(root2-n) >= 0.000001) {
			root = (left+right) / 2.0;
			root2 = root*root;
			if (root2 > n)
				right = root;
			else if (root2 < n)
				left = root;
		}
		return root;
	}

	public static void main(String[] args) {

		double s = sqrt(1000.0);
		System.out.println(s);

	}
}
