package hqtestzug;

import java.util.HashMap;
import java.util.Map;

/**
 * Compute the n-th row for a Pascal's triangle
 * @author psuzzi
 *
 */
public class TestOnsite {
	
	public static void main(String[] args) {
		TestOnsite t = new TestOnsite();
		// no memoization: 33: 10777ms, 32: 6481ms, 31:2679ms
		// with memoization: 31: 2ms, 50: 5 ms, 100: 6 ms
		int n = 30;
		long time = System.currentTimeMillis();
		int ns[] = t.pascal(n);
		time = System.currentTimeMillis() - time;
		for( int k : ns) {
			System.out.println(" " + k);
		}
		System.out.printf("Computed pascal(%s) in %s ms %n", n, time);
	}

	/**
	 * Compute the n-th row of a Pascal's triangle
	 * @param i
	 * @return
	 */
	private int[] pascal(int n) {
		int[] ns = new int[n];
		for(int i=0; i<n; i++)
			ns[i] = xSolve(n,i);
		return ns;
	}

	/**
	 * Compute the i-th element of the n-th row of a Pascal triangle
	 * @param n
	 * @param i
	 * @return
	 */
	private int compute(int n, int i) {
		if(i==0 || i==n-1)
			return 1;
		return compute(n-1, i-1) + compute(n-1, i);
	}
	
	static String key(int n, int i) {
		if(i>n/2) {
			return "" + n + "-" + (n-i); 
		}
		return "" + n + "-" + i;
	}
	Map<String, Integer> map = new HashMap<>();
	
	private int xSolve(int n, int i){
		if(i==0 || i==n-1)
			return 1;
		String key = key(n,i);
		if(map.containsKey(key)) {
			return map.get(key);
		}
		int val = xSolve(n-1, i-1) + xSolve(n-1, i);
		map.put(key, val);
		return val;
	}

}
