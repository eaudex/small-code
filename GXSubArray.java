
import java.util.*;

// Subarray Copy
//Arrays.copyOfRange(array, i, j)
//
// Substring Copy
//str.substring(i, j)

public class GXSubArray {

	// longest subarray with continuous elements
	// input: *distinct* integer array
	// [Time] O(n^2)
	public static int longestContinousSubarray0(int[] array) {
		if (array==null || array.length<=0)
			return 0;

		int max_len = 1;
		for (int i=0; i<array.length; ++i) {
			int max = array[i];
			int min = array[i];
			for (int j=i; j<array.length; ++j) {
				if (array[j] > max)
					max = array[j];
				if (array[j] < min)
					min = array[j];
				if ((max-min)==(j-i) && (j-i+1)>max_len)
					max_len = j-i+1;
			}
		}
		return max_len;
	}

	// longest subarray of continous elements
	// input: *non-distinct* integer array
	// [Time] O(n^2)
	public static int longestContinousSubarray1(int[] array) {
		if (array==null || array.length<=0)
			return 0;

		int max_len = 1;
		for (int i=0; i<array.length; ++i) {
			int max = array[i];
			int min = array[i];
			Set<Integer> s = new HashSet<Integer>(array.length);
			for (int j=i; j<array.length; ++j) {
				// stop checking if we find any duplicate in subarray
				if (s.contains(array[j]))
					break;
				s.add(array[j]);
				if (array[j] > max)
					max = array[j];
				if (array[j] < min)
					min = array[j];
				if ((max-min)==(j-i) && (j-i+1)>max_len)
					max_len = j-i+1;
			}
		}
		return max_len;
	}



	public static void main(String[] args) {
		//int[] array0 = {10,12,11};
		//int[] array0 = {14,12,11,20};
		int[] array0 = {1,56,58,57,90,92,94,93,91,45};
		int max_len0 = longestContinousSubarray0(array0);
		System.out.println(Arrays.toString(array0) + " max_len=" + max_len0);

		int[] array1 = {10,12,12,10,10,11,10};
		int max_len1 = longestContinousSubarray1(array1);
		System.out.println(Arrays.toString(array1) + " max_len=" + max_len1);

	}
}
