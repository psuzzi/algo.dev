package roundb;

import java.util.*;
import java.util.function.Consumer;
import java.io.*;

public class SolutionB3 {
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
		Set<Integer> exclude = new HashSet<>();
		
		for(int i=0; i<n; i++) {
			int key = ns[i];
			int val = 1;
			if(!map.containsKey(key)) {
				map.put(key, val);
			} else {
				val = map.get(key) + 1;
				map.put(key, val);
				if(val>s && !exclude.contains(key)) {
					exclude.add(key);
				}
			}
		}
		
		// compute start value
		int count = n;
		for(int key: exclude) {
			count -= map.get(key);
		}
		
		int max = count;
		
		int lo = 0;
		int hi = n-1;
		
		while(lo<=hi) {
			if(exclude.size()==0) {
				// nothing to improve
				break;
			}
			if(exclude.contains(ns[lo])) {
				int key = ns[lo];
				int val = map.get(key) -1;
				if(val>0) {
					map.put(key,val);
				} else {
					map.remove(key);
				}
				if(val==s) {
					exclude.remove(key);
					count += s;
				}
				// increase lo
				lo++;
			} else if(exclude.contains(ns[hi])) {
				// remove
				int key = ns[hi];
				int val = map.get(key) -1;
				if(val>0) {
					map.put(key,val);
				} else {
					map.remove(key);
				}
				if(val==s) {
					exclude.remove(key);
					count += s;
				}
				// decrease hi
				hi--;
			} else {
				boolean islo = true;
				// pick one side
				int lolo = lo;
				int hihi = hi;
				// goes toward the closest element to skip
				while(lolo<=hihi) {
					lolo++;
					hihi--;
					if(exclude.contains(ns[lolo])) {
						islo = true;
						break;
					}
					if(exclude.contains(ns[hihi])) {
						islo = false;
						break;
					}
				}
				if(islo) {
					int key = ns[lo];
					int val = map.get(key) -1;
					if(val>0) {
						map.put(key, val);
					} else {
						map.remove(key);
					}
					count--;
					lo++;
				} else {
					int key = ns[hi];
					int val = map.get(key) -1;
					if(val>0) {
						map.put(key,val);
					} else {
						map.remove(key);
					}
					count--;
					hi--;
				}
			}
			if(count>max) {
				max = count;
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
