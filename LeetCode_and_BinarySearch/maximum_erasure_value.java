import java.util.*;

class MaximumErasureValue {
  // Problem Question: https://leetcode.com/problems/maximum-erasure-value/
  public int maximumUniqueSubarray(int[] nums) {
    /*
    Analogy:
    -----------
    
    We need to find the subarray which contains unique elements, 
    that sums to the maximum sum, and we are given the positive integers only, in the constraints.
    
    At current index, we track the left (or start) of the range.
    We also track if current number exists in this range or not, by saving the last seen index of this number and checking if this index falls in this range.
    
    To track the sum of the range of elements, we use prefix sum.
    But, we store the prefix sum in the nums array itself, without using extra space.
    
    To get the current number for checking above, we cannot do like nums[i],
    as the nums[i] contains the sum till ith index, so we check current number by using prefix sum itself.
    
    Current number is prefix sum at ith index - prefix sum at (i - 1)th index
    
    Approach:
    ------------
  
    latestIndices map contains the latest index at which current number might be present, before this current index.
    
    1. Make the nums array, the prefix sum array, and precalculate the prefix sums.
    2. Mark the leftRange (start of the range) as 0, and also mark this 0th index in the latestIndices map.
    3. Loop through every element of nums. And the current number will be nums[i] - nums[i - 1], as nums already became the prefix sum.
    4. If latest indices map does not contain this current number, then we just update the index in the map.
    5. If it does contain the current number, we check at which index the current number is last seen before this index.
    6. Then we check if this lastSeenIndex falls in the range from leftRange to rightRange, where rightRange is actually currentIndex i - 1.
    7. If the last seen index falls in the range, meaning we have to reset the range and sums and compare currSum with maxSum.
    8. currSum can be calculated using prefix sum logic.
    9. After this, we update the latestIndices map with current index as usual.
    10. After the loop is over, we again check the current sum taking into account the right of the range to be the last element of the array, and then again comparing it with maxSum.
    
    -------------------------------------------------------------------------
    
    Time complexity: O(n), as hashmap lookup and store used inside the loop is O(1)
    Space Complexity: O(n), as we use hashmap and we store every integer's latest index in it
    */
    
    int len = nums.length;
    HashMap<Integer, Integer> latestIndices = new HashMap<>();
    int leftRange = -1, currSum = 0, maxSum = 0;
    
    for(int i=1; i<len; i++){
      nums[i] += nums[i-1];
    }
    
    // take the first element as the maxSum, and also mark it to be the left of the range
    // and mark its index in the latestIndices hashmap.
    // the logic for 0th index is done separately, bcoz in the loop, we do nums[i - 1] too, and we don't need to do so many logical steps (as given in the loop) for 0th index
    maxSum = nums[0];
    latestIndices.put(nums[0], 0);
    leftRange = 0;

    for(int i=1; i<len; i++){
      int currNum = nums[i] - nums[i - 1];

      if(!latestIndices.containsKey(currNum)){
        // this number is unique actually
        latestIndices.put(currNum, i);
      }else{
        int rightRange = i - 1;
        int lastSeenIndex = latestIndices.get(currNum); // last seen index
        
        if(leftRange <= rightRange && lastSeenIndex >= leftRange && lastSeenIndex <= rightRange){
          // not unique, and we should reset the left of the range
          if(leftRange == 0){
            currSum = nums[rightRange];
          }else{
            currSum = nums[rightRange] - nums[leftRange - 1];
          }
          
          if(currSum > maxSum){
            maxSum = currSum;
          }
          
          currSum = 0; // set it to 0, as we calculate it using prefix sums in O(1) time and only 1 line as above
          leftRange = lastSeenIndex + 1; // set the left of the range to the (conflicting last seen index) + 1, meaning after that index
        }
        
        latestIndices.put(currNum, i);
      }
    }
    
    // at last we check the same and update the maxSum
    if(leftRange == 0){
      currSum = nums[len - 1];
    }else{
      currSum = nums[len - 1] - nums[leftRange - 1];
    }
    
    if(currSum > maxSum){
      maxSum = currSum;
    }
    
    return maxSum;
  }
}