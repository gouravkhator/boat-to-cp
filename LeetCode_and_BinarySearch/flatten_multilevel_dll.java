import java.util.*;

class FlattenMultilevelDLL {
  class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
  }

  // Problem Question: https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
  public Node flatten(Node head) {
    Node curr = head;
    Stack<Node> stack = new Stack<>();
      
    while(curr!=null){
      // if curr's child is there, then push the next element to stack, and then connect this curr to its child as the next, and also unset the child
      if(curr.child != null){
        if(curr.next != null){
          stack.push(curr.next);
        }
        
        curr.next = curr.child;
        curr.next.prev = curr;
        curr.child = null;
      }else{
        // if curr's child is not there, then see if the curr's next is null, then we should take node from the stack (only if stack is not empty)
        if(curr.next == null && !stack.isEmpty()){
          curr.next = stack.pop();
          curr.next.prev = curr;
        }
      }
      
      curr = curr.next; // we then go to the next element and do the similar thing while next becomes null totally, and stack is also empty
      // if stack becomes empty and curr is null, then the code inside else block fails too.
    }
    
    return head;
  }
}
