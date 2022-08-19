import java.util.Arrays;

class ShortEncodingOfWords {

  /*
Test cases:
["time","me","bell"]
["the","he","is","not","good","walking","hgood"]
["t"]
["weso","awesome","some","people","hate","ate","me"]
  */
  public int getUniqueWordLenCount(Trie.TrieNode root, int len) {
    if (root == null) {
      return 0;
    }

    int resCount = 0;

    for (int i = 0; i < 26; i++) {
      if (root.children[i] != null) {
        resCount += getUniqueWordLenCount(root.children[i], len + 1); // count the next character and then recur.
      }
    }

    if (resCount == 0) {
      return (len + 1); // +1, for the # character
    } else {
      return resCount;
    }
  }

  // Problem Question: https://leetcode.com/problems/short-encoding-of-words/
  public int minimumLengthEncoding(String[] words) {
    /*
    Analogy behind the question:
    -----------------------------------------------------------
    
    Question told that we can have ANY valid encoding of the words array.
    Valid encoding was like we can put the words in any indices possible, but the previous word should end with next word, and the next word will then be not used in different hash group, rather it will be merged with previous word to make the smallest reference string s.
    
    As the indices can be anything, and it is just that the string from that index to a # character, matches the word. And we need to make the smallest possible string s.
    
    Approach behind this most optimal solution:
    ------------------------------------------------
    
    As we want to find the words which end with other words, and then only count the set of larger words s1, which also includes the set of words ending with the words in set s1.
    
    So, we can use trie.
    
    In trie, if we insert every word in normal way, then we will not be able to make trie where we have similar path for two words which end in the same suffix.
    
    So, we should insert word in the reversed way. This way, let's say two words "time" and "me" will be inserted in trie like "emit", read from root to leaf, where node m is a terminal, and t is also a terminal.
    
    If we had inserted in normal way, then words "time" and "me" will be inserted in trie like "time" and "me", with two different branches from the root. This means, that it will give wrong answer.
    
    We have to count the length of word from the root to the leaf, and not stop at the terminal nodes, meaning the nodes which are lead to actual word in words array.
    We should stop at node, which has no children. If a node has no children, means we reached a word which should be counted.
    
    We will count the length of the word so far, and +1 for the # character.
    
    -----------------------------------------------------------------------------------
    
    Time complexities: 
    1. For insertion of reversed words in trie: O(word_length * number_of_words)
    2. For recursively checking the number of unique words in trie, with the length and the # characters: O(word_length * number_of_words), as we branch to the valid child, and don't recur for all 26 children. But it is complex to generalise.
    
    So, approx time complexity: O(word_length * number_of_words)
    
    Space complexity: O(word_length * number_of_words * 26) for the trie data structure, where there are (word_length * number_of_words) number of trie nodes and each having 26 length long children array.
    */

    Trie trie = new Trie();

    for (int i = 0; i < words.length; i++) {
      words[i] = (new StringBuffer(words[i])).reverse().toString();

      trie.insert(words[i]);
    }

    return getUniqueWordLenCount(trie.root, 0);
  }
}

class Trie {

  class TrieNode {

    TrieNode[] children;
    boolean terminal;
  }

  TrieNode root;

  public Trie() {
    root = new TrieNode();
    root.children = new TrieNode[26];
    root.terminal = false;
  }

  public void insert(String word) {
    int len = word.length();

    TrieNode temp = root;

    for (int i = 0; i < len; i++) {
      char c = word.charAt(i);

      if (temp.children[c - 97] == null) {
        TrieNode newnode = new TrieNode();
        newnode.terminal = false;
        newnode.children = new TrieNode[26];

        temp.children[c - 97] = newnode;
      }

      temp = temp.children[c - 97];
    }

    temp.terminal = true;
  }

  public boolean search(String word) {
    int len = word.length();
    TrieNode temp = root;

    for (int i = 0; i < len; i++) {
      char c = word.charAt(i);

      if (temp.children[c - 97] != null) {
        temp = temp.children[c - 97];
      } else {
        return false;
      }
    }

    return temp.terminal;
  }

  public boolean startsWith(String prefix) {
    int len = prefix.length();
    TrieNode temp = root;

    for (int i = 0; i < len; i++) {
      char c = prefix.charAt(i);

      if (temp.children[c - 97] != null) {
        temp = temp.children[c - 97];
      } else {
        return false;
      }
    }

    return true;
  }
}

class SolutionNaive {

  // Problem Question: https://leetcode.com/problems/short-encoding-of-words/
  public int minimumLengthEncoding(String[] words) {
    /*
    This approach was the naive one.
    
    Analogy behind the question is given above, in the most optimal solution comments.
    
    Approach for naive solution:
    ---------------------------------
    
    So, we can take every word and then every other word, and see if the one word ends with the other word, then set the other word's index to null, meaning we don't want that other word.
    
    And we do this for every pairs of words which are not nullified.
    
    After all this, we loop through the words array and see if any word is left which is not nullified by its higher length word, ending with same suffix.
    
    We take the length of that word, and as we know a # is also put after the word, so resCount will include the length of that word, and 1 more character (which is #).
    
    NOTE: Sorting the words array by decreasing length order, makes it more optimised, as the larger word has the potential to nullify most smaller words, and thus reducing the iterations.
    
    -------------------------------------------------------------------------------
    
    n is the length of the words array.
    Time complexity: O(n^2)
    Space complexity: O(1)
    */
    int len = words.length, resCount = 0;

    if (len == 0) {
      return 0;
    }

    Arrays.sort(words, (a, b) -> Integer.compare(b.length(), a.length()));

    for (int i = 0; i < len; i++) {
      // if the current word is nullified, then no need to loop
      if (words[i] == null) {
        continue;
      }

      for (int j = 0; j < len; j++) {
        // if index is same, or the words[j] is nullified, then no need for further checks
        if (i == j || words[j] == null) {
          continue;
        }

        if (words[i].endsWith(words[j])) {
          // nullify or kill the words[j], as words[i] is relevant for us, for making the smallest reference string s
          words[j] = null;
        }
      }
    }

    for (int i = 0; i < len; i++) {
      if (words[i] != null) {
        resCount += words[i].length() + 1; // 1 more character # is also included with the length of the word, in the resCount
      }
    }

    return resCount;
  }
}
