package gcj180.qual;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Saving the Universe Again
 * https://codingcompetitions.withgoogle.com/codejam/round/00000000000000cb/0000000000007966
 * @author psuzzi
 *
 */
public class SolutionQ1 {
	
	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("input/qual/q1.txt", in -> {
			int t = in.nextInt(); // n tests
			for (int i = 1; i <= t; ++i) {
				int d = in.nextInt();
				String p = in.next();
				String sol = solve(d,p);
				System.out.println("Case #" + i + ": " + sol);
			}
		});
		//in.close();
	}

	private static String solve(int d, String p) {
		char [] chars = p.toCharArray();
		int [] damages = new int[chars.length];
		int beam = 1;
		int damage = 0;
		int ccount = 0;
		Map<Integer, Integer> cMap = new LinkedHashMap<>();
		for(int i=0; i<chars.length; i++) {
			if(chars[i]=='c') {
				beam *= 2;
				cMap.put(ccount, i);
				ccount++;
			} else {
				// shoot
				damages[i] = beam;
				damage += beam;
			}
		}
		
		// can we resist the min damage?
		int scount = chars.length - ccount;
		if(scount > d)
			return "IMPOSSIBLE";
		
		// can resist the damage?
		if(damage <= d)
			return "0";
		
		// compute
		int nswap = 0;
		int ccurr = 0;
		while(damage > d && ccurr<cMap.size()) {
			// get the workable 'c' with lowest index
			int cIndex = cMap.get(ccurr);
			if(cIndex==0) {
				cIndex++;
			}
			// move the charge L to R
			// 
			nswap++;
		}
		
		return null;
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
