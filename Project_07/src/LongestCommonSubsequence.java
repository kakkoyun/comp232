/**
 * Comp 232 - Project 7
 * @author Kemal Akkoyun
 * Longest Common Subsequence
 * Algorithms implemented using lecture notes of Comp232 and from Introduction to algorithms. 3rd. by Cormen.
 * 
 */

public class LongestCommonSubsequence {
	
	// Cache matrix for sub-solutions of problems.
	private static Integer[][] memoizeMatrix;

	public static int lcsNaive(String x, String y){
		
		// Represent String as char arrays.
		char[] seq1 = x.toCharArray();
		char[] seq2 = y.toCharArray();
		
		// Length of Arrays.
		int i = seq1.length;
		int j = seq2.length;
				
		// Call helper functions.
		return lcsNaiveHelper(seq1, seq2, i, j);	
	}

	private static int lcsNaiveHelper(char[] seq1, char[] seq2, int i, int  j) {
		// if I have come to end of one of sequences,
		if(i == 0 || j == 0){
			// return 0,
			return 0;
		} else if(seq1[i - 1] == seq2[j - 1]){
			// if I have a match increment length counter, recursive call for rest.
			return 1 + lcsNaiveHelper(seq1, seq2, i - 1, j - 1);
		} else {
			return Math.max(
					lcsNaiveHelper(seq1, seq2, i - 1, j),
					lcsNaiveHelper(seq1, seq2, i, j - 1));
		}	
	}
	
	public static int lcsNaiveMemoized(String x, String y){
		
		// Represent String as char arrays.
		char[] seq1 = x.toCharArray();
		char[] seq2 = y.toCharArray();
		
		// Length of Arrays.
		int i = seq1.length;
		int j = seq2.length;
		
		// Initialise memoizedMatrix.
		memoizeMatrix = new Integer[i][j];
				
		// Call helper functions.
		return lcsNaiveMemoizeHelper(seq1, seq2, i, j, memoizeMatrix);	
	
	}
	
	private static int lcsNaiveMemoizeHelper(char[] seq1, char[] seq2, int i, int  j, Integer[][] cache) {
		// if I have come to end of one of sequences,
		if(i == 0 || j == 0){
			// return 0,
			return 0;
		} else
			// Check whether a solution has calculated previously, or not.
		if(!(cache[i -1][j - 1] == null)){
			// if so, return it.
			return cache[i -1][j - 1];
		} else {
			// if not, calculate it.
			cache[i -1][j - 1] = lcsNaiveHelper(seq1, seq2, i, j);
			return cache[i -1][j - 1];
		}
	}
}
