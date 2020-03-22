package kickstart.tt18.set01.p02;

import java.util.Scanner;

/**
 * @see: https://codingcompetitions.withgoogle.com/codejam/round/0000000000000130/0000000000000523
 */
public class Solution {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while (t-- > 0) {
            int n = in.nextInt();
            int [] ps = new int[n];
            for(int i=0; i<n; i++){
                ps[i] = in.nextInt();
            }
            
			solve(new ArrayList<String>(), ps);
		}
	}

	private static void solve(List<String> plan, int[] ps) {

    }

}


