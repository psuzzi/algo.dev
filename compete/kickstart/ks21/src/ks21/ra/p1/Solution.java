package ks21.ra.p1;


import java.io.File;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Solution for the K-Goodness String, KS 2021 A 1
 * @see https://codingcompetitions.withgoogle.com/kickstart/round/0000000000436140/000000000068cca3
 * @author psuzzi
 *
 */
public class Solution {

	public static void main(String[] args) {
//		scan(null, null, in ->{
		scan(Solution.class, "in.txt", in ->{
			int T = in.nextInt();
			for (int k = 1; k <= T; ++k) {
				int N = in.nextInt();
				int K = in.nextInt();
				String S = in.next();
				System.out.printf("Case #%s: %s%n", k, solve(N, K, S));
			}
		});
	}
	
	
	private static int solve(int n, int k, String s) {
		int score = 0;
		char[] chars = s.toCharArray();
		for(int i=0; i<n/2; i++) {
			if(chars[i]!=chars[n-1-i]) {
				score++;
			}
		}
		return Math.abs(k-score);
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
