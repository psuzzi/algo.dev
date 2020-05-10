package codejam20.qual.p2;

import java.io.File;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * CodeJam 20, Qualification, Problem 2
 * 
 * @author psuzzi
 * @see https://codingcompetitions.withgoogle.com/codejam/round/000000000019fd27/0000000000209a9f
 */
public class Solution {

	public static void main(String[] args) {
		scan(Solution.class, "in.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				String s = in.next();
				System.out.println("Case #" + i + ": " + solve(s));
			}
		});
	}

	private static String solve(String s) {
		StringBuilder sb = new StringBuilder();
		int n;
		int level = 0;
		for (char c : s.toCharArray()) {
			n = c - '0';
			// open
			for (; level < n; level++)
				sb.append('(');
			// close
			for (; level > n; level--)
				sb.append(')');

			sb.append(n);
		}
		// close remaining
		for (; level > 0; level--)
			sb.append(')');

		return sb.toString();
	}

	/**
	 * Scan System.in, a resource on classpath or a given file depending on params
	 */
	public static void scan(Class<?> cl, String path, Consumer<Scanner> consumer) {
		try (Scanner sc = (path == null) ? new Scanner(System.in)
				: (cl == null) ? new Scanner(new File(path)) : new Scanner(cl.getResourceAsStream(path))) {
			consumer.accept(sc);
		} catch (Exception e) {
			System.err.println("ERROR in " + cl + " < " + path);
			e.printStackTrace();
		}
	}

}
