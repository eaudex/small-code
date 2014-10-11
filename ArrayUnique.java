
import java.util.*;

public class ArrayUnique {

	public static int[] unique(int[] array) {
		int[] sol = new int[array.length];
		int k = 0;
		for (int i=0; i<array.length; ++i) {
			if (k<=0 || sol[k-1]!=array[i]) {
				sol[k] = array[i];
				k += 1;
			}
		}
		return Arrays.copyOfRange(sol, 0, k);
	}

	public static int binarySearch(int[] array, int key) {
		int left=0, right=array.length-1;
		while (left <= right) {
			int mid = left + (right-left)/2;
			if (key == array[mid])
				return mid;
			else if (key > array[mid]) {
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		return -1;
	}

	// by almost sorted array, the element may be put in the position that is right/left-shift by 1 from where it is supposed to be.
	public static int binarySearchOverAlmostSortedArray(int[] array, int key) {
		int left=0, right=array.length-1;
		while (left <= right) {
			int mid = left + (right-left)/2;
			if (array[mid] == key)
				return mid;
			else if ((mid-1)>=left && array[mid-1]==key)
				return mid-1;
			else if ((mid+1)<=right && array[mid+1]==key)
				return mid+1;
			else if (array[mid] > key)
				right = mid - 2;
			else
				left = mid + 2;
		}
		return -1;
	}


	public static void main(String[] args) {
		int[] array = {1,2,2,6,5,4,7,9,4,6,7,8,0,0};
		Arrays.sort(array);
		System.out.println("[Sort] " + Arrays.toString(array));
		
		int[] uniq = unique(array);
		System.out.println("[Unique] " + Arrays.toString(uniq));

		int key = 3;
		int idx = binarySearch(uniq, key);
		System.out.println("key " + key + " at index " + idx);

		key = 40;
		int[] brray = {10, 3, 40, 20, 50, 80, 70};
		//int[] brray = {3, 2, 10, 4, 40};
		idx = binarySearchOverAlmostSortedArray(brray, key);
		System.out.println("key " + key + " at index " + idx);

	}

}
