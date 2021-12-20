import java.util.*;

class KUniqueString {
  // Problem Question: https://binarysearch.com/problems/K-Unique-String
  public int solve(String s, int k) {
    /*
    Logic:

    If the string's length is at most k, then no characters to be removed, then return 0.
    If the string has at most k distinct characters, then no characters to be removed, then return 0.

    If none of above are true, then we need to find minimum changes,
    that can be done to keep string with atmost k distinct chars:
    1. For minimum changes to be done, we can remove characters with least frequency.
    2. We should only remove (total distinct characters - k) characters from string.

    For that, we sort the frequency in ascending order.
    And then, we keep a variable charsToRemove, which keeps track of (total distinct characters - k).

    Then, we count the number of chars removed,
    just by getting number of times those (total distinct characters - k) characters occur in the string.
    That count is our answer.
    */
    
    int len = s.length();

    if (len <= k) {
      // if length of string is max k, then nothing to remove
      return 0;
    }

    HashMap<Character, Integer> freq = new HashMap<>();

    for (int i = 0; i < len; i++) {
      char c = s.charAt(i);

      if (freq.containsKey(c)) {
        freq.put(c, freq.get(c) + 1);
      } else {
        freq.put(c, 1);
      }
    }

    if (freq.size() <= k) {
      // if number of distinct chars is less than or equal to k, then no chars to remove
      return 0;
    }

    int resultantCount = 0, charsToRemove = freq.size() - k;

    List<Map.Entry<Character, Integer>> freqSortedList = new LinkedList<Map.Entry<Character, Integer>>(freq.entrySet());

    // Sort the frequency hashmap
    Collections.sort(freqSortedList, new Comparator<Map.Entry<Character, Integer>>() {
      public int compare(Map.Entry<Character, Integer> o1,
          Map.Entry<Character, Integer> o2) {
        return (o1.getValue()).compareTo(o2.getValue());
      }
    });

    for (Map.Entry<Character, Integer> entry : freqSortedList) {
      if (charsToRemove > 0) {
        resultantCount += entry.getValue();
      }

      charsToRemove--;
    }

    return resultantCount;
  }
}
