package lc.s1.p00;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;

/**
 * CodeJam 20, Qualification, Problem 1
 * 
 * @author psuzzi
 * @see https://codingcompetitions.withgoogle.com/codejam/round/000000000019fd27/000000000020993c
 */
public class Solution {

	public static void main(String[] args) {
		scan(Solution.class, null, in -> {
			int t = in.nextInt();
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				int[][] matrix = new int[n][n];
				for (int r = 0; r < n; r++) {
					for (int c = 0; c < n; c++) {
						matrix[r][c] = in.nextInt();
					}
				}
				System.out.println("Case #" + i + ": " + solve(n, matrix));
			}
		});
	}

	public static String solve(int n, int[][] matrix) {
		int k = 0, r = 0, c = 0;
		Set<Integer> set = new HashSet<>();

		for (int i = 0; i < n; i++)
			k += matrix[i][i];

		for (int ri = 0; ri < n; ri++) {
			set.clear();
			for (int ci = 0; ci < n; ci++) {
				set.add(matrix[ri][ci]);
			}
			if (set.size() != n) {
				r++;
			}
		}

		for (int ci = 0; ci < n; ci++) {
			set.clear();
			for (int ri = 0; ri < n; ri++) {
				set.add(matrix[ri][ci]);
			}
			if (set.size() != n) {
				c++;
			}
		}

		return String.format("%s %s %s", k, r, c);
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