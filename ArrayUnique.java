
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

	public static void main(String[] args) {
		int[] array = {1,2,2,6,5,4,7,9,4,6,7,8,0,0};
		Arrays.sort(array);
		System.out.println("[Sort] " + Arrays.toString(array));
		
		int[] uniq = unique(array);
		System.out.println("[Unique] " + Arrays.toString(uniq));

		int key = 3;
		int idx = binarySearch(uniq, key);
		System.out.println("key " + key + " at index " + idx);
	}

}
