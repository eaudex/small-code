
import java.util.*;

public class GXString {

	// [Time] O(n)
	public static boolean isPalindrom(String str) {
		if (str == null)
			return false;
		if (str.length() <= 1)
			return true;

		int len = str.length();
		for (int i=0; i<len/2; ++i) {
			if (str.charAt(i) != str.charAt(len-i-1))
				return false;
		}
		return true;
	}

	// [Time] O(m+n) if KMP algo is implemented in `str.contains(.)`
	public static boolean areRotations(String str1, String str2) {
		if (str1==null || str2==null)
			return false;
		if (str1.length() != str2.length())
			return false;

		String str = str1 + str1;
		return str.contains(str2);
	}

	public static boolean rotatePalindrom(String str) {
		if (str == null)
			return false;
		if (str.length() <= 0)
			return true;

		for (int i=0; i<str.length(); ++i) {
			String rotate = str.substring(i) + str.substring(0,i);
			//System.out.println(rotate);
			if (isPalindrom(rotate))
				return true;
		}
		return false;
	}


	// find if `pattern` is a substring of `text` (first occurance)
	// [Time] O(m*n)
	public static int isSubstring(String text, String pattern) {
		if (text==null || pattern==null)
			return -1;
		if (pattern.length() <= 0)
			return 0;
		if (text.length() < pattern.length())
			return -1;

		for (int i=0; i<=(text.length()-pattern.length()); ++i) {
			int ii=i, j=0;
			for (j=0; j<pattern.length(); ++j,++ii) {
				char c0 = text.charAt(ii);
				char c1 = pattern.charAt(j);
				if (c0 != c1)
					break;
			}
			if (j == pattern.length())
				return i;
		}
		return  -1;
	}

	// find if pattern is a subsequence of text
	// [Time] O(m)
	public static boolean isSubsequence(String text, String pattern) {
		if (text==null || pattern==null)
			return false;
		if (pattern.length() <= 0)
			return true;
		if (text.length() < pattern.length())
			return false;

		int j = 0;
		for (int i=0; i<text.length(); ++i) {
			char c0 = text.charAt(i);
			char c1 = pattern.charAt(j);
			if (c0 == c1)
				j += 1;
			if (j >= pattern.length())
				return true;
		}
		return false;
	}

	public static void searchRobinKarp(String text, String pattern) {
	}

	// search for all anagrams of pattern in text
	// [Time] O(m), consider equals(.,.) costs O(1) because histogram has fixed size
	public static void searchAnagrams(String text, String pattern) {
		if (text==null || pattern==null)
			return;
		if (pattern.length() <= 0)
			return;
		if (text.length() < pattern.length())
			return;

		System.out.println("[AnagramSearch] (text="+text + " pattern="+pattern+")");
		int len = pattern.length();
		int[] hist0 = toHistogram(pattern);

		String str = text.substring(0,len);
		int[] hist = toHistogram(str);
		if (GXString.equals(hist,hist0))
			System.out.println(str + " starts @0");
		for (int i=1; i<=(text.length()-len); ++i) {
			char cOld = text.charAt(i-1);
			char cNew = text.charAt(i+len-1);
			hist[cOld] -= 1;
			hist[cNew] += 1;
			if (GXString.equals(hist,hist0))
				System.out.println(text.substring(i,i+len) + " starts @" + i);
		}
		System.out.println("----");
	}
	// charset is ascii
	private static int[] toHistogram(String str) {
		int[] hist = new int[256];
		for (int i=0; i<hist.length; ++i)
			hist[i] = 0;
		for (int i=0; i<str.length(); ++i) {
			char c = str.charAt(i);
			if (0<=c && c<=255)
				hist[c] += 1;
			//else
			//	System.out.println("[OutOfBound] " + c + "@" + i);
		}
		//System.out.println("[Histogram] " + Arrays.toString(hist));
		return hist;
	}
	private static boolean equals(int[] hist1, int[] hist2) {
		if (hist1==null || hist2==null)
			return false;
		if (hist1.length != hist2.length)
			return false;

		for (int i=0; i<hist1.length; ++i) {
			if (hist1[i] != hist2[i])
				return false;
		}
		return true;
	}


