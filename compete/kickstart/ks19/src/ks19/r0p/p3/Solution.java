package ks19.r0p.p3;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;


public class Solution {
	
	public static void main(String[] args) {
//		scan(null, null, in ->{
		scan(Solution.class, "in.txt", in ->{
			int T = in.nextInt();
			for (int nCase = 1; nCase <= T; ++nCase) {
				int N = in.nextInt();// n params
				int K = in.nextInt();// wakeup calls
				int x1 = in.nextInt();
				int y1 = in.nextInt();
				int C = in.nextInt();
				int D = in.nextInt();
				int E1 = in.nextInt();
				int E2 = in.nextInt();
				int F = in.nextInt();
				
				
				
				System.out.printf("Case #%s: %s%n", nCase, solve(N, K, x1, y1, C, D, E1, E2, F));
			}
		});
	}
	
	private static Object solve(int N, int K, int x1, int y1, int C, int D, int E1, int E2, int F) {
		
		
		int[] as = new int[N];
		as[0] = (x1+y1) % F;
		for(int i=1; i<N; i++) {
			as[i] = ( (C+D)*as[i-1] + E1 + E2 ) %F;
		}
		
		int result = 0;
		
		for(int i=1; i<K; i++) {			
			for( int l=0; l<N; l++) {
				for(int r=l; r<N; r++) {
					List<Integer> sub = new ArrayList<>();
					int sum=0;
					int exp = 0;
					for(int j=l; j<=r; j++) {
						result = ( result + ( as[j] * (int) Math.pow(j-l+1, i) ) %F ) %F;
//					sum += as[k] *  ;
						sub.add(as[j]);
					}
//					System.out.println(sum + " <- " + sub);
					// TODO FIXME is WRONG
				}
			}
		}
		
		
		return result;
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
