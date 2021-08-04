class SumOfNodesWithEvenGP {
  public class Tree {
    int val;
    Tree left;
    Tree right;
  }

  // Code logic contributed by Mohit Daga
  // Problem Question: https://binarysearch.com/problems/Sum-of-Nodes-with-Even-Grandparent-Values
  public int solve(Tree root) {
      /*
      Logic:
      Forward the parent and grandparent along with current child node.
      If grandparent is present for current node, and its value is even
      Just add the current child node's value to sum.
      */
      return recurseTree(root, null, null); // no parent and no grandparent for current node which is root
  }

  public int recurseTree(Tree currentNode, Tree parent, Tree grandparent){
      if(currentNode == null) return 0;

      int sum=0;
      if(currentNode.left != null)
          sum = recurseTree(currentNode.left, currentNode, parent);
      
      if(currentNode.right != null)
          sum += recurseTree(currentNode.right, currentNode, parent);

      if(grandparent != null && grandparent.val % 2 == 0){
          // grandparent exists for current node and grandparent's val is even
          sum += currentNode.val;
      }

      return sum; // required sum is returned in this recursion
  }
}