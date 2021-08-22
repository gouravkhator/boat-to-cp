import java.util.HashMap;
import java.util.Map;

class WordFormationSequel {
    public static void createCountMap(String word, HashMap<Character, Integer> map, int wordLen) {
        for(int i=0; i<wordLen; i++){
            char c = word.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }else{
                map.put(c, 1);
            }
        }
    }

    public static int countCommon(String mainWord, int wordLen, HashMap<Character, Integer> lettersCountMap){
        int count=0;

        HashMap<Character, Integer> mainWordMap = new HashMap<>();
        createCountMap(mainWord, mainWordMap, wordLen); // get counts of each letter of main word

        for(Map.Entry<Character, Integer> entry: mainWordMap.entrySet()){
            char c = entry.getKey();

            if(lettersCountMap.containsKey(c)){
                count+= Math.min(lettersCountMap.get(c), entry.getValue());
            }
        }

        return count;
    }

    // Problem Question: https://binarysearch.com/problems/Word-Formation-Sequel
    public static int solve(String[] words, String letters) {
        /*
        Logic:

        Create a map of counts of each letter in letters.
        Get count of stars in letters string.
        Count common letters in letters and word.

        This is done as follows:
        1. Create a count map of word's letters too.
        2. If the character in that word also occurs in letters map, then get the minimum of two counts.
        (It means minimum will contain common number of occurrences in the two)
        3. Increment count by that minimum.

        This count is the count of common letters in both strings.
        Now, uncommon ones in main word is word length - number of common ones.
        If uncommon ones can fit in the given number of stars, then we check for maximum word length.
        (Fit here means if number of uncommons <= number of stars in letters string).

        */
        int letterLen = letters.length(), starCount = 0;

        HashMap<Character, Integer> map = new HashMap<>();
        createCountMap(letters, map, letterLen);

        if(map.containsKey('*'))
            starCount = map.get('*');

        int currentWordLen = 0, uncommonLen = 0, maxWordLen = 0;

        for(String word: words){
            currentWordLen = word.length();

            if(currentWordLen <= letterLen){
                uncommonLen = currentWordLen - countCommon(word, currentWordLen, map);

                if(uncommonLen <= starCount && currentWordLen > maxWordLen){
                    maxWordLen = currentWordLen;
                }
            }
        }

        return maxWordLen;
    }
}
