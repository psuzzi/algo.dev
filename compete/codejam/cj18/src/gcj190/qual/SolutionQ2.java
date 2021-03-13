package gcj190.qual;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Consumer;

public class SolutionQ2 {

	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("input/qual/qual2.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				String s = in.next();
				String sol = solve(n,s);
				System.out.println("Case #" + i + ": " + sol);
			}
		});
		//in.close();
	}

	private static String solve(int n, String s) {
		char[] chars =s.toCharArray();
		char[] steps = new char[chars.length];
		for(int i=chars.length-1; i>=0; i--) {
			if(chars[i]=='S') {
				steps[i] = 'E';
			} else {
				steps[i] = 'S';
			}
		}
		return new String(steps);
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
