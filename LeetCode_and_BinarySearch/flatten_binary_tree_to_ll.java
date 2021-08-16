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