package rounda;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.function.Consumer;

public class SolutionA1 {
	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("input/rounda/input1.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				int p = in.nextInt();
				Integer[] skills = new Integer[n];
				for(int j=0; j<n; j++) {
					skills[j] = in.nextInt();
				}
				System.out.println("Case #" + i + ": " + solve(n,p,skills));
			}
		});
		//in.close();
	}

	private static int solve(int n, int p, Integer[] s) {
		// list p students: min n. of hours coaching for having a fair team
		
		// sort skills
		Arrays.sort(s, (a,b) -> b-a ); 
		
		int lo = 0;
		int curr = 0;
		int min = Integer.MAX_VALUE;
		
		for(int i=0; i<n; i++) {
			
		}

		
		
		return min;
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
