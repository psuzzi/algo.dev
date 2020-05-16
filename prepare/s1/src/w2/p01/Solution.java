package w2.p01;

/**
 * Reverse a linked list
 * 
 * @author psuzzi
 * @see https://leetcode.com/problems/reverse-linked-list/
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr!=null){
            ListNode tempNext = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tempNext;
        }
        return prev;
    }
}


/**
 * Definition for singly-linked list.
 * @author psuzzi
 *
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
