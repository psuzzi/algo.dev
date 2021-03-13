package codejam20.comp.r1b2;
import java.io.File;
import java.util.Scanner;
import java.util.function.Consumer;

public class Solution {

	public static void main(String[] args) {
		scan(Solution.class, null, in -> {
			int t = in.nextInt();
			int a = in.nextInt();
			int b = in.nextInt();
			for (int i = 1; i <= t; ++i) {
				solve(a, b, in);
			}
		});
	}

	private static void solve(int rmin, int rmax, Scanner in) {
		int n = 0;
		int bindex=0;
		String ans = "";
		int x=0, y=0;
		while(n<300 && !"CENTER".equals(ans) && !"WRONG".equals(ans)) {
			System.out.println(x + " " + y);
			ans = in.next();
			n++;
		}
			
			
		// bits at pos 0, 10, .. 90 are unknown
		StringBuilder sb = new StringBuilder();
		
		String out = sb.toString();
		System.out.println(out);
		
		ans = in.next();
		if("CENTER".equals(ans) || "WRONG".equals(ans) || n == 300 ) {
			if("WRONG".equals(ans))
				System.err.println("ERROR");
			if(n==300)
				System.err.println("MAX");
			return;
		}
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

