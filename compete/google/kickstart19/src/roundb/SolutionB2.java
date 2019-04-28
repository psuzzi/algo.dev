package roundb;

import java.util.*;
import java.util.function.Consumer;
import java.io.*;

public class SolutionB2 {
	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("input/roundb/input2.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				List<Stone> list = new ArrayList<>();
				for(int j=0; j<n; j++) {
					list.add(new Stone(in.nextInt(), in.nextInt(), in.nextInt()));
				}
				System.out.println("Case #" + i + ": " + solve(n, list));
			}
		});
		//in.close();
	}
	
	public static class Stone{
		private long s,e,l;
		public Stone(long s, long e, long l) {
			this.s = s;
			this.e = e;
			this.l = l;
		}
		/** return the energy loss in time t */
		long loss(long t) {
			return this.l * t;
		}
	}
	
	static final Comparator<Stone> byEnergyDes = (a,b) -> Long.compare(b.e, a.e);
	static final Comparator<Stone> byLossDes = (a,b) -> Long.compare(b.l, a.l);
	static final Comparator<Stone> byTimeInc = (a,b) -> Long.compare(a.s, b.s);

	private static long solve(int n, List<Stone> list) {
		
		List<Stone> noLoss = new ArrayList<>();
		List<Stone> withLoss = new ArrayList<>();
		for(Stone s: list) {
			if(s.l==0) {
				noLoss.add(s);
			} else {
				withLoss.add(s);
			}
		}
		
		Collections.sort(withLoss, byTimeInc);
		Collections.sort(withLoss, byLossDes);
		Collections.sort(withLoss, byEnergyDes);
		
		
		// assuming I have a defined order
		long t = 0;
		long e = 0;
		for(Stone s : withLoss) {
			long re = s.e - (t*s.l); 
			e += (re>0)?re:0;
			t += s.s;
		}
		
		for(Stone s: noLoss) {
			e += s.e;
		}
		
		return e;
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
