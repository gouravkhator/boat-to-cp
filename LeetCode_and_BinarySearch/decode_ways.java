class DecodeWays {
  // Problem Question: https://leetcode.com/problems/decode-ways/
  public int numDecodings(String s) {
    /*
Test cases:
"12"
"111111111111111111111111111111111111"
"06"
"11111111111111111111111111111111111111066123123417621"
    */
    
    /*
    Analogy:
    -----------
    
    I thought to do a recursion, where the first character will be checked if valid, and if yes, then we would recur for the decodings for remaining string. ANd we then check for the second character if applicable, if it is valid or not.
    If valid, then we recur for remaining string.
    
    If we get the empty string, we know that we got it from some strings which is processed fully. So, we return 1 (denoting 1 way is processed now).
    
    Here, recursion is exponential, and the constraint was 1 length to 100 length long string.
    
    Main analogy was from below example:
    Ex- "123451234"
    
    We take valid groups first. Here, valid groups are as follows:
    
    1 or 12 ; 2 or 23 ; 3 ; 4 ; 5 ; 1 or 12 ; 2 or 23 ; 3 ; 4
    
    Here, in above groups, we see that if we take 1, then we can take the number of decodings possible from rest of the subsequent combination of groups.
    
    So, we can take either 2 or 23.
    
    But if we take 12 now, then we cannot take the ways formed by 2 or 23, and we have to take from the ways formed from 3 only.
    
    Similarly, if we take 2, then we can take ways possible from 3. If we take 23, then we take ways possible from 4.
    
    So, as each one is dependent on the next 2, so it is a pure DP. Here, we start from the base subproblem that is the end of the string.
    
    We take 4, and only 1 way is possible. We then take 3 and here also after 3, we would take 4, so one way possible too.
    
    We then take 2, and now, we can take ways possible from 3 that is 1 way only. We can also take 23 now, leaving us to take ways possible from 4 that is 1 too.
    
    So, decodings possible from 234 is 1+1 = 2, i.e., 234 and 23;4 are the valid decodings.
    
    Then we take 1, and here ways possible are formed from ways possible from 2 that is 2.
    We can take 12 as a whole, leaving us to take ways possible from 3, which is 1.
    Total decodings possible is 2+1 = 3
    
    We continue this process till we come to the 0th index, which will give us the exact number of decodings.
    
    -------------------------------------------------------------------------
    
    Time Complexity: O(n)
    Space Complexity: O(1)
    
    As we just lookup next two numbers only, we don't need any array, rather we need just two pointers pointing to the next two locations, and we need to update the pointers accordingly.
    
    The pointers go from end index to the 0th index.
    
    a denotes the next to next waysCount for the current index, and b denotes the next waysCount for the current index.
    
    This logic is similar to modified fibonacci, but in reverse order.
    */
    
    int len = s.length();    
    int a = 0, b = 0; // a is initially beyond the imaginary array, and b is at the last index
    
    if(s.charAt(len - 1) == '0'){
      b = 0;
    }else{
      b = 1;
    }
    
    for(int i=len - 2; i>=0; i--){
      int c1 = s.charAt(i) - 48;
      int numOfWays = 0;
      
      if(c1 != 0){
        // valid character which is just 1 char long
        numOfWays += b;
      }
      
      int c2 = s.charAt(i+1) - 48;
      
      int twoCharNumber = c1 * 10 + c2;
      
      if(twoCharNumber >= 10 && twoCharNumber <= 26){
        if((i+2) < len){
          numOfWays += a;
        }else{
          numOfWays += 1;
        }
      }
      
      // update the pointers, to point 1 index backwards, as we go backwards in this loop
      a = b;
      b = numOfWays;
    }
    
    return b;
  }
}

// naive approach with recursion -- non-optimal approach
class DecodeWaysNaiveWithRecursion {
  public int numDecodings(String s) {
    /*
     * Approach:
     * -----------
     * 
     * Take first char and recur or take first two chars and then recur.
     * But, we check for valid chars only.
     */
    int len = s.length();
    
    if(len == 0){
      // length is only 0, when we processed a string..
      // it is bcoz, the input string constraint was of length 1 through 100 
      return 1;
    }
    
    int decodingsCount = 0;
    
    int firstChar = s.charAt(0) - 48;
    
    if(firstChar >= 1 && firstChar <= 9){
      // first char is valid
      decodingsCount += numDecodings(s.substring(1));
    }
    
    if(len <= 1){
      return decodingsCount;
    }
    
    int secondChar = s.charAt(1) - 48;
    
    int twoChars = firstChar * 10 + secondChar;
    
    if(twoChars >= 10 && twoChars <= 26){
      // first two chars is valid
      decodingsCount += numDecodings(s.substring(2));
    }

    return decodingsCount;
  }
}