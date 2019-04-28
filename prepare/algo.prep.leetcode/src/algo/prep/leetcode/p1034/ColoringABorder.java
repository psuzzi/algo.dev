package algo.prep.leetcode.p1034;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

import algo.prep.leetcode.IO;

/**
 * Given a 2Dimensional grid of integers, each value represents the color of the
 * cell
 * <p>
 * Two squares belong to the same connected component in they have same color
 * and are next to each other in any of the 4 directions
 * <p>
 * 
 * 
 * @see https://leetcode.com/contest/weekly-contest-134/problems/coloring-a-border/
 * @author psuzzi
 *
 */
public class ColoringABorder {

	/**
	 * Color the border of the connected component starting at r0,c0 in the 2d array
	 * 
	 * @param grid
	 * @param r0
	 * @param c0
	 * @param color
	 * @return
	 */
	public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
		int n = grid.length;
		int m = grid[0].length;

		Queue<int[]> q = new ArrayDeque<>();
		int[] dr = { 1, 0, -1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		q.add(new int[] { r0, c0 });
		boolean [][] that = new boolean[n][m];
		that[r0][c0] = true;
		
		// find all the elements in the connected component
		while (!q.isEmpty()) {
			// remove queue head
			int[] cur = q.poll();
			int r = cur[0], c = cur[1];
			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k], nc = c + dc[k];
				// connected component: adjacent cell in the grid, same color
				if(nr>=0 && nr < n && nc >=0 && nc<m && grid[nr][nc]==grid[r][c]) {
					if(!that[nr][nc]) {
						that[nr][nc] = true;
						q.add(new int[] {nr, nc});
					}
				}

			}
		}
		
		// color the border in the c.c.
		int ret[][] = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(!that[i][j]) {
					// not in the connected component
					ret[i][j] = grid[i][j];
				} else {
					// color the border only
					int r = i, c = j;
					int edgesInside = 0;
					// not border if same color in 4 dir
					for(int k=0; k<4; k++) {
						int nr = r+dr[k], nc = c+dc[k];
						if(nr>=0 && nr<n && nc >= 0 && nc<m && grid[nr][nc]==grid[r][c]) {
							// the edge is inside the connected component
							edgesInside++;
						}
					}
					// any of the edges outside the CC?
					if(edgesInside!=4) {
						// not an edge, keep color
						ret[r][c] = color;
					} else {
						// not a border
						ret[i][j] = grid[i][j];	
					}
				}
			}
		}

		return ret;
	}

	public static void main(String[] args) {
		ColoringABorder sol = new ColoringABorder();
		try (Scanner in = new Scanner(new File("input/1034-in.txt"))) {
			int t = in.nextInt();
			for (int k = 1; k <= t; k++) {
				int nr = in.nextInt();
				int nc = in.nextInt();
				int[][] grid = new int[nr][nc];
				for (int i = 0; i < nr; i++) {
					for (int j = 0; j < nc; j++) {
						grid[i][j] = in.nextInt();
					}
				}
				int r0 = in.nextInt();
				int c0 = in.nextInt();
				int color = in.nextInt();
				IO.print(sol.colorBorder(grid, r0, c0, color));
			}
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected error", e);
		}
	}

}
