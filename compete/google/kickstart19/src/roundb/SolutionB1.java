package roundb;

import java.util.*;
import java.util.function.Consumer;
import java.io.*;

/**
 * Building Palindromes <p>
 * 
 * @author psuzzi
 *
 */
public class SolutionB1 {
	
	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("input/roundb/input1.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				int q = in.nextInt();
				String s = in.next();// n blocks
				PCount pc = new PCount(s);
				for(int k=0; k<q; k++) {
					pc.qs.add(new Integer[] {in.nextInt(), in.nextInt()});
				}
				System.out.println("Case #" + i + ": " + pc.solve());
			}
		});
		//in.close();
	}
	
	static class PCount{
		private char[] chars;
		List<Integer[]> qs = new ArrayList<>();
		List<Integer[]> prefixsums = new ArrayList<>();

		/**
		 * Initialize ancompute the prefix sums 
		 * @param string
		 */
		public PCount(String string) {
			this.chars = string.toCharArray();
			// all zeroes at zero index
			prefixsums.add(new Integer[26]);
			// compute the prefix sums
//			for(char c)
			
		}

		public String solve() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	/**
	 * For the given string and a set of queries representing a substring, 
	 * count the number of substrings that are a palindrome. <p>
	 */
	private int solve2(int n, int q, String string, List<Integer[]> queries) {
		return 0;
	}

	/**
	 * For the given string and a set of queries representing a substring, 
	 * count the number of substrings that are a palindrome. <p>
	 */
	private static int solve(int n, int q, String string, List<Integer[]> queries) {
		int solvable = 0;
		// for each query get the limits: inclusive bounds of the query substring
		for(Integer [] limits : queries) {
			String sub = string.substring(limits[0]-1, limits[1]);
//			System.out.println(k++ + " " + sub);
			char[] chars = sub.toCharArray();
			int [] count = new int[26];
			for(char c:chars) {
				count[c-'A']++;
			}
			int countOdd = 0;
			for(int i:count) {
				if(i%2!=0) {
					countOdd++;
				}
			}
			if(chars.length%2==0) {
				// even number of chars
				if(countOdd==0) {
					solvable++;
				}
			} else {
				// odd number of chars
				if(countOdd<=1) {
					solvable++;
				}
			}
		}
		// TODO Auto-generated method stub
		return solvable;
	}

	static void scan(String filename, Consumer<Scanner> consumer) {
		try (Scanner sc = new Scanner(new File(filename))) {
			consumer.accept(sc);
		} catch (FileNotFoundException e) {
			System.err.printf("Error scanning %s", filename);
			e.printStackTrace();
		}
	}
	
}