import java.util.*;
import java.util.function.Consumer;
import java.io.*;

public class Solution {
	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("input/input.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				int m = in.nextInt();
				System.out.println("Case #" + i + ": " + (n + m) + " " + (n * m));
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