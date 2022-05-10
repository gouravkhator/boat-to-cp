class PartitionToKEqualSumSubset {

  public boolean canPartitionRecur(int[] nums, int k, boolean[] used, int iterationStart, int currSum, int subsetSum) {
    if (k == 1) {
      // only one subset is left to be filled, and that will be obvious that we can fill that last subset
      return true;
    }

    if (currSum == subsetSum) {
      /**
       * if current sum for this kth subset is same as the subsetSum needed,
       * then we know that the kth subset is filled fully, now, we recur for filling (k-1)th subset.
       * 
       * So, left over subsets is actually (k-1).. 
      */
      return canPartitionRecur(nums, k - 1, used, 0, 0, subsetSum);
    }

    // below logic is just the modified version of combination sum 

    // we loop from the iteration start to the rest of the numbers
    for (int i = iterationStart; i < nums.length; i++) {
      // we use this number if it is not used yet, and if including this would not exceed the subsetSum
      if (used[i] == false && (nums[i] + currSum) <= subsetSum) {

        used[i] = true; // choose this number

        // we use this number, and then recur for the rest of the numbers
        if (canPartitionRecur(nums, k, used, i + 1, currSum + nums[i], subsetSum)) {
          return true;
        }

        used[i] = false; // we unchoose this number
      }
    }

    return false;
  }

  // Problem Question: https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
  public boolean canPartitionKSubsets(int[] nums, int k) {
    /**
     * Analogy:
     * 
     * We can try one of the below approaches:
     * 1. Either fill each subset at a time, and 
     * for each subset, recur for every possible combination of the unused elements of the array.
     * 2. Or, fill each number in the subsets, and check if other numbers can be filled successfully those k subsets.
     * 
     * This problem is mainly based on Full Bin packing problem.
     * And the approach and code was taken from leetcode discuss, after many attempts of mine. 
     * 
     * Approach:
     * 
     * Here, I took the approach number 1, where I tried for kth subset to be filled with
     * every possible combination of unused numbers.
     * 
     * And once the kth subset is filled, we then try for possible combinations of unused numbers to fill (k-1)th subset.
     * 
     * And if remaining k is 1, then we see that (k-1) subsets have been filled, so just return true for this last subset.
     * It is bcoz, if all subsets are filled, then the last one can be filled with remaining numbers.
     * 
     * (It is also bcoz, the total sum is divisible by k,
     * so we will be remaining with exact sbset sum and the exact unused numbers) 
     * 
     * Time complexity: O(k* 2^n)
     * (As for k subsets, we do combination sum kind of logic trying to include or exclude an unused number)
     * (So, 2^n as the worst case for combination sum logic, and this is done for each k)
     * 
     * Space complexity: O(n) extra space for boolean 'used' array
     */
    int totalSum = 0, len = nums.length;

    for (int i = 0; i < len; i++) {
      totalSum += nums[i];
    }

    // 1 <= k <= 16, so no need to check for k=0
    if (totalSum % k == 1) {
      // if total sum is not divisible by k, then we cannot uniformly put the numbers in each subset
      return false;
    }

    boolean[] used = new boolean[len];
    int subsetSum = totalSum / k;

    return canPartitionRecur(nums, k, used, 0, 0, subsetSum);
  }
}
