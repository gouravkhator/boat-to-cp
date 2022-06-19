class NumWaysToSplitArr {
  // Problem Question: https://leetcode.com/problems/number-of-ways-to-split-array/
  public int waysToSplitArray(int[] nums) {
    /*
    As checked from the method of waysToSplitArrayWithoutLong, we use prefix sum for storing the sum till ith index.
    
    And then, at each index i, we calculate, if (the sum till ith index) is greater than or equal to {total sum - (sum till ith index)}.
    
    We need to use long to store the sums. Why so??
    > We got one test case failed for the nums = [0,-1,-2,.....-99999], when using the integer array or the sum as int.
    (It is bcoz, nums array is int, and using it for prefix sums will lead to wrong answer, when the sum might be beyond the integer range).
    
    So, we don't process via the integer array of sums. Rather, we use a long sum. 
    */
   int len = nums.length, count = 0;
   long totalSum = 0, currentSum = 0;
   
   for(int i=0; i<len; i++){
     totalSum += nums[i];
   }
    
   for(int i=0; i<=len - 2; i++){
     currentSum += nums[i];
     
     if(currentSum >= (totalSum - currentSum)){
       count++;
     }
   }
   
   return count;
 }
 
 // this method used the logic without long, and it gave wrong answer, as the sum was probably out of integer range.
 public int waysToSplitArrayWithoutLong(int[] nums) {
   /*
   This approach faced 99/100 passed, with just 1 test case failing, when
   nums = [0,-1,-2,-3,...,-99999], and output of this method was 52605, and expected output is 70710
   */
   int len = nums.length, count = 0;
   
   for(int i=1; i<len; i++){
     nums[i] += nums[i-1];
   }
   
   for(int i=0; i<=len - 2; i++){
     if(nums[i] >= (nums[len - 1] - nums[i])){
       count++;
     }
   }
   
   return count;
 }
}