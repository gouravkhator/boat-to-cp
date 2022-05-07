import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class ThreeSum {
  // Problem Question: https://leetcode.com/problems/3sum/
  public List<List<Integer>> threeSum(int[] nums) {
      /*
      Inspired from Leetcode discuss section's solution..
      
      Approach:
      
      If the sum is assumed to be some number, we need to find the two numbers which sum to that number.
      
      So, for example: [-4,-1,-1,0,1,2]
      Here, if we take third number to be -4, then the two numbers should be checked from after this number, as their sum should be 4, and 4 will come after this number itself.
      
      For the finding of two numbers, we can use two pointer approach, and this whole thing will only work if array is sorted before any operations.
      
      Time complexity: O(nlogn + n^2) = O(n^2)
      */
      
      int i = 0, len = nums.length;

      // set is used, so that we result out non-duplicates or unique triplets.
      Set<List<Integer>> triplets = new HashSet<>();
      
      Arrays.sort(nums); // sort the array in the first place
      
      for(i=0; i<=len-2; i++){
          int sumRequired = 0 - nums[i]; // sum required from the remaining two numbers
          
          /**
           * Here, we are Setting the range for those two numbers.
           * Those two numbers will obviously sum to this negative of this third number,
           * when those two numbers are present in the later part of this third number in the sorted order of array.
           */
          int left = i+1, right = len - 1;
          
          while(left < right){
              if((nums[left]+nums[right]) == sumRequired){
                  List<Integer> currentTriplet = new ArrayList<>();
                  
                  /*
                  As the array is already sorted,
                  nums[i] is the smallest of all,
                  nums[left] >= nums[i],
                  and nums[right] >= nums[left]..

                  So we push to the list in that fashion, just to keep the set of lists all unique..
                  */
                  currentTriplet.add(nums[i]);
                  currentTriplet.add(nums[left]);
                  currentTriplet.add(nums[right]);
                  
                  triplets.add(currentTriplet);
                  left++;
                  right--;
              }else if((nums[left]+nums[right]) < sumRequired){
                  // sum of two numbers is smaller than the sum required, then we increment the left pointer
                  left++;
              }else {
                  // sum of two numbers is greater than the sum required, then we decrement the right pointer
                  right--;
              }
          }
      }
      
      return new ArrayList<>(triplets);
  }
}