class NumberOfStepsToReduceToZero {
  // Problem Question: https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
  public int numberOfSteps(int num) {
    /*
    My coded approach runs mostly for all operations. So, my approach's Time complexity is O(T),
    where T is the number of operations performed. 
    
    A better approach in O(64) or O(32) which is approx O(1): 
    https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/discuss/2077939/bit-manipulation-intution-understanding-constant-time-algorithm
    
    But, that approach is really tough to understand.
    */
    int count = 0;
    
    while(num > 0){
      if((1 & num) == 0){
        // lsb bit is not set
        // even number
        num = num >> 1; // shift by 1 bit, or divide by 2 in other words
      }else{
        // odd number
        num = num - 1;
      }
      
      count++;
    }
    
    return count;
  }
}