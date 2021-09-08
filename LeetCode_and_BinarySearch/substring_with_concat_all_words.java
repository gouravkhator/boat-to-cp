import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

class SubstrConcatAllWords {
    public static void main(String[] args) {
        //Test Cases:
        /*
        "ababababab"
        ["ababa","babab"]
        "barfoothefoobarman"
        ["foo","bar"]
        "wordgoodgoodgoodbestword"
        ["word","good","best","word"]
        "barfoofoobarthefoobarman"
        ["bar","foo","the"]
        "lingmindraboofooowingdingbarrwingmonkeypoundcake"
        ["fooo","barr","wing","ding","wing"]
        */
        System.out.println(findSubstring("ababababab", new String[]{"ababa","babab"}));
    }

    public static HashMap<String, Integer> createHashMap(String[] words, int totalWords){
        HashMap<String, Integer> map = new HashMap<>();
        
        int i=0;
        for(i=0; i<totalWords; i++){
            if(map.containsKey(words[i])){
                map.put(words[i], map.get(words[i]) + 1);
            }else{
                map.put(words[i], 1);
            }
        }
        
        return map;
    }
    
    // Problem Question: https://leetcode.com/problems/substring-with-concatenation-of-all-words/
    public static List<Integer> findSubstring(String s, String[] words) {
        /*
        Logic:

        Create a map of all words and how many times they occur in words array.

        Then, create a startIndices list which contains, from which indices the words do occur in the words array.
        (For ex- s="foothe" and words=["foo", "bar"], so startIndices will be [0] as foo was present at index 0 and was in words array too)

        Start indices are done so that from any index, we can calculate if the whole pattern after that index matches the concat of all words.
        For each start index,

        Reset tempMap which is just to cut the required freq count if a word has been seen the current time.
        It's initialised with original map. And we have tempTotalWords which is the count of all words in this subsequence.

        From the current startIndex, if the word is in tempMap and its count is 0
        That means, the word was already taken and no more occurrence is allowed.
        So, check if tempTotalWords is equal to totalWords, then add the startIndex to resultant set and break the current startindex loop..

        Else, if the count is not 0, then just count it, and deduct one occurrence from tempMap.
        If the word was not already in tempMap, then check the tempTotalWords as before, add to set if applicable, and break the current startindex loop.
        
        After this loop, if any tempTotalWords were not reset, and its equal to total words then add to set.

        After the outer loop, make a list from this set. And return the list.
        */
        HashSet<Integer> set = new HashSet<>(); // set is used so that multiple indexes don't get added.
        
        int i=0, slen = s.length(), totalWords = words.length, wordlen = words[0].length();
        String temp = "";
        
        HashMap<String, Integer> map = createHashMap(words, totalWords);
        
        ArrayList<Integer> startIndices = new ArrayList<>();
        
        for(i=0; i<=(slen - wordlen); i++){
            temp = s.substring(i, i+wordlen);
            
            if(map.containsKey(temp)){
                startIndices.add(i);        
            }
        }
        
        int tempTotalWords = 0;
        
        for(Integer startIndex: startIndices){
            HashMap<String, Integer> tempMap = new HashMap<>(map);
            tempTotalWords = 0;
            
            for(i=startIndex; (i+wordlen)<=slen; i+=wordlen){
                temp = s.substring(i, i+wordlen);

                if(tempMap.containsKey(temp)){
                    int oldMapCount = tempMap.get(temp);

                    if(oldMapCount == 0){
                        // check if all words were formed or not
                        if(tempTotalWords == totalWords){
                            set.add(startIndex);    
                        }
                        
                        break;
                    }else{
                        // count this temp in that tempMap
                        tempTotalWords++;
                        tempMap.put(temp, tempMap.get(temp) - 1);
                    }
                }else{
                    // tempMap does not contain this word
                    // check if the totalWords were met, and if yes the startIndex is pushed to set
                    if(tempTotalWords == totalWords){
                        set.add(startIndex);    
                    }
                    break;
                }
            }

            // for the patterns that match at the end of string s
            if(tempTotalWords == totalWords){
                set.add(startIndex);    
            }
        }

        List<Integer> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }
}
