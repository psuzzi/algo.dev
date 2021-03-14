package ks20.ra.p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Solution {

	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("problem/ra.p1/input1.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				int b = in.nextInt();
				List<Integer> a = new ArrayList<>();
				for(int j=0; j<n; j++) {
					a.add(in.nextInt());
				}
				System.out.println("Case #" + i + ": " + solve(b,a));
			}
		});
		//in.close();
	}

	private static final String solve(final int b, final List<Integer> a) {
		Collections.sort(a);
		int n = 0, budget = b, cost;
		for( int i=0; i<a.size(); i++) {
			cost = a.get(i);
			if (cost<=budget) {
				budget = budget-cost;
				n++;
			}
		}
		return ""+n;
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
 
