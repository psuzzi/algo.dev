package round1c;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Robot programming strategy
 * @author psuzzi
 *
 */
public class Solution1C1 {
	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("input/round1c/input1b.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				String[] moves = new String[n];
				for(int j=0; j<n; j++) {
					moves[j] = in.next();
				}
				System.out.println("Case #" + i + ": " + solve(n,moves));
			}
		});
		//in.close();
	}

	/**
	 * 
	 * @param n number of robots
	 * @param moves move of the robots
	 * @return
	 */
	private static String solve(int n, String[] moves) {
		StringBuilder sb = new StringBuilder();
		
		char[] next = new char[n];
		boolean[] beaten = new boolean[n];
		
		int index = 0;
		while(true) {
			// get all other's moves 
			for(int k=0; k<n; k++) {
				next[k] = moves(moves[k], index);
			}
			// find a move that beats or draws all
			char move = findMove(next, beaten);
			
			// if no such move impossible
			if(move=='0') {
				return "IMPOSSIBLE";
			} else {
				sb.append(move);
				for(int k=0; k<n; k++) {
					// remove the robots beaten with this move
					if(!beaten[k]) {
						if(compare(move, next[k])==1) {
							beaten[k] = true;
						}
					}
				}
				// count beaten
				int nBeaten = 0;
				for(int k=0; k<n; k++) {
					if(beaten[k]) {
						nBeaten++;
					}
				}
				if(nBeaten==n) {
					// win all
					break;
				}
			}
			index++;
		}

		
		return sb.toString();
	}
	
	/**
	 * Find a move that beats all or draws all.
	 * Return '0' if no such move exists
	 */
	private static final char findMove(char[] next, boolean[] beaten) {
//		System.out.printf("findMove %s %s %n ", Arrays.toString(next), Arrays.toString(beaten));
		char[] moves = new char[] {'R', 'P', 'S'};
		int[] loses = new int[moves.length];
		int[] draws = new int[moves.length];
		int[] wins = new int[moves.length];
		for(int i=0; i<next.length; i++) {
			if(!beaten[i]) {
				char rMove = next[i];
				for(int j=0; j<moves.length; j++) {
					int cmp = compare(moves[j], rMove);
					switch (cmp) {
					case 1:
						wins[j]++;
						break;
					case 0:
						draws[j]++;
						break;	
					case -1:
						loses[j]++;
						break;
					default:
						break;
					}
				}
			}
		}
		
		// select the move 
		int index = -1;
		int maxWin = 0;
		for(int j=0; j<moves.length; j++) {
			if(loses[j]>0)
				continue;
			// choose the move that wins the most
			if(wins[j]>=maxWin) {
				index = j;
				maxWin = wins[j];
			}
		}
		if(index<0)
			return '0';
		// TODO Auto-generated method stub
		return moves[index];
	}

	/** 0 equals, 1 a wins, -1 a loses */
	static final int compare(char a, char b) {
		if(a==b)
			return 0;
		if((a=='P' && b=='R') || (a=='R' && b=='S') || (a=='S' && b=='P') )
			return 1;
		return -1;
	}

	/** get the ith move for the given program
	 */
	private static char moves(String program, int index) {
		return  program.charAt(index%program.length());
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