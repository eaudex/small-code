import java.util.*;

// PriorityQueue<E>([initCapacity, comparator])
//	offer(E) -> boolean
//	peek() -> E/null
//	poll() -> E/null
//	size() -> int

public class GXArray {
	/*
	public static <T> void swap(T[] array, int i, int j) {
		T tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	*/

	public static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static void reverse(int[] array) {
		int len = array.length;
		for (int i=0; i<len/2; ++i) {
			swap(array, i, len-i-1);
		}
	}

	private static final class TripletCMP implements Comparator<GX.Triplet<Integer,Integer,Integer>> {
		public TripletCMP() {
		}
		@Override
		public int compare(GX.Triplet<Integer,Integer,Integer> left, GX.Triplet<Integer,Integer,Integer> right) {
			return left.getThree().compareTo(right.getThree());
		}
	}

	// k-way merge algo in O(m*n*log(m))
	public static LinkedList<Integer> sortedMatrix2SortedArray(int[][] data) {
		if (data==null || data.length<=0 || data[0].length<=0)
			return null;

		// output
		LinkedList<Integer> output = new LinkedList<Integer>();

		int m=data.length, n=data[0].length;
		PriorityQueue<GX.Triplet<Integer,Integer,Integer>> pq = new PriorityQueue<GX.Triplet<Integer,Integer,Integer>>(m, new TripletCMP());
		for (int i=0; i<m; ++i)
			pq.offer(new GX.Triplet<Integer,Integer,Integer>(i,0,data[i][0]));

		while (pq.size() > 0) {
			GX.Triplet<Integer,Integer,Integer> t = pq.poll();
			int r = t.getOne();
			int c = t.getTwo();
			int d = t.getThree();
			output.offer(d);
			if (c+1 < n)
				pq.offer(new GX.Triplet<Integer,Integer,Integer>(r,c+1,data[r][c+1]));
		}

		System.out.println(output);
		return output;
	}

	// in O(n^2) time, O(1) space
	public static boolean alternatingRearrange(int[] data) {
		if (data == null)
			return false;
		if (data.length <= 0)
			return true;

		int len = data.length;
		int sign = (data[0])>0?(+1):(-1);
		for (int i=1; i<len; ++i) {
			if (sign * data[i] < 0) {
				sign *= -1;
				continue;
			}
			int j;
			for (j=i+1; j<len; ++j) {
				if (sign * data[j] < 0) {
					GXArray.swap(data, i, j);
					sign *= -1;
					break;
				}
			}
			if (j == len)
				return false;
		}
		return true;
	}


	public static void main(String[] args) {
		int[] array = {1,2,3,4,5};

		// reverse
		System.out.println(Arrays.toString(array));
		reverse(array);
		System.out.println(Arrays.toString(array));

		//
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.offer(2);
		pq.offer(1);
		pq.offer(11);
		pq.offer(-1);
		System.out.println(pq);

		while (pq.size() > 0)
			System.out.println(pq.poll());

		int m=5, n=3;
		int[][] mtx = new int[m][n];
		mtx[0][0] = 1;
		mtx[0][1] = 2;
		mtx[0][2] = 3;
		mtx[1][0] = 2;
		mtx[1][1] = 3;
		mtx[1][2] = 4;
		mtx[2][0] = 3;
		mtx[2][1] = 4;
		mtx[2][2] = 5;
		mtx[3][0] = 4;
		mtx[3][1] = 5;
		mtx[3][2] = 6;
		mtx[4][0] = 5;
		mtx[4][1] = 6;
		mtx[4][2] = 7;
		for (int i=0; i<m; ++i) {
			for (int j=0; j<n; ++j) {
				System.out.print(" " + mtx[i][j]);
			}
			System.out.println();
		}

		sortedMatrix2SortedArray(mtx);

		int[] data = {-1,-2,3,4,-1,-4,-8,3,1,-9,-7};
		boolean good = alternatingRearrange(data);
		System.out.println("[" + good + "]	" + Arrays.toString(data));
	}

}
