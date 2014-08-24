import java.util.ArrayList;

/**
 * 
 * @author Kemal Akkoyun
 * @category Comp232/Projects
 * Project 8 * String Matching Algorithms.
 *	Algorithm from Knuth Morris Pratt orginal paper.
 *	Source: http://courses.cs.bilgi.edu.tr/file.php/271/Knuth77.pdf
 * 
 */

public class KnuthMorrisPratt implements IStringMatcher{

	public ArrayList<Integer> mulMatcher(String text, String pattern)
	{       
		// Collector for index of matches.
		ArrayList<Integer> indexesOfOccurences = new ArrayList<Integer>();
		// Prefix table for KMP.
		int[] prefixTable = computePrefix(pattern);
		int i = 0;
		int j = 0;
		while ( i < text.length())
		{
			while ( j >= 0 && text.charAt(i) != pattern.charAt(j)){
				j = prefixTable[j];
			}
			i++;
			j++;
			if ( j == pattern.length()) // a match is found
			{
				indexesOfOccurences.add(i - pattern.length());
				j = prefixTable[j];
			}
		}
		return indexesOfOccurences;
	}
	
	/**
	 * A function to compute prefix table of pattern.
	 * @param pattern Substring that is going to be searched in the text.
	 * @return A prefix table which tells where to go.
	 */
	private int[] computePrefix(String pattern)
	{
		int i = 0;
		int j = -1;
		int[] prefixTable = new int[pattern.length() + 1];
		prefixTable[i]=j;
		while (i < pattern.length())
		{
			while (j >= 0 && pattern.charAt(i) != pattern.charAt(j)){
				j = prefixTable[j];
			}
			i++;
			j++;
			prefixTable[i]=j;
		}
		return prefixTable;
	}

	@Override
	public int matcher(String text, String pattern) {
		// Prefix table for KMP.
		int[] prefixTable = computePrefix(pattern);
		int i = 0;
		int j = 0;
		while ( i < text.length())
		{
			while ( j >= 0 && text.charAt(i) != pattern.charAt(j)){
				j = prefixTable[j];
			}
			i++;
			j++;
			if ( j == pattern.length()) // a match is found
			{
				// Useless for returning first match.
				j = prefixTable[j];
				return i - pattern.length();
				
			}
		}
		return -1;
	}
}
