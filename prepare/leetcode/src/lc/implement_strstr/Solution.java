package lc.implement_strstr;

import static util.IO.scan;

/**
 * @see: https://leetcode.com/problems/implement-strstr/
 * @author psuzzi
 *
 */
public class Solution {

	public static void main(String[] args) {
		scan(Solution.class, "in.txt", in -> {
			int t = in.nextInt();
			for (int i = 0; i < t; i++) {
				System.out.print("Case #" + i + ": ");
				System.out.println(solve(in.next(), in.next()));
			}
		});
	}

	/**
	 * Print the start index of pattern in the string, or -1 for no match
	 * 
	 * @param s
	 * @param p
	 */
	private static int solve(String s, String p) {
		if (p.isEmpty())
			return 0;
		for (int i = 0, j = 0; i < s.length(); i++) {
			if (s.charAt(i) == p.charAt(j))

				if (j == p.length() - 1)
					return i - j;
				else
					j++;
			else {
				i -= j;
				j = 0;
			}
		}
		return -1;
	}

}
