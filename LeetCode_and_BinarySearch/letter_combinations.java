import java.util.*;

class LetterCombinations {
    public void recur(String digits, HashMap<Character, String> map, int index, String prevStr, ArrayList<String> res){
        if(index >= digits.length()){
            res.add(prevStr);
            return;
        }
        
        String s = map.get(digits.charAt(index));
        
        for(int i=0; i<s.length(); i++){
            recur(digits, map, index + 1, prevStr + s.charAt(i), res);
        }
    }
    
    // Problem Question: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    public List<String> letterCombinations(String digits) {
        /*
        Logic:

        recur is a normal recursive function.

        First, we create a map from digits to letters.
        Then, the iteration we would have done, for each characters for that digit, loop through next characters of next digit and so on.

        This iteration is implemented by recursion, as we don't know for how many times we need the for loop, as it's dynamic.
        The leaf node or the base condition where recursion ends, appends the resultant string to res list.

        And we maintain the prevStr, and add current character to the prevStr and pass it to next recursive call. 
        */
        if(digits.length() == 0){
            return new ArrayList<>();
        }

        HashMap<Character, String> map = new HashMap<>();

		map.put('2', "abc");
		map.put('3', "def");
		map.put('4', "ghi");
		map.put('5', "jkl");
		map.put('6', "mno");
		map.put('7', "pqrs");
		map.put('8', "tuv");
		map.put('9', "wxyz");
        
        ArrayList<String> res = new ArrayList<>();
        recur(digits, map, 0, "", res);
        return res;
    }
}
