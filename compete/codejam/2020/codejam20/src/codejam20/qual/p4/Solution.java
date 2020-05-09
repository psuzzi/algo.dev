package codejam20.qual.p4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.function.Consumer;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		int b = in.nextInt();
		for (int i = 1; i <= t; ++i) {
			solve(b,in);
		}
		in.close();
	}
	
	
	
	private static void solve(int b, Scanner in) {
		int bindex=0;
		Deque<Character> bits = new LinkedList<>();
		for(int i=0; i<150 || bits.size()<b; i++) {
			if(i%10==0) {
				// fluc query
				System.out.println("1");
				in.next();
				continue;
			}
			System.out.println(++bindex);
			String ans = in.next();
			bits.add(ans.charAt(0));
		}
		// bits at pos 0, 10, .. 90 are unknown
		StringBuilder sb = new StringBuilder();
		while(!bits.isEmpty()) {
			sb.append(bits.remove());
		}
		String out = sb.toString();
		System.out.println(out);
		String s = in.next();
		if(s.equals("N")) {
			System.err.printf("NO : %s , after %s %n", out);
		} else {
			System.err.printf("YES : %s , after %s %n", out);
		}
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
 
