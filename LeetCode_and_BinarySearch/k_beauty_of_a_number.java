class KBeautyOfNum {
  // Problem Question: https://leetcode.com/problems/find-the-k-beauty-of-a-number/
  public int divisorSubstrings(int num, int k) {
    /*
    Approach:
    ---------
    
    Take the digits one by one, and then when we have the length of substring as k, we check for the divisor thing.
    
    And we also decrement the k-length number, via both factor, the number of length k, and the length tracker.
    
    ------------------------------------------------------------------
    
    TC: O(number of digits in n)
    SC: O(1)
    */
    
    int originalNum = num, rem = 0, factor = 1, currSubstrLen = 0, subnum = 0, resCount = 0;
    
    while(num > 0){
      rem = num % 10; // take the digit one by one
      
      // k is atleast 1, so at the very least, we have to consider atleast 1 digit, s no check needed for the case when k = 0
      currSubstrLen++; // count this digit
      
      subnum += rem*factor; // store the digit in subnum
      factor *= 10; // multiply the factor for making the subnum later too
      num = num/10; // we processed 1 digit in the num
      
      if(currSubstrLen == k){
        // if the current length is k, meaning we should check if the subnum is a divisor of originalNum or not.
        if((subnum > 0) && ((originalNum % subnum) == 0)){
          resCount++; // if it is a divisor, just count this
        }
        
        subnum /= 10; // now, remove the last digit from subnum
        factor /= 10; // as we have 1 digit less in subnum, we should also make the factor 10 times lower
        currSubstrLen--; // also decrement the length of subnum..
      }
    }
    
    return resCount;
  }
}