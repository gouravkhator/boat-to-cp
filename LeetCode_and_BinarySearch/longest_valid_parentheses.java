class LongestValidParentheses {
  // Problem Question: https://leetcode.com/problems/longest-valid-parentheses/
  public int longestValidParentheses(String s) {
    /*
     * Analogy:
     * -----------------------------
     * 
     * 1. There is one recursive approach which is exponential in time complexity and does not fit for the given constraints.
     * 
     * 2. We just iterate through every substring and check if that is valid or not.
     * 3. Now, to check for every substring and check if that is valid, 
     * we could pass a substring into isValid function, which pushes and pops from stack and checks validity.
     * 
     * But that would be around O(n^3), as the isValid method also takes O(n).
     * 
     * 4. And every time a new character was added to a substring, we had to check for all the characters of that substring again.
     * So, to reduce that, we could use the same stack and track at every push or pop stages if valid or not.
     * 
     * 5. But, as we know there are only open braces pushed to the stack.
     * And when we pop from the stack, we just reduce one open brace. 
     * So, why to maintain a stack, when we can maintain the counter of openBraces.
     * 
     * This will reduce the time and space complexity much much more.
     * 
     * -----------------------------------------------------------
     * 
     * Some more optimizations done were:
     * 1. A probably valid substring would not start with ')',
     * so, we just checked the ith character and ignore the whole j loop, if c is ')'.
     * 
     * -----------------------------------------------------------
     * 
     * Approach step by step:
     * 1. Loop through all substrings.
     * 2. And if that ith character is ')', it will not start with ')', so don't loop even.
     * 3. If jth character is '(', we increment open brace count (push to imaginary stack)
     * 4. If jth character is ')', we decrement from the stack if stack if not empty (stack is represented by open braces count)
     * 5. After removing the open brace, if the open brace count is now 0, meaning stack is empty,
     * meaning it is valid, so consider the length.
     * 
     * This step 5 check is only needed, if we remove the open brace, as that might make the substring valid.
     * But if we increment the open brace count, it is now not valid.
     * 
     * -----------------------------------------------------------
     * 
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    int len = s.length(), currValidStrLen = 0, maxLen = 0, openBracesCount = 0;
    
    for(int i=0; i<len; i++){
      char c = s.charAt(i);
      
      if(c == ')'){
        // we don't start with ')', as this closing brace will not make a valid parentheses substring
        continue;
      }
      
      openBracesCount = 1; // bcoz ith character is an open brace now
      currValidStrLen = 0;
      
      for(int j=i+1; j<len; j++){
        char c2 = s.charAt(j);
        
        if(c2 == '('){
          openBracesCount++; // meaning to push to the imaginary stack
        }else if(c2 == ')'){
          if(openBracesCount == 0){
            // if that imaginary stack is empty and c2 is ')', this is not valid and we break
            break;
          }else{
            // if c2 is ')' and imaginary stack is not empty, then we pop from stack, meaning reduce the openBraces count
            openBracesCount--;
          }
          
          // if now the imaginary stack becomes empty, we calculate the length for this valid substring
          if(openBracesCount == 0){
            currValidStrLen = j - i + 1; // this jth character when added, makes the substring valid
          }
        }
      }
      
      if(maxLen < currValidStrLen){
        maxLen = currValidStrLen;
      }
    }
    
    return maxLen;
  }
}

// Recursion approach with exponential time complexity with optimization by using hashmap to lookup
class LongestValidParenthesesRecursion{
  public boolean isValid(String s){
    Stack<Character> stack = new Stack<>();
    
    for(int i=0; i<s.length(); i++){
      char c = s.charAt(i);
      
      switch(c){
        case '(':
          stack.push(c);
          break;
        case ')':
          if(!stack.isEmpty()){
            stack.pop();
          } else{
            return false;
          }
      }      
    }
    
    return stack.isEmpty();
  }
  
  public int longestValidParenthesesRecur(String s, HashMap<String, Boolean> map) {
    int len = s.length();
    
    if(map.containsKey(s) && map.get(s) == true){
      return len;
    }
    
    if(len == 0){
      return 0;
    }
    
    if(isValid(s)){
      map.put(s, true);
      return len;
    }else{
      map.put(s, false);
    }
    
    String leftStr = s.substring(0, len - 1);
    String rightStr = s.substring(1);

    if((map.containsKey(leftStr) && map.get(leftStr) == true) || (map.containsKey(rightStr) && map.get(rightStr) == true)){
      return len - 1;
    }
    
    int maxLen = 0;

    if(!map.containsKey(leftStr)){
      maxLen = Math.max(maxLen, longestValidParenthesesRecur(leftStr, map));
    }
    
    if(!map.containsKey(rightStr)){
      maxLen = Math.max(maxLen, longestValidParenthesesRecur(rightStr, map));
    }
    
    return maxLen;
  }
  
  // Problem Question: https://leetcode.com/problems/longest-valid-parentheses/
  public int longestValidParentheses(String s) {
    /*
    For this recursive approach:
    Getting TLE on a string with 1524 characters, at 218 / 231 test cases passed on leetcode.

    Approach:
    ------------

    Either remove the first character and then recur or remove last character and then recur.
    And then check if they are valid, then take the length and return.

    We optimized many recursive calls, by just using hashmaps to store the result already calculated.

    ------------------------------------------------------------------------------

    Time complexity: Exponential as we recur twice from each string, which means around O(2^n), where n is the length of the string.
    This is the worst case, when map does not have the same entries, and all recursive calls are unique.

    Space complexity: Entries in map, and that can be much much more, not bounded by the string length.
    It is also having worst case space complexity of O(2^n)
    */
    HashMap<String, Boolean> map = new HashMap<>();
    
    map.put("", true);
    map.put("()", true); // additional test cases, just for the sake of lesser recursion calls
    
    return longestValidParenthesesRecur(s, map);
  }
}