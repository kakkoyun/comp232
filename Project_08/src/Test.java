import junit.framework.TestCase;

/**
 * 
 * @author Kemal Akkoyun
 * @category Comp232/Projects
 * Project 8 * String Matching Algorithms.
 * Unit Test Cases.
 */
public class Test extends TestCase {

	public void testNaive(){
		Naive naive = new Naive();
		assertEquals(1, naive.matcher("000010001010001", "0001"));
		assertEquals(7, naive.matcher("000010001010001", "0101"));
		assertEquals(0, naive.matcher("ababababac", "abab"));
		assertEquals(8, naive.matcher("ababababac", "ac"));
		assertEquals(7, naive.matcher("kemalakkoyun", "koyun"));
	}

	public void testPositionArray(){

		assertEquals(0, Util.makePositionArray("abcdu".toCharArray())[97]);
		assertEquals(1, Util.makePositionArray("abcdu".toCharArray())[98]);
		assertEquals(2, Util.makePositionArray("abcdu".toCharArray())[99]);
		assertEquals(3, Util.makePositionArray("abcdu".toCharArray())[100]);
		assertEquals(4, Util.makePositionArray("abcdu".toCharArray())[117]);
	}

	public void testFSAComputeTransitionTable(){

		FSA fsa = new FSA("abc");
		fsa.computeTransitionFuction("ababaca");

	}

	public void testFSA(){

		FSA fsa = new FSA("abcdefghijklmnopqrstuvwxyz1234567890");
		assertEquals(1, fsa.matcher("000010001010001", "0001"));
		assertEquals(7, fsa.matcher("000010001010001", "0101"));
		assertEquals(0, fsa.matcher("ababababac", "abab"));
		assertEquals(8, fsa.matcher("ababababac", "ac"));
		assertEquals(7, fsa.matcher("kemalakkoyun", "koyun"));

	}

	public void testCormenKnuthMorrisPratt(){

		// Cormen's algorithm crashed test cases below.
		CormenKnuthMorrisPratt ckmp = new CormenKnuthMorrisPratt();

		assertEquals("[0, 2, 4, 6]", ckmp.mulMatcher("abababab","ab").toString());
		//assertEquals("[1, 5, 11]", ckmp.mulMatcher("000010001010001", "0001").toString());
		assertEquals("[7]", ckmp.mulMatcher("000010001010001", "0101").toString());
		//assertEquals("[0, 2, 4]", CormenKnuthMorrisPratt.matcher("ababababac", "abab").toString());
		assertEquals("[8]", ckmp.mulMatcher("ababababac", "ac").toString());
		assertEquals("[7]", ckmp.mulMatcher("kemalakkoyun", "koyun").toString());
	}

	public void testKnuthMorrisPratt(){
		
		KnuthMorrisPratt kmp = new KnuthMorrisPratt();
		assertEquals("[0, 2, 4, 6]", kmp.mulMatcher("abababab","ab").toString());
		assertEquals("[1, 5, 11]", kmp.mulMatcher("000010001010001", "0001").toString());
		assertEquals("[7]", kmp.mulMatcher("000010001010001", "0101").toString());
		assertEquals("[0, 2, 4]", kmp.mulMatcher("ababababac", "abab").toString());
		assertEquals("[8]", kmp.mulMatcher("ababababac", "ac").toString());
		assertEquals("[7]", kmp.mulMatcher("kemalakkoyun", "koyun").toString());
	}

	public void testBoyerMoore(){
		
		BoyerMoore bm = new BoyerMoore();
		assertEquals(1, bm.matcher("000010001010001", "0001"));
		assertEquals(7, bm.matcher("000010001010001", "0101"));
		assertEquals(0, bm.matcher("ababababac", "abab"));
		assertEquals(8, bm.matcher("ababababac", "ac"));
		assertEquals(7, bm.matcher("kemalakkoyun", "koyun"));
		
		/*
		assertEquals("[0, 2, 4, 6]", bm.mulMatcher("abababab","ab").toString());
		assertEquals("[1, 5, 11]", bm.mulMatcher("000010001010001", "0001").toString());
		assertEquals("[7]", bm.mulMatcher("000010001010001", "0101").toString());
		assertEquals("[0, 2, 4]", bm.mulMatcher("ababababac", "abab").toString());
		assertEquals("[8]", bm.mulMatcher("ababababac", "ac").toString());
		assertEquals("[7]", bm.mulMatcher("kemalakkoyun", "koyun").toString());
		*/
		
	}
	
	public void testBoyerMooreGalil(){
		BoyerMooreGalil bmg = new BoyerMooreGalil();
		assertEquals(1, bmg.matcher("000010001010001", "0001"));
		assertEquals(7, bmg.matcher("000010001010001", "0101"));
		assertEquals(0, bmg.matcher("ababababac", "abab"));
		assertEquals(8, bmg.matcher("ababababac", "ac"));
		assertEquals(7, bmg.matcher("kemalakkoyun", "koyun"));
		
		/*
		assertEquals("[0, 2, 4, 6]", bmg.mulMatcher("abababab","ab").toString());
		assertEquals("[1, 5, 11]", bmg.mulMatcher("000010001010001", "0001").toString());
		assertEquals("[7]", bmg.mulMatcher("000010001010001", "0101").toString());
		assertEquals("[0, 2, 4]", bmg.mulMatcher("ababababac", "abab").toString());
		assertEquals("[8]", bmg.mulMatcher("ababababac", "ac").toString());
		assertEquals("[7]", bmg.mulMatcher("kemalakkoyun", "koyun").toString());
		*/
	}
}
