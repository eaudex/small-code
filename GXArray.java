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

	// reverse an array
	public static void reverse(int[] array) {
		if (array==null || array.length<=1)
			return;

		int len = array.length;
		for (int i=0; i<len/2; ++i)
			swap(array, i, len-i-1);
	}

	// permute given array
	// [Time] O(n*n!)
	public static void permute(int[] array) {
		System.out.println("---");
		_permute(array, 0);
		System.out.println("---");
	}
	private static void _permute(int[] array, int idx) {
		if (idx >= array.length-1) {
			System.out.println(Arrays.toString(array));
			return;
		}
		for (int i=idx; i<array.length; ++i) {
			GXArray.swap(array, idx, i);
			_permute(array, idx+1);
			GXArray.swap(array, idx, i);
		}
	}

	// gen power set of given array
	// [Time] O(2^n)
	public static void powerset(int[] array) {
		if (array.length > 32)
			return;
		System.out.println("-----------------");
		_powerset(array, 0, 0);
		System.out.println("-----------------");
	}
	private static void _powerset(int[] array, int idx, int sol) {
		if (idx >= array.length) {
			System.out.print("{ ");
			for (int i=0; i<array.length; ++i) {
				if ((sol&(1<<i)) > 0)
					System.out.print(array[i] + " ");
			}
			System.out.println("}");
			return;
		}
		_powerset(array, idx+1, sol);			//unset idx-th bit
		_powerset(array, idx+1, (sol|(1<<idx)));//set idx-th bit
	}

	public static void subsetsum(int[] array) {
		_subsetsum(array, 0, 0);
	}
	private static void _subsetsum(int[] array, int idx, int currSum) {
		if (idx >= array.length) {
			System.out.println("[subsum] " + currSum);
			return;
		}
		_subsetsum(array, idx+1, currSum);
		_subsetsum(array, idx+1, currSum+array[idx]);
	}

	// find the first element that appears more than once in an array
	public static int findFirstRepeat(int[] array) {
		if (array==null || array.length<=0)
			return -1;

		int minIdx = -1;
		Set<Integer> visits = new HashSet<Integer>(array.length);
		for (int i=array.length-1; i>=0; --i) {
			if ( ! visits.contains(array[i]))
				visits.add(array[i]);
			else
				minIdx = i;
		}
		return minIdx;
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

	// alternative rearrange
	// [Time] O(n^2)
	// [Space] O(1)
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

	// Bubble Sort
	// [Time] best: O(n), average: O(n^2), worse: O(n^2)
	// [Space] O(1)
	// in-place, stable?
	public static void bubleSort(int[] array) {
		if (array==null || array.length<=1)
			return;
		boolean isSwap = true;
		while (isSwap) {
			isSwap = false;
			for (int i=1; i<array.length; ++i) {
				if (array[i-1] > array[i]) {
					GXArray.swap(array, i-1, i);
					isSwap = true;
				}
			}
		}
		System.out.println(Arrays.toString(array));
	}

	// Selection Sort
	// [Time] best: O(n^2), average: O(n^2), worst: O(n^2)
	// [Space] O(1)
	// in-place, stable?
	public static void selectSort(int[] array) {
		if (array==null || array.length<=1)
			return;
		for (int i=0; i<array.length; ++i) {
			int minIdx = i;
			int minVal = array[i];
			for (int j=i+1; j<array.length; ++j) {
				if (minVal > array[j]) {
					minIdx = j;
					minVal = array[j];
				}
			}
			if (i != minIdx)
				GXArray.swap(array, i, minIdx);
		}
		System.out.println(Arrays.toString(array));
	}


	// Insertion Sort
	// [Time] best: O(n), average: O(n^2),  worse: O(n^2)
	// [Space] O(1)
	// in-place, stable
	public static void insertSort(int[] array) {
		if (array==null || array.length<=1)
			return;
		for (int i=1; i<array.length; ++i) {
			for (int j=i-1; j>=0; --j) {
				if (array[j] > array[j+1])
					GXArray.swap(array, j, j+1);
				else
					break;
			}
		}
		System.out.println(Arrays.toString(array));
	}

	// Merge Sort
	// [Time] best O(n*log(n)), average O(n*log(n)), worse O(n*log(n))
	// [Space] `O(n)`
	// stable
	public static void mergeSort(int[] array) {
		_mergeSort(array, 0, array.length-1);
		System.out.println(Arrays.toString(array));
	}
	private static void _mergeSort(int[] array, int idx, int jdx) {
		if (idx >= jdx)
			return;
		int mdx = idx + (jdx-idx)/2;
		_mergeSort(array, idx, mdx);
		_mergeSort(array, mdx+1, jdx);
		_merge(array, idx, mdx, mdx+1, jdx);
	}
	private static void _merge(int[] array, int idxStart, int idxEnd, int jdxStart, int jdxEnd) {
		int iLen = (idxEnd-idxStart) + 1;
		int jLen = (jdxEnd-jdxStart) + 1;
		int[] sorted = new int[iLen+jLen];

		int idx=idxStart, jdx=jdxStart, kdx=0;
		while (idx<=idxEnd && jdx<=jdxEnd) {
			if (array[idx] > array[jdx]) {
				sorted[kdx] = array[jdx];
				jdx += 1;
				kdx += 1;
			}
			else {
				sorted[kdx] = array[idx];
				idx += 1;
				kdx += 1;
			}
		}
		while (idx <= idxEnd) {
			sorted[kdx] = array[idx];
			idx += 1;
			kdx += 1;
		}
		while (jdx <= jdxEnd) {
			sorted[kdx] = array[jdx];
			jdx += 1;
			kdx += 1;
		}

		int k = 0;
		for (int i=idxStart; i<=idxEnd; ++i) {
			array[i] = sorted[k];
			k += 1;
		}
		for (int j=jdxStart; j<=jdxEnd; ++j) {
			array[j] = sorted[k];
			k += 1;
		}
	}

	// Quick Sort
	// [Time] best: O(n*log(n)), average: O(n*log(n)), worse: O(n^2)
	// [Space] best: O(log(n)), average: O(log(n)), worse: O(n)
	// not stable
	public static void quickSort(int[] array) {
		_quickSort(array, 0, array.length-1);
		System.out.println(Arrays.toString(array));
	}
	private static void _quickSort(int[] array, int idx, int jdx) {
		if (idx >= jdx)
			return;
		int pdx = _quickPartition(array, idx, jdx);
		_quickSort(array, idx, pdx-1);
		_quickSort(array, pdx+1, jdx);
	}
	private static int _quickPartition(int[] array, int idx, int jdx) {
		int mdx = idx + (jdx-idx)/2;
		int mval = array[mdx];
		GXArray.swap(array, mdx, jdx);

		int j = idx;
		for (int i=idx; i<jdx; ++i) {
			if (array[i] < mval) {
				GXArray.swap(array, i, j);
				j += 1;
			}
		}
		GXArray.swap(array, j, jdx);
		return j;
	}


	public static void main(String[] args) {
		int[] array = {1,2,3};
		System.out.println(Arrays.toString(array));

		// reverse
		reverse(array);
		System.out.println(Arrays.toString(array));

		permute(array);

		// PriorityQueue<E> practice
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.offer(2);
		pq.offer(1);
		pq.offer(11);
		pq.offer(-1);
		System.out.println(pq);
		while (pq.size() > 0)
			System.out.println(pq.poll());

		// sorted matrix
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
		// to sorted array
		sortedMatrix2SortedArray(mtx);

		int[] data = {-1,-2,3,4,-1,-4,-8,3,1,-9,-7};
		boolean good = alternatingRearrange(data);
		System.out.println("[" + good + "]	" + Arrays.toString(data));

		// various sorting algorithms
		bubleSort(data);
		selectSort(data);
		insertSort(data);
		mergeSort(data);
		quickSort(data);

		int[] data2 = {6, 10, 5, 4, 9, 120, 4, 6, 10};
		int minIdx = findFirstRepeat(data2);
		System.out.println(Arrays.toString(data2) + " has " + data2[minIdx] + "@" + minIdx);


		powerset(data);
		subsetsum(array);
	}

}
