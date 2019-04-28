package roundb;

import java.util.*;
import java.util.function.Consumer;
import java.io.*;

public class SolutionB3A {
	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("input/roundb/input3.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				int s = in.nextInt();
				Integer[] ns = new Integer[n];
				for(int j=0; j<n; j++) {
					ns[j] = in.nextInt(); 
				}
				System.out.println("Case #" + i + ": " + solve(n,s, ns));
			}
		});
		//in.close();
	}

	private static int solve(int n, int s, Integer[] ns) {
		Map<Integer, Integer> map = new HashMap<>();
		Set<Integer> set = new HashSet<>();
		for(int i=0; i<n; i++) {
			if(!map.containsKey(ns[i])) {
				map.put(ns[i], 1);
			} else {
				int curr = map.get(ns[i])+1;
				map.put(ns[i], curr);
				if(curr>s) {
					// set to be thrown away
					set.add(ns[i]);
				}
			}
		}
		
		int curr = 0;
		int max = 0;
		for(Integer key : map.keySet()) {
			if(!set.contains(key)) {
				curr += map.get(key);
			}
		}
		max = curr;
		
		int lo = 0;
		int hi = n-1;
		
		while(lo<hi) {
			if(set.size()==0) {
				// nothing to remove
				break;
			}
			int key = -1;
			if(set.contains(ns[lo])) {
				// skip lo
				key = ns[lo];
				lo++;
			} else if(set.contains(ns[hi])) {
				// skip hi
				key = ns[hi];
				hi--;
			} else {
				// skip the one with higher number
				if(map.get(ns[hi])>map.get(ns[lo])) {
					key = ns[hi];
					hi--;	
				} else {
					key = ns[lo];
					lo++;	
				}
			}
			int val = map.get(key)-1;
			if(val==s) {
				curr += s;
			} else {
				curr --;
			}
			if(val>0) {
				map.put(key, val);
				if(val<=s) {
					set.remove(key);
				}
			} else {
				map.remove(key);
			}
			// recompute max
			if(curr>max) {
				max = curr;
			}
		}
		

		return max;
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
