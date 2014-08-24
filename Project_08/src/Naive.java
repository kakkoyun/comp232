import java.util.ArrayList;


/**
 * 
 * @author Kemal Akkoyun
 * @category Comp232/Projects
 * Project 8 * String Matching Algorithms.
 * Naive algorithm mostly depends on Chris Stephenson's comp232 lecture notes and Introduction to algorithms, 3rd ed.
 * --- by Cormen. (Lecture Videos: video.cs.bilgi.edu.tr)
 * 
 */
public class Naive implements IStringMatcher{

	public int matcher(String text, String pattern){
		// represent strings as char array since arrays are iterable and has constant time access..
		char[] t = text.toCharArray();
		char[] p = pattern.toCharArray();
		// call and auxiliary functions that deals with char arrays.
		return findMatch(t, p); 
	}
	
	/**
	 * 
	 * @param text Raw text to be processed, pattern will search in this text.
	 * @param pattern Substring that is going to be searched in the text.
	 * @return Index of First left-most occurence of given pattern in text.
	 */

	public int findMatch(char[] text, char[] pattern){
		// look naively for all substrings.
		for(int i = 0; i<text.length - pattern.length + 1; i++){
			if(checkMatch(text,pattern,i)){
				return i;
			}
		}
		//return the first occurrence of pattern in text, otherwise -1.
		return -1;
	}

	/**
	 *
	 * @param text Raw text to be processed, pattern will search in this text.
	 * @param pattern Substring that is going to be searched in the text.
	 * @param i Current position of pointer in array.
	 * @return If it is a match or not
	 */
	public boolean checkMatch(char[] text, char[] pattern, int i){
		// Check whether pattern match for substring of text starting from given index.
		for(int j = 0; j<pattern.length; j++){
			if(text[i+j] != pattern[j]){
				return false;
			}
		}
		return true;
	}

	@Override
	public ArrayList<Integer> mulMatcher(String text, String pattern) {
		// represent strings as char array since arrays are iterable and has constant time access..
		char[] t = text.toCharArray();
		char[] p = pattern.toCharArray();
		// call and auxiliary functions that deals with char arrays.
		return findMulMatch(t, p); 
	}
	/*
	 * @param text Raw text to be processed, pattern will search in this text.
	 * @param pattern Substring that is going to be searched in the text.
	 * @return Index of First left-most occurence of given pattern in text.
	*/
	public ArrayList<Integer> findMulMatch(char[] text, char[] pattern){
		
		 ArrayList<Integer> indexesOfOccurences = new ArrayList<Integer>();
		// look naively for all substrings.
		for(int i = 0; i<text.length - pattern.length + 1; i++){
			if(checkMatch(text,pattern,i)){
				indexesOfOccurences.add(i);
			}
		}
		return indexesOfOccurences;
	}
}
