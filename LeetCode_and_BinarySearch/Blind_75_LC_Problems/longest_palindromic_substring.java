class LongestPalindromicSubstring {
  public static void main(String[] args) {
    LongestPalindromicSubstring ob = new LongestPalindromicSubstring();
    System.out.println(ob.longestPalindrome("mommy"));
    System.out.println(ob.longestPalindrome("gagofwassawpurirup"));
    System.out.println(ob.longestPalindrome("mynameisgouravgudduragiga"));
  }

  // Problem Question: https://leetcode.com/problems/longest-palindromic-substring/
  // Space optimised approach with Time complexity as O(n^2) and Space complexity as O(1) extra space
  public String longestPalindrome(String str) {
    /**
     * Question is to print the longest palindromic substring..
     * 
     * Analogy:
     * 
     * I thought that the DP approach for common substring would similarly work for this longest palindromic substring.
     * But if we find the longest common substring between str and its reverse, then for example:
     * 
     * for str="mummy", at some point, the "um" will be common too between the str and its reverse.
     * So, it might lead to a wrong answer for some good test cases.
     *   
     * So, a different analogy to do via DP was also getting tough.
     * We can refer this solution for the DP approach for longest palindromic substring: 
     * https://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
     * 
     * Time complexity of DP approach was: O(n^2)
     * Space complexity of DP approach was: O(n^2) extra space.
     * 
     * ---------------------------------------------------------------------------------
     * 
     * What approach can we take?
     * > Longest palindromic hits us with one analogy, which is to fix an index,
     * and then go leftwards and rightwards, till we have the same characters bothways.
     * 
     * For odd palindromic substring like "mum", we should fix the index at 1, and then go towards left and right and then check.
     * 
     * For substring "muum", we should fix the index at 1, then check leftwards and rightwards.
     * But here, for rightwards, we see that m is not equal to u..
     * So, we should check the fixed character with the leftward or rightward character is they are same.
     *  
     * Meaning while the fixed index is same as the character in left or in right, we go towards the left and right.
     * 
     * And then once they are not same as the fixed index, we check the left character with the right character, while they are same.
     * 
     * Why we have to fix an index?
     * > It is like taking one of the index as the centric point for this palindromic substring and then going leftwards and rightwards,
     * whilst satisfying the palindrome property.
     * 
     * And this we have to do for all indices,
     * as we don't know exactly which index can be the centric point for longest palindromic substring. 
     * 
     * --------------------------------------------------------------------------------------------
     * 
     * Time complexity of this approach is O(n^2), as we can go at max to n, either left or right sides.
     * Space complexity of this approach is O(1) extra space.
     */

    int len = str.length(), i = 0, maxLen = 0;
    String resString = "";

    for (i = 0; i < len; i++) {
      // fix the center of the palindromic substring,
      // and now go left and right till we have this palindromic..

      // we start from (i-1)th index in left side, and (i+1)th index in right side.
      int left = i - 1, right = i + 1;

      /**
       * meaning go till we have the same character as this ith one..
       * 
       * It is done so that we can account for odd as well as even palindromic substring..
      */
      while (left >= 0 && str.charAt(left) == str.charAt(i)) {
        left--;
      }

      while (right < len && str.charAt(right) == str.charAt(i)) {
        right++;
      }

      while (left >= 0 && right < len && str.charAt(left) == str.charAt(right)) {
        left--;
        right++;
      }

      // as left was decremented before, so, (left+1) is actually the exact palindromic substring's start.
      left++;
      // as right was incremented before, so, (right-1) is actually the exact palindromic substring's end.
      right--;

      int currentLen = right - left + 1;

      if (currentLen > maxLen) {
        // if current len is max, then we take the substring starting from 'left' index, and ending at 'right' index.
        maxLen = currentLen;
        resString = str.substring(left, right + 1);
      }
    }

    return resString;
  }
}