class ConvertBinaryTreeToSumTree{
    static class TreeNode {
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

    public static int recur(TreeNode root) {
        /*
        Logic:

        If the root is a leaf node, just set it 0, and return its old value to its parent.

        If its non-leaf node, sum the returned values of its children and,
        then set the sum of returned values of children to this node.
        
        Return the sum of their returned values + current node's old value from this node.
        */
        if(root == null) return -1;

        if(root.left == null && root.right == null){
            int val = root.val;
            root.val = 0;
            return val;
        }

        int sum = root.val;
        if(root.left != null){
            sum += recur(root.left);
        }

        if(root.right != null){
            sum += recur(root.right);
        }

        root.val = sum - root.val;
        return sum;
    }

    public static void traverse(TreeNode root) {
        if(root == null) return;
        System.out.println(root.val);
        traverse(root.left);
        traverse(root.right);
    }

    // Problem Question: https://www.geeksforgeeks.org/convert-a-given-tree-to-sum-tree/
    public static void solve(TreeNode root) {
        recur(root);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(10);
        node.left = new TreeNode(-2);
        node.right = new TreeNode(6);

        node.left.left = new TreeNode(8);
        node.left.right = new TreeNode(-4);
        node.right.left = new TreeNode(7);
        node.right.right = new TreeNode(5);

        solve(node);
        traverse(node);
    }
}