	// A is encoded as 1, ..., Z is encoded as 26
	// Given digit sequence, count #decodes of it
	public static int decode(String seq) {
		if (seq == null)
			return -1;
		if (seq.length() <= 0)
			return 0;
		return _decode(seq, 0, 0);
	}
	private static int _decode(String seq, int idx, int jdx) {
		if (jdx >= seq.length()) {
			if (idx == jdx)
				return 1;
			else
				return 0;
		}

		if (idx == jdx) {
			char c = seq.charAt(idx);
			if (c=='1' || c=='2') {
				return _decode(seq,idx+1,idx+1) + _decode(seq,idx,idx+1);
			}
			else if ('3'<=c && c<='9') {
				return _decode(seq,idx+1,idx+1);
			}
		}
		else if (idx+1 == jdx) {
			char c0 = seq.charAt(idx);
			char c1 = seq.charAt(jdx);
			if (c0 == '1') {
				return _decode(seq,jdx+1,jdx+1);
			}
			else if (c0 == '2') {
				if ('0'<=c1 && c1<='6') {
					return _decode(seq,jdx+1,jdx+1);
				}
			}
		}

		return 0;
	}


	// digits-to-word conversion on phone keypad
	// [Time] O(4^n)
	// char[] to store the output
	// handle un-defined chars
	public static void enumWords(String seq) {
		if (seq == null)
			return;
		if (seq.length() <= 0)
			return;

		Map<Character,String> dict = new HashMap<Character,String>(8);
		dict.put('2',"ABC");
		dict.put('3',"DEF");
		dict.put('4',"GHI");
		dict.put('5',"JKL");
		dict.put('6',"MNO");
		dict.put('7',"PQRS");
		dict.put('8',"TUV");
		dict.put('9',"WXYZ");

		char[] sol = new char[seq.length()];
		_enumWords(seq, dict, 0, sol);
	}

	private static void _enumWords(String seq, Map<Character,String> dict, int n, char[] sol) {
		if (n >= seq.length()) {
			System.out.println("[WORD] " + new String(sol));
			return;
		}

		char d = seq.charAt(n);
		if ( ! dict.keySet().contains(d)) {
			sol[n] = '_';
			_enumWords(seq, dict, n+1, sol);
		}
		else {
			String letters = dict.get(d);
			for (int i=0; i<letters.length(); ++i) {
				char c = letters.charAt(i);
				sol[n] = c;
				_enumWords(seq, dict, n+1, sol);
			}
		}
	}


	// longest common subsequence (DP)
	// [Time] O(m*n)
	// [Space] O(m*n)
	public static int lcs(String str1, String str2) {
		if (str1==null || str2==null)
			return 0;
		if (str1.length()<=0 || str2.length()<=0)
			return 0;

		int len1 = str1.length();
		int len2 = str2.length();

		int[][] count = new int[len1+1][len2+1];
		for (int i=0; i<len1+1; ++i)
			count[i][0] = 0;
		for (int j=0; j<len2+1; ++j)
			count[0][j] = 0;

		for (int i=1; i<len1+1; ++i) {
			for (int j=1; j<len2+1; ++j) {
				char c1 = str1.charAt(i-1);
				char c2 = str2.charAt(j-1);
				if (c1 == c2) {
					count[i][j] = count[i-1][j-1] + 1;
				}
				else {
					count[i][j] = Math.max(count[i][j-1], count[i-1][j]);
				}
			}
		}

		// backtrack
		int lenLCS = count[len1][len2];
		char[] lcs = new char[lenLCS];
		int i=len1, j=len2;
		int k = lenLCS-1;
		while (k >= 0) {
			if (str1.charAt(i-1) == str2.charAt(j-1)) {
				lcs[k] = str1.charAt(i-1);
				k -= 1;
				i -= 1;
				j -= 1;
			}
			else {
				if (count[i][j-1] >= count[i-1][j])
					j -= 1;
				else
					i -= 1;
			}
		}
		System.out.println("LCS("+str1+","+str2+") " + new String(lcs));

		return lenLCS;
	}


	public static void main(String[] args) {
		String str1="abcba", str2="aabcc";
		int[] hist1 = toHistogram(str1);
		int[] hist2 = toHistogram(str2);
		System.out.println("Are " + str1 + " & " + str2 + " anagrams? " + equals(hist1,hist2));

		searchAnagrams("BACDGABCDA", "ABCD");
		searchAnagrams("AAABABAA", "AABA");

		System.out.println("[IsSubstring] " + isSubstring("abcde","ab") );
		System.out.println("[IsSubstring] " + isSubstring("abcde","de") );
		System.out.println("[IsSubstring] " + isSubstring("abcde","def") );
		System.out.println("[IsSubsequence] " + isSubsequence("geeksforgeeks","gksrek") );

		String code = "12020";
		System.out.println("[#Decodings] " + decode(code));

		System.out.println( isPalindrom("ababa") );
		System.out.println( areRotations("ABCD","ACBD") );

		System.out.println( rotatePalindrom("aaaad") );
		System.out.println( rotatePalindrom("abcd") );

		enumWords("2304");

		lcs("ABCDGH","AEDFHR");
		lcs("AGGTAB","GXTXAYB");

	}


}
