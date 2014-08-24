import java.util.ArrayList;

/**
 * 
 * @author Kemal Akkoyun
 * @category Comp232/Projects
 * Project 8 * String Matching Algorithms.
 * 	Algorithm from Boyer Moore orginal paper.
 *	Source: http://courses.cs.bilgi.edu.tr/file.php/271/fstrpos.pdf
 */
public class BoyerMoore implements IStringMatcher {
	
    private int[] rightPositionArray;  // Position Array of right most occurence of chars at pattern.
    
    public int matcher(String text, String pattern) {
    	// Make a position array from pattern to know and access positions of char at pattern.
        rightPositionArray = Util.makePositionArray(pattern.toCharArray());
        int patternLength = pattern.length();
        int textLength = text.length();
        int jump;
        for (int i = 0; i <= textLength - patternLength; i += jump) {
            jump = 0;
            for (int j = patternLength - 1; j >= 0; j--) {
            	if (pattern.charAt(j) != text.charAt(i+j)) {
            		// Find max jump.
                    jump = Math.max(1, j - rightPositionArray[text.charAt( i + j )]);
                    break;
                }
            }
            if (jump == 0){ 
            	return i;
            }
        }
        return -1;  
    }

	@Override
	public ArrayList<Integer> mulMatcher(String text, String pattern) {
		// Make a position array from pattern to know and access positions of char at pattern.
        rightPositionArray = Util.makePositionArray(pattern.toCharArray());
        ArrayList<Integer> indexesOfOccurences = new ArrayList<Integer>();
        int patternLength = pattern.length();
        int textLength = text.length();
        int jump;
        for (int i = 0; i <= textLength - patternLength; i += jump) {
            jump = 0;
            for (int j = patternLength - 1; j >= 0; j--) {
            	if (pattern.charAt(j) != text.charAt(i+j)) {
                    jump = Math.max(1, j - rightPositionArray[text.charAt( i + j )]);
                    break;
                }
            }
            if (jump == 0){ 
            	indexesOfOccurences.add(i);
            	jump = patternLength;
            }
        }
        return indexesOfOccurences;  
	}
}
