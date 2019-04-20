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

public class SolutionQ3 {
	
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
				String sol = solve(n, cs);
				System.out.println("Case #" + i + ": " + sol);
			}
		});
//		in.close();
			
	}

	private static String solve(BigInteger n, BigInteger[] cs) {
		BigInteger[] ts = new BigInteger[cs.length+1];
		
//		System.out.println(Arrays.toString(cs));
		
		// find the smallest product PQ
		BigInteger minPQ = n;
		int minI = 0;
		BigInteger bi;
		for(int i=0; i<cs.length; i++) {
			bi = cs[i];
			if(bi.compareTo(minPQ) == -1) {
				minPQ = bi;
				minI = i;
			}
		}
		
		// decompose min PQ
		BigInteger minP = factor2(minPQ);
		BigInteger minQ = minPQ.divide(minP);
		
//		System.out.println("minPQ: " + minPQ + " = " + minP + " * " + minQ);
		
		// compose to right
		BigInteger [] pq = new BigInteger[2];
		
		if(minI<cs.length-1) {
			if(divideExact(minQ, cs[minI+1])) {
				ts[minI] = minP;
				ts[minI+1] = minQ;
			} else {
				ts[minI] = minQ;
				ts[minI+1] = minP;
			}
		} else {
			// assert(minI>0);
			if(divideExact(minP, cs[minI-1])) {
				ts[minI] = minP;
				ts[minI+1] = minQ;
			} else {
				ts[minI] = minQ;
				ts[minI+1] = minP;
			}
		}
//		System.out.println("" + minPQ + " = " + minP + " * " + minQ);
		// s[minI] and s[minI+1] are in the correct order
		
		// right part
		for(int i=minI+1; i<cs.length; i++) {
			ts[i+1] = cs[i].divide(ts[i]);
		}
		
		// left part
		for(int i=minI-1; i>=0; i--) {
			ts[i] = cs[i].divide(ts[i+1]);
		}
		
	
		Set<BigInteger> alphabet = new HashSet<>();
		for(int i=0; i<ts.length ; i++) {
			alphabet.add(ts[i]);
		}
		List<BigInteger> aList = new ArrayList<>(alphabet);
		Collections.sort(aList);
		Map<BigInteger, Character> cmap = new HashMap<>();
		char letter = 'A';
		for(int i=0; i<aList.size() && cmap.size()<26; i++) {
			cmap.put(aList.get(i), (char)(letter + i));
		}
		char[] plaintext = new char[ts.length];
		for(int i=0; i<ts.length; i++) {
			plaintext[i] = cmap.get(ts[i]);
		}
		return new String(plaintext);
	}

	private static final boolean divideExact(BigInteger divisor, BigInteger dividend) {
		return dividend.mod(divisor).equals(BigInteger.ZERO);
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
	
	private static final BigInteger factor2(BigInteger n) {
		if(n.mod(BI_TWO).compareTo(BigInteger.ZERO) == 0) {
			return BI_TWO;
		}
		// Fermat's Factorization Method
		BigInteger A = sqrt(n);
		BigInteger Bsq = A.multiply(A).subtract(n);
		BigInteger B = sqrt(Bsq);
		BigInteger AminusB = A.subtract(B);
		
		// c is a chosen bound which controls when Fermat stops
		BigInteger c = new BigInteger("30");
		BigInteger AminusB_prev = A.subtract(B).add(c);
		BigInteger result = null;
		
		while (!sqrt(Bsq).pow(2).equals(Bsq) && AminusB_prev.subtract(AminusB).compareTo(c) > -1) {
			A = A.add(BigInteger.ONE);
			Bsq = A.multiply(A).subtract(n);
			
			B = sqrt(Bsq);
			AminusB_prev = AminusB;
			AminusB = A.subtract(B);
		}
		
		if (sqrt(Bsq).pow(2).equals(Bsq)) {
			result = AminusB;
		} 
		
		// Trial Division
		else {
			boolean solved = false;
			BigInteger p = AminusB.add(BI_TWO); 
			
			if (p.remainder(BI_TWO).intValue() == 0) { 
				p = p.add(BigInteger.ONE);
			}
			while (!solved) {
				p = p.subtract(BI_TWO);
				if (n.remainder(p).equals(BigInteger.ZERO)) {
					solved = true;
				}
			}
			
			result = p;
		}
		
		return result;
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
