package gcj190.qual;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int i = 1; i <= t; ++i) {
			int n = in.nextInt();
			int b = in.nextInt();
			int f = in.nextInt();
			solve(n, b, f, in);
		}
		in.close();
		System.exit(0);
	}

	/**
	 * 
	 * @param n  total number of servers
	 * @param b  number of broken servers
	 * @param f  max number of attempts
	 * @param in scanner to use for input
	 */
	private static void solve(int n, int b, int f, Scanner in) {

		int[][] A = new int[5][];
		int[][] B = new int[5][];

		// I/O
		for (int i = 0; i < A.length; i++) {

			A[i] = new int[n];
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < n; j++) {
				A[i][j] = (j >> i) & 1;
				// write
				sb.append(A[i][j]);
			}
			System.out.println(sb.toString());

			B[i] = new int[n - b];
			String line = in.next();
			char[] read = line.toCharArray();
			for (int j = 0; j < (n - b); j++) {
				// read
				B[i][j] = Integer.valueOf(read[j] == '0' ? 0 : 1);
			}
		}

		// find broken
		List<Integer> broken = new ArrayList<>();
		for (int i = 0, j = 0; i < A[0].length; i++) {
			int ai = fbit(A, i);
			if (j>=B[0].length || ai % 32 != fbit(B, j) % 32) {
				broken.add(i);
			} else {
				j++;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int brokenIndex : broken) {
			sb.append(" ").append(brokenIndex);
		}
		System.out.println(sb.toString());
		int verdict = in.nextInt();// 1 is success

	}

	private static int fbit(int[][] bits, int index) {
		int intVal = 0;
		for (int i = bits.length - 1; i >= 0; i--) {
			intVal = intVal + bits[i][index];
			if (i > 0) {
				intVal = intVal << 1;
			}
		}
		return intVal;
	}

}