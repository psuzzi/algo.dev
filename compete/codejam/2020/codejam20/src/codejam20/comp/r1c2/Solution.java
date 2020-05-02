package codejam20.comp.r1c2;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Problem 2 from Codejam Round 1C 2020
 * https://codingcompetitions.withgoogle.com/codejam/round/000000000019fef4
 * @author psuzzi
 *
 */
public class Solution {

	public static void main(String[] args) {
		scan(Solution.class, "sample.in.txt", in -> {
			int t = in.nextInt();
			for (int i = 1; i <= t; ++i) {
				System.out.print("Case #" + i + ": ");
				solve(in);
			}
		});
	}

	/**
	 * Solve using Benford's Law
	 * @see: https://en.wikipedia.org/wiki/Benford%27s_law
	 * @param in
	 */
	private static void solve(Scanner in) {

		Map<Character, Integer> map = new HashMap<>();
		Set<Character> set = new HashSet<>();
		in.next();// skip U
		for (int i = 0; i < 10000; i++) {
			in.next();// skip Qi
			String r = in.next();
			if (set.size() < 10) {
				for (char c : r.toCharArray())
					set.add(c);
			}
			char r0 = r.charAt(0);
			// frequency map
			map.computeIfAbsent(r0, letter -> 0);
			map.computeIfPresent(r0, (letter, count) -> count + 1);
		}

		StringBuilder sb = new StringBuilder();
		// 0 is never present as first digit
		for( char c : set ) {
			if ( !map.containsKey(c) ) {
				sb.append(c);
				break;
			}
		}
		// sorted by frequency of other digits
		map.entrySet().stream()
			.sorted((a, b) -> b.getValue() - a.getValue())
			.forEach(e -> sb.append(e.getKey()));

		System.out.println(sb.toString());
	}


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
