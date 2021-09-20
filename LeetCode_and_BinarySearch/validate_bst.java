class ValidateBST {
    public class TreeNode {
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

    public boolean helper(TreeNode root, Integer min, Integer max){
        /*
        Logic:

        Take min and max.
        min is for assuring that the current root.val should be > min,
        and max is for assuring that current root.val < max.

        If min is not null and root.val <= min, it violates and so return false.
        If max is not null and root.val >= max, it violates and so return false.

        Then recur for left child with min as same minimum, and max as current root.val
        Recur for right child with min as root.val, and max as same maximum.

        The logic is the left child will be lesser than current val, so keep max as current val for left child.
        The right child will be greater than current val, so keep min as current val for right child.

        Why used Integer wrapper class instead of int?
        Ans: So that, we can initialise min and max with null.
        As the question has full int range as valid bst node values, so cannot keep them in the checking part.
        */

        if(root == null) return true;
        
        if(min != null && root.val <= min){
            return false;
        }
        
        if(max != null && root.val >= max){
            return false;
        }
        
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }
    
    // Problem Question: https://leetcode.com/problems/validate-binary-search-tree/
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        
        return helper(root, null, null);
    }
}
