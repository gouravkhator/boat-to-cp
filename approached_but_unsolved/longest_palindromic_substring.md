# [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)

## Approach

### Approach #1 | Using Longest Common Substring :

Take the string and its reversed and just find longest common substring.

Longest common substring can be found using dp. Here if a character in column matches a character in row, we set cell value to 1. If the next character of column is same as next character or row, we set cell value to 1 + its left cell. 

If the next characters are different, and current character does not match this column character, then 0 is put there. If current character of row matches this column character, set 1.

Now traverse this dp to check which is the highest and backtrack in that row to that many characters and print that characters string.

### Approach #2 | Full DP Solution

Logic is given in the code. It is a better approach.

## Code | TLE Solution

> DP Solution:

```java
import java.util.Scanner;

class LPS{
  // Question URL: https://leetcode.com/problems/longest-palindromic-substring/
  // Issue: Getting TLE on strings of length > 850 (constraint goes till 1000)
  public static String longestPalindromeStr(String str) {
    String subString = "";
    int length = str.length();
    int[][] dp = new int[length+1][length];

    /*
    dp for madamsir looks like:

       m a d a m s i r
    0  0 0 0 0 0 0 0 0
    1  0 0 0 0 0 0 0 0
    2  0 0 0 0 0 0 0 0
    3  0 0 0 3 0 0 0 0
    4  0 0 0 3 0 0 0 0
    5  0 0 0 3 5 0 0 0
    6  0 0 0 3 5 0 0 0
    7  0 0 0 3 5 0 0 0
    8  0 0 0 3 5 0 0 0

    Logic:
    Column contains characters of string, and rows contain the current length
    Ignore row 0 and row 1. 
    Row 2 means for each character in the string, 
    we check a substring (of 2 character long) starting from current character leftwards.
    Then if that substring is palindromic, set that cell in dp to that row number, else copy its upper row value.

    If the substring cannot be taken (when we are in row 3 and want substring from 2nd column leftwards, we cannot take a substring of length 2 here)
    Then we copy upper row's value to this cell.

    After dp is created, traverse from bottom right to top left, checking if the current cell's value didn't come from its upper cell.
    If it did not come from its upper cell, it means it was palindromic, return that many characters (from that column leftwards).
    
    If the value came from upper cell, it means that current length row was not palindromic, 
    and it contained a smaller palindrome of some length (whose value that's copied from upper cells).

    Why its the longest palindromic substring, is because we traversed from bottom right.
    (length is larger in bottom rows and right ensures that we can take result string from right to left). 
    */

    for (int i = 2; i <= length; i++) {
      for(int j = 1; j < length; j++){
        if(j+1 < i){
          dp[i][j] = dp[i-1][j];
          continue;
        }

        subString = str.substring(j+1 - i, j+1);

        StringBuffer sb = new StringBuffer(subString);
        if(sb.reverse().toString().equals(subString)){
          // this substring is a palindrome
          dp[i][j] = i;
        }else{
          dp[i][j] = dp[i-1][j]; // i is the length of the substring which is palindrome
        }
      }
    }

    // dp printing like below
    for(int i=0; i <= length; i++){
      for(int j=0;j<length;j++){
        System.out.print(dp[i][j]+" ");
      }

      System.out.println();
    }

    for(int i=length; i >= 1; i--){
      for(int j=length - 1; j >= 0;j--){
        if(dp[i][j] != dp[i-1][j]){
          return str.substring(Math.max(0, j+1 - i), j+1);
        }
      }
    }

    return ""+str.charAt(0);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine();
    System.out.println(longestPalindromeStr(str));
    sc.close();
  }
}
```

> Longest Common substring Approach (the worst approach):

```java
class LPS_LCS {
      public String longestCommonSubstr(String s1, String s2){
        int len1 = s1.length();
        int len2 = s2.length();

        int[][] dp = new int[len1][len2];

        for(int i=0;i<len1;i++){
          for(int j=0;j<len2;){
            if(s1.charAt(i) == s2.charAt(j)){
              dp[i][j] = 1;

              j++;
              for(int k=i+1; j<len2 && k<len1 && s1.charAt(k) == s2.charAt(j); k++, j++){
                dp[i][j] = dp[i][j-1] + 1;
              }

            }else{
              j++;
            }
          }
        }

        int maxVal = -1, finalJ=0;
        for(int i=0;i<len1;i++){
          for(int j=0;j<len2; j++){
            if(dp[i][j] > maxVal){
                String longestSubstr = s2.substring(Math.max(0, j+1-dp[i][j]), j+1);
              // check if longest substring got is actually palindrome or not
              // ex- aacabdkacaa this can have aaca as longest common in reversed and original string
              // aaca is not palindrome at all

              if(longestSubstr.equals(new StringBuffer(longestSubstr).reverse().toString())){
                maxVal = dp[i][j];
                finalJ = j;
              }
            }
          }
        }

        return s2.substring(Math.max(finalJ+1 - maxVal, 0), finalJ+1);
    }

    public String longestPalindrome(String s) {        
        int len = s.length();
        if(len==1){
            return s;
        }

        String reversed = (new StringBuffer(s)).reverse().toString();

        if(s.equals(reversed)){
          return s;
        }

        return longestCommonSubstr(s, reversed);
    }
}
```
