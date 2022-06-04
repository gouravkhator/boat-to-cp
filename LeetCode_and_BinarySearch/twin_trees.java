import java.util.*;

/**
 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */
class TwinTrees {
  // Problem Question: https://binarysearch.com/problems/Twin-Trees
  public boolean solve(Tree root0, Tree root1) {
    /*
    Approach:
    -----------

    If any one of the roots are null, then they are not twins, so we return false.
    If both are null, then they are twins and we return true.

    If both are not null, then we check their values and then recur for their respective left children and right children.

    If the values differ, we don't even recur for left, and if from left recursive call, we get false, we don't recur for right.
    This is optimal and good, as we already got the answer that they are not twins.
    */
    if(root0 == null && root1 == null){
      return true;
    }

    if(root0 != null && root1 == null){
      return false;
    }

    if(root0 == null && root1 != null){
      return false;
    }

    return (root0.val == root1.val) && solve(root0.left, root1.left) && solve(root0.right, root1.right);
  }
}
