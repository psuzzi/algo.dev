package ks20.rc.p1;

import java.io.File;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * 
 * @author psuzzi
 * @see https://codingcompetitions.withgoogle.com/kickstart/round/000000000019ff43/00000000003379bb
 */
public class Solution {

	public static void main(String[] args) {
		scan(Solution.class, "in.txt", in -> {
			int t = in.nextInt();
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				int k = in.nextInt();
				int ns[] = new int[n];
				for(int j=0; j<n; j++) {
					ns[j] = in.nextInt();
				}
				System.out.print("Case #" + i + ": " );
				solve(ns, k);
			}
		});
	}
	
	// 1 2 3 7 9 3 2 1 8 3 2 1
	
	private static void solve(int[] ns, int k) {
		int count=0;
		
		int len=ns.length;
		
		int cdlen=0;
		
		for(int i=len-1; i>=0; i--) {
			if(ns[i]==1) {
				// count existing
				if(cdlen>=k) count++;
				// start a new sequence
				cdlen=1;
			} else 
				if( i<len-1 && cdlen>0 ) {
					if( ns[i]==ns[i+1]+1) {
						// +1
						cdlen++;
					} else {
						// break sequence
						// count existing
						if(cdlen>=k) count++;
						cdlen=0;
					}
				}
		}
		// count existing
		if(cdlen>=k) count++;
		
		System.out.printf("%s%n", count);
	}




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
