import java.util.ArrayList;
import java.util.HashMap;

class InterleavingString {

  public boolean isInterleaveRecur(
    int i1,
    int i2,
    String s1,
    String s2,
    String s3,
    HashMap<ArrayList<Integer>, Boolean> map
  ) {
    /*
     * Analogy:
     * -------------
     *
     * When the string s1 is "gatin", s2 is "ourvite" s3 is like "gouravitinte", then we should understand if the current character has come from string 1 or from s2.
     * ANd it can happen that some characters have come from s1, so we cannot consider for s2, and we don't get to the interleaving string.
     *
     * As for above example, s3 = "gouravitinte", 'g' comes from s1, 'o' from s2,
     *  'u' from s2, 'r' from s2, 'a' from s1, 'v' from s2, 'i' from s2.
     *
     * Now comes character 't', and it can come from either of the two strings. So, we should check for both cases.
     * And it may happen that in each case, there will be more diversions and we can get 2 branches for each case.
     *
     * Worst case: We will have recursion with 2 branches for each character in the s3 string.
     * But we know that most of the times, some branches in this recursion will be overlapping.
     *
     * So, to avoid those overlapping recursive calls, we can memoize it, which will be done using HashMap.
     *
     * If the length of string s1 is m, and length of string s2 is n,
     * Then there can be atmost (m*n) subproblems, for which we have to store the states.
     *
     * > NOTE: Here, we want to store the indices start for each of the strings s1 and s2.
     *
     * Q) Why this lookup strategy works internally?
     *
     * > It may happen that let's say from (0,0) we started, then we might get the character match from both strings.
     * So, we can go to state (0,1) meaning process the character from string s2 and recur.
     * Or, go to state (1,0) meaning process the character from string s1 and then recur.
     *
     * But from both states, we can go to state (1,1) by processing characters from either strings.
     *
     * We can solve this state once, and then lookup the next time we encounter the same state (1,1).
     *
     * Q) Why we don't store the index of the string s3?
     * > It is bcoz when we are at index i1, and index i2 for the strings s1 and s2 respectively,
     * it means we would be processing the character at i1 in s1, or at i2 in s2.
     *
     * It also means that we can processed i1 characters before that in s1, and i2 characters before that in s2.
     * So, we have processed (i1+i2) characters before overall in the string s3.
     * So, we don't store the index of s3, rather we compute it as (i1+i2).
     *
     * ---------------------------------------------------------------------------------------
     *
     * Time complexity: O(m*n)
     * Space complexity: O(m*n)
     *
     * If we had used just the recursion without memoization, then:
     *
     * Time complexity would have been: O(2^(m*n))
     * Space complexity: O(m*n), for the recursive call stack
     *
     * ---------------------------------------------------------------------------------------
     *
     * Extra Notes:
     * ----------------
     *
     * When using HashMap with some Pair custom class, or some array of primitive types, the containsKey and get recognises them as different keys as their references are different.
     * Make sure to implement equals method for the custom class used in HashMap or Set, or use the ArrayList<Integer> as I did to store those two indices.
     *
     */

    int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();

    /*
     * Base cases:
     * 1. If s1 and s2 totally does not make to s3, then we know it will never be possible to interleave those.
     * 2. If the (i1 + i2) (i.e. i3) is greater than or equals len3, meaning we processed all chars in s3, so return true.
     */
    if ((len1 + len2) != len3) {
      return false;
    }

    if ((i1 + i2) >= len3) {
      return true;
    }

    if (i1 < len1 && s1.charAt(i1) == s3.charAt(i1 + i2)) {
      ArrayList<Integer> list = new ArrayList<>();

      list.add(i1 + 1);
      list.add(i2);

      if (map.containsKey(list)) {
        if (map.get(list) == true) {
          return true;
        }
      } else {
        boolean flag = isInterleaveRecur(i1 + 1, i2, s1, s2, s3, map);

        map.put(list, flag);

        if (flag == true) {
          return true;
        }
      }
    }

    if (i2 < len2 && s2.charAt(i2) == s3.charAt(i1 + i2)) {
      ArrayList<Integer> list = new ArrayList<>();

      list.add(i1);
      list.add(i2 + 1);

      if (map.containsKey(list)) {
        if (map.get(list) == true) {
          return true;
        }
      } else {
        boolean flag = isInterleaveRecur(i1, i2 + 1, s1, s2, s3, map);

        map.put(list, flag);

        if (flag == true) {
          return true;
        }
      }
    }

    return false;
  }

  public boolean isInterleave(String s1, String s2, String s3) {
    /*
Test cases:
"aabcc"
"dbbca"
"aadbbcbcac"
"bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa"
"babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab"
"babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab"
    */

    HashMap<ArrayList<Integer>, Boolean> map = new HashMap<>();

    return isInterleaveRecur(0, 0, s1, s2, s3, map);
  }
}
