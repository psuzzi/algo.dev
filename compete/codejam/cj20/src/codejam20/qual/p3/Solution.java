package codejam20.qual.p3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * CodeJam 20, Qualification, Problem 3
 * 
 * @author psuzzi
 * @see https://codingcompetitions.withgoogle.com/codejam/round/000000000019fd27/000000000020bdf9
 */
public class Solution {

	public static void main(String[] args) {
		scan(Solution.class, "in.txt", in -> {
			int t = in.nextInt();
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				List<Activity> activities = new ArrayList<>();
				for (int l = 0; l < n; l++) {
					activities.add(new Activity(l, in.nextInt(), in.nextInt()));
				}
				System.out.println("Case #" + i + ": " + solve(n, activities));
			}
		});
	}

	static class Activity {
		public Activity(int id, int start, int end) {
			this.id = id;
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return String.format("[%s - %s]", start, end);
		}

		int id;
		int start;
		int end;

	}

	private static String solve(int n, List<Activity> as) {
		char[] res = new char[n];
		int endC = 0, endJ = 0;

		as.sort((a, b) -> Integer.compare(a.start, b.start));
		for (Activity act : as) {
			if (endC <= act.start) {
				endC = act.end;
				res[act.id] = 'C';
			} else if (endJ <= act.start) {
				endJ = act.end;
				res[act.id] = 'J';
			} else {
				return "IMPOSSIBLE";
			}
		}
		return new String(res);
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
