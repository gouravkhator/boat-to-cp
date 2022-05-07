class MissingNumber {
  // Problem Question: https://leetcode.com/problems/missing-number/
  public int missingNumber(int[] nums) {
      /*
      Approach:

      Given an unsorted array with elements ranging from [0, n] where n is the length of the array.
      We have to find the missing number in the array, and there will be exactly one missing number for sure,
      as the array has elements from range [0, n].

      We have to do that in O(n) time complexity and O(1) extra space.

      What we can do is modify the same array elements for checking if some element is present or not,
      rather than taking extra array like the hash-type array.

      When we encounter the current element say arr[i], we could negate the number at arr[i] position,
      meaning arr[arr[i]] = -arr[arr[i]] but this fails because 0 is also there in the elements list.
      And negating 0 does not produce any effect.

      We can subtract each number by (n+1) meaning we can follow below algorithm:
      1. If the current number is at ith index, and is arr[i], we might have this number as negative.
      2. Check if this number is negative, if yes, we modified this index before,
      so, just add (n+1) to this variable and get the original number back.

      Note: don't add (n+1) to the array index directly, as we want the array to be storing the existence of each element.

      3. If this number is non-negative, we already have the originalElem as nums[i].
      4. Now, just modify the nums[originalElem] by subtracting by (n+1), which signifies that originalElem exists in the array.

      Then loop through this modified array and return the index of non-negative element,
      and if we don't get any non-negative element, then return len..

      Ex- [4,0,1,3]
      len = 4

      For elem 4, it is positive, so not modified yet, and as it is not less than len, so we cannot modify 4th index in array.
      For elem 0, it is positive, and also 0 < len, we modify 0th index by subtracting nums[0] by (len+1).
      For elem 1, it is positive, and also 1 < len, we modify 1st index by subtracting nums[1] by (len+1).
      For elem 3, it is positive, and also 3 < len, we modify 3rd index by subtracting nums[3] by (len+1).

      The final modified array becomes [-1, -5, 1, -2]
      Here, we notice that index 2 will not be modified, meaning element 2 was not present in the array.
      
      Just loop again through array, and check if any element is non-negative, then just return its index.
      If we didn't get any elements as non-negative, then return the len as the missing number.

      For example: for nums = [1,0], then modified array became [-1, -2],
      so no non-negative element present in the array, so missing number is 2 (the length of the array). 
      */
      int len = nums.length, i = 0;
      
      for(i=0; i<len; i++){
          int originalElem = nums[i];
          
          if(nums[i] < 0){
              // we modified this index before
              originalElem = nums[i] + (len + 1);
          }
          
          if(originalElem < len){
              nums[originalElem] -= (len + 1);
          }
      }
      
      for(i=0; i<len; i++){
          if(nums[i] >= 0){
              return i;
          }
      }
      
      return len;
  }
}