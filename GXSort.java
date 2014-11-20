
import java.util.*;

public class GXSort {

	public static int[] array = {5,7,0,3,10,8,1,4};

	// simple sort
	public static void bubble(int[] array) {
	}
	public static void select(int[] array) {
	}
	public static void insert(int[] array) {
	}

	// advanced sort
	public static void quick(int[] array) {
	}
	public static void merge(int[] array) {
	}
	public static void heap(int[] array) {
	}

	public static void sortArrayByArray(int[] array, int[] brray) {
		if (array==null || array.length<=1)
			return;
		System.out.println(Arrays.toString(array));

		Map<Integer,Integer> dict = new HashMap<Integer,Integer>(brray.length);
		for (int i=0; i<brray.length; ++i)
			dict.put(brray[i], i);

		boolean isSwap = true;
		while (isSwap) {
			isSwap = false;
			for (int i=1; i<array.length; ++i) {
				int a=brray.length+array[i-1], b=brray.length+array[i];
				if (dict.containsKey(array[i-1]))
					a = dict.get(array[i-1]);
				if (dict.containsKey(array[i]))
					b = dict.get(array[i]);
				if (a > b) {
					GXArray.swap(array, i-1, i);
					isSwap = true;
				}
			}
		}
		System.out.println(Arrays.toString(array));
	}

	public static void main(String[] args) {

		int[] array = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8};
		int[] brray = {2, 1, 8, 3};

		sortArrayByArray(array, brray);
	}

}
