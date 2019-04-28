package algo.prep.leetcode;

import java.io.PrintStream;

public class IO {
	
	public static PrintStream out = System.out;
	
	public static <T> void print(T[][] array) {
		for(int i=0; i<array.length; i++) {
			for(int j=0; j<array[i].length; j++) {
				out.print(array[i][j]);
				out.print(" ");
			}
			out.println();
		}
	}
	
	public static void print(int[][] array) {
		for(int i=0; i<array.length; i++) {
			for(int j=0; j<array[i].length; j++) {
				out.print(array[i][j]);
				out.print(" ");
			}
			out.println();
		}
	}

}
