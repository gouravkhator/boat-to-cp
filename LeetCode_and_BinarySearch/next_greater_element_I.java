import java.util.*;

class NextGreaterElementI {
  public HashMap<Integer, Integer> getNextGreaterElems(int[] nums){
      /**
       * Approach for getting next greater elements:
       * 
       * Note that each of nums1 and nums2 are having unique elements, and nums1 is a subset of nums2.
       * 
       * For getting the next greater like for instance for the below array:
       * [4,8,5,2,6,0,25]
       * 
       * For element 4, next greater is 8. For element 8, next greater is 25.
       * For elements 5 and 2, next greater is 6.
       * For elements 6 and 0, next greater is 25.
       * For element 25, next greater is -1 (as no next greater present).
       * 
       * So, if we see that some elements only come for sometime, and then they just are not required.
       * 
       * Meaning, if we use stack's pop and push feature, that can help.
       * 
       * Approach for above example:
       * 1. Push index of 4 to the stack.
       * 2. Now, our current element is at index 1, that is 8. Pop all smaller elements than 8 from the stack.
       * 3. And while popping those elements, just mark their next greater as 8.
       * (This marking of next greater element for a particular element can be done using HashMap,
       * as nums1 and nums2 have different order of elements, so if we use some other data structure,
       * then, we will have a much larger time complexity)
       * 
       * 4. Now, Push 8's index to the stack.
       * 5. Then, check the current element which is at 5, with the remains of the stack.
       * 6. Lastly, when we reach the last of the array, and we have no next greater possibility,
       * then just pop all remaining elements from the stack, and mark their next greater as -1.
       * 
       * Approach generalised:
       * 1. Push an element's index to the stack.
       * 2. Check if the current element is larger than the element with index as top of stack.
       * 3. If yes, then pop the element's index from the stack, and mark that element's next greater element as the current element.
       * 4. Repeat steps 2 and 3 until current element is larger than than the element with index as top of stack.
       * 5. We then push the current element's index to the stack and increment the index to point to the next element.
       * 6. We repeat steps 2 to 5, while stack is not empty and the current element's index is valid in the nums array.
       * 7. Lastly, when we reach the last of the array, and we have no next greater possibility,
       * then just pop all remaining elements from the stack, and mark their next greater as -1.
       */
      int len = nums.length;
      HashMap<Integer, Integer> nextGreaters = new HashMap<>(); // hashmap from the actual number to its next greater
      
      if(len == 0){
          return nextGreaters;
      }
      
      Stack<Integer> stack = new Stack<>(); // stack contains the indexes
      
      stack.push(0); // pushing 0 index to the stack
      
      int i = 1; // current element's index
      
      while(!stack.isEmpty() && i < len){
          while(!stack.isEmpty() && nums[stack.peek()] < nums[i]){
              // while top of stack indexed element is smaller than current element and stack is not empty..
              // we map the next greater element for element with popped index, as the current element.
              nextGreaters.put(nums[stack.pop()], nums[i]);
          }
          
          stack.push(i);
          i++;
      }
      
      while(!stack.isEmpty()){
          nextGreaters.put(nums[stack.pop()], -1);
      }
      
      return nextGreaters;
  }
  
  // Problem Question: https://leetcode.com/problems/next-greater-element-i/
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
      // find the next greater elements for each element in nums2
      HashMap<Integer, Integer> nextGreaterMapping = getNextGreaterElems(nums2);
      
      int[] resultantArr = new int[nums1.length];
      
      for(int i=0; i<nums1.length; i++){
          // as nums1 is a subset of nums2, so we always have that element's mapping in the nextGreaterMapping
          resultantArr[i] = nextGreaterMapping.get(nums1[i]);
      }
      
      return resultantArr;
  }
}