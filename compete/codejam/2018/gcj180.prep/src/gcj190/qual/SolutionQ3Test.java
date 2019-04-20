package gcj190.qual;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;

public class SolutionQ3Test {
	
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		BigInteger p = new BigInteger(
				"5915587277"
//				"66405897020462343733"
//				"2425967623052370772757633156976982469681"
				);
		BigInteger q = new BigInteger(
//				"3267000013"
				"54673257461630679457"
//				"4384165182867240584805930970951575013697"
				);
		BigInteger pq = p.multiply(q);
//		PollardRho.factor(pq);
		BigInteger[] fac = new BigInteger[2];
		fac[0] = rho(pq);
		fac[1] = pq.divide(fac[0]);
		//fac = factors(BigInteger.TEN, pq);
//		fac[0] = factor(pq);
//		fac[1] = pq.divide(fac[0]);
		time = System.currentTimeMillis() - time;
//		System.out.printf("done in %s ms.%n", time);
		System.out.printf("done in %s ms.%n - p = %s,%n - q = %s%n - pq = %s%n%n", time, fac[0], fac[1], pq);
		// Scanner in = new Scanner(System.in);
		time = System.currentTimeMillis();
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
		time = System.currentTimeMillis() - time;
		System.out.printf("done in %s ms", time);
		//in.close();
	}

	private static String solve(BigInteger n, BigInteger[] cs) {
		BigInteger[] ts = new BigInteger[cs.length+1];
		// find prime factors
		BigInteger [] pq = new BigInteger[2];
		pq[0] = factor(cs[0]);
		pq[1] = cs[0].divide(pq[0]);
//		pq = factors(n, cs[0]);
		if( divideExact(pq[0], cs[0]) && divideExact(pq[1], cs[1]) ) {
			ts[0] = pq[0];
			ts[1] = pq[1];
		} else {
			ts[0] = pq[1];
			ts[1] = pq[0];
		}
		for(int i=1; i<cs.length; i++) {
			ts[i+1] = cs[i].divide(ts[i]);
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

	private static final BigInteger[] factors(BigInteger max, BigInteger n) {
		BigInteger a = sqrt(n);
		BigInteger b2 = a.multiply(a).subtract(n);
		while(!isSquare(b2)) {
			a = a.add(BigInteger.ONE);
			b2 = a.multiply(a).subtract(n);
		}
		BigInteger b = sqrt(b2);
		return new BigInteger[] {a.subtract(b), a.add(b)};
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
	
	private static final BigInteger factor(BigInteger n) {
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
	
	private final static SecureRandom random = new SecureRandom();
	/**
	 * Find one factor of pq using Pollard-Rho algorithm
	 * @param pq p*q: positive number to factor
	 * @return p one of the factors
	 */
	private final static BigInteger rho(BigInteger pq) {
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

}
