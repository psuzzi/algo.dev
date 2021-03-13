package ksp190.prep;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * Solution to the Mural problem,
 * 
 * @see: https://codingcompetitions.withgoogle.com/kickstart/round/0000000000051060/0000000000058b89
 * @author psuzzi
 *
 */
public class SolutionP2 {

	public static void main(String[] args) throws FileNotFoundException {
//		Scanner in = new Scanner(System.in);
		Scanner in = new Scanner(new File("input/input2.txt"));
		int t = in.nextInt();
		int tcase = 0;
		while (tcase < t) {
			int r = in.nextInt();
			int c = in.nextInt();
			boolean[][] world = new boolean[r][c];
			boolean hasAll = true;
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					boolean hasOffice = "1".equals(in.next()); 
					world[i][j] = hasOffice;
					hasAll = hasAll && hasOffice;
				}
			}
			int ans = hasAll ? 0 : solve(r, c, world);
			System.out.printf("Case #%s: %s %n", ++tcase, ans);
		}
		in.close();
	}

	/** Solve in case there is at least one square without a delivery office */
	private static int solve(int r, int c, boolean[][] world) {
		// TODO Auto-generated method stub
		return 0;
	}

}
