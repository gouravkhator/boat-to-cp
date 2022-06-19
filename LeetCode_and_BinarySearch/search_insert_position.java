class SearchInsertPosition {
  // Problem Question: https://leetcode.com/problems/search-insert-position/
  public int searchInsert(int[] nums, int target) {
    /*
    Approach:
    ----------------
    
    If we get the target element, then we return that index, else we track the next greater element.
    If we don't get any next greater element, and it is the greatest element in this array, then we return the length of the array as the valid index, as it is the index we should have inserted.
    
    For tracking next greater element, we update the next greater element only when the mid element is greater than target.
    
    ----------------------------------------------------------------------------
    
    Time complexity: O(log n)
    Space Complexity: O(1)
    */
    int len = nums.length;
    int left = 0, right = len - 1, nextGreaterIndex = -1;
    
    while(left <= right){
      int mid = left + (right - left)/2;
      
      if(nums[mid] == target){
        return mid;
      }else if(nums[mid] > target){
        nextGreaterIndex = mid; // update the next greater element as the mid element
        right = mid - 1;
      }else{
        left = mid + 1;
      }
    }
    
    return (nextGreaterIndex == -1) ? len : nextGreaterIndex;
  }
}