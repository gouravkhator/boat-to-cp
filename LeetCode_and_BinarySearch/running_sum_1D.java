class RunningSum1DArr {
  // Problem Question: https://leetcode.com/problems/running-sum-of-1d-array/
  public int[] runningSum(int[] nums) {
    // prefix sum logic for the running sum question
    for(int i=1; i<nums.length; i++){
      nums[i] = nums[i] + nums[i-1];
    }
    
    return nums;
  }
}