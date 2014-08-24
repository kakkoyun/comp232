import java.util.ArrayList;


public class BoyerMooreGalil implements IStringMatcher {
	
	private int[] rightPositionArray;  // Position Array of right most occurence of chars at pattern.
	
	public int matcher(String text, String pattern){
		// Make a position array from pattern to know and access positions of char at pattern.
        rightPositionArray = Util.makePositionArray(pattern.toCharArray());
        int patternLength = pattern.length();
        int textLength = text.length();
        int jump;
        int l;
        for (int i = 0; i <= textLength - patternLength; i += jump) {
            jump = 0;
            l = 0;
            for (int j = patternLength - 1; j >= l; j--) {
            	if (pattern.charAt(j) != text.charAt(i+j)) {
                    jump = Math.max(1, j - rightPositionArray[text.charAt(i+j)]);
                    break;
                }
            }
            if (jump == l){ 
            	l = i;
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
        int l = 0;
        for (int i = 0; i <= textLength - patternLength; i += jump) {
            jump = 0;
            for (int j = patternLength - 1; j >= l; j--) {
            	if (pattern.charAt(j) != text.charAt(i+j)) {
                    jump = Math.max(1, j - rightPositionArray[text.charAt(i+j)]);
                    break;
                }
            }
            if (jump == l){ 
            	l = patternLength - jump + 1;
            	indexesOfOccurences.add(i);
            }
        }
        return indexesOfOccurences;  
	}
}
