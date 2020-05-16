package lc.s1.p00;


import util.IO;

public class Runner {
	
	public static void main(String[] args) {
		IO.scan(Solution.class, "in.txt", in -> {
			int t = in.nextInt();
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				int[][] matrix = new int[n][n];
				for (int r = 0; r < n; r++) {
					for (int c = 0; c < n; c++) {
						matrix[r][c] = in.nextInt();
					}
				}
				System.out.println("Case #" + i + ": " + Solution.solve(n, matrix));
			}
		});
	}

}
