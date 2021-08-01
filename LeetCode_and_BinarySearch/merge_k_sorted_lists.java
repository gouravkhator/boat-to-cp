class MergeKSortedLists {
  static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  public static ListNode insertionSort(ListNode head){
    // head is a copy of reference of resHead and updating head will not update resHead
    // so, return head and set it to resHead
    ListNode a,b,p,q;
    if(head==null){
        return null;
    }
    
    for(a=head, b=head.next; b!=null;){
        
      for(p=null, q=head; q!=b && q.val <= b.val; p=q, q=q.next);
      
      if(q==b){
          // all previous elements were sorted
          a=a.next;
          b=b.next;
      }else{
          // not all previous elements were sorted
          if(p==null){
              // the 1st element needs to be swapped with the smaller element at b
              head = b;
              a.next = b.next;
              b.next = q;
              b = a.next;
          }else{
              p.next = b;
              a.next = b.next;
              b.next = q;
              b = a.next;
          }
      }
    }
    return head;
  }
  
  // Problem Question: https://leetcode.com/problems/merge-k-sorted-lists/
  public static ListNode mergeKLists(ListNode[] lists) {
    int k = lists.length, i=0;
    ListNode resHead = null, resTail = null, tempHead = null;
    // resHead means the head of final merged list
    // resTail means tail of final merged list
    // resTail is used to merge multiple linked lists

    for(i=0; i<k; i++){
      if(resTail != null){
        resTail.next = lists[i]; // link resTail with current head if resTail was set
        // if current head is null, then also resTail.next will be null which is required.
      }
      
      if(lists[i] == null){
        // if current head is null, just don't do the below steps
        continue;
      }
      
      // current head, lists[i] is now not null

      if(resHead == null){
        resHead = lists[i]; // set resHead if resHead was not set before
      }
      
      tempHead = lists[i]; // head of the current list
      
      while(tempHead.next != null){
        tempHead = tempHead.next;
      }
      
      resTail = tempHead; // set resTail to last element of the current list
    }
    
    resHead = insertionSort(resHead);
    return resHead;
  }

  public static void main(String[] args) {
    ListNode[] lists = new ListNode[2];

    lists[0] = new ListNode(1, null);
    lists[1] = new ListNode(0, null);
    ListNode resHead = mergeKLists(lists);

    while(resHead!=null){
      System.out.println(resHead.val);
      resHead=resHead.next;
    }
  }
}