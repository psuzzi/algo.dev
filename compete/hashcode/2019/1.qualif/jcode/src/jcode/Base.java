package jcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.function.Consumer;

public class Base {

	static void scan(String filename, Consumer<Scanner> consumer) {
		try (Scanner sc = new Scanner(new File(filename))) {
			consumer.accept(sc);
		} catch (FileNotFoundException e) {
			System.err.printf("Error scanning %s", filename);
			e.printStackTrace();
		}
	}

	/** Open a buffered reader for the file, and executes the lambda consumer */
	static void readFile(String filename, Consumer<BufferedReader> consumer) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			consumer.accept(br);
		} catch (Exception e) {
			System.err.printf("Error reading %s", filename);
		}
	}

	/** Read all integers split by space in a string */
	static int[] readLineInts(String line) {
		return readLineInts(line, " ", -1);
	}

	/** Read the first {@code n} integers split by {@code splitText} in a string */
	static int[] readLineInts(String line, String splitText, int n) {
		String split[] = line.split(splitText);
		int[] nums = new int[n == -1 ? split.length : n];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.valueOf(split[i]);
		}
		return nums;
	}

}
