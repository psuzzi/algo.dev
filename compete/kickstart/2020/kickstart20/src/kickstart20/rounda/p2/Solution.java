package kickstart20.rounda.p2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Solution {

	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		scan("problem/ra.p2/input1.txt", in -> {
			int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
			for (int i = 1; i <= t; ++i) {
				int n = in.nextInt();
				int k = in.nextInt();
				int p = in.nextInt();				
				int bs [][] = new int [n][k];
				for(int r=0; r<n; r++) {
					for(int c=0; c<k; c++) {
						bs[r][c] = in.nextInt();	
					}
				}
				System.out.println("Case #" + i + ": " + solve(p,bs) );
			}
		});
		//in.close();
	}
	
	private static class Node{
		Node parent;
		public Node(Node parent) {
			this.parent = parent;
		}
		List<Node> children = new LinkedList<>();
		int weight;
	}

	private static String solve(int p, int[][] bs) {
		Node root = new Node(null);
		for(int r=0; r<bs.length; r++ ) {
			Node parent = root;
			for(int c=bs[0].length-1; c>=0; c--) {
				Node n = new Node(parent);
				n.weight = bs[r][c];
			}
		}
		return solve(root, p, 0);
	}

	private static String solve(Node node, int picked, int sum) {
		
		return null;
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