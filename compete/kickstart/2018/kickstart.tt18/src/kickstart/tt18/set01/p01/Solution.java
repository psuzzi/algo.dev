package kickstart.tt18.set01.p01;

import java.util.Scanner;

/**
 * @see: https://codingcompetitions.withgoogle.com/codejam/round/0000000000000130/0000000000000523
 */
public class Solution {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while (t-- > 0) {
			// (a,b], nGuesses
			int a = in.nextInt();
			int b = in.nextInt();
			int n = in.nextInt();
			guess(a+1, b, in);
		}
	}

	private static void guess(int a, int b, Scanner in) {
		// round up integer division
		int q = (a + b) / 2;
		System.out.println(q);
		String s = in.next();
		if( s.equals("CORRECT")) {
			return;
		} else if (s.equals("TOO_SMALL")){
			guess(q+1, b, in);
		} else {
			guess(a, q-1, in);
		}
	}

}


