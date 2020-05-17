package kickstart20.rc.p2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;

public class Solution {

	public static void main(String[] args) {
		scan(Solution.class, "in.txt", in -> {
			int t = in.nextInt();
			for (int i = 1; i <= t; ++i) {
				int r = in.nextInt();
				int c = in.nextInt();
				char[][] wall = new char[r][c];
				for(int j=0; j<r; j++) {
					wall[j] = in.next().toCharArray();
				}
				System.out.print("Case #" + i + ": " );
				solve(wall);
			}
		});
	}

	private static void solve(char[][] wall) {
		Set<Character> set = new HashSet<>();
		
		int rl = wall.length;
		int cl = wall[0].length;
		// get charset
		for( int r=0; r<rl; r++) {
			for(int c=0; c<cl; c++) {
				set.add(wall[r][c]);
			}
		}
		
		// map
		Map<Character, Node> map = new HashMap<>();
		
		// compute support 
		String[] supp = new String[cl];
		for( int r=rl-1; r>=0; r--) {
			for(int c=0; c<cl; c++) {
				char ch = wall[r][c];
				map.computeIfAbsent(ch, k -> new Node(k) );
				set.add(ch);
				
				if(r==rl-1) {
					supp[c] = ""+ch;
				} else {
					if( wall[r][c] != wall[r+1][c] )
						map.get(wall[r+1][c]).incoming.add(map.get(wall[r][c]));
						supp[c]+=wall[r][c];
				}
			}
		}
		
		Stack<Character> stack = new Stack<>();
		
		// topological sort
		for(Node n : map.values()) {
//			System.out.printf("%n%s%n", n);
			if(!n.visited)
				topSort(n, stack);
		}

		String out = "";
		while(!stack.isEmpty())
			out += stack.pop();
		
		System.out.printf("%s%n", out);
//		System.out.printf("%s%n", Arrays.asList(supp));
	}

	private static void topSort(Node n, Stack<Character> stack) {
		n.visited = true;
		for(Node cn : n.incoming) {
			if(!cn.visited) {
				topSort(cn, stack);
			}
		}
		stack.push(n.data);
	}
	
	private static boolean isCyclic() {
		
	}
	

	static class Node{
		
		public Node(char ch) {
			this.data = ch;
		}
		char data;
		boolean visited = false;
		Set<Node> incoming = new HashSet<>();
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(data).append(" : ");
			for(Node n : incoming ) {
				sb.append(n.data).append(" ");
			}
			return sb.toString();
		}
		
		@Override
		public boolean equals(Object obj) {
			return data == ((Node)obj).data;
		}
		
	}



	private static Object topologicalSort(ArrayList<Character>[] supp) {
		// TODO Auto-generated method stub
		return null;
	}

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
