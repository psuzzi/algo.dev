package w1.p02;

/**
 * Definition for singly-linked list.
 */
class ListNode {
	int val;
	ListNode next;
	ListNode() {}
	ListNode(int val) { this.val = val; }
	ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        
        if(l1==null && l2==null)
            return null;
        
        ListNode parent = null, root = null;
        while(l1!=null || l2!=null){
            ListNode curr;
            if(l1==null || l2!=null && l2.val<l1.val){
                curr = l2;
                l2 = l2.next;
            } else {
                // l2 is null or l1.val<=l2.val
                curr = l1;
                l1=l1.next;
            }
            if(parent==null){
                root=curr;
            } else {
                // update current parent
                parent.next=curr;
            }
            // next parent
            parent=curr;
        }
        return root;
    }

}