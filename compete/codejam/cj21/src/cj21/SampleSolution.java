package cj21;

import java.io.PrintStream;
import java.util.Scanner;

public class SampleSolution {
	
	static PrintStream out = System.out;

	public static void main(String[] args) {
		
		try(Scanner in = new Scanner(System.in)){	
			int t = in.nextInt();
			for( int it = 1; it<=t; it++ ) {
				out.printf("Case #%s%n", it);
			}
		}

	}

}
