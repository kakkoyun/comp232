import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * 
 * @author Kemal Akkoyun
 * @category Comp232/Projects
 * Project 8 * String Matching Algorithms.
 * Performance Test Cases.
 */

public class PerformanceTest {

	public static long getAnalysis(IStringMatcher algorithm, String text, String pattern)
	{
		long startTime;
		long endTime;

		startTime = System.currentTimeMillis();
		algorithm.matcher(text, pattern);
		endTime = System.currentTimeMillis();

		return endTime - startTime;
	}


	public static void main(String[] args) {		

		Naive  naive = new Naive();
		FSA fsa = new FSA(Util.makeAlphabet());
		KnuthMorrisPratt kmp = new KnuthMorrisPratt();
		BoyerMoore bm = new BoyerMoore();
		BoyerMooreGalil bmg = new BoyerMooreGalil();

		long naiveTime, fsaTime, kmpTime, bmTime, bmgTime;

		try {
			FileWriter fstream = new FileWriter("data.dat");
			BufferedWriter out = new BufferedWriter(fstream);

			// Read from a file.
			//InputStreamReader inputStream = new InputStreamReader(System.in);
			FileReader inputStream = new FileReader(System.getProperty("user.dir") + "/shakespeare.txt");
			//BufferedReader in = new BufferedReader(inputStream);

			Scanner scan = new Scanner(inputStream);
			//String text = bufRead.toString().trim().replaceAll(" ", "");

			/*
			 * 1. column stands for pattern that searched
			 * 2. stands for naive.
			 * 3. stands for fsa.
			 * 4. stands for knuth morris pratt.
			 * 5. stands for boyer moore.
			 * 6. stands for boyer moore with galil improvement.
			 */

			// Pattern array.
			String[] patternArray = {"cherubin", "concealed", "maim.", "our", "did win", 
					"chastity", "witchcraft", 
					"caged", "nor true nor kind. They sought their shame that so their shame did find",
					"stranger", "by", "opportunity of sharp revenge", 
					" to Rome, The people will accept whom he admits. TITUS. Tribunes, I thank you; and this suit I make,",
					"I give thee thanks in part of thy deserts",
					"true nobility Warrants these words in princely courtesy. SATURNINUS. Thanks, sweet Lavinia. Romans, let us go.",
			"traitorous haughty sons, Confederates all thus to dishonour me. Was there none else in Rome to make a stale But Saturnine? Full well, Andronicus"};

			while(scan.hasNext()){
				String text = scan.nextLine().trim().replaceAll(" ", "");
				for(String pattern : patternArray){
					//pattern = pattern.trim();
					naiveTime = getAnalysis(naive, text, pattern);
					fsaTime = getAnalysis(fsa, text, pattern);
					kmpTime = getAnalysis(kmp, text, pattern);
					bmTime = getAnalysis(bm, text, pattern);
					bmgTime = getAnalysis(bmg, text, pattern);

					out.write(String.format("%s\t\t %s\t\t %s\t\t %s\t\t\n",
							pattern,
							naiveTime,
							fsaTime,
							kmpTime,
							bmTime,
							bmgTime
							));
					out.flush();
					out.close();
				}
			}
			System.out.println("Done!");

		} catch (IOException e) {
			System.err.printf("Error: %s\n", e.getMessage());
		}
	}
}
