class QueriesPermutationKey {
  static class Node{
    int data;
    Node next;
    Node(int data){
      this.data = data;
      this.next = null;
    }
  }

  static class LinkedList{
    Node head;
    int n;

    public LinkedList(int n){
      this.n = n;
      this.head = new Node(1);
      Node temp = this.head;
      for (int i = 2; i <= n; i++) {
        temp.next = new Node(i);
        temp = temp.next;
      }
    }

    public void traverse(){
      if(this.head == null) return;
      Node temp = this.head;
      for (int i = 1; i <= n; i++) {
        System.out.print(temp.data+" ");
        temp = temp.next;
      }
    }

    // Insert the data to the start of linked list
    public void insertFirst(int data){
      if(this.head == null){
        this.head = new Node(data);
        return;
      }

      Node temp = new Node(data);
      temp.next = this.head;
      this.head = temp;
    }

    // For getting modified remove method, I implemented Linked List.
    // This remove method removes the data from the list,
    // but gets the index where the data was previously present before being removed.
    public int remove(int data){
      if(this.head == null) return -1;

      if(this.head.data == data){
        this.head = this.head.next;
        return 0;
      }

      Node temp = this.head;
      int index = 0;
      while(temp.next != null){
        if(temp.next.data == data){
          temp.next = temp.next.next;
          return (index + 1);
        }

        index++;
        temp = temp.next;
      }

      return -1;
    }
  }
  
  // Problem Question: https://leetcode.com/problems/queries-on-a-permutation-with-key/
  public static int[] processQueries(int[] queries, int m) {
    LinkedList ll = new LinkedList(m); // my Linked List implementation
    int[] removedIndices = new int[queries.length];

    int removedIndex = -1, k = 0;

    /*
    Logic:

    Take each query, and then remove that query element from linked list.
    We also get index of that element before it was removed.
    Insert that element to the start of linked list.

    Note:

    My implementation of linked list gets modified version of remove method.
    This remove method removes the data whilst giving the index in that traversal only.
    Or else, in an inbuilt LinkedList, I had to get index and remove in two traversals.
    */
    for (int query : queries) {
      removedIndex = ll.remove(query);
      removedIndices[k++] = removedIndex;
      ll.insertFirst(query);
    }

    return removedIndices;
  }

  public static void main(String[] args) {
    int[] arr = processQueries(new int[]{4,1,2,2}, 4);
    // int[] arr = processQueries(new int[]{7,5,5,8,3}, 8);

    for (int i : arr) {
      System.out.println(i);
    }
  }
}