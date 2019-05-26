package roundc;
import java.util.*;
import java.util.function.Consumer;
import java.io.*;

public class SolutionC3 {
	
	private static String solve(int n, int k, int[] pos, int[] col) {
		// TODO Auto-generated method stub
		return String.format("n:%s, k:%s, pos:%s, col:%s", n, k, Arrays.toString(pos), Arrays.toString(col));
	}

	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("input/roundc/input3.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				int k = in.nextInt();
				int[] pos = new int[n];
				int[] col = new int[n];
				for(int j=0; j<n; j++) {
					pos[i] = in.nextInt();
				}
				for(int j=0; j<n; j++) {
					col[i] = in.nextInt();
				}
				System.out.println("Case #" + i + ": " + solve(n, k, pos, col));
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