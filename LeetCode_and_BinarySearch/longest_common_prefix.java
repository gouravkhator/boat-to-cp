class LongestCommonPrefix {
    // Problem Question: https://leetcode.com/problems/longest-common-prefix/
    public String longestCommonPrefix(String[] strs) {
        /*
        Logic:

        First find the min length of the strings in strs array.
        Then, only go till that length for all strs and check if the character matches with first string's character at that index.

        If the character does not match, then stop the outer loop too and just return resultant string.
        If the inner loop through all strings satisfy that character, then that character is added to resultant string.
        */

        int minLen = 210, len = strs.length;
        
        for(int i=0; i<len; i++){
            minLen = minLen > strs[i].length() ? strs[i].length() : minLen;
        }
        
        String res = "";
        
        Outer:
        for(int i=0; i<minLen; i++){
            char c = strs[0].charAt(i);
            
            for(int j=1; j<len; j++){
                if(strs[j].charAt(i) != c){
                    break Outer;
                }
            }
            
            res += c;
        }
        
        return res;
    }
}
