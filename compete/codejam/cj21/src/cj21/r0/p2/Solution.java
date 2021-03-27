package cj21.r0.p2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @see https://codingcompetitions.withgoogle.com/codejam/round/000000000043580a/00000000006d1145
 * @author pasu
 */
public class Solution {

	public static void main(String[] args) {
		Solution sol = new Solution();
//		try(Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)))){
		try(Scanner in = new Scanner(new BufferedReader(new InputStreamReader(Solution.class.getResourceAsStream("in.txt"))))){	
			int t = in.nextInt();
			for( int it = 1; it<=t; it++ ) {
				sol.x = in.nextInt();
				sol.y = in.nextInt();
				sol.mural = in.next();
				System.out.println("Case #" + it + ": " + sol.solve());
			}
		}

	}
	
	int x;
	int y;
	String mural;
	
	private int solve() {
		int cc = 0, jc = 0;
		char [] arr = mural.toCharArray();
		
		// no 'C' or no 'J' cost is zero
		for(int i=0; i<arr.length; i++) {
			if(arr[i]=='C') cc++;
			if(arr[i]=='J') jc++;
		}
		if(cc==0 || jc==0)
			return 0;
		
		
		int cost = 0;
		for(int i=0; i<arr.length-1; i++) {
			if( arr[i]=='C' && arr[i+1]=='J' ) {
				cost += x;
				continue; 
			}
			if( arr[i]=='J' && arr[i+1]=='C' ) {				
				cost += y;
				continue; 
			}
			if( arr[i]=='C' && arr[i+1]=='?' ) {
				arr[i+1] = 'C';
				continue;
			}
			if( arr[i]=='J' && arr[i+1]=='?' ) {
				arr[i+1] = 'J';
				continue;
			}
			if( arr[i]=='?' && arr[i+1]=='J' ) {
				arr[i] = 'J';
				continue;
			}
			if( arr[i]=='?' && arr[i+1]=='C' ) {
				arr[i] = 'C';
				continue;
			}
			if( arr[i]=='?' && arr[i+1]=='?' ) {
				continue;
			}
				
		}
		
		return cost;
	}



}
