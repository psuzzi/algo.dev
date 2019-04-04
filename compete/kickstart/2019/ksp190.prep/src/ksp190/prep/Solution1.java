package ksp190.prep;

import java.util.Scanner;

/**
 * Solution for the <em>Number Guessing (6pts, 12pts)</em> Example of solution.
 * The solution class shall be called Solution (with exactly that
 * capitalization). It must contain a public main method.
 * 
 * @author psuzzi
 * @see https://codingcompetitions.withgoogle.com/kickstart/round/0000000000051060/00000000000588f4
 *
 */
public class Solution1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while (t-- > 0) {
			// (a,b], nGuesses
			int a = in.nextInt();
			int b = in.nextInt();
			int n = in.nextInt();
			guess(a, b, n, in);
		}
	}

	private static void guess(int lo, int hi, int n, Scanner in) {
		int guess = hi - (int) ((hi - lo) / 2);
		System.out.println(guess);
		String read = in.next();
		
		if(read.equals("CORRECT")) {
			return;
		} else if (read.equals("TOO_SMALL")){
			guess(guess, hi, n - 1, in);			
		} else {
			guess(lo, guess - 1, n - 1, in);			
		}
	}

}
