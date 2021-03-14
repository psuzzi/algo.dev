package ks19.r0p.p2;

import java.io.File;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Solution for the Mural problem, KS 2019 practice
 * @see https://codingcompetitions.withgoogle.com/kickstart/round/0000000000051060/0000000000058b89
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
				int [] scores = new int[N];
				char [] chars = in.next().trim().toCharArray();
				for(int i =0; i<chars.length; i++) {
					scores[i] = chars[i] - '0';
				}
				System.out.printf("Case #%s: %s%n", k, solve(N, scores));
			}
		});
	}
	
	
	private static int solve(int n, int[] scores) {
		int winLen = (int) (n+1)/2;
		int sum = 0, max = 0;
		for(int i=0; i<winLen; i++) {
			sum+=scores[i];
		}
		max = sum;
		for(int i=0; i<n-winLen; i++) {
			sum=sum-scores[i] + scores[i+winLen];
			max = Math.max(max, sum);
		}
		
		return max;
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
