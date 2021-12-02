class FlattenBinaryTree {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public static void recursive(TreeNode root, TreeNode savedRight){
    /*
    Logic:
    Send right node as parameter and process left nodes.
    If left node is processed fully, 
    take the right node back and process it and then attach right's head to the left node chain's tail node.

    Don't forget to remove left subtree after we have copied them in right.

    Core logic can be explained through an example :
        1
      /   \
    2      5
  /  \      \
 3    4      6
    
    For above tree, we first get 1, and then while going to 2, we send 5 as the savedRight node.
    Also, while going to node 2, we attach node 2 to right of node 1, and we reset node 1's left.
    This is always done when going to left child.

    So, in recursive function, root is node 2 and savedRight is node 5.
    Again, we go to node 3 and send 4 as savedRight.
    And node 3 has been attached to node 2's right and node 2's left has been reset.

    Now, at node 3, we see no child. So, we attach savedRight to 3's right and recurse for node 4.
    Then, when that recursion is done, we again backtrack to node 2 with savedRight as node 5.

    Now, for attaching savedRight, that is, node 5 we must travel to node 2's rightmost element.
    (As already the left children have been null, and we have attached the nodes to right)

    So, node 4 is right of node 3, which is right of node 2, which is right of node 1.
    We will attach node 5 to right of node 4.
    Now, recurse for node 5 with savedRight as null.
    Now, as node 5 does not have left, we goto its right and recurse there with savedRight as null.

    And hence, the binary tree is flattened.
    */

    if(root == null) return;

    // if left child is there
    if(root.left != null){
      // store right child
      TreeNode rightTemp = root.right;
      root.right = root.left; // assign left child to right of the current root
      recursive(root.left, rightTemp); // recurse in left subtree

      root.left = null; // remove left subtree after we have traversed it and stored all of them in right.

    }else if(root.right == null && root.left == null){
      // if both children are not there, and savedRight is there
      if(savedRight != null){
        // attach savedRight to right of the root and recurse through savedRight subtree
        root.right = savedRight;
        recursive(root.right, null);
        savedRight = null; // set savedRight to null as we have used savedRight and we don't want to attach it again
      }

    }else if(root.right != null && root.left == null){
      // if right child is only there, then recurse through right subtree
      recursive(root.right, savedRight);
      savedRight = null; // set savedRight to null as we have used savedRight and we don't want to attach it again.
    }

    if(savedRight != null){
      // if savedRight was not attached previously,
      // it means when left child was being traversed, and we didn't use savedRight

      recursive(savedRight, null); // recurse through savedRight like the starting node

      TreeNode temp = root; // traverse from current root where we originally got savedRight in parameter.

      while(temp.right != null){
        temp.left = null; // remove left subtree after we have traversed it and stored all of them in right.

        temp = temp.right; // traverse to rightmost element of root
        // here most elements are flattened
      }

      temp.left = null; // remove left subtree after we have traversed it and stored all of them in right.
      temp.right = savedRight; // attach savedRight to right of the rightmost node
    }
  }
  
  // Problem Question: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
  public static void flatten(TreeNode root) {
    recursive(root, null);
    // Initially, we did not store any right child, so savedRight is passed as null
  }

  // Traverse flattened tree using preorder traversal
  public static void traverse(TreeNode root) {
    if(root == null) return;
    System.out.println(root.val);
    traverse(root.left);
    traverse(root.right);
  }

  public static void main(String[] args) {
    TreeNode node = new TreeNode(1, new TreeNode(2), new TreeNode(5));
    node.left.left = new TreeNode(3);
    TreeNode temp = new TreeNode(4, new TreeNode(7), new TreeNode(9));
    node.left.right = temp;
    temp.left.right = new TreeNode(8);
    node.right.right = new TreeNode(6);

    // Another test case given below, comment above and uncomment below
    // TreeNode node = new TreeNode(1, new TreeNode(2), new TreeNode(5));
    // node.left.left = new TreeNode(3);
    // node.left.right = new TreeNode(4);
    // node.right.right = new TreeNode(6);
    recursive(node, null);
    traverse(node);
  }
}