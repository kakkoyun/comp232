import java.util.ArrayList;


public interface IStringMatcher {
	
	/**
	 * 
	 * @param text Raw text to be processed, pattern will search in this text.
	 * @param pattern Substring that is going to be searched in the text.
	 * @return Index of First left-most occurence of given pattern in text.
	 */
	public int matcher(String text, String pattern);
	
	/**
	 * 
	 * @param text Raw text to be processed, pattern will search in this text.
	 * @param pattern Substring that is going to be searched in the text.
	 * @return A collection of indexes that pattern has matched i text.
	 */
	public ArrayList<Integer> mulMatcher(String text, String pattern);
}
