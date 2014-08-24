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

public class FSA implements IStringMatcher{
	
	// Position array.
	int[] posArray;
	// Alphabet.
	char[] alphabet;
	
	public FSA(String alphabet){
		// Alphabet of FSA, initialise it.
		this.alphabet = alphabet.toCharArray();
		// Position Array, generate it from Util class related method.
		this.posArray = Util.makePositionArray(this.alphabet);
	}
	
	// Transition function represented as an 2 dimensional int array therefore, states are represented as int numbers
	// -- also ASCII code equivalents used for characters.
	public int[][] computeTransitionFuction(String pattern){
		// Number of states.
		int numberOfStates = pattern.length();
		// Create a proper length Transition table.
		int[][] transition = new int[numberOfStates + 1][alphabet.length];
		// Initialise a Transition table.
		for(int q = 0; q <= numberOfStates; q++){
			String matchedSofar = pattern.substring(0, q);
			for(char a : alphabet){
				// The string that I match so far and add it new char and check it match? or should I go back another state that I past.
				int nextState = findSuffixState(pattern, matchedSofar + a);
				transition[q][findPositionOfChar(a)] = nextState;
			}
		}
		//printTransitionFunction(transition);
		return transition;
	}
	
	private int findPositionOfChar(char a){
		// position of given character in my position array according to alphabet.
		return posArray[(int)a];
	}
	
	// Finds proper length of suffix that matches from pattern, then return its index which means next state. 
	public int findSuffixState(String pattern, String toMatch){
		// The state that I should go next.
		int state = 0;
		for (int i=1; i <= pattern.length(); i++) {
			if (toMatch.endsWith(pattern.substring(0, i)))
				// Then I found my state
				state = i;
		}
		// Just give it and finish it.
		return state;
	}
	
	public int matcher(String text, String pattern){
		// initialise transition function.
		int[][] transition = computeTransitionFuction(pattern);
		// Represents current state of FSA.
		int q = 0;
		for(int i = 0; i<text.length(); i++){
			// So animate FSA by inputs from text.
			q = transition[q][findPositionOfChar(text.charAt(i))];
			// Yeah, I got a match!
			if(q == pattern.length()){
				// It is plus one because I have a initial state q0, it compromises result.
				return i - pattern.length() + 1;
			}
		}
		// Sorry mate, not this time!
		return -1;
	}
	
	// Just for a visual test!
	public void printTransitionFunction(int[][] transition){
		for(int[] arr : transition){
			for(int i : arr){
				System.out.print(i + " ");
			}
			System.out.println("");
		}
	}

	@Override
	public ArrayList<Integer> mulMatcher(String text, String pattern) {
		ArrayList<Integer> indexesOfOccurences = new ArrayList<Integer>();
		// initialise transition function.
				int[][] transition = computeTransitionFuction(pattern);
				// Represents current state of FSA.
				int q = 0;
				for(int i = 0; i<text.length(); i++){
					// So animate FSA by inputs from text.
					q = transition[q][findPositionOfChar(text.charAt(i))];
					// Yeah, I got a match!
					if(q == pattern.length()){
						// It is plus one because I have a initial state q0, it compromises result.
						indexesOfOccurences.add(i - pattern.length() + 1);
					}
				}
				return indexesOfOccurences;
	}
}
