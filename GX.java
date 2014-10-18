

public class GX {

	// static nested class, accessible from outside class GX
	public static class Pair<S,T> {
		public S key;
		public T value;

		public Pair(S k, T v) {
			key = k;
			value = v;
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
