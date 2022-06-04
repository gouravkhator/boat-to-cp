# [Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/)

## Code | TLE on a string length of 1524 characters, and 218 / 231 test cases passed on leetcode

```java
class Solution {
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
  
  public int longestValidParentheses(String s) {
    /*
    getting TLE on a string with 1524 characters, at 218 / 231 test cases passed
    */
    HashMap<String, Boolean> map = new HashMap<>();
    
    map.put("", true);
    map.put("()", true); // additional test cases, just for the sake of lesser recursion calls
    
    return longestValidParenthesesRecur(s, map);
  }
}
```

