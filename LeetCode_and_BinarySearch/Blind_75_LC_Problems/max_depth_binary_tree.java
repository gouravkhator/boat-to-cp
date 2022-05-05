class MaxDepthBinaryTree {
    class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int data) {
            this.val = data;
        }

        TreeNode(int data, TreeNode left, TreeNode right) {
            this.val = data;
            this.left = left;
            this.right = right;
        }
    }

    // Problem Question: https://leetcode.com/problems/maximum-depth-of-binary-tree/
    public int maxDepth(TreeNode root) {
        /**
         * Approach:
         * 
         * Maximum depth of binary tree is maximum of depth of left child and depth of
         * right child + 1 (1 is for counting this current node)
         * 
         * -------------------------------------------------------
         * 
         * Time complexity: O(n), where n is the number of nodes..
         * as it traverses all the nodes
         */
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}