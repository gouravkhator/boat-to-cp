class BinaryTreeMaxPathSum {  
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

  class IntMod{
    int value = 0;
  }
  
  /**
   * Returns the maximum of all the elements in the elements array.
   */
  public int findMaximum(int[] elements){
    int max = Integer.MIN_VALUE;
    
    for(int elem: elements){
      if(max < elem){
        max = elem;
      }
    }
    
    return max;
  }
  
  public int maxPathRecur(TreeNode root, IntMod maxRes, IntMod maxElem){
      /*
Test cases (the binary tree on leetcode test cases):
----------------------------------------------------

[1,2,3]
[9,6,5,null,7,3,-4,null,null,-1,2]
[-10,9,20,null,null,15,7]
[0]
[9,1]
[9,1,null,4,-2,null,null,5]
[-1]
[-3,-10,-12,-5,null,-1]
[5,4,8,11,null,13,4,7,2,null,null,null,1]
[8,9,-6,null,null,5,9]
  */
    
    /*
    Analogy:
    ----------
    
    There can be negative numbers too.
    And even some negative numbers could be included in the max path, as if we just exclude the subtree containing the negative numbers, we will get a wrong answer.
    
    Ex- Tree example on leetcode: [8,9,-6,null,null,5,9]
    
              8
            /   \
           9    -6
                /  \
               5    9
    
    Here, if we exclude the subtree with root as -6 here, we will have to exclude the whole subtree with max possible sum of 3 (which is 9+(-6) = 3).
    And this subtree should be included in the max path sum of 9+8+3 = 20.
    
    We can either take the node itself only, or if we take the children also, we can take either of the two children or both of the children along with this node, or if we don't take the node, then we can take either of the children only, but not both.
    
    And while calculating the max path sum, we should track the maximum result.
    But, when we return the max path sum from this current node to its parents, we only return the max path sum got from this node.
    This max path sum from current node can be the maximum of either this node only, or the maximum of this node with the max of its children's max path sum.
    
    Approach:
    ----------
    
    If root is null, it means that we have no max path sum. And here, we should return the min int value, instead of 0, just to respect the negative elements too.
    
    If root's left is present, we recur for it and get the max path sum from that left subtree only.
    
    If root's right is present, we recur for it and get the max path sum from that left subtree only.
    
    We then calculate the max Current path sum.
    Max current path sum can be root's value too, excluding its children, so this is one of the possibility which is common in every edge case listed below.
    That is why we initialise maxCurrPathSum with root.val.
    
    Note: In Java, if we add Integer.MIN_VALUE with negative number, it results in positive number probably Integer.MAX_VALUE, and if we add Integer.MIN_VALUE to positive number, then it results the proper negative result which should be the case.
    
    So, we should not add the sumLeft or sumRight to anything, if they are Integer.MIN_VALUE.
    
    If sumLeft is Integer.MIN_VALUE, then I only consider cases for root's value and sumRight, from which we can get the maximum path sum.
    Similarly for case when sumRight is Integer.MIN_VALUE.
    
    What is the maximum current path sum?
    > It is the maximum of multiple things: a) Only root's val b) root's val with sumLeft c) root's val with sumRight (means only the right subtree with this root node is taken) d) root's val with both sumLeft and sumRight (meaning the whole subtree along with root is taken) e) Only sumLeft (meaning the max path from the left subtree is only taken) f) Only sumRight.
    
    This also excludes some factors based on whether sumLeft or sumRight or both are Integer.MIN_VALUE.
    
    Then, we maximize the global max, with this maxCurrPathSum, as this path can be the maximum.
    
    But, we would only return the maxpath which would not be closed in itself..
    Meaning, if a path which is maximum in itself contains the left subtree, the right subtree as well as its root, then it is eligible for max path, but not for the max path from this node, that can be returned.
    
    Ex- 
              8
            /   \
           7    -6
                /  \
               5    9
               
    Here, subtree with root as -6 can be one of the max path. But, while returning, we only return -6+9. And not -6+5+9, bcoz, if we take path from 7 to 8 to -6 to 9, we don't include 5 here.
    
    This means, the path from higher level cares about the max path sum to be returned from lower level.
    
    Extra Notes:
    -------------
    
    If we only have negative elements in the tree, then we will face some issues, as we take the max every time.
    It might return 0 for the tree where every element is negative. It is wrong answer, as we want the "maximum path sum of any non-empty path.", which means we should have atleast one element.
    
    So, if we have only the negatives, then we return the max negative element.
    If we have positive elements too or only positive, then we know that the maxRes value will obviously give us the correct output.
    
    -------------------------------------------------------------------------
    
    Time complexity: O(n), where n is the number of nodes
    Space complexity: O(1)
    */
    
    if(root == null){
      return Integer.MIN_VALUE;
    }
    
    int sumLeft = 0, sumRight = 0;
    
    if(root.left != null){
      sumLeft = maxPathRecur(root.left, maxRes, maxElem);
    }
    
    if(root.right != null){
      sumRight = maxPathRecur(root.right, maxRes, maxElem);
    }
    
    int maxCurrPathSum = root.val; // max current path sum can be root's value only too, excluding the children, so this is one of the possibility which is common in every edge case listed below 
    
    if(sumLeft == Integer.MIN_VALUE){
      maxCurrPathSum = findMaximum(new int[]{maxCurrPathSum, sumRight, root.val + sumRight});
    }
  
    if(sumRight == Integer.MIN_VALUE){
      maxCurrPathSum = findMaximum(new int[]{maxCurrPathSum, sumLeft, root.val + sumLeft});
    }
    
    if(sumLeft != Integer.MIN_VALUE && sumRight != Integer.MIN_VALUE){
      maxCurrPathSum = findMaximum(new int[]{maxCurrPathSum, sumLeft, sumRight, root.val + sumLeft, root.val + sumRight, root.val + sumLeft + sumRight});
    }

    maxRes.value = Math.max(maxRes.value, maxCurrPathSum);
    maxElem.value = Math.max(maxElem.value, root.val);
    
    return Math.max(root.val, root.val + Math.max(sumLeft, sumRight)); // max path sum from this node to be returned to its higher levels, can either be the node itself, or the node with the max children's path sum.
  }
  
  // Problem Question: https://leetcode.com/problems/binary-tree-maximum-path-sum/
  public int maxPathSum(TreeNode root) {
    IntMod maxRes = new IntMod();
    IntMod maxElem = new IntMod();
    
    maxElem.value = Integer.MIN_VALUE;
    maxRes.value = Integer.MIN_VALUE;
    
    maxPathRecur(root, maxRes, maxElem);
    
    if(maxElem.value < 0){
      // if max element is negative, then return that up, as maxRes value is incorrect for such cases
      return maxElem.value;
    }else{
      return maxRes.value;
    }
  }
}