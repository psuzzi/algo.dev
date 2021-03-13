package kickstart.tt18.set01.p02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
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
            int tot = 0;
            PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
            for(int i=0; i<n; i++){
                ps[i] = in.nextInt();
                maxPQ.add(ps[i]);
                tot+=ps[i];
            }
            
			solve(new ArrayList<String>(), ps, tot);
		}
	}

	private static void solve(List<String> plan, int[] ps, int tot) {
		if(tot==0) {
			System.out.println(plan);
			
		}
		
        boolean majority=false;
        int inside = 0;
        for(int i=0; i<ps.length; i++){
            inside += ps[i];
        }


    }

}


