class KthBitInNthBinaryStr {
  // Problem Question: https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/
  public char findKthBit(int n, int k) {
    /*
    To find kth bit, we don't need to make the whole string and then return kth bit, as it will be way inefficient, in both space and time complexities.
    
    My approach:
    ------------
    
    Let's say n=3, and we are given some k (1-based indexed k is given).
    
    For n=3, total string length would be 2^n - 1 = 2^3 - 1 = 7.
    If required position is the mid position in this string, then it will be obviously '1'.
    If required k is less than the mid position in this string, then it will be the same bit when checked in the previous string when n was 2, and k was the same.
    
    If required k is greater than the mid position in this string, then it will check the value at index, such that it checks the reversed index and then the value to be returned is the inverse of bit we got..
    
    --------------------------------------------------------------------------------
    
    Note: k is not greater than integer max range, that is why, we don't need to save/store the values in long data type. 
    */
    if(n == 1){
      return '0';
    }
    
    int totalStrLen = (int)Math.pow(2,n) - 1; // as also given in the question
    
    if((k - 1) == (totalStrLen)/2){
      // kth position is the mid position
      return '1';
    }else if((k - 1) < (totalStrLen)/2){
      // kth position is in the left part of string
      return findKthBit(n - 1, k);
    }else{
      // kth position is in the right part of string
      int rightStrLen = (totalStrLen - 1)/2;
      
      /*
      k is 1-based indexed. 
      
      For example,
      If n=3, then totalStrLen is 7, and rightStrLen is 3.
      If we need to find the k=7th index, then we know that we have to check in right side.
      
      (k - rightStrLen - 1) here means (7 - 3 - 1) = 3, meaning we first shift the right side indices to the index in left side.
      So, 5th index maps to 1st index, 6th to 2nd and 7th index maps to 3rd index.
      
      Then, we get the reverse of this string, meaning reversed_string_index will be (rightStrLen + 1 - the_shifted_index_got) = (3 + 1 - 3) = 1.
      
      So, 7th index value will be inversion of bit present at 1st index in the recursive call when n = 2.
      */
      return findKthBit(n - 1, rightStrLen + 1 - (k - rightStrLen - 1)) == '0' ? '1': '0';
    }
  }
}