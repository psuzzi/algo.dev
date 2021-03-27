package cj21.r0.p1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @see https://codingcompetitions.withgoogle.com/codejam/round/000000000043580a/00000000006d0a5c
 * @author pasu
 *
 */
public class Solution {

	public static void main(String[] args) {
		
//		try(Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)))){
		try(Scanner in = new Scanner(new BufferedReader(new InputStreamReader(Solution.class.getResourceAsStream("in.txt"))))){	
			int t = in.nextInt();
			for( int it = 1; it<=t; it++ ) {
				int l = in.nextInt();
				int arr[] = new int[l];
				for(int i=0; i<l; i++) {
					arr[i] = in.nextInt();
				}
				System.out.println("Case #" + it + ": " + solve(arr));
			}
		}

	}

	private static int solve(int[] arr) {
		int cost = 0;
		for(int i=0; i<=arr.length-2; i++) {
			int min = Integer.MAX_VALUE;
			int j = i;
			for(int k=i; k<=arr.length-1; k++) {
				if( arr[k] < min ) {
					min = arr[k];
					j = k;
				}
			}
			if(j>i)
				reverse(arr, i, j);
			cost += j-i + 1;
			
		}
		return cost;
	}

	private static void reverse(int[] arr, int i, int j) {
		int k=0;
		while(i+2*k<j) {
			int v = arr[i+k];
			arr[i+k] = arr[j-k];
			arr[j-k] = v;
			k++;
		}
	}

}
