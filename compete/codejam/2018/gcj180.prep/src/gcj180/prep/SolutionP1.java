package gcj180.prep;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SolutionP1 {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("input/input1.txt"));
		int t = in.nextInt(); 
		for (int i = 1; i <= t; ++i) {
			int n = in.nextInt();// N parties
			int [] ps = new int[n];
			for(int j=0; j<n; j++) {
				// P1 .. Pn: n. of sen per Party
				ps[j] = in.nextInt();
			}
			//System.out.println("Case #" + i + ": " + (n + Arrays.toString(ps)) + " ");
			System.out.print("Case #" + i + ": ");
			int sum = 0;
			for(int pi:ps)
				sum += pi;
			solve(n, ps, sum);
			System.out.println();
		}
		in.close();
	}

	/**
	 * Evacuate so that no party has ever a majority.
	 * @param n n parties
	 * @param ps n of MP per party
	 * @param pi tot n of MPs
	 * @return
	 */
	private static void solve(int n, int[] ps, int pi) {
		// find i, j to evacuate
		int [] ij = find(n, ps);
		int i = ij[0];
		int j = ij[1];
		if(i>=0 || j>0) {
			if(i>=0) {
				ps[i]--;
			}
			if(j>=0) {
				ps[j]--;
			}
//			solve(n, ps);
		}
		
		if(ij.length==2) {
			ps[ij[0]]--;
			ps[ij[1]]--;
//			solve(n, ps);
		} else {
			// 
			ps[ij[0]]--;
//			solve(n, ps);
		}
		
		System.out.print(" " + ('A'+i) + ('A'+j));
		// last evac: two
//		recurse(n, ps);
		
		
		// last ev should be two of different parties 
		//return "" + (n + Arrays.toString(ps));
		
	}

	private static int[] find(int n, int[] ps) {
		// TODO Auto-generated method stub
		return null;
	}

	private static void recurse(int n, int[] ps) {
		int sum = 0;
	}
}