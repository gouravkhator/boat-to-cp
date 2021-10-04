import java.util.*;
import java.util.stream.Collectors;

class ConcatStringsLongestUnique{
    /*
    Question:

    Given an array A of strings, calculate the length of the longest string S such that:
    a) S is a concatenation of some of strings from array A.
    b) every letter in S is different.

    If no such string exists, return 0.
    Given length of Array A is N, where N is in range [1..8]

    Ex-
    A = ["jan", "eva", "tyn", "potato", "jqw"]

    Answer is 9, as evatynjqw can be one of the longest strings having no duplicate characters.
    */

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("jan");
        list.add("eva");
        list.add("tyn");
        list.add("potato");
        list.add("jqw");
        System.out.println(solve(list));
    }

    public static Set<Character> getSetFromStr(String s) {
        return s.chars().mapToObj(e -> (char)e).collect(Collectors.toSet());
    }

    public static int calcMaxLen(List<String> mainStrs, int cIndex, int n, String concatStr) {

        if(cIndex >= n){
            return concatStr.length();
            // if we reach beyond mainStrs size,
            // just return concatStr's length which is the valid concatenated string's length.
        }

        String currentStr = mainStrs.get(cIndex);

        String tempStr = concatStr + currentStr;
        Set<Character> tempSet = getSetFromStr(tempStr);

        int max = 0;

        // for inclusion of currentStr, this check is important
        if(tempSet.size() == tempStr.length()){
            // if after including current string in previously concatenated string, we get that the resultant string has no duplicate characters,
            // then include this string for next recursive call.
            int res1 = calcMaxLen(mainStrs, cIndex + 1, n, tempStr);

            max = max > res1? max: res1;
        }

        int res2 = calcMaxLen(mainStrs, cIndex + 1, n, concatStr); // excluding currentStr and recurring
        max = max > res2? max: res2;
        
        return max; // return the max length
    }

    public static int solve(List<String> list) {
        /*
        Logic:

        First remove all strings which are themselves not unique (means they have duplicate characters).
        This can be found by calculating set of string and checking if size of set and of string is same or not,
        If found same, then don't add it to mainStrs list.

        Next, the recursion logic is used.

        Main logic is each valid string from the mainStrs list, can be present or absent in the resultant string.
        So, at each time, just recur for these two cases.

        But, if the string, made so far from concatenation of previous strings, is having some characters common with current string,
        then current string is not included in the resultant string for next recusive call.
        */

        List<String> mainStrs = new ArrayList<>();

        for (String string : list) {
            Set<Character> set = getSetFromStr(string);

            if(set.size() == string.length()){
                // if string has unique characters, then add it to mainStrs list
                mainStrs.add(string);
            }
        }

        return calcMaxLen(mainStrs, 0, mainStrs.size(), "");
    }
}
