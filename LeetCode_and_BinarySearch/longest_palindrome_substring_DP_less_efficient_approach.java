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
