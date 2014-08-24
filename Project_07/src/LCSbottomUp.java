/**
 * Comp 232 - Project 7
 * @author Kemal Akkoyun
 * Longest Common Subsequence
 * Algorithms implemented using lecture notes of Comp232 and from Introduction to algorithms. 3rd. by Cormen.
 * 
 */
public class LCSbottomUp {
	
	// Collector for sequence.
	static String collector = "";
	
	public static lcsMark[][] lcsLength(String seq0, String seq1){
		
		// Represent sequences as char arrays.
		char[] x = seq0.toCharArray();
		char[] y = seq1.toCharArray();
		
		// Length of arrays.
		int m = x.length;
		int n = y.length;
		
		// Cache Tables.
		lcsMark[][] marks = new lcsMark[m][n];
		int[][] cache = new int[m + 1][n + 1];
		
		// Initialise cache table.
		for(int i = 0; i<cache.length; i++){ 
			cache[i][0] = 0;
			}
		for(int j = 0; j<cache[0].length; j++){ 
			cache[0][j] = 0;
			}
		// Basically, solve it bottom-up.
		for(int i = 1; i<cache.length; i++){
			for(int j = 1; j<cache[0].length; j++){
				if(x[i - 1] == y[j - 1]){
					cache[i][j] = cache[i - 1][j - 1] + 1;
					marks[i - 1][j - 1] = lcsMark.NORTHWEST;
				} else if(cache[i - 1][j] >= cache[i][j - 1]){
					cache[i][j] = cache[i - 1][j];
					marks[i - 1][j - 1] = lcsMark.NORTH;
				} else { 
					cache[i][j] = cache[i][j - 1];
					marks[i - 1][j - 1] = lcsMark.WEST;
				}
			}
		}
		return marks;
	}
	
	// Method to get An actual subsequence represented as String.
	public static void printLcs(lcsMark[][] marks, char[] x, int i , int j){
		if(i == 0 || j == 0){
			return;
		}
		if(marks[i - 1][j - 1] == lcsMark.NORTHWEST){
			printLcs(marks, x, i - 1, j - 1);
			collector += x[i - 1];		
		} else if (marks[i - 1][j - 1] == lcsMark.NORTH){
			printLcs(marks, x, i - 1, j);
		} else {
			printLcs(marks, x, i, j - 1);
		}
	}
	
	// Call methods and return a sequence.
	public static String lcs(String x, String y){
		collector = "";
		printLcs(lcsLength(x,y), x.toCharArray(), x.length(), y.length());
		return collector;
	}
}
