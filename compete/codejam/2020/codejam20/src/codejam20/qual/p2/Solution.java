package codejam20.qual.p2;
import java.io.File;
import java.util.Scanner;
import java.util.function.Consumer;

public class Solution {

	public static void main(String[] args) {
		scan(Solution.class, "in.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				String s = in.next();
				System.out.println("Case #" + i + ": " + solve(s) );
			}
		});
	}

	private static String solve(String s) {
		StringBuilder sb = new StringBuilder();
		int n;
		char [] chars = s.toCharArray();
		int level = 0;
		int toAdd=0;
		for(int i=0; i<chars.length; i++) {
			n = chars[i]-'0';
			toAdd = n-level;
			if(toAdd>0) {
				// open par
				for(int p=0; p<toAdd; p++)
					sb.append('(');
			} else if (toAdd<0) {
				// close par
				for(int p=0; p<(-toAdd); p++)
					sb.append(')');
			}
			level += toAdd; 
			sb.append(n);
		}
		if(level>0) {
			for(int p=0; p<level; p++)
				sb.append(')');
		}
		return sb.toString();
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
 
