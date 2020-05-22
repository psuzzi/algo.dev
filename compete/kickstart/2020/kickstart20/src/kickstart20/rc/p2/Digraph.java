package kickstart20.rc.p2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Subproblem: build a directed graph with Strings. Return "-1" if has cycles,
 * or
 * 
 * @author psuzzi
 *
 * @param <T>
 */
public class Digraph {

	public static void main(String[] args) {
		Digraph d1 = new Digraph(4);
		d1.addEdge(0, 1)
			.addEdge(1, 2)
			.addEdge(2, 3)
			.addEdge(0, 2);
		System.out.println(d1.topologicalSort());
		Digraph d2 = new Digraph(4);
		d1.addEdge(0, 1)
			.addEdge(1, 2)
			.addEdge(2, 3)
			.addEdge(0, 2)
			.addEdge(2, 0);//this creates a cycle
		System.out.println(d2.topologicalSort());
	}

	/**
	 * In a DAG: DFS post order, in reverse.
	 * If not a DAG -1;
	 * @return
	 */
	private List<Integer> topologicalSort() {
		Stack<Integer> stack = new Stack<>();
		
		boolean visited[] = new boolean[v];
		boolean complete[] = new boolean[v];
		for(int i=0; i<v; i++) {
			topSortUtil(visited, complete, )
			if(!visited[i]) {
				
			}
		}
		return topsort;
	}

	private final int v;
	private final List<List<Integer>> adj;

	public Digraph(int v) {
		this.v = v;
		adj = new ArrayList<>();
		for (int i = 0; i < v; i++)
			adj.add(new ArrayList<Integer>());
	}

	Digraph addEdge(int v, int w) {
		adj.get(v).add(w);
		return this;
	}

}
