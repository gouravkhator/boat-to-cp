//problem question : https://leetcode.com/problems/add-two-numbers/

public class Add_Numbers_LinkedList {

	static class ListNode {
		 int val;
		 ListNode next;
		 ListNode() {}
		 ListNode(int val) { this.val = val; }
		 ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
	
	static void printList(ListNode head) {
		ListNode temp = head;
		while(temp!=null) {
			System.out.println(temp.val);
			temp = temp.next;
		}
	}
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(8);
//		l1.next = new ListNode(9);
//		l12.next = new ListNode(3);
		
		ListNode l2 = new ListNode(5);
//		ListNode l22 = l2.next = new ListNode(7);
//		l22.next = new ListNode(4);
		
		ListNode res = addTwoNumbers(l1, l2);
		printList(res);
	}

	static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int rem = 0, count = 1, a, b, res=0;
		
		if(l1==null || l2==null) return null;
		
		ListNode resultNode =null;
		ListNode head = null;
		
		while(l1!=null && l2!=null) {
			a = l1.val;
			b = l2.val;
			res = (a+b+rem)%10;
			if(count==1) {
				resultNode = new ListNode(res);
				head = resultNode; //assigning head if its 1st node
			}else {
				resultNode.next = new ListNode(res); //creating new node and assigning next to that
				resultNode = resultNode.next;
			}
			count++;
			rem = (a+b+rem)/10;
			l1 = l1.next;
			l2 = l2.next;
		}
		
		//if they were of equal length but creation of new node was required
		//for test case : 5+5 = 10 so creation of node 1 is required after both lists were null as remainder was left
		if(l1==null && l2==null && rem > 0) {
			resultNode.next = new ListNode(rem);
			return head;
		}
		
		while(l1!=null) {
			a=l1.val;
			res = (a+rem)%10;
			resultNode.next = new ListNode(res);
			resultNode = resultNode.next;
			rem = (a+rem)/10;
			l1 = l1.next;
		}
		
		while(l2 != null) {
			b = l2.val;
			res = (b+rem)%10;
			resultNode.next = new ListNode(res);
			resultNode = resultNode.next;
			rem = (b+rem)/10;
			l2 = l2.next;
		}
		
		//for test case, 98+5 here the list were of unequal length and 
		//the sum would also result in creation of new node after both lists are null 
		if(rem > 0) {
			resultNode.next = new ListNode(rem);
		}
		
		return head;
	}
}
