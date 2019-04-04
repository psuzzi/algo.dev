package hqtestzug;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Compute the n-th row for a Pascal's triangle
 * @author psuzzi
 *
 */
public class TestOnsiteBigInt {
	
	public static void main(String[] args) {
		TestOnsiteBigInt t = new TestOnsiteBigInt();
		// no memoization: 33: 10777ms, 32: 6481ms, 31:2679ms
		// with memoization: 31: 2ms, 50: 5 ms, 100: 6 ms
		int n = 1000;
		long time = System.currentTimeMillis();
		BigInteger ns[] = t.pascal(n);
		time = System.currentTimeMillis() - time;
		for( BigInteger k : ns) {
			System.out.println(" " + k);
		}
		System.out.printf("Computed pascal(%s) in %s ms %n", n, time);
	}

	/**
	 * Compute the n-th row of a Pascal's triangle
	 * @param i
	 * @return
	 */
	private BigInteger[] pascal(int n) {
		BigInteger[] ns = new BigInteger[n];
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
	private BigInteger compute(int n, int i) {
		if(i==0 || i==n-1)
			return ONE;
		return compute(n-1, i-1).add(compute(n-1, i));
	}
	
	static String key(int n, int i) {
		if(i>n/2) {
			return "" + n + "-" + (n-i); 
		}
		return "" + n + "-" + i;
	}
	Map<String, BigInteger> map = new HashMap<>();
	static BigInteger ONE = BigInteger.valueOf(1);
	
	private BigInteger xSolve(int n, int i){
		if(i==0 || i==n-1)
			return ONE;
		String key = key(n,i);
		if(map.containsKey(key)) {
			return map.get(key);
		}
		BigInteger val = xSolve(n-1, i-1).add(xSolve(n-1, i));
		map.put(key, val);
		return val;
	}

}
