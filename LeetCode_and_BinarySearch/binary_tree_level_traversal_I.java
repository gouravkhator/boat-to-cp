import java.util.ArrayList;
import java.util.List;

class BinaryTreeLevelTraversalI {

  // Definition for a binary tree node.
  public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  // Problem Question: https://leetcode.com/problems/binary-tree-level-order-traversal/
  public List<List<Integer>> levelOrder(TreeNode root) {
    /*
     * Approach:
     * -------------
     *
     * Take the parents and children list, and add the root to the parents list.
     * Now, process the parents list, and add all the children of the current parents to the chldren list..
     *
     * While processing the parents, add its values to the tempList,
     * and when the parents list gets empty, then add this tempList to the res list of lists.
     *
     * ---------------------------------------------------------------
     *
     * Let `n` be the number of nodes in the binary tree.
     *
     * Time complexity: O(n), as we traverse each element only once.
     *
     * Space Complexity: O(n/2), as at each time, we are only storing two levels of the nodes,
     * mainly the parents and children.
     * And even in a full binary tree also, we will have (n/2) elements in the last level.
     * So, we approximate it to O(n/2).
     */
    if (root == null) {
      return new ArrayList<>();
    }

    List<TreeNode> parents = new ArrayList<>();
    List<TreeNode> children = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> tempList = new ArrayList<>();

    parents.add(root);

    while (parents.size() > 0) {
      TreeNode p = parents.get(0);

      tempList.add(p.val);

      if (p.left != null) children.add(p.left);

      if (p.right != null) children.add(p.right);

      parents.remove(p);

      if (parents.size() == 0) {
        res.add(new ArrayList<>(tempList));
        tempList = new ArrayList<>();

        if (children.size() == 0) {
          break;
        }

        parents = children;
        children = new ArrayList<>();
      }
    }

    return res;
  }
}
