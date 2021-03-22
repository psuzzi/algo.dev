package ks21.ra.p2;

import java.io.File;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Solution for the L Shaped Plots, KS 2021 A 1
 * @see https://codingcompetitions.withgoogle.com/kickstart/round/0000000000436140/000000000068c509
 * @author psuzzi
 *
 */
public class Solution {

	public static void main(String[] args) {
//		scan(null, null, in ->{
		scan(Solution.class, "in.txt", in ->{
			int T = in.nextInt();
			for (int k = 1; k <= T; ++k) {
				int R = in.nextInt();
				int C = in.nextInt();
				int[][] grid = new int[R][C];
				for(int i=0; i<R; i++) {
					for(int j=0; j<C; j++) {
						grid[i][j] = in.nextInt();
					}
				}
				System.out.printf("Case #%s: %s%n", k, solve(R, C, grid));
			}
		});
	}
	
	/**
	 * Count number of Ls given the length of the two legs. 
	 * @param ax1
	 * @param ax2
	 * @return
	 */
	private static final int nls(int ax1, int ax2) {
	    return
	        Math.max(0, Math.min(ax1/2, ax2)-1 ) +
	        Math.max(0, Math.min(ax1, ax2/2)-1 );
	}
	
	private static int solve(int rlen, int clen, int [][] grid) {
		int count = 0;
		boolean goUp, goRight, goDown, goLeft;
		int up=0, rx=0, dn=0, lx=0;
		for(int r=0; r<rlen; r++) {
			for(int c=0; c<clen; c++) {
				if(grid[r][c]==1) {
					goUp = r>0 && grid[r-1][c]==1;
					goRight = c<grid[r].length-1 && grid[r][c+1]==1;
					goDown = r<grid.length-1 && grid[r+1][c]==1;
					goLeft = c>0 && grid[r][c-1]==1;
					if( (goUp||goDown) && (goRight||goLeft) ) {
						// it's an endpoint: junction point for two legs of an L
						up=0; rx=0; dn=0; lx=0;
						for(up=0; goUp && r-up>=0 && grid[r-up][c]==1; up++);
						for(rx=0; goRight && c+rx<clen && grid[r][c+rx]==1; rx++);
						for(dn=0; goDown && r+dn<rlen && grid[r+dn][c]==1; dn++);
						for(lx=0; goLeft && c-lx>=0 && grid[r][c-lx]==1; lx++);
						if(goUp&&goRight) {
							count += nls(up, rx);
						}
						if(goRight&&goDown) {
							count += nls(rx, dn);
						}
						if(goDown&&goLeft) {
							count += nls(dn, lx);
						}
						if(goLeft&&goUp) {
							count += nls(lx, up);
						}
						
					}
				}
			}
		}
		
		return count;
	}


	/**
	 * Scan System.in, a resource on classpath or a given file depending on params  
	 */
	public static void scan(Class<?> cl, String path, Consumer<Scanner> consumer) {
		try (Scanner sc = (path == null) ? new Scanner(System.in)
				: (cl == null) ? new Scanner(new File(path)) : new Scanner(cl.getResourceAsStream(path))) {
			consumer.accept(sc);
		} catch (Exception e) {
			System.err.println("ERROR in " + cl + " < " + path);
			e.printStackTrace();
		}
	}

}
