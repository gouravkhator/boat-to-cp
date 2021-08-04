import java.util.Iterator;
import java.util.ArrayList;

class BSTIterator {
  public class Tree {
    int val;
    Tree left;
    Tree right;
  }
  
  Iterator<Integer> iterator;

  public void inorderTraversal(Tree root, ArrayList<Integer> list){
    // Do inorder recursive traversal and add values to the list
    if(root == null) return;

    if(root.left != null){
        inorderTraversal(root.left, list);
    }

    list.add(root.val);

    if(root.right != null){
        inorderTraversal(root.right, list);
    }
  }

  // Problem Question: https://binarysearch.com/problems/Binary-Search-Tree-Iterator
  // Below methods are methods that are called by binarysearch
  public BSTIterator(Tree root) {
      ArrayList<Integer> list = new ArrayList<>();
      inorderTraversal(root, list);
      this.iterator = list.iterator(); // iterator of the stored inorder traversal list 
  }

  public int next() {
      if(this.iterator.hasNext()){
          return this.iterator.next();
      }

      return 0;
  }

  public boolean hasnext() {
      return this.iterator.hasNext();
  }
}
