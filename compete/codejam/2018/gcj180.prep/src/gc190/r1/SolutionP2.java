package gc190.r1;

import java.util.*;
import java.util.function.Consumer;
import java.io.*;

public class SolutionP2 {
	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("input/r1a/input1.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				int m = in.nextInt();
				solve(n,m);
				System.out.print("Case #" + i + ": " );
				System.out.println();
			}
		});
		//in.close();
	}

	private static void solve(int nr, int nc) {
		if(nr<=2 && nc <=2) {
			System.out.println("IMPOSSIBLE");
		} else {
			System.out.println("POSSIBLE");
			steps(nr, nc);
		}
	}

	private static void steps(int nr, int nc) {
		boolean[][] visited = new boolean[nr][nc];
		for(int i = 0; i<nr; i++) {
			for(int j=0; j<nc; j++){
				int r = (j+i+1)%nr;
				int c = (j+i+nr)%nc;
				
				
				System.out.printf("%s %s%n", r, c);
			}
		}
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