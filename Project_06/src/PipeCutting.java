
/**
 * Pipe Cutting Problem.
 * @author Kemal Akkoyun
 * Implementation of some pipe cutting problem algorithms with dynamic programming approach.
 * Algorithms mostly depend on Introduction to algorithms, 3rd ed. from Cormen and Comp232 lectures
 * --- from Chris Stephenson. 
 * 
 */

public class PipeCutting {

	/**
	 * Naive Backtrack approach.
	 * @param priceTable An array that stores values/prices of cuts related by their index.
	 * @param lengthOfPipe Length of given pipe.
	 * @return Value/Price of Optimum cut.
	 */
	public static int cutRod(int[] priceTable, int lengthOfPipe){
		// Value of optimum cut.
		int value;
		// If there is no pipe to cut, it is "0".
		if(lengthOfPipe == 0){
			return 0;
		}
		value = Integer.MIN_VALUE;
		// Solve it for subproblems.
		for(int i = 1; i <= lengthOfPipe; i++){
			value = Math.max(value, (priceTable[i-1] + cutRod(priceTable, (lengthOfPipe - i))));
		}
		// return the value of optimum cut.
		return value;
	}
	
	/**
	 * Dynamic Programming for Optimal Rod Cutting.
	 * @param lengthOfPipe Length of given pipe.
	 * @param priceTable An array that stores values/prices of cuts related by their index.
	 * @return Value/Price of Optimum cut.
	 * Simply, caches the solutions for subproblems that it solve previously. 	 
	 */
	public static int memoizedCutRod(int[] priceTable, int lengthOfPipe){
		// An array to cache subsolutions.
		int[] memoized = new int[lengthOfPipe + 1];
		// Initialize memoize array.
		for(int i = 0; i < memoized.length; i++){
			memoized[i] = Integer.MIN_VALUE;
		}
		return memoizedCutRodHelper(priceTable, lengthOfPipe, memoized);
	}
	
	/**
	 * 
	 * @param lengthOfPipe Length of given pipe.
	 * @param priceTable An array that stores values/prices of cuts related by their index.
	 * @param memoized Stores solutions of previously solved subproblems. 
	 * @return Value/Price of Optimum cut.
	 */
	private static int memoizedCutRodHelper(int[] priceTable, int lengthOfPipe, int[] memoized) {
		// Value of optimum cut.
		int value;
		// Then I have reach end, because previously I set a optimum value here.
		if(memoized[lengthOfPipe] >= 0){
			// Then return it.
			return memoized[lengthOfPipe];
		}
		// I have nothing to cut anymore.
		if(lengthOfPipe == 0){
			value = 0;
		} else {
			value = Integer.MIN_VALUE;
			for(int i = 1; i <= lengthOfPipe; i++){
				value = Math.max(value, (priceTable[i - 1] + memoizedCutRodHelper(priceTable, lengthOfPipe - i, memoized)));
			}
		}
		memoized[lengthOfPipe] = value;
		return value;
	}
	
	/**
	 * Bottom Up Dynamic Programming for Optimal Rod Cutting.
	 * @param lengthOfPipe Length of given pipe.
	 * @param priceTable An array that stores values/prices of cuts related by their index.
	 * @return Value/Price of Optimum cut.
	 *  	 
	 */
	public static int bottomUpCutRod(int[] priceTable, int lengthOfPipe){
		// An array to cache sub-solutions.
		int[] memoized = new int[lengthOfPipe + 1];
		// Instead of recursion, Cormen directly reference an "0" entry. 
		// This also why the arrays larger than they meant to be.
		memoized[0] = 0;
		for(int j = 1; j <= lengthOfPipe; j++){
			// Initialise value of optimum cut.
			int value = Integer.MIN_VALUE;
			for(int i = 1; i <= j; i++){
				value = Math.max(value, priceTable[i - 1] + memoized[j - i]);
			}
			memoized[j] = value;
		}
		return memoized[lengthOfPipe];
	}
	
	/**
	 * Extended Bottom Up Dynamic Programming for Optimal Rod Cutting.
	 * Extension is to return an actual cut sequence.
	 * @param lengthOfPipe Length of given pipe.
	 * @param priceTable An array that stores values/prices of cuts related by their index.
	 * @return Value/Price of Optimum cut.
	 *  	 
	 */
	public static int[] extendedBottomUpCutRod(int[] priceTable, int lengthOfPipe){
		// An array to cache sub-solutions.
		int[] memoized = new int[lengthOfPipe + 1];
		int[] solution = new int[lengthOfPipe + 1];
		// Instead of recursion, Cormen directly reference an "0" entry. 
		// This also why the arrays larger than they meant to be.
		memoized[0] = 0;
		for(int j = 1; j <= lengthOfPipe; j++){
			// Initialise value of optimum cut.
			int value = Integer.MIN_VALUE;
			for(int i = 1; i <= j; i++){
				if(value < Math.max(value, priceTable[i - 1] + memoized[j - i])){
					value = Math.max(value, priceTable[i - 1] + memoized[j - i]);
					solution[j] = i;
				}
			}
			memoized[j] = value;
		}
		return solution;
	}
	
	/**
	 * A method to run extendedBottomUpCutRod then return actual rut as a String sequence.
	 * @param lengthOfPipe Length of given pipe.
	 * @param priceTable An array that stores values/prices of cuts related by their index.
	 * @return A Sequence of Actual Cut represented as a string.
	 */
	public static String printActualCut(int[] priceTable, int lengthOfPipe){
		// Just run algorithm.
		int[] toPrint = extendedBottomUpCutRod(priceTable, lengthOfPipe);
		String s="";
		while(lengthOfPipe > 0){
			s += toPrint[lengthOfPipe] + " ";
			lengthOfPipe -= toPrint[lengthOfPipe];
		}
		return s.trim();
	}
}