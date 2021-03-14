package ks20.rc.p2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
		
		int rl = wall.length;
		int cl = wall[0].length;
		
		// map
		Map<Character, Node> map = new HashMap<>();
		
		// compute support 
		String[] supp = new String[cl];
		for( int r=rl-1; r>=0; r--) {
			for(int c=0; c<cl; c++) {
				char ch = wall[r][c];
				map.computeIfAbsent(ch, k -> new Node(k) );
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
		
		Set<Node> visited = new  HashSet<>();
		Set<Node> marked = new  HashSet<>();
		
		for(Node n : map.values()) {
			if(hasCycle(n, marked, visited)) {
				System.out.println("-1");
				return;
			}
		}
		
		visited.clear();
		// topological sort
		for(Node n : map.values()) {
//			System.out.printf("%n%s%n", n);
			if(!visited.contains(n))
				topSort(n, visited, stack);
		}
		

		String out = "";
		while(!stack.isEmpty())
			out += stack.pop();
		
		System.out.printf("%s%n", out);
//		System.out.printf("%s%n", Arrays.asList(supp));
	}

	private static void topSort(Node n, Set<Node> visited, Stack<Character> stack) {
		visited.add(n);
		for(Node cn : n.incoming) {
			if(!visited.contains(cn)) {
				topSort(cn, visited, stack);
			} 
		}
		stack.push(n.data);
	}
	
	private static boolean hasCycle(Node n, Set<Node> marked, Set<Node> visited) {
		marked.add(n);
		for(Node cn : n.incoming) {
			if(marked.contains(cn))
				return true;
			else if( !visited.contains(cn) && hasCycle(cn, marked, visited))
				return true;
		}
		marked.remove(n);
		return false;
	}
	
	

	static class Node{
		

		public Node(char ch) {
			this.data = ch;
		}
		char data;
		boolean marked = false;
		public boolean visited = false;
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
