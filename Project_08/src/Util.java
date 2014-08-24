
/**
 * 
 * @author Kemal Akkoyun
 * @category Comp232/Projects
 * Project 8 * String Matching Algorithms.
 * 
 */

public class Util {
	
	/**
	 * // A position array stores, positions of characters in alphabet at corresponding
	 * @param alphabet A char array that stores alphabet chars.
	 * @return A position array that stores indexes of chars in given alphabet.
	 */

	public static int[] makePositionArray(char[] alphabet){
		
		// ASCII representations as index of position array.
		int[] positionArray = new int[256];
		for(int i = 0; i < 256; i++ ){
			positionArray[i] = -1;
		}
		for(int i = 0; i<alphabet.length; i++){
			positionArray[(int)alphabet[i]] = i;
		}
		return positionArray;
	}
	
	/**
	 * Making an alphabet for FSA. All ASCII characters.
	 * @return a string of ASCII characters.
	 */
	public static String makeAlphabet(){
		String alphabet = ""; 
		for(int i = 0; i < 256; i++ ){
			 alphabet += (char)i;
		}
		return alphabet;
	}
}
