package cj21.r0.p2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @see https://codingcompetitions.withgoogle.com/codejam/round/000000000043580a/00000000006d1145
 * @author pasu
 *
 */
public class Solution2 {

	public static void main(String[] args) {
		Solution2 sol = new Solution2();
//		try(Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)))){
		try(Scanner in = new Scanner(new BufferedReader(new InputStreamReader(Solution2.class.getResourceAsStream("in.txt"))))){	
			int t = in.nextInt();
			for( int it = 1; it<=t; it++ ) {
				int x = in.nextInt();
				int y = in.nextInt();
				String mural = in.next();
				System.out.println("Case #" + it + ": " + sol.solve(x, y, mural));
			}
		}

	}
	
	 
	int minCost;
	int x;
	int y;

	private int solve(int x, int y, String mural) {
		this.x = x;
		this.y = y;
		
		minCost = Integer.MAX_VALUE;
		backtrack(mural.toCharArray(),0, 0);
		return minCost;
	}

	private void backtrack(char[] arr, int i, int cost) {
		if(i==arr.length) {
			// base case
			minCost = Math.min(minCost, cost);
//			System.out.println(new String(arr) + " " + cost);
			return;
		}
		
		
		if(arr[i]=='?') {
			
			arr[i] = 'C';
			int dc = cost(arr,i);
			backtrack(arr, i+1, cost + dc);
			arr[i] = 'J';
			int dj = cost(arr,i);
			backtrack(arr, i+1, cost + dj);
			arr[i] = '?';
		} else {
			backtrack(arr, i+1, cost + cost(arr,i));			
		}
	}
	
	final int cost(char[] arr, int i) {
		if(i>0) {
			if( arr[i-1] == 'C' && arr[i]=='J')
				return x;
			if( arr[i-1] == 'J' && arr[i]=='C')
				return y;
		}
		return 0;
	}

	


}
