import java.util.*;

class FindLargestValEachTreeRow {
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

  // Problem Question: https://leetcode.com/problems/find-largest-value-in-each-tree-row/
  public List<Integer> largestValues(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }

    List<Integer> res = new ArrayList<>();

    Queue<TreeNode> parents = new LinkedList<>();
    Queue<TreeNode> children = new LinkedList<>();

    parents.add(root);
    res.add(root.val); // add the only maximum node in the 1st level, to the resultant

    long maxNode = Long.MIN_VALUE; // taking long, as the range for node values are in the int range

    while (true) {
      TreeNode node = parents.poll(); // remove a node from the parents list

      if (node.left != null) {
        // then add left or right children if they exists, and also track the max node value from them
        maxNode = Math.max(maxNode, node.left.val);
        children.add(node.left);
      }

      if (node.right != null) {
        maxNode = Math.max(maxNode, node.right.val);
        children.add(node.right);
      }

      if (parents.isEmpty()) {
        // if parents is empty, which means we have to go to next level..

        if (children.isEmpty()) {
          // if the next level is not there, meaning children is empty, we break from the loop
          break;
        }

        res.add((int) maxNode); // adding the max node value of the children

        parents = children; // making children, the new parents

        maxNode = Long.MIN_VALUE; // reset the max node value, as we go to the next level
        children = new LinkedList<>(); // resetting the children list
      }
    }

    return res;
  }
}
