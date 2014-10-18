

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


	// compute n^p (n raised to the power of p)
	public static double pow(double n, int p) {
		if (n == 0.0)
			return 1.0;

		if (p >= 0)
			return _pow(n, p);
		else
			return 1.0/_pow(n, -p);
	}

	// n can be either positive or negative (must not be zero)
	// p must be non-negative
	private static double _pow(double n, int p) {
		if (p == 0)
			return 1.0;
		else if (p == 1)
			return n;

		double i = _pow(n, p/2);
		if (p % 2 == 0) {
			return i*i;
		}
		else {
			return i*i*n;
		}
	}

	public static void main(String[] args) {
		// sqrt
		double s = sqrt(1000.0);
		System.out.println(s);

		// pow
		System.out.println(pow(0.0, 0));
		System.out.println(pow(0.0,-1));
		System.out.println(pow(0.0,+1));

		System.out.println(pow(2.0, 0));
		System.out.println(pow(2.0,-5));
		System.out.println(pow(2.0,+5));
	}
}
