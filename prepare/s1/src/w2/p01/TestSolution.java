package w2.p01;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

class TestSolution {

	@Test
	void test() {

		ListNode in = build( new int[] {1, 2, 3, 4, 5});
		ListNode outExpected = build( new int[] {5, 4, 3, 2, 1});
		
		print(in);
		ListNode out = new Solution().reverseList(in);

		System.out.println();
		print(out);
		print(outExpected);
		
		assertTrue("", compare(out, outExpected));

	}
	
	/** Recursively compare O(n) */
	private boolean compare(ListNode l1, ListNode l2) {
		if( l1 == null && l2 == null )
			return true;
		if( l1 !=null && l2 != null) {
			return l1.val == l2.val && compare(l1.next, l2.next);
		}
		return false;
	}

	static ListNode build(int[] values) {
		ListNode head = null;
		ListNode prev = null;
		for( int val : values) {
			ListNode curr = new ListNode(val);
			if ( head == null)
				head = curr;
			if( prev != null )
				prev.next = curr;
			prev = curr;
		}
		return head;
	}
	
	/** consume all nodes in order */
	static void consumeAll(ListNode node, Consumer<ListNode> consumer) {
		while(node!=null) {
			consumer.accept(node);
			node=node.next;
		}
	}
	
	/** print all nodes in order */
	static void print(ListNode node) {
		List<Integer> list = new ArrayList<>();
		consumeAll(node, n -> list.add(n.val) );
		System.out.println(list.toString());
	}
	

}
