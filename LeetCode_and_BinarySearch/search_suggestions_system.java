import java.util.*;

class SearchSuggestionsSystem {
  public void evalTheWordsPrefixedWith(Trie.TrieNode node){
    node.wordsPrefixedWith = new ArrayList<>();
    
    if(node.isTerminal == true){
      // if this current character is itself the terminal, then we add the word index to the list
      // this addition should be done before traversing the children, as it should be in ascending order
      // ex- "mango" and "mangoes" are two words, so we should add the index of word "mango" before traversing to the children "e" and going towards making word "mangoes"
      node.wordsPrefixedWith.add(node.wordIndex);
    }
    
    for(int i=0; i<26; i++){
      if(node.children[i] != null){
        evalTheWordsPrefixedWith(node.children[i]);
        
        node.wordsPrefixedWith.addAll(node.children[i].wordsPrefixedWith);
      }
    }
  }
  
  // Problem Question: https://leetcode.com/problems/search-suggestions-system/
  public List<List<String>> suggestedProducts(String[] products, String searchWord) {
      /*
  Test cases:
["mobile","mouse","moneypot","monitor","mousepad"]
"mouses"
["havana"]
"havana"
["bags","baggage","banner","box","cloths"]
"bags"
["bags","baggage","banner","box","cloths","bagging","banned","boxes","clothings","clothesforzebrasandelephantsviathebirdseyesareverybeautifulandangryafaftermycheekgotswelledup"]
"clothortina"
  */
    /*
    Approach:
    ------------
    
    We make a trie.
    And as in trie, the children are always in sorted order, so for the reuslt to be in lexigraphically ascending order, we already have them in ascending order.
    
    In the trienode, we normally have the TrieNode[] children array, the isTerminal, the wordIndex (denoting the index of a word which is ending with this character, and as the products array is unique, so we have a unique index of word ending with a character).
    We also have the wordsPrefixedWith list, which contains the list of indices of words prefixed with the string till current character.
    
    We firstly insert the word in the trie, in such a way that the word index is also set in the last character node.
    
    Then, we evaluate the wordsPrefixedWith list, via the DFS like approach.
    
    To evaluate the wordsPrefixedWith list for every node:
    1. Check if the current node itself is a terminal, and if yes, then add its own word index.
    2. Then, recur for its children and for each of the paths in the trie, and then add to their list, the valid word indices.
    3. When we backtrack to current node again, we then add the word indices of its children too.
    
    This means, each character knows to which words, it would be a prefix for. And if the children (or successor) is a prefix of some words, and that successor comes from the current node, then this current node is also a prefix of those words.
    
    To get the suggested words now:
    1. We check the searchWord character by character and go down the path till we are getting that character in the trie.
    2. While we go the path, for each character match in trie as well, we will just take atmost 3 word indices from the respective node, and then add the strings to the list.
    3. Then, we append this list to the resultant list of lists.
    4. At some point, if we don't get the character match, then from that point, we won't ever be possible to make the search word, so we break.
    5. And we add remaining lists as the empty lists to the resultant list of lists, as tnow the suggested products will be empty, due to the character mismatch, and no words prefixed with that character which was not matched.
    
    --------------------------------------------------------------------------------
    
    Time Complexities:
    [ n is the number of products, and m is the length of searchWord. ]
    1. For insertion in trie: O(n * length_of_each_product_word)
    2. For evaluating the wordsPrefixedWith: O(number_of_nodes_in_trie) approximately, as we traverse each node only once, like in DFS.
    3. For getting suggestions: O(m)
    
    Total time complexity is O(n * length_of_each_product_word + number_of_nodes_in_trie + m)
    approximated to around O(n * length_of_each_product_word + m), as the number of nodes in trie becomes approximately close to the (n * length_of_each_product_word) in most cases.
    
    Space complexity involves the trie custom data structure, with each node involving the wordsPrefixedWith list, and the 26 length array of the similar node.
    Approx space complexity cannot be calculated in a generalised way.
    */
    
    Trie trie = new Trie();
    
    for(int i=0; i<products.length; i++){
      trie.insert(products[i], i);
    }
    
    evalTheWordsPrefixedWith(trie.root);
    
    return trie.getSuggestedWords(searchWord, products);
  }
}

class Trie {
  static class TrieNode{
    TrieNode[] children;
    List<Integer> wordsPrefixedWith; // indices of words, which are prefixed with the current character
    boolean isTerminal;
    int wordIndex = -1; // default value of this wordIndex is -1, and it is set only when the isTerminal value is true
  }

  TrieNode root;

  public Trie() {
    root = new TrieNode();
    root.children = new TrieNode[26];
    root.isTerminal = false;
  }

  /**
  * Inserts the given word in the trie, with the last character of the word marked as isTerminal, in trie.
  * Once we get to the last character, we also add its wordIndex as present in the products array
  */
  public void insert(String word, int wordIndex) {
    int len = word.length();

    TrieNode temp = root;

    for(int i=0; i<len; i++){
      char c = word.charAt(i);

      if(temp.children[c - 97] == null){
        TrieNode newnode = new TrieNode();
        newnode.isTerminal = false;
        newnode.children = new TrieNode[26];

        temp.children[c - 97] = newnode;
      }

      temp = temp.children[c - 97];
    }

    temp.isTerminal = true;
    temp.wordIndex = wordIndex; // sets the array's index of this word in the last character's node
  }

  /**
  * Returns the required suggested words.
  */
  public List<List<String>> getSuggestedWords(String word, String[] products) {
    List<List<String>> result = new ArrayList<>();
    
    int len = word.length();
    TrieNode temp = root;

    int i = 0;
    
    for(i=0; i<len; i++){
      char c = word.charAt(i);

      if(temp.children[c - 97] != null){
        int numWordsPrefixedWith = temp.children[c - 97].wordsPrefixedWith.size(); // get the indices of the words that are prefixed with this character
        
        List<String> list = new ArrayList<>();
        
        for(int j=0; j<Math.min(numWordsPrefixedWith, 3); j++){
          // we get the min of the number of such words, or 3, as we need to return at most 3
          int wordIndex = temp.children[c - 97].wordsPrefixedWith.get(j);
          list.add(products[wordIndex]); // we add the products word at that wordIndex
        }
        
        result.add(new ArrayList<>(list));
        temp = temp.children[c - 97];
      }else{
        break; // break, as from now, no characters will be present in trie
      }
    }

    // if we break out from the loop, and still i is less than len, then we add empty lists to the list of lists, as no string is a valid suggestion from now
    for(int j=i; j<len; j++){
      result.add(new ArrayList<>());
    }
    
    return result;
  }
}
