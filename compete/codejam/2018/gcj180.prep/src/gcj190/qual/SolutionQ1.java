package gcj190.qual;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.function.Consumer;

public class SolutionQ1 {
	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("input/qual/qual1.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				String sol = solve(n);
				System.out.println("Case #" + i + ": " + sol);
			}
		});
		//in.close();
	}

	private static String solve(int n) {
		String s = "" + n;
		char[] cs = s.toCharArray();
		int[] ns = new int[cs.length];
		int[] as = new int[cs.length];
		int[] bs = new int[cs.length];
		for(int i=0; i<cs.length; i++) {
			ns[i] = cs[i] - '0';
			as[i] = ns[i];
		}
		BigInteger a = BigInteger.valueOf(0);
		BigInteger b = BigInteger.valueOf(0);
		BigInteger mult = BigInteger.valueOf(1);
		BigInteger bi10 = BigInteger.valueOf(10);
		for(int i=ns.length-1; i>=0; i--) {
			if(ns[i]==4) {
				as[i] = 3;
				bs[i] = 1;
			}
			
			a = a.add( BigInteger.valueOf(as[i]).multiply(mult) );
			b = b.add( BigInteger.valueOf(bs[i]).multiply(mult) );
			mult = mult.multiply(bi10); 
		}
		
		return "" + a +" " + b;
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
