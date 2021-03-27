package cj21.r0.p2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @see https://codingcompetitions.withgoogle.com/codejam/round/000000000043580a/00000000006d1145
 * @author pasu
 * @see: https://www.youtube.com/watch?v=u3hIjnnkwww
 */
public class Solution3 {

	public static void main(String[] args) {
		Solution3 sol = new Solution3();
//		try(Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)))){
		try(Scanner in = new Scanner(new BufferedReader(new InputStreamReader(Solution3.class.getResourceAsStream("in.txt"))))){	
			int t = in.nextInt();
			for( int it = 1; it<=t; it++ ) {
				int x = in.nextInt();
				int y = in.nextInt();
				String mural = in.next();
				System.out.println("Case #" + it + ": " + sol.solve(x, y, mural));
			}
		}

	}
	
	private int solve(int x, int y, String s) {
		int jc = 0, cc = 0;
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)=='C' )
				cc++;
			if(s.charAt(i)=='J')
				jc++;
			if(jc==0 || cc==0)
				return 0;
		}
		int sum=0;
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)=='C' && s.charAt(i+1)=='J') {
				sum+=x;
				continue;
			} 
			if(s.charAt(i)=='J' && s.charAt(i+1)=='C') {
				sum+=y;
				continue;
			}
			if(s.charAt(i)=='C' && s.charAt(i+1)=='?') {
				s=s.substring(0, i+1) + "C" + s.substring(i+2); 
				continue;
			}
			if(s.charAt(i)=='J' && s.charAt(i+1)=='?') {
				s=s.substring(0, i+1) + "J" + s.substring(i+2); 
				continue;
			}
			if(s.charAt(i)=='?' && s.charAt(i+1)=='C') {
				s=s.substring(0, i) + "C" + s.substring(i+1); 
				continue;
			}
			if(s.charAt(i)=='?' && s.charAt(i+1)=='J') {
				s=s.substring(0, i) + "J" + s.substring(i+1); 
				continue;
			}
			if(s.charAt(i)=='?' && s.charAt(i+1)=='?') {
				s=s.substring(0, i) + "J" + s.substring(i+1); 
				continue;
			}
		}
		return sum;
	}
	



}
