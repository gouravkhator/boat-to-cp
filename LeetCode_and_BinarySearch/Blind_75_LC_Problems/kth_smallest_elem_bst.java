class KThSmallestInBST {
    class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class StaticInt {
        int val = -1;

        StaticInt(int val) {
            this.val = val;
        }
    }

    public int kthSmallestRecur(TreeNode root, StaticInt kInt) {
        /**
         * Approach:
         * 
         * We just visit the left child first, and if at any point, the k values becomes
         * 0, we just return that node's value,
         * else, we return -1 as we don't need any other values.
         * 
         * And when we return from left child, we check if k's value became 0 in left
         * subtree or not, and if it became 0, then return that value returned from left
         * subtree.
         * 
         * If it didn't become 0 in left subtree, we decrement k by 1 as we visited this
         * node now.
         * Now, we again check if k becomes 0 or not, and if yes, then return this
         * node's value.
         * 
         * If k didn't become 0 yet, we recur for right subtree and then we again check
         * for k is 0 or not after that right subtree traversal, and if it became 0,
         * then return the result returned by right subtree.
         * 
         * -----------------------------------------------------------------
         * 
         * Time complexity: O(k + log(n)), as it traverses k elements, each element only once
         * but it may travel the whole depth of the tree to find those k elements.
         * 
         * For example: For finding 1st smallest element, it has to travel to the leftmost of the BST.
         */
        if (root == null) {
            return -1;
        }

        int leftRes = kthSmallestRecur(root.left, kInt);

        if (kInt.val == 0) {
            return leftRes;
        }

        kInt.val--;

        if (kInt.val == 0) {
            return root.val;
        }

        int rightRes = kthSmallestRecur(root.right, kInt);

        if (kInt.val == 0) {
            return rightRes;
        }

        return -1;
    }

    // Problem Question:
    // https://leetcode.com/problems/kth-smallest-element-in-a-bst/
    public int kthSmallest(TreeNode root, int k) {
        return kthSmallestRecur(root, new StaticInt(k));
    }
}