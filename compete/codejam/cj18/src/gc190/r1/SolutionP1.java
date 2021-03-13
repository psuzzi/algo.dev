package gc190.r1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;

public class SolutionP1 {
	public static void main(String[] args) {
		SolutionP1 sol = new SolutionP1();
		// Scanner in = new Scanner(System.in);
		scan("input/r1a/input1.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				int m = in.nextInt();
				sol.solve(n, m);
				System.out.print("Case #" + i + ": ");
				System.out.println();
			}
		});
		// in.close();
	}
	
	static Random rand = new Random();
	
	private int nr;
	private int nc;
	List<Integer[]> jumps;
	private boolean[][] visited;

	private void solve(int nr, int nc) {
		this.nr = nr;
		this.nc = nc;
		jumps= new ArrayList<>();
		visited = new boolean[nr][nc];
		
		start(rand.nextInt(nr), rand.nextInt(nc));
	}
	
	

	private void start(int r, int c) {
		jumps.add(new Integer[] {r, c});
		// base case
		if (jumps.size()==nr*nc) {
			System.out.println("POSSIBLE");
			for(Integer[] jump : jumps) {
				System.out.println("" + jump[0] + " " + jump[1]);
			}
		}
		
	}



	/**
	 * prints the jumps 
	 * <pre>
	 * r!=r': not the same row 
	 * c!=c': not the same col 
	 * r-c!=r'-c' = not on the main diagonal 
	 * r+c!=r'+c' = not on inv. diagonal
	 * </pre>
	 * @param nr
	 * @param nc
	 */
	private static void steps(int nr, int nc) {
		int r0, c0, r, c, k;
		r0 = c0 = r = c = k = 0;
		for(int i=0; i<nr; i++) {
			for(int j=0; j<nc; j++) {
				k = i+j;
				if(k%2==0) {
					r = i + 1;
					c = j + 2;	
				} else {
					r = i;
					c = j-1;
				}
				
				System.out.printf("%s %s%n", 1+r%nr, 1+c%nc);
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