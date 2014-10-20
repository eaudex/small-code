import java.util.*;

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

	public static void main(String[] args) {
		int[] array = {1,2,3,4,5};

		// reverse
		System.out.println(Arrays.toString(array));
		reverse(array);
		System.out.println(Arrays.toString(array));

	}

}
