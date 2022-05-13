class KthSymbolFastestApproach {
  // Problem Question: https://leetcode.com/problems/k-th-symbol-in-grammar/
  public int kthGrammar(int n, int k) {
    /**
     * Credits:
     * --------
     * My analogy of a visually simulated tree is used here too.
     * In that less optimal approach, I thought to do BFS to optimise it and use caching, but it didn't work too.
     * 
     * The fastest approach's analogy is taken from the leetcode discuss section.
     * 
     * -----------------------------------------------------------------------------------
     * Analogy:
     * --------
     * 
     * When visualising a tree, we get below tree:
     *           0
     *        /     \
     *       0       1
     *      / \     / \
     *     0   1   1   0
     *    / \ / \ / \ / \
     *   0  1 1 0 0 1 1 0
     * 
     * Either we could go by basic recursion and backtracking approach like my less optimal one,
     * but that gave TLE for some extreme cases.
     * 
     * Now, when we see the patterns between each parent and child, we will note that:
     * 
     * For child at level 3 and position 2 (all indices are considered 1-indexed),
     * It checks if its parent is 0, then it will be 1, else it will be 0.
     * 
     * For child at level 3 and position 3, it checks if its parent is 0, then it will be 1, else will be 0.
     * 
     * As it is considered 1-indexed, so, for position even or odd, the parent might be evaluated in different way.
     * 
     * For position (k) as even, its parent will be k/2, so for position 2, parent is at position 1.
     * For position (k) as odd, its parent will be (k+1)/2, so for position 3, parent is at position 2.
     */
    if (n == 1) {
      return 0;
    }

    if (k % 2 == 0) {
      // we are at nth level, and we traverse the tree upwards..
      return kthGrammar(n - 1, k / 2) == 0 ? 1 : 0;
    } else {
      return kthGrammar(n - 1, (k + 1) / 2) == 0 ? 0 : 1;
    }
  }
}

/**
 * this less optimal approach works mostly,
 * but on extreme edge cases, where we had to simulate a tree fully till the bottom rightmost,
 * then it gave TLE..
 * 
 * It is bcoz backtracking was used here and each node had 2 children when visually simulated..
*/
class KthSymbolLessOptimal {
  int leafKCount = 0;

  public int grammarRecur(int currLevel, int totalLevels, int currentNode, int k) {
    /**
     * Analogy:
     * --------
     * When visualising a tree, we get below tree:
     *           0
     *        /     \
     *       0       1
     *      / \     / \
     *     0   1   1   0
     *    / \ / \ / \ / \
     *   0  1 1 0 0 1 1 0
     * 
     * Now, once we get to level = n,
     * we just keep track of how many leaf nodes are already done, and at what leaf node we are at.
     * 
     * If we get the kth value at nth level, we return that value, 
     * and when we backtrack we check if we got the value or not, and if yes, we don't recur for right subtree.
     * 
     * But in worst case, when n=29, and k = 2^28 (the extreme rightmost position at nth level):
     * then, we have to traverse the whole tree..
     * 
     * This worst case leads to the TLE.
     * 
     * --------------------------------------------------------------------------------------------
     * 
     * Approach:
     * ---------
     * 
     * Mostly, approach is told in analogy section.
     * 
     * We return -1, when we are not at kth leaf node,
     * and if the returned value is -1, we have to recur for right subtree too.
     * 
     * --------------------------------------------------------------------------------------------
     * 
     * Time complexity: O(nk * 2^(n-1)) -- found mostly by approximation and patterns from the examples
     * Space complexity: O(1) extra space
     * 
     * ----------------------------------------------------------------------------------------
     * 
     * One way to optimise the backtracking approach is to use BFS (level order traversal), and caching.
     * But mostly, this also does not solve the TLE issue.
     */
    if (currLevel == totalLevels) {
      // leaf node
      leafKCount++;

      if (leafKCount == k) {
        return currentNode;
      }

      return -1;
    }

    if (currentNode == 0) {
      int resVal = grammarRecur(currLevel + 1, totalLevels, 0, k);

      if (resVal == -1) {
        return grammarRecur(currLevel + 1, totalLevels, 1, k);
      } else {
        return resVal;
      }
    }

    if (currentNode == 1) {
      int resVal = grammarRecur(currLevel + 1, totalLevels, 1, k);

      if (resVal == -1) {
        return grammarRecur(currLevel + 1, totalLevels, 0, k);
      } else {
        return resVal;
      }
    }

    return -1;
  }

  public int kthGrammar(int n, int k) {
    // build a visually simulated tree of height=n, starting level to be 1.
    return grammarRecur(1, n, 0, k);
  }
}