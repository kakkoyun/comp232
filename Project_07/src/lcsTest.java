
import junit.framework.TestCase;

/**
 * Comp 232 - Project 7
 * @author Kemal Akkoyun
 * Longest Common Subsequence
 * Test Class.
 */


public class lcsTest extends TestCase {

		public void testLcsNaive() {
			assertEquals(5, LongestCommonSubsequence.lcsNaive("kemal", "kemalasda"));
			assertEquals(16, LongestCommonSubsequence.lcsNaive("aatgcusgtcuattcg", "aatgcusastsggatgtcuattcg"));
			assertEquals(0, LongestCommonSubsequence.lcsNaive("", ""));
			assertEquals(1, LongestCommonSubsequence.lcsNaive("12", "21"));
			assertEquals(0, LongestCommonSubsequence.lcsNaive("", "kemalasda"));
		}
		
		public void testLcsNaiveMemoized() {
			assertEquals(5, LongestCommonSubsequence.lcsNaiveMemoized("kemal", "kemalasda"));
			assertEquals(16, LongestCommonSubsequence.lcsNaiveMemoized("aatgcusgtcuattcg", "aatgcusastsggatgtcuattcg"));
			assertEquals(0, LongestCommonSubsequence.lcsNaiveMemoized("", ""));
			assertEquals(1, LongestCommonSubsequence.lcsNaiveMemoized("12", "21"));
			assertEquals(0, LongestCommonSubsequence.lcsNaiveMemoized("", "kemalasda"));
		}
		public void testLcsBottomUp() {
			assertEquals("kemal", LCSbottomUp.lcs("kemal", "kemalasda"));
			assertEquals("aatgcusgtcuattcg",LCSbottomUp.lcs("aatgcusgtcuattcg", "aatgcusastsggatgtcuattcg"));
			assertEquals("", LCSbottomUp.lcs("", ""));
			assertEquals("1", LCSbottomUp.lcs("12", "21"));
			assertEquals("", LCSbottomUp.lcs("1", "kemalasda"));
		}
}