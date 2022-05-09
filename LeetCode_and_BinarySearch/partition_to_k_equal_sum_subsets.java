import java.util.*;

/**
 * !ISSUE: TLE in the input test case:
 * 
 * [10,1,10,9,6,1,9,5,9,10,7,8,5,2,10,8]
 * 11
 * 
 * Passed 155/159 test cases on Leetcode.. 
*/
class PartitionToKEqualSum {
  public boolean partitionNumsRecur(int[] nums, int index, int[] subsetSums, int subsetSumRequired) {
    int k = subsetSums.length;

    if (index < 0) {
      /*
      if all the elements are processed,
      then we check if all the subset sums being tracked, is equal to the sum we want in each subsets.
      */
      for (int i = 0; i < k; i++) {
        if (subsetSums[i] != subsetSumRequired) {
          return false;
        }
      }

      return true;
    }

    // try putting nums[index] in any one of the k subsets
    for (int i = 0; i < k; i++) {
      /**
       * if the nums[index] is valid to be put in subset i,
       * then we just include it to the subsetSums[i], and recur for index-1 indexed element
       * 
       * Meaning the approach to solve this problem is the n-queens problem, where we place a queen in one of k spots, and then recur for next queens.
       * 
       * If (index-1)th index is well placed in a subset, then we return true from here as well.
       * Else, we remove (index)th element from subset[i], and just try for other subsets.
       */
      if (subsetSums[i] + nums[index] <= subsetSumRequired) {
        subsetSums[i] += nums[index];

        if (partitionNumsRecur(nums, index - 1, subsetSums, subsetSumRequired) == true) {
          return true;
        }

        subsetSums[i] -= nums[index];
      }
    }

    return false; // if this could not be placed in any subset, we return false
  }

  // Problem Question: https://leetcode.com/problems/partition-to-k-equal-sum-subsets
  public boolean canPartitionKSubsets(int[] nums, int k) {
    /**
     * Analogy:
     * 
     * This is mainly a full bin packing problem.
     * There are k bins given and we need to put numbers in them, such that it does not exceed the bin's capacity.
     * 
     * Time complexity: O(n log n + k * 2^n) = O(k * 2^n)
     * 
     * Explanation of time complexity:
     * 
     * n log n is just needed to sort numbers and thus optimise the state space tree,
     * so that it does not try useless paths..
     * 
     * k * 2^n is mainly by below:
     * 
     * 2^n for either including a number or excluding it.
     * And for each number, we loop through all subsets.
     */
    int len = nums.length, totalSum = 0;

    for (int i = 0; i < len; i++) {
      totalSum += nums[i];
    }

    if (totalSum % k == 1) {
      // if total sum is not divisible by k, then we cannot divide the numbers, uniformly into k subsets
      return false;
    }

    Arrays.sort(nums); // ascending order sorting

    /**
     * mainly we traverse the ascending sorted array in reverse order, to first place the greater numbers first..
     * 
     * If we don't sort it is not a problem, but is worse for the time complexity, as it will try to make a larger state space tree.
     * So, sorting and then traversing in descending order helps.
     */
    return partitionNumsRecur(nums, len - 1, new int[k], totalSum / k);
  }
}