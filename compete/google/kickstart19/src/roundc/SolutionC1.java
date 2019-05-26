package roundc;
import java.util.*;
import java.util.function.Consumer;
import java.io.*;

public class SolutionC1 {
	
	private static String solve(int n, int r, int c, int sr, int sc, char[] inst) {
		String desc = String.format("n:%s, r:%s, c:%s, sr:%s, sc:%s, inst:%s", n, r, c, sr, sc, Arrays.toString(inst));
		return desc;
	}
	
	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("input/roundc/input1.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				int r = in.nextInt();
				int c = in.nextInt();
				int sr = in.nextInt();
				int sc = in.nextInt();
				char[] inst = in.next().toCharArray();

				System.out.println("Case #" + i + ": " + solve(n,r,c,sr,sc,inst));
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