package codejam20.comp.r1b3;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Solution {

	public static void main(String[] args) {
		scan(Solution.class, "in.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int r = in.nextInt();
				int s = in.nextInt();
				System.out.print("Case #" + i + ": ");
				solve(r,s);
			}
		});
	}

	private static void solve(int r, int s) {
		int n = 0;
		int k = 0, nr = 0, nc = 0, sum = 0;
		
		int [][] deck = new int[r][s];
		
		List<int[]> ops = new ArrayList<>();
		System.out.println(n);
		for(int[] op : ops)
			System.out.println(op[0] + " " + op[1]);
	}
	
	static final void swap(int [][] deck, List<int[]> ops) {
		
	}

	/**
	 * Scan System.in, a resource on classpath or a given file depending on params  
	 */
	public static void scan(Class<?> cl, String path, Consumer<Scanner> consumer) {
		try (Scanner sc = (path == null) ? new Scanner(System.in)
				: (cl == null) ? new Scanner(new File(path)) : new Scanner(cl.getResourceAsStream(path))) {
			consumer.accept(sc);
		} catch (Exception e) {
			System.err.println("ERROR in " + cl + " < " + path);
			e.printStackTrace();
		}
	}

}
 
