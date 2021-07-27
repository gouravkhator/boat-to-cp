//problem question : https://leetcode.com/problems/swap-nodes-in-pairs/submissions/

public class SwapNodes {

	static class ListNode {
		 int val;
		 ListNode next;
		 ListNode() {}
		 ListNode(int val) { this.val = val; }
		 ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode e2 = new ListNode(2);
		ListNode e3 = new ListNode(3);
		ListNode e4 = new ListNode(4);
		ListNode e5 = new ListNode(5);
		ListNode e6 = new ListNode(6);
		head.next = e2;
		e2.next = e3;
		e3.next = e4;
		e4.next = e5;
		e5.next = e6;
		e6.next = null;
		head = swapPairs(head);
		printList(head);
	}
	static void printList(ListNode head) {
		ListNode temp = head;
		while(temp!=null) {
			System.out.println(temp.val);
			temp = temp.next;
		}
	}
	
	static ListNode swapPairs(ListNode head) {
		if(head == null) return null;
        if(head.next == null) return head;
        
		ListNode firstNode = head;
		ListNode secondNode = head.next;
		ListNode prevNode = null;
		
		//secondNode can refer to somewhere else whilst not changing head reference
		//as they are reference variables and secondNode and head was referring to same object before, 
		//now secondNode refers to other place
		
		firstNode.next = secondNode.next;
		secondNode.next = firstNode;
		head = secondNode;
		prevNode = firstNode;
		firstNode = firstNode.next;
		while(firstNode!=null && firstNode.next != null) {
			//from 3rd element onwards
			//firstNode referring to 3rd element at start of loop
			secondNode = firstNode.next;
			firstNode.next = secondNode.next;
			secondNode.next = firstNode;
			prevNode.next = secondNode;
			prevNode = firstNode;
			firstNode = firstNode.next;
		}

		return head;
	}
}