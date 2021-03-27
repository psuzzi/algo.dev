package cj21.r0.p3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @see https://codingcompetitions.withgoogle.com/codejam/round/000000000043580a/00000000006d12d7
 * @author pasu
 *
 */
public class Solution {

	private int n;
	private int c;
	private int minCost;
	private int maxCost;

	public static void main(String[] args) {
		Solution sol = new Solution();
//		try(Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)))){
		try (Scanner in = new Scanner(
				new BufferedReader(new InputStreamReader(Solution.class.getResourceAsStream("in.txt"))))) {
			int t = in.nextInt();
			for (int it = 1; it <= t; it++) {
				sol.n = in.nextInt();
				sol.c = in.nextInt();
				System.out.print("Case #" + it + ": ");
				sol.solve();
			}
		}

	}

	/**
	 * Compute minimum and maximum cost. min cost: array len-1, when array already
	 * sorted max cost: (arr.len-1) + (arr.len-2) + .. + 1, when need to reverse all
	 * the remaining numbers, at each iteration
	 * 
	 */
	void checkCost() {
		minCost = n - 1;
		maxCost = minCost;
		for (int i = 1; i <= n - 1; i++)
			maxCost += i;
	}

	private void solve() {
		checkCost();
		int arr[] = new int[n];
		if (c < minCost || c > maxCost) {
			System.out.println("IMPOSSIBLE");
			return;
		}

		// natural ordering, only when desired cost c = minCost
		for (int i = 0; i < n; i++)
			arr[i] = i + 1;

		int dc = c - minCost;
		int l = 0, r = n - 1;
		char dir = 'R';
		int k = n - 1;// cycle
		if (dc > 0) {
			while (dc >= k) {
				reverse(arr, l, r);
				if (dir == 'R') {
					r--;
					dir = 'L';
				} else {
					l++;
					dir = 'R';
				}
				dc -= k;
				k--;
			}
			if (dc > 0) {
				// now dc < k, last reverse
				if (dir == 'R') {
					reverse(arr, l, l + dc);
				} else {
					reverse(arr, r - dc, r);
				}
			}
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			System.out.print(" ");
		}
		System.out.println();
	}

	void swap(int[] arr, int i, int j) {
		int v = arr[i];
		arr[i] = arr[j];
		arr[j] = v;
	}

	String str(int... arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
			if (i < arr.length - 1)
				sb.append(" ");
		}
		return sb.toString();
	}

	private void reverse(int[] arr, int i, int j) {
		int k = 0;
		while (i + 2 * k < j) {
			int v = arr[i + k];
			arr[i + k] = arr[j - k];
			arr[j - k] = v;
			k++;
		}
	}

}
