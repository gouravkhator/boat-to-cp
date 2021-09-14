import java.util.List;
import java.util.ArrayList;

class BinaryTreeRightSide {
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

    public void recur(TreeNode root, int depth, List<Integer> list){
        /*
        Logic:

        We traverse the right side first,
        and if list size is equal to depth, it means we got first non-null from right side.

        As at each level, we have 1 element more in the list. 
        So, before adding this node, if list size is 2, it means this node addition will make it 3.
        And this node is first non-null from the right at depth = 3 or level 3.
        */
        if(root == null) return;
        
        if(list.size() == depth){
            list.add(root.val);
        }
        
        recur(root.right, depth + 1, list);
        recur(root.left, depth + 1, list);
    }
    
    // Problem Question: https://leetcode.com/problems/binary-tree-right-side-view/
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        
        recur(root, 0, list);
        
        return list;
    }
}
