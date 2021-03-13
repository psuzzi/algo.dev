package codejam20.comp.r1b1;
import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.function.Consumer;

public class Solution {

	public static void main(String[] args) {
		scan(Solution.class, "in.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int x = in.nextInt();
				int y = in.nextInt();
				System.out.println("Case #" + i + ": " + solve(x,y) );
			}
		});
	}

	private static String solve(int x, int y) {
		StringBuilder sb = new StringBuilder();
		
//		int minPw2 = pw2round(min);
//		sb.append(minPw2);
		
		Deque<Character> deque = new LinkedList<>();
//		f(x,y, deque);
		
		
		
		return String.format("%s", sb.toString());
	}
	
	static final int dist(int x, int y) {
		return Math.abs(x) + Math.abs(y);
	}
	
	static final char dir(int x, int y, int step) {
		int [][] hops = new int[][] {
				{x,y-step},
				{x,y+step},
				{x+step,y},
				{x-step,y}
		};
		char[] dirs = new char[] {'N', 'S', 'W', 'E'};
		int [] dists= new int[4];
		
		int imin = -1;
		int dmin=Integer.MAX_VALUE;
		for( int i=0; i<4; i++) {
			dists[i] = dist(hops[i][0], hops[i][1]);
			if(dmin>dists[i]) {
				dmin = dists[i];
				
			}
		}
		
		return dirs[imin];
	}
	
	void String(int x, int y, Deque<Character> dir, int step) {
		if(x==0 && y==0) {
			return;
		}
		if( step > dist(x,y))
			;
//			return "IMPOSSIBLE"
		
			
	}
	
	private static final int pw2round(int dist) {
		int step = 1;
		while(step<dist) {
			step += 
					2;
		}
		return step;
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
 

