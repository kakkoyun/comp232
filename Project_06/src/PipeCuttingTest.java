
import java.util.Random;

import junit.framework.TestCase;


public class PipeCuttingTest extends TestCase {
	
	// An example price table.
	int[] price = {1,5,8,9,10,17,17,20,24,30};
	
	// A method to create an integer array.
	public int[] createTestArray(int n){
		Random rnd = new Random();
		int[] result = new int[n];
		result[0] = 1;
		for(int i = 1; i < n; i++){
			result[i] = result[i - 1] + rnd.nextInt(10);
		}
		return result;
	}
	
	/**
	 * This is a naive performance test for pipe cutting algorithm.
	 */
	public void testTimePerformance(){
		// An example array for performance test.
		// Gave a number to test for that length of pipe.
		int[] performancePrice = createTestArray(30);
		
		for(int i = 0; i < performancePrice.length; i++){
			
			long startTime = System.currentTimeMillis();
			PipeCutting.cutRod(performancePrice, i);
			long finishTime = System.currentTimeMillis();

			long startTime1 = System.currentTimeMillis();
			PipeCutting.memoizedCutRod(performancePrice, i);
			long finishTime1 = System.currentTimeMillis();

			long startTime2 = System.currentTimeMillis();
			PipeCutting.bottomUpCutRod(performancePrice, i);
			long finishTime2 = System.currentTimeMillis();
			
		System.out.println("Time of Cut Rod for lenght " + (i+1) +" : "+(finishTime-startTime)+ " ms");	
		System.out.println("Time of Memoized Cut Rod for lenght " + (i+1) +" : "+(finishTime1-startTime1)+ " ms");
		System.out.println("Time of Bottom Up Cut Rod for lenght " + (i+1) +" : "+(finishTime2-startTime2)+ " ms");		
	}
}
	
	public void testCutRod(){
		
		assertEquals(1, PipeCutting.cutRod(price, 1));
		assertEquals(5, PipeCutting.cutRod(price, 2));
		assertEquals(8, PipeCutting.cutRod(price, 3));
		assertEquals(10, PipeCutting.cutRod(price, 4));
		assertEquals(13, PipeCutting.cutRod(price, 5));
		assertEquals(17, PipeCutting.cutRod(price, 6));
		assertEquals(18, PipeCutting.cutRod(price, 7));
		assertEquals(22, PipeCutting.cutRod(price, 8));
		assertEquals(25, PipeCutting.cutRod(price, 9));
		assertEquals(30, PipeCutting.cutRod(price, 10));
		
	}
	
	public void testMemoizedCutRod(){
		
		assertEquals(1, PipeCutting.memoizedCutRod(price, 1));
		assertEquals(5, PipeCutting.memoizedCutRod(price, 2));
		assertEquals(8, PipeCutting.memoizedCutRod(price, 3));
		assertEquals(10, PipeCutting.memoizedCutRod(price, 4));
		assertEquals(13, PipeCutting.memoizedCutRod(price, 5));
		assertEquals(17, PipeCutting.memoizedCutRod(price, 6));
		assertEquals(18, PipeCutting.memoizedCutRod(price, 7));
		assertEquals(22, PipeCutting.memoizedCutRod(price, 8));
		assertEquals(25, PipeCutting.memoizedCutRod(price, 9));
		assertEquals(30, PipeCutting.memoizedCutRod(price, 10));
		
	}
	
	public void testbottomUpCutRod(){
		
		assertEquals(1, PipeCutting.bottomUpCutRod(price, 1));
		assertEquals(5, PipeCutting.bottomUpCutRod(price, 2));
		assertEquals(8, PipeCutting.bottomUpCutRod(price, 3));
		assertEquals(10, PipeCutting.bottomUpCutRod(price, 4));
		assertEquals(13, PipeCutting.bottomUpCutRod(price, 5));
		assertEquals(17, PipeCutting.bottomUpCutRod(price, 6));
		assertEquals(18, PipeCutting.bottomUpCutRod(price, 7));
		assertEquals(22, PipeCutting.bottomUpCutRod(price, 8));
		assertEquals(25, PipeCutting.bottomUpCutRod(price, 9));
		assertEquals(30, PipeCutting.bottomUpCutRod(price, 10));
		
	}
	
	public void testextendedBottomUpCutRod(){
		
		assertEquals("1", PipeCutting.printActualCut(price, 1));
		assertEquals("2", PipeCutting.printActualCut(price, 2));
		assertEquals("3", PipeCutting.printActualCut(price, 3));
		assertEquals("2 2", PipeCutting.printActualCut(price, 4));
		assertEquals("2 3", PipeCutting.printActualCut(price, 5));
		assertEquals("6", PipeCutting.printActualCut(price, 6));
		assertEquals("1 6", PipeCutting.printActualCut(price, 7));
		assertEquals("2 6", PipeCutting.printActualCut(price, 8));
		assertEquals("3 6", PipeCutting.printActualCut(price, 9));
		assertEquals("10", PipeCutting.printActualCut(price, 10));
		
	}
}
