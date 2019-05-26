package round1b;

import java.util.*;
import java.util.function.Consumer;
import java.io.*;

public class Solution1B1 {
	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("input/round1b/intput1.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int k = 1; k <= t; ++k) {
				int p = in.nextInt();
				int q = in.nextInt();
				List<Person> ps = new ArrayList<>();
				for(int i=0; i<p; i++) {
					ps.add( new Person(in.nextInt(), in.nextInt(), in.next()));
				}
				System.out.println("Case #" + k + ": " + solve(p,q,ps));
			}
		});
		//in.close();
	}
	
	private static String solve(int np, int q, List<Person> ps) {
		//List<Integer> xs = new ArrayList<>();
		//List<Integer> ys = new ArrayList<>();
		
		int cx = 0;
		int cy = 0;
		int cxd = 0;
		int cyd = 0;
		double avgx = 0;
		double avgy = 0;
		double avgxd = 0;
		double avgyd = 0;
		
		
		
		for(Person p : ps ) {
			switch (p.dir) {
			case 'N':
				avgy = (avgy*cy + p.y)/(++cy);
				//ys.add(p.y+1);
				break;
			case 'S':
				avgyd = (avgyd*cyd + p.y)/(++cyd);
				//ys.add(p.y-1);
				break;
			case 'E':
				avgx = (avgx*cx + p.x)/(++cx);
				//xs.add(p.x+1);
				break;
			case 'W':
				avgxd = (avgxd*cxd + p.x)/(++cxd);
				//xs.add(p.x-1);
				break;
			}
		}
		
		System.out.printf("%ny: %s, yd: %s, x: %s, xd: %s%n", avgy, avgyd, avgx, avgxd);
		
		//long x = (xs.isEmpty()) ? 0 : average(xs);
		//long y = (ys.isEmpty()) ? 0 : average(ys);
		
		if(avgx!=0)
			avgx++;
		if(avgx!=0)
			avgy++;
		
		return "" + (long)avgx + " " + (long)avgy;
	}

	private static long average(List<Integer> xs) {
		if(xs.isEmpty())
			return 0;
		OptionalDouble average = xs.stream().mapToDouble(n -> n).average();
		if(average.isPresent()) {
			return Math.round(average.getAsDouble());
		}
		return 0;
	}

	public final static class Person {
		int x, y;
		char dir;
		public Person(int px, int py, String pdir) {
			this.x = px;
			this.y = py;
			this.dir = pdir.charAt(0);
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