package round1b;

import java.util.*;
import java.util.function.Consumer;
import java.io.*;

public class Solution1B3 {
	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("input/round1b/input3.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				int k = in.nextInt();
				int [] ci = new int[n];
				int [] di = new int[n];
				for(int j=0; j<n; j++) {
					ci[j] = in.nextInt();
				}
				for(int j=0; j<n; j++) {
					di[j] = in.nextInt();
				}
				System.out.println("Case #" + i + ": " + solve(n,k,ci,di));
			}
		});
		//in.close();
	}

	private final static int solve(int n, int k, int[] ci, int[] di) {
		int nf = 0;
		Arrays.sort(ci);
		Arrays.sort(di);
		for(int i=0; i<n; i++) {
			
		}
		// 
		return nf;
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