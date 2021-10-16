// Problem Question: https://leetcode.com/problems/implement-trie-prefix-tree/
class Trie {
    /*
    Logic:

    Each node has the children of characters, which are themselves TrieNode..
    Also, if the word is inserted directly, it's last character TrieNode will be a terminal one.

    Ex- "cat" and "cattle" are inserted in the given order. Then, 't' and 'e' will be marked terminal..
    
    Now, for saving children, we can only save the character to node mapping of children using hashmap for each trienode.
    But hashmap uses lots of time complexity, so using array lookup.

    We know at each level, we have 26 characters only (as it's said that only lowercase English letters are present).
    So, at 0th index of the array, save TrieNode for 'a', at 1st index, save TrieNode for 'b' and so on.
    
    We have the root node which has array of 26 TrieNode and a terminal flag.
    
    If "cat" is to be inserted:

    Firstly, we go to root node. We check if 'c' has some node in root's children array.
    If yes, then goto 'c'. Else, create a new node for 'c' and then go there.
    Now, create 'a' and 't' and at last make 't' as the terminal one.

    Now, if "cattle" is inserted.
    Firstly, go to root node, check if 'c' has some node in it. For 'c', we check ('c' - 97) index in root's children.
    As 'c' is already mapped. Now, go to children of 'c'. Check if 'a' has some node mapped in children of 'c'.
    Goto, children of 't', and here another 't' is not mapped in children of this 't'.
    So, create a node and map it to 't'.
    Check for 'e' in children of second 't'. If not set, create a node and map 'e' to children of second 't'.
    And mark 'e' as terminal.

    Searching is similar too, we visit till the characters are same, and if whole word matches, then we return true if the last character is terminal else false.
    If some characters were not same as the searched word, return false there itself.

    Starts with method is similar to searching, but it returns true if whole word is present.
    It does not check if the last character is set as terminal node or not.
    */
    
    class TrieNode{
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
        
        for(int i=0; i<len; i++){
            char c = word.charAt(i);
            
            if(temp.children[c - 97] == null){
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
        
        for(int i=0; i<len; i++){
            char c = word.charAt(i);
            
            if(temp.children[c - 97] != null){
                temp = temp.children[c - 97];
            }else{
                return false;
            }
        }
        
        return temp.terminal;
    }
    
    public boolean startsWith(String prefix) {
        int len = prefix.length();
        TrieNode temp = root;
        
        for(int i=0; i<len; i++){
            char c = prefix.charAt(i);
            
            if(temp.children[c - 97] != null){
                temp = temp.children[c - 97];
            }else{
                return false;
            }            
        }
                
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
