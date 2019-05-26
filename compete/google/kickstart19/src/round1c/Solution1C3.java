package round1c;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;

public class Solution1C3 {
	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("input/round1c/input3.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int k = 1; k <= t; ++k) {
				int r = in.nextInt();
				int c = in.nextInt();
				char[][] cells = new char[r][c];
				for(int i=0; i<r; i++) {
					cells[i] = in.next().toCharArray();
				}
				System.out.println("Case #" + k + ": " + new Solution1C3(r,c, cells).solve());
			}
		});
		//in.close();
	}

	private int r;
	private int c;
	private char[][] cells;
	
	public Solution1C3(int r, int c, char[][] cells) {
		this.r = r;
		this.c = c;
		this.cells = cells;
	}

	private String solve() {
		System.out.println();
		System.out.printf("%s%s%n", r, c);
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				System.out.print(cells[i][j]);
			}
			System.out.println();
		}
		while(true) {
			playB(cells);
			playT(cells);
			if(r==0)
				break;
		}
		// 
		return "\n";
	}
	
	
	private void playOptimally(char[][] game) {
		Set<Move> moves = new HashSet<>();
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++){
				if(game[i][j]=='c') {
					moves.add(new Move(i,j,'H'));
					moves.add(new Move(i,j,'V'));
				}
			}
		}
		if(moves.size()==0) {
			// no moves
		}
		// possible move
		for(Move m: moves) {
			play(game, moves, m);
		}
		
	}
	
	public static class Move{
		int r;
		int c;
		char x;
		public Move(int r, int c, char x) {
			super();
			this.r = r;
			this.c = c;
			this.x = x;
		}
	}

	/**
	 * Recursion
	 */
	private void play(char[][] game, Set<Move> moves, Move m) {
		// remove the possible move
		// play the move
		
	}

	private void playT(char[][] game) {
		// TODO Auto-generated method stub
		
	}

	private void playB(char[][] game) {
		// TODO Auto-generated method stub
		
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