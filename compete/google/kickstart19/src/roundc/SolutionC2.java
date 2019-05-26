package roundc;
import java.util.*;
import java.util.function.Consumer;
import java.io.*;

public class SolutionC2 {
	
	private static String solve(int r, int c, int k, int[][] mat) {
		// TODO Auto-generated method stub
		return String.format("r:%s, c:%s, k:%s, mat:%s", r, c, k, print(mat));
	}
	
	static String BR = System.getProperty("line.separator");
	
	private static String print(int[][] mat) {
		StringBuilder sb = new StringBuilder();
		sb.append(BR);
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat[i].length; j++) {
				sb.append(" ").append(mat[i][j]);
			}
			sb.append(BR);
		}
		sb.append(BR);
		return sb.toString();
	}

	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("input/roundc/input2_1.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int r = in.nextInt();
				int c = in.nextInt();
				int k = in.nextInt();
				int[][] mat = new int[r][c];
				for(int j=0; j<r; j++) {
					for(int z=0; z<c; z++) {
						mat[j][z] = in.nextInt();
					}
				}
				System.out.println("Case #" + i + ": " + solve(r, c, k, mat));
			}
		});
		//in.close();
	}

	static void scan(String filename, Consumer<Scanner> consumer) {
		try (Scanner sc = new Scanner(new File(filename))) {
			consumer.accept(sc);
		} catch (FileNotFoundException e) {
			System.err.printf("Error scanning %s", filename);
			e.printStackTrace();
		}
	}
	
}