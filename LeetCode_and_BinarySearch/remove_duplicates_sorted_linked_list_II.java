class Remove_Duplicates_Sorted_Linked_List {

    class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Problem Question: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null; // 0 elements
        
        if(head.next == null) return head; // 1 element
        
        ListNode previousNodeToLink = null, previousNode = head;
        // previousNode means just previous node of current node
        // previousNodeToLink means the last non-duplicate node 
        // which can have nextpointer to next node removing any duplicates in between. 

        ListNode currentNode = head.next;
        int currentValueCount = 1;
        // currentValueCount means how many times the currentValue appears in this list
        // previousValue means which is the previous Node value, and it's initialised outside of the given constraint
        
        int headValCount = 1; // count how many times head's value occur
        
        while(currentNode != null){
            
            if(currentNode.val == head.val){
                // if current node has same value as that of head
                headValCount++;
            }else{
                if(headValCount > 1){
                    // if head value is duplicating, update head
                    head = currentNode;
                }
                
                headValCount = 1; // reset headValCount
            }
            
            if(currentNode.val == previousNode.val){
                // if the currentnode is continuation of previousNode
                currentValueCount++;
            }else{
                // current value is not equal to previousValue so remove duplicates in between (if any)
                
                if(currentValueCount > 1){
                    // duplicate element found
                    if(previousNodeToLink != null){
                        // duplicates were in middle of list somewhere
                        previousNodeToLink.next = currentNode; // set next of previousNodeToLink to this node
                    }
                }else{
                    // no duplicate for previous node's value, so set that previous node as previous node to link
                    previousNodeToLink = previousNode;
                }
                
                currentValueCount = 1; // reset currentValue Count
            }
            
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        
        // update if required, after seeing headValCount at last
        if(headValCount > 1){
            head = currentNode;
        }
        
        // update if required after seeing currentValueCount at last
        if(currentValueCount > 1){
            // last elements were duplicate
            // if previousNodeToLink is somewhere in middle
            if(previousNodeToLink != null){
                previousNodeToLink.next = null;
            }
        }else{
            // no duplicate for last node's value, set previousNodeToLink to last node
            previousNodeToLink = previousNode;
        }
        
        return head;
    }
}
