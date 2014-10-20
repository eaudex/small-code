

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

	public static void main(String[] args) {
		GX.Pair<String,String> p = new GX.Pair<String,String>("my_key","my_value");
		System.out.println(p);
	}

}
