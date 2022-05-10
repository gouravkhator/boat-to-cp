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
