import java.util.ArrayList;

/**
 * 
 * @author Kemal Akkoyun
 * @category Comp232/Projects
 * Project 8 * String Matching Algorithms.
 * Algorithm mostly depends on Chris Stephenson's comp232 lecture notes and Introduction to algorithms, 3rd ed.
 * --- by Cormen. (Lecture Videos: video.cs.bilgi.edu.tr)
 * 
 */
public class CormenKnuthMorrisPratt implements IStringMatcher{

	public ArrayList<Integer> mulMatcher(String text, String pattern){
		// Result collector.
		ArrayList<Integer> indexesOfOccurences = new ArrayList<Integer>();
		// Pattern length.
		int patternLength = pattern.length();
		// Compute prefix.
		int[] prefix = computePrefixFunction(pattern);
		// Number of characters matched so far.
		int state = 0;
		for(int i = 0; i < text.length(); i++){
			while(state > 0 && pattern.charAt(state) != text.charAt(i)){
				// Go back int the pattern to another potential match.
				state = prefix[state - 1];
			}
			if(pattern.charAt(state) == text.charAt(i)){
				state = state + 1; // next character matches!
			}
			if(state == patternLength){ // is all of P match!? 
				// Match !
				state = prefix[state - 1]; // look for next match!
				// Since I have a initial state, add one.
				indexesOfOccurences.add(i - patternLength + 1); 
			}
		}
		return indexesOfOccurences;
		// I am not sure about here!
	}

	private static int[] computePrefixFunction(String pattern) {
		// Used several times so cache it.
		int pLength = pattern.length();
		// Prefix table.
		int[] prefixTable = new int[pLength];
		// Initial state has no match, since initial char hasn't got any prefix!
		// prefixTable[0] = 0;
		// State that stores jumps
		int state = 0;
		for(int q = 1; q < pLength; q++){
			// While I am not at initial state end hasn't got a match
			while(state > 0 && prefixTable[state] != prefixTable[q]){
				// Go where I should be!
				state = prefixTable[state - 1];
			}
			// Look for more characters, maybe I have more match!
			if(prefixTable[state] == prefixTable[q]){
				state = state + 1;
			}
			// So that's all then cache it to my table!
			prefixTable[q] = state;
		}
		// Return completed table.
		return prefixTable;
	}

	@Override
	public int matcher(String text, String pattern) {
		// Pattern length.
		int patternLength = pattern.length();
		// Compute prefix.
		int[] prefix = computePrefixFunction(pattern);
		// Number of characters matched so far.
		int state = 0;
		for(int i = 0; i < text.length(); i++){
			while(state > 0 && pattern.charAt(state) != text.charAt(i)){
				// Go back int the pattern to another potential match.
				state = prefix[state - 1];
			}
			if(pattern.charAt(state) == text.charAt(i)){
				state = state + 1; // next character matches!
			}
			if(state == patternLength){ // is all of P match!? 
				// Match !
				state = prefix[state - 1]; // look for next match!
				// Since I have a initial state, add one.
				return i - patternLength + 1; 
			}
		}
		return -1;
	}
}
