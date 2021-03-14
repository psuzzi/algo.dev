package ks19.r0p;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * Solution to the Mural problem,
 * 
 * @see: https://codingcompetitions.withgoogle.com/kickstart/round/0000000000051060/0000000000058b89
 * @author psuzzi
 *
 */
public class SolutionP3 {

	public static void main(String[] args) throws FileNotFoundException {
//		Scanner in = new Scanner(System.in);
		Scanner in = new Scanner(new File("input/input3.txt"));
		int t = in.nextInt();
		int tcase = 0;
		while (tcase < t) {
			int n = in.nextInt();//seats
			int q = in.nextInt();//bookings
			List<Booking> bs = new ArrayList<>();
			for(int i=0; i<q; i++) {
				Booking b = new Booking();
				b.from = in.nextInt();
				b.to = in.nextInt();
				bs.add(b);
			}
			int ans = solve(n, q, bs);
			System.out.printf("Case #%s: %s %n", ++tcase, ans);
		}
		in.close();
	}
	
	public static class Booking{
		int from;
		int to;
		int assigned = 0;
		int out = 0;
		@Override
		public String toString() {
			return String.format("%s %s (%s %s)", from, to, assigned, out);
		}
	}

	/**
	 * Largest integer k: for an order I can enter the booking,
	 */
	private static int solve(int n, int q, List<Booking> bs) {
		// sort by ascending size
		Collections.sort(bs, (b1,b2) -> {
			return (b2.from - b2.to) - (b1.from - b1.to);
		});
		boolean used [] = new boolean[n];//used seats
		for(Booking b : bs) {
			for(int i=b.from-1; i<=b.to-1; i++) {
				if(!used[i]) {
					used[i] = true;
					b.assigned ++;
				} else {
					b.out++;
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for(Booking b : bs) {
			if(b.assigned<min) {
				min = b.assigned;
			}
		}
		return min;
	}



}
