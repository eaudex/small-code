
import java.util.*;

public class GX {

	// static nested class, accessible from outside class GX
	// make it immutable
	public static final class Pair<S,T> {
		private S key;
		private T value;

		public Pair(S k, T v) {
			key = k;
			value = v;
		}

		public S getKey() {
			return key;
		}
		public T getValue() {
			return value;
		}

		@Override
		public String toString() {
			return "(" + key + ", " + value + ")";
		}
	}

	public static final class Triplet<P,Q,R> {
		private P one;
		private Q two;
		private R three;

		public Triplet(P one, Q two, R three) {
			this.one = one;
			this.two = two;
			this.three = three;
		}

		public P getOne() {
			return one;
		}
		public Q getTwo() {
			return two;
		}
		public R getThree() {
			return three;
		}

		@Override
		public String toString() {
			return "(" + one + ", " + two + ", " + three + ")";
		}
	}

	// Comparator<T> interface declares `compare(T,T)` to be overridden
	// usage: Arrays.sort(Integer[], new IndexCMP(double[]))
	public static class IndexCMP implements Comparator<Integer> {
		private double[] values;
		public IndexCMP(double[] vals) {
			values = vals;
		}
		@Override
		public int compare(Integer idx, Integer jdx) {
			if (values[idx] > values[jdx])
				return +1;
			else if (values[idx] < values[jdx])
				return -1;
			else
				return 0;
		}
	}

	public static void main(String[] args) {
		GX.Pair<String,String> p = new GX.Pair<String,String>("my_key","my_value");
		System.out.println(p);

		GX.Triplet<Integer,Integer,Double> t = new GX.Triplet<Integer,Integer,Double>(1,2,3.14159);
		System.out.println(t);
	}

}


