package ks19.r0;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Example of solution. The solution class shall be called Solution (with
 * exactly that capitalization). It must contain a public main method.
 * 
 * @author psuzzi
 *
 */
public class SolutionExample {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		// Scanner has functions to read ints, longs, strings, chars, etc.
		int t = in.nextInt();
		for (int i = 1; i <= t; ++i) {
			int n = in.nextInt();
			int m = in.nextInt();
			System.out.println("Case #" + i + ": " + (n + m) + " " + (n * m));
		}
	}

}
