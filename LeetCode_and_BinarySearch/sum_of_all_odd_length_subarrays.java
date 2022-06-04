class SumOddLengthSubarrays {
  // Problem Question: https://leetcode.com/problems/sum-of-all-odd-length-subarrays/
  public int sumOddLengthSubarrays(int[] arr) {
    /*
    Naive approach is coded in method sumOddLengthSubarraysNaive.
    
    In the naive approach, we have to calculate the sum multiple times. 
    This can be reduced by using prefix sum, which can give us the sum from a startindex to the end index in O(1) time, with a prerequisite of O(n) for creating the prefix sum array.
    
    For array: [1,2,3,4,5]
    
    If we start with 0th index, we can have a 1 length, 3 length and 5 length subarray.
    And for each of the odd length subarrays, we can directly get the end index, and thus the sum from ith index to the calculated endindex, using prefix sum.
    
    Q) How to calculate the number of subarrays that can we made, which starts with ith index?
    > For subarrays starting with ith index, we can calculate the number of elements from this ith index to the last element of the array.
    Then, we can loop through every odd lengths, starting with this ith index.
    
    Q) Why using same array for prefix sum?
    > As the elements of arr array is not really used after we calculate the prefix sum. So, we can overwrite those elements with the prefix sums.
    ------------------------------------------------------------------------------
    
    For Naive approach, TC: approx O(n^3), and SC: O(1)
    For this optimal approach, TC: O(n^2), and SC: O(1), as we use the same array for prefix sum, and not another array.
    */
    
    int len = arr.length, resSum = 0;
    
    // prefix sum calculation
    for(int i=1; i<len; i++){
      arr[i] = arr[i] + arr[i-1]; // we can use this current array even, and not need the extra array for prefix sum.
    }
    
    for(int startIndex=0; startIndex<len; startIndex++){
      int elemCountFromCurr = len - startIndex; // number of elements from (startIndex)th index to the last element of array arr
      
      // take the odd length subarrays, to define the endindex
      for(int l=1; l<=elemCountFromCurr; l+=2){
        // looping through odd length subarrays, with max odd length possible, to be less than or equal to elemCountFromCurr
        int endIndex = startIndex + l - 1;
        
        if(startIndex == 0){
          // if startindex is 0, then we know that the sum will be arr[endIndex] itself
          resSum += arr[endIndex];
        }else{
          resSum += arr[endIndex] - arr[startIndex - 1]; // we subtract two prefix sums, to get a sum between two points of the subarray.
        }
      }
    }
    
    return resSum;
  }
  
  public int sumOddLengthSubarraysNaive(int[] arr) {
    /*
    This is the naive approach.
    
    Approach:
    ------------
    
    Take the current length to be the largest odd length.
    For this current length, we start from 0th index, and take the sum till start index + current length.
    Then, increment the start index, so that we take the same length subarray starting from a new startIndex.
    We go on till we are done with this length subarray for all indexes.
    Then, we decrement the current length by 2, to take a smaller odd length subarray.
    We repeat the above steps for this current length too.
    */
    
    int len = arr.length, currLen = arr.length, resSum = 0;
    
    if(currLen % 2 == 0){
      currLen--;
    }
    
    while(currLen >= 1){
      int sum = 0, startIndex = 0;
      
      while((startIndex + currLen) <= len){
        for(int i=startIndex; i < (startIndex + currLen); i++){
          sum += arr[i];
        }
        
        resSum += sum;
        sum = 0;
        startIndex++;
      }
      
      currLen -= 2;
    }
    
    return resSum;
  }
}