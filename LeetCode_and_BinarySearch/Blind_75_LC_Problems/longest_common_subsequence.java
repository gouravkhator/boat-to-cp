class LongestCommonSubsequence {
  // recursive solution, which resulted in TLE on leetcode for some inputs
  public int lcsRecur(String text1, int ind1, String text2, int ind2) {
    /**
     * If m is the length of text1 and n is the length of text2,
     * 
     * Time complexity: O(2^m * 2^n) = O(2^(m+n))
     * Space complexity: O(1) extra space
     * 
     * Why exponential and why 2^m * 2^n ?
     * > In the worst case, no characters will match and we have to do the below:
     * For every possibility in string with length m, we check for every possibility in string with length n.
     * 
     * What is this possibility then?
     * > Each character in text1, can either occur in the further comparisons, or cannot occur at all in further comparisons.
     * So, each character has 2 possibilities. So, total possibilities in text1 is 2^m strings.
     * 
     * For every 2^m possible subsequences in text1, we have similar 2^n possible subsequences in text2.
     * So, Time complexity is O(2^m * 2^n)
     * 
     * How did we decode the above analogy from the recursive code?
     * >  As we can see, that in worst case, all the characters will differ.
     * So, we get to the recursive calls of below:
     * 
     * `Math.max(lcsRecur(text1, ind1 + 1, text2, ind2),
     *      lcsRecur(text1, ind1, text2, ind2 + 1));`
     * 
     * And here, at each time when a character from text1, is excluded for further iteration (by doing ind1 + 1), we take all of text2.
     * At each time a character from text2, is excluded for further iteration (by doing ind2 + 1), we take all of text1.
     * 
     * So, possibilities gets developed for both the strings, either in first recursive call or the 2nd one.
     * And the exclusion of character and inclusion of character denote the 2^m complexity.
     */
    if (ind1 >= text1.length()) {
      return 0;
    }

    if (ind2 >= text2.length()) {
      return 0;
    }

    if (text1.charAt(ind1) == text2.charAt(ind2)) {
      return 1 + lcsRecur(text1, ind1 + 1, text2, ind2 + 1);
    }

    return Math.max(lcsRecur(text1, ind1 + 1, text2, ind2), lcsRecur(text1, ind1, text2, ind2 + 1));
  }

  // DP solution, optimised one
  public int lcsDP(String text1, String text2) {
    /**
     * Approach:
     * 
     * Firstly, I checked the pattern by recursive approach.
     * In recursive approach, I initialised ind1 and ind2 with 0 and 0,
     * and for (0,0), I would calculate the (1,1) or (0,1) or (1,0) and then I would get final value from (0, 0).
     * 
     * So, this recursive function took a slightly different approach to see same problem.
     * 
     * For DP approach, I followed below pattern:
     * 1. If ith character of str1 and jth character of str2 are equal, then the length is incremented by 1.
     * 2. If ith character of str1 and jth character of str2 are not equal, then we take the maximum of LCS for:
     *  a. (i-1)th character of str1 with jth character, and
     *  b. ith character of str1 with (j - 1)th character
     * 
     * In the DP approach too, I started with 0th indices of the two strings, but here I calculated (0,0) first, and progressed towards (m,n)
     * And got the result from (m,n) where m is length of str1 and n is length of str2.
     * 
     * Some edge cases like:
     * 1. If it is the first row or first column, then just manually saw the pattern and coded it.
     * 2. If it is some non-zero rows and columns, then the above pattern was working fine.
     * 
     * Analogy:
     * 
     * If the characters are same, then that is always counted in longest common subsequence, and we then check for remaining characters of both strings.
     * If the characters differ, then we either ignore one character from str1 or one from str2, and keep the other string intact and check what those return us.
     *  
     * --------------------------------------------------------------------
     * 
     * Time complexity: O(len(str1) * len(str2))
     * Space complexity: O(len(str1) * len(str2))
     */
    int m = text1.length(), n = text2.length();

    int[][] LCS = new int[m][n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        char c1 = text1.charAt(i);
        char c2 = text2.charAt(j);

        if (i == 0 && j == 0) {
          // for cell [0,0]
          if (c1 == c2) {
            LCS[i][j] = 1;
          }
        } else if (i == 0 && j > 0) {
          // for first row
          if (c1 == c2) {
            LCS[i][j] = 1;
          } else {
            // if the characters are not same, then just the previous column's value will be put here.
            LCS[i][j] = LCS[i][j - 1];
          }
        } else if (i > 0 && j == 0) {
          // for first column
          if (c1 == c2) {
            LCS[i][j] = 1;
          } else {
            LCS[i][j] = LCS[i - 1][j];
          }
        } else {
          // other non-zero rows and columns

          if (c1 == c2) {
            LCS[i][j] = 1 + LCS[i - 1][j - 1];
          } else {
            LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
          }
        }
      }
    }

    /**
     * the bottom rightmost cell, signifying that all the indices were traversed from both strings,
     * and we result out from last index of each string..
     */
    return LCS[m - 1][n - 1];
  }

  // Problem Question: https://leetcode.com/problems/longest-common-subsequence/
  public int longestCommonSubsequence(String text1, String text2) {
    return lcsDP(text1, text2);
  }
}
