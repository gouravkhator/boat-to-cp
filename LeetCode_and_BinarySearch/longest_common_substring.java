class LongestCommonSubstring {
  public static void main(String[] args) {
    LongestCommonSubstring ob = new LongestCommonSubstring();
    System.out.println(ob.longestCommonSubstring("geeks", "gooks"));
    System.out.println(ob.longestCommonSubstring("guys", "buy"));
    System.out.println(ob.longestCommonSubstring("nearingfuture", "learningnurture"));
    System.out.println(ob.longestCommonSubstring("", "gooks"));
    System.out.println(ob.longestCommonSubstring("geeks", ""));
  }

  // Problem Question and DP solution taken from: https://www.geeksforgeeks.org/longest-common-substring-dp-29/
  public int longestCommonSubstring(String str1, String str2) {
    /**
     * Question: Given two strings, find the length of the longest common substring.
     * 
     * Analogy:
     * 
     * We can have a recursive analogy like below:
     * LCS(str1, str2, m, n) = 1 + LCS(str1, str2, m-1, n-1) if str1[m] == str2[n]
     * LCS(str1, str2, m, n) = 0 if str1[m] != str2[n]
     * 
     * Max length of longest = Max(max length till now, LCS(str1, str2, m, n))
     * 
     * Approach:
     * 
     * As we see that there might be many overlapping subproblems in recursive approach,
     * we try to use the DP approach..
     * 
     * Also, here we are only told to check the longest length, but if we are told to print the longest common substring,
     * then just we can either traverse this DP afterwards,
     * or track the start of the string and the longest length of the string then and then. 
     * 
     * Also this longest common substring is somewhat different from longest common subsequence. Why?
     * > It is bcoz in longest common substring:
     *  a) If any characters are not same, we just put 0 there, and we have the maximum length in one another variable.
     *  b) If they are same, we just want i-1,j-1 thing to return us the length similarly.
     * 
     * In Longest common subsequence, we have the similar DP, but as it is a subsequence,
     * it can take the i,j-1 or i-1,j whichever is max, if the current characters don't match..
     * And as we are tracking down fully even when current characters don't match, we have the max at LCS[m-1][n-1]..
     */

    int m = str1.length(), n = str2.length();
    int[][] LCS = new int[m][n];
    int maxLen = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 || j == 0) {
          if (str1.charAt(i) == str2.charAt(j)) {
            LCS[i][j] = 1;
          }
        } else {
          // non-zero rows and cols
          if (str1.charAt(i) == str2.charAt(j)) {
            LCS[i][j] = 1 + LCS[i - 1][j - 1];
          } else {
            LCS[i][j] = 0;
          }
        }

        maxLen = Math.max(maxLen, LCS[i][j]);
      }
    }

    return maxLen;
  }
}