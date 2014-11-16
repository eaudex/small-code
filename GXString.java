
import java.util.*;

public class GXString {

	public static void searchNaive(String text, String pattern) {
	}

	public static void searchRobinKarp(String text, String pattern) {
	}

	public static void searchAnagrams(String text, String pattern) {
		int len = pattern.length();
		int[] hist0 = toHistogram(pattern);
		int[] hist = toHistogram(text.substring(0,len));
		boolean f = equals(hist0, hist);
		if(f) System.out.println("Hit @0");
			
		for (int i=len; i<text.length(); ++i) {
			char cOld = text.charAt(i-len);
			char cNew = text.charAt(i);
			hist[cOld] -= 1;
			hist[cNew] += 1;
			f = equals(hist0, hist);
			if(f) System.out.println("Hit @" + (i-len+1));
		}
	}


	public static int[] toHistogram(String str) {
		int[] hist = new int[256];
		for (int i=0; i<hist.length; ++i)
			hist[i] = 0;
		for (int i=0; i<str.length(); ++i) {
			char c = str.charAt(i);
			if (0<=c && c<=255)
				hist[c] += 1;
			else
				System.out.println("[OutOfBound] " + c + "@" + i);
		}
		System.out.println("[Histogram] " + Arrays.toString(hist));
		return hist;
	}
	public static boolean equals(int[] hist1, int[] hist2) {
		if (hist1==null || hist2==null || hist1.length!=hist2.length)
			return false;

		for (int i=0; i<hist1.length; ++i) {
			if (hist1[i] != hist2[i])
				return false;
		}
		return true;
	}


	public static void main(String[] args) {
		String str1="abcba", str2="aabcc";
		int[] hist1 = toHistogram(str1);
		int[] hist2 = toHistogram(str2);
		System.out.println(equals(hist1,hist2));

		searchAnagrams("BACDGABCDA", "ABCD");
		searchAnagrams("AAABABAA", "AABA");
	}

}
