class FourSum {
  // Problem Question: https://leetcode.com/problems/4sum/
  public List<List<Integer>> fourSum(int[] nums, int target) {
      /*
      Approach:
      
      As I saw in some examples patterns, that if the two numbers are fixed in a nested loop,
      then the sum required (target - (nums[i]+nums[j])) is always found after jth index..
      (as we already sort the array in the first stage).
      
      So, just use nested to fix first two numbers, then after jth index, find the sumRequired using two pointers method.
      
      Time complexity: O(n log n + n^3) = O(n^3)
      
      We may improve on the first nested loop, maybe through some patterns to take them using two pointers method too.
      But, when tried for different patterns, I couldn't derive any, and neither on leetcode discuss section.
      
      I tried this solution on my own, taking inspirations from 3-sum problem, just expanded it.
      */
      
      /*
      Test Cases: (nums array, followed by target)
      [1,0,-1,0,-2,2]
      0
      
      [-2,-1,-1,0,1,1,2,2,3,3,3,5]
      2
      
      [-3]
      8
      
      [0]
      0
      
      [-2,-1,-1,0,1,1,2,2,-4,5,-6,-1]
      3
      
      [-2,-1,-1,0,1,1,2,2,-4,5,-6,-1]
      -3
      */
      
      // set is used, so that we result out non-duplicates or unique quads.
      Set<List<Integer>> quads = new HashSet<>();
      
      int len = nums.length;
      Arrays.sort(nums); // sort the array in the first place
      
      for(int i=0; i<len; i++){
          for(int j=i+1; j<len; j++){
              int sumRequired = target - (nums[i] + nums[j]);
              int left = j+1, right = len - 1;
              
              while(left < right){
                  if((nums[left]+nums[right]) == sumRequired){
                  List<Integer> currentQuad = new ArrayList<>();
                  
                  /*
                  As the array is sorted.
                  nums[i] is the smallest of all, 
                  nums[j] >= nums[i], 
                  nums[left] >= nums[j], 
                  and nums[right] >= nums[left]..

                  So we push to the list in that fashion, just to keep the set of lists all unique..
                  */
                  currentQuad.add(nums[i]);
                  currentQuad.add(nums[j]);
                  currentQuad.add(nums[left]);
                  currentQuad.add(nums[right]);
                  
                  quads.add(currentQuad);
                      
                  // if the current quad is taken, we just increment the left and decrement the right
                  // it may happen that some other pair of numbers in this two pointer approach, sum up to sumRequired
                  left++;
                  right--;
                  }else if((nums[left]+nums[right]) < sumRequired){
                      // sum of two numbers is smaller than the sum required
                      left++;
                  }else {
                      right--;
                  }
              }
          }
      }
      
      return new ArrayList<>(quads);
  }
}