import java.util.*;

class PalindromePartitioning {
  // Problem Question: https://leetcode.com/problems/palindrome-partitioning/
  public boolean isPalindrome(String s){
    StringBuilder sb = new StringBuilder(s);
    
    return sb.reverse().toString().equals(s);
  }
  
  public void partitionRecur(String s, int startIndex, List<String> currentList, List<List<String>> result){
    /*
    Using HashMap to store isPalindrome or not, is actually taking some more time as per Leetcode, but for bigger test cases, maybe I can say that it reduces some of the time for checking isPalindrome. 
    (as HashMap's containsKey, get and put are mostly O(1) operations).
    (But due to so many extra space for storing possibly every substring, we don't user HashMap here).
    ---------------------------------------------------------------------------
    
    Main implementation approach can be explained by below example:
    
    Given string is "aab", so for this, we can have "a" and then recur for "ab"..
    Or, we can goto "aa" and recur for b.
    
    The point is we take a string if it is palindromic, and then recur for rest of the strings, and at last we get to the last index somehow, then add the list of strings to the resultant nested list.
    
    ANother example: "oamimom", and for this, we take "o" and recur for "amimom".
    We can do for the rest string too, and then another possibility is to keep taking a string starting with "o" till the point it is palindromic and then again recur.
    
    This way, we cover all substrings starting with "o", and are palindromic.
    And then in the recur, we start with the index after the current substring.
    
    So, if I take 3 characters now as one of my palindromic substring, then we recur from the 4th character.
    */
    int len = s.length();
    
    if(startIndex >= len){
      result.add(new ArrayList<>(currentList));
      return;
    }
    
    String temp = "";
    
    for(int i=startIndex; i<len; i++){
      temp += s.charAt(i);    
      
      if(isPalindrome(temp)){
        int index = currentList.size();
        
        currentList.add(index, temp);
        partitionRecur(s, i + 1, currentList, result);
        currentList.remove(index);
      }
    }
  }
  
  public List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<>();
    
    partitionRecur(s, 0, new ArrayList<>(), result);
    return result;
  }
}