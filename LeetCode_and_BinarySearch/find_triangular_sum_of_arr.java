import java.util.*;

class FindTriangularSumNaive {

  // Problem Question: https://leetcode.com/problems/find-triangular-sum-of-an-array/
  public int triangularSum(int[] nums) {
    /*
     * Approach:
     * ------------
     *
     * We use the naive way to solve this.
     * Parents will be the current elements, and children will be the new elements set we make.
     *
     * We continue till the parents contain atleast more than 1 element.
     * We make the children the new parents each time in the loop.
     */

    List<Integer> parents = new ArrayList<>();
    List<Integer> children = new ArrayList<>();

    for (int num : nums) {
      parents.add(Integer.valueOf(num));
    }

    while (parents.size() > 1) {
      int parentsCount = parents.size();

      for (int i = 0; i <= (parentsCount - 2); i++) {
        children.add((parents.get(i) + parents.get(i + 1)) % 10);
      }

      parents = children;
      children = new ArrayList<>();
    }

    return parents.get(0); // we then return the 1st element in the 1 element list
    // NOTE: in the constraint, we have atleast 1 element given in nums array, and nums array is non-empty
  }
}
