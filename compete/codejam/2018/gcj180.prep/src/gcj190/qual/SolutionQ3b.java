package gcj190.qual;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;

public class SolutionQ3b {
	
	public static void maind(String[] args) {
		BigInteger pq,p;
		long time = System.currentTimeMillis();
		pq = new BigInteger("375469");
		p = rho(pq);
		time = System.currentTimeMillis() - time;
		System.out.printf("%s %s - %s ms%n", pq, p, time);
	}
	
	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
		scan("input/qual/qual3.txt", in -> {
			int t = in.nextInt(); 
			for (int i = 1; i <= t; ++i) {
				BigInteger n = in.nextBigInteger();
				int l = in.nextInt();
				BigInteger[] cs = new BigInteger[l];
				for(int j=0; j<l; j++) {
					cs[j] = in.nextBigInteger();
				}
				System.out.print("Case #" + i + ": ");
				solve(n, cs);
				System.out.println();
			}
		});
//		in.close();
			
	}
	
	private static void solve(BigInteger n, BigInteger[] cs) {
		BigInteger[] ts = new BigInteger[cs.length+1];
		
		// smallest product and its index
		BigInteger minPQ = n;
		int i = -1;
		for(int k=0; k<cs.length; k++) {
			if(cs[k].compareTo(minPQ) == -1) {
				minPQ = cs[k];
				i = k;
			}
		}
		
		// factors
		ts[i] = rho(minPQ);
		ts[i+1] = minPQ.divide(ts[i]);
		
		BigInteger[] dr;
		boolean swap = false;
		if(i>0) {
			for(int j=i-1; !swap && j>=0; j--) {
				dr = cs[j].divideAndRemainder(ts[j+1]);
				if(dr[1].compareTo(BigInteger.ZERO)==0) {
					ts[j] = dr[0];
				} else {
					swap = true;
				}
			}
		}
		if(i<cs.length-1 && !swap) {
			for(int j=i+1; !swap && j<cs.length; j++) {
				dr = cs[j].divideAndRemainder(ts[j]);
				if(dr[1].compareTo(BigInteger.ZERO)==0) {
					ts[j+1] = dr[0];
				} else {
					swap = true;
				}
			}
		}
		
		if(swap) {
			// swap p q
			BigInteger tmp = ts[i];
			ts[i] = ts[i+1];
			ts[i+1] = tmp;
			
			if(i>0) {
				//compute all the ts[i] to the left
				for(int j=i-1; j>=0; j--) {
					ts[j] = cs[j].divide(ts[j+1]);
				}
			}
			if(i<cs.length-1) {
				// compute all the ts[i] to the right
				for(int j=i+1; j<cs.length; j++) {
					ts[j+1] = cs[j].divide(ts[j]);
				}
			}
		}

		
		Set<BigInteger> alphabet = new HashSet<>();
		for(int j=0; j<ts.length; j++) {
			alphabet.add(ts[j]);
		}
		// sort the alphabet
		List<BigInteger> aList = new ArrayList<>(alphabet);
		Collections.sort(aList);
		// map primes to alphabet chars
		Map<BigInteger, Character> cmap = new HashMap<>();
		char letter = 'A';
		for(int j=0; j<aList.size(); j++) {
			cmap.put(aList.get(j), (char)(letter + j));
		}
		// reconstruct the plaintext
		char[] plaintext = new char[ts.length];
		for(int j=0; j<ts.length; j++) {
			plaintext[j] = cmap.get(ts[j]);
		}
		String result = new String(plaintext);
		System.out.print(result);
	}
	
	private static BigInteger primeFactor(BigInteger n) {
		if(n.mod(BI_TWO).compareTo(BigInteger.ZERO) == 0) {
			return BI_TWO;
		}
		BigInteger a = sqrt(n);
		BigInteger b2 = a.multiply(a).subtract(n);
		while(!isSquare(b2)) {
			a = a.add(BigInteger.ONE);
			b2 = a.multiply(a).subtract(n);
		}
		BigInteger b = sqrt(b2);
		return a.subtract(b);
	}


	private static final BigInteger factor(BigInteger n) {
		BigInteger a = sqrt(n);
		BigInteger b2 = a.multiply(a).subtract(n);
		while(!isSquare(b2)) {
			a = a.add(BigInteger.ONE);
			b2 = a.multiply(a).subtract(n);
		}
		BigInteger b = sqrt(b2);
		return a.subtract(b);
	}
	
	private static final boolean isSquare(BigInteger b2) {
		return sqrt(b2).pow(2).equals(b2);
	}

	private static final BigInteger sqrt(BigInteger n) {
		BigInteger r = BigInteger.ZERO;
		BigInteger m = r.setBit(2 * n.bitLength());
		BigInteger nr;
		do {
			nr = r.add(m);
			if(nr.compareTo(n)!=1) {
				n = n.subtract(nr);
				r = nr.add(m);
			}
			r = r.shiftRight(1);
			m = m.shiftRight(2);
		} while(m.bitCount()!=0);
		return r;
	}

	static void scan(String filename, Consumer<Scanner> consumer) {
		try (Scanner sc = new Scanner(new File(filename))) {
			consumer.accept(sc);
		} catch (FileNotFoundException e) {
			System.err.printf("Error scanning %s", filename);
			e.printStackTrace();
		}
	}
	
	private static final BigInteger BI_TWO = BigInteger.valueOf(2);
	private static final BigInteger BI_THREE = BigInteger.valueOf(3);
	
	
	private final static SecureRandom random = new SecureRandom();
	/**
	 * Find one factor of pq using Pollard-Rho algorithm
	 * @param pq p*q: positive number to factor
	 * @return p one of the factors
	 */
	private final static BigInteger rho(BigInteger pq) {
		if (pq.compareTo(BigInteger.ONE) == 0) {
			return BigInteger.ONE;
		}
		BigInteger p = null;
		// find two random integers in [0, 2^nBitsPq]
		BigInteger a = new BigInteger(pq.bitLength(), random);
		BigInteger b = new BigInteger(pq.bitLength(), random);
		BigInteger c = b;
		
		// 
		if(pq.mod(BI_TWO).compareTo(BigInteger.ZERO) == 0) {
			return BI_TWO;
		}
		
		do {
			b = b.multiply(b).mod(pq).add(a).mod(pq);
			c = c.multiply(c).mod(pq).add(a).mod(pq);
			c = c.multiply(c).mod(pq).add(a).mod(pq);
			p = b.subtract(c).gcd(pq);
		} while((p.compareTo(BigInteger.ONE))==0);
		
		return p;
	}
	
	public static void factorPR(BigInteger N) {
		if (N.compareTo(BigInteger.ONE) == 0)
			return;
		if (N.isProbablePrime(20)) {
			System.out.println(N);
			return;
		}
		BigInteger divisor = rho(N);
		factor(divisor);
		factor(N.divide(divisor));
	}

}
