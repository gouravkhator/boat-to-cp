class ReverseNodesKGroup {
    public static void main(String[] args) {
        ListNode node = new ListNode(1);

        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);

        reverseKGroup(node, 3);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void printList(ListNode head){
        while(head != null){
            System.out.print(head.val + "  ");
            head = head.next;
        }
        
        System.out.println();
    }
    
    public static void reverse(ListNode start, ListNode end){
        ListNode grandfather = null, father = start, son = father.next;
        
        do{
            son = father.next;
            father.next = grandfather;
            grandfather = father;
            father = son;
        }while(father != end);

        father.next = grandfather;
    }
    
    // Problem Question: https://leetcode.com/problems/reverse-nodes-in-k-group/
    public static ListNode reverseKGroup(ListNode head, int k) {
        /*
        Logic:

        current is the current node. start is the starting node of the current list.
        prevStart is the starting node of previous list, but which will be connected to start node of current list, as previous list is reversed now.

        firstKList is just a boolean, to track if the current list is the first list of k nodes, and if it is, then edit the head of the whole list.

        If k is 1, then no nodes are reversed, so return the head directly.
        Else, do the following..

        count at every node.
        If count becomes k, we have to do few things:
        1. First save the next element as tempNextStart. It will be used to go to next list easily.
        2. Reverse the list of start to current node. (using grandfather, father, son approach).
        3. If firstKList is true, it means set head to current node (as now the list is getting reversed).
         Also, set firstKList to false, for not setting head again and again.
        4. If prevStart is present, then previous start (or the last node of reversed previous list) is to be linked to current node.
        5. Now, set prevStart to start, it's for further linking of current list of K nodes to next list.
        6. Set start to tempNextStart and current to tempNextStart. It's for setting the next list start.
        7. Reset count to 0.

        If count is less than k, then just current is current.next 

        After we have processed all elements, it may happen that the whole list length was not divisible by K, 
        and left over nodes needs to be linked to the previous K nodes list.
        If count is less than k and greater than 0, then prevStart which is the node to be linked to start.

        Ex- List : 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
        k = 3

        After all processing in while loop,
        3 -> 2 -> 1 -> 6 -> 5 -> 4  7 -> 8

        And prevStart is pointing to 4, and start is pointing to 7.
        So, link prevStart and start as 7 -> 8 is a list with nodes less than 3.
        */

        if(k==1) return head;
        
        ListNode current = head, start = head, prevStart = null;
        int count = 0;
        boolean firstKList = true;
        
        while(current != null){
            count++;
            
            if(count == k){
                ListNode tempNextStart = current.next;
                
                // reverse from start to current using grandfather, father and son approach.
                reverse(start, current);
                
                if(firstKList == true){
                    head = current;
                    firstKList = false;
                }
                
                if(prevStart != null){
                    prevStart.next = current;
                }
                
                prevStart = start;

                start = tempNextStart;
                current = tempNextStart;
                count = 0;
            }else{
                current = current.next;
            }
        }
        
        if(count > 0 && count < k){
            prevStart.next = start; // link the previous list of K nodes to current list of less than K nodes.
        }
        
        printList(head); // print the list for testing purpose
        return head;
    }
}
