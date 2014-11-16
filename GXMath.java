
import java.util.*;

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

	public static int[] multiplyPolynomial(int[] a, int[] b) {
		int m = a.length;
		int n = b.length;

		int l = (m-1+n-1)+1;
		int[] p = new int[l];
		for (int i=0; i<l; ++i)
			p[i] = 0;

		for (int i=0; i<m; ++i) {
			for (int j=0; j<n; ++j) {
				p[i+j] += a[i]*b[j];
			}
		}
		return p;
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

		int[] p1 = {5,0,10,6};
		int[] p2 = {1,2,4};
		int[] p3 = multiplyPolynomial(p1,p2);
		System.out.println(Arrays.toString(p3));
	}
}
