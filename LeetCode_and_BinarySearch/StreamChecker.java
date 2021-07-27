//https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/552/week-4-august-22nd-august-28th/3434/

//the code is somewhat giving wrong answer on some different input
import java.util.Set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class StreamChecker {

	public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] words = br.readLine().split(" ");
    	String[] letters = br.readLine().split(" ");
    	
    	StreamChecker obj = new StreamChecker(words);
    	for(String letter: letters) {
    		char c = letter.charAt(0);
    		boolean param_1 = obj.query(c);
    		System.out.println(param_1);
    	}
	}
    
    Set<Character> queriedSet = new HashSet<>();
    String combined = null;
    public StreamChecker(String[] words) {
        combined = String.join(",", words);
    }
    
    public boolean query(char letter) {
        if(!combined.contains(letter + "")){ //the letter is not in the list
            return false;
        }
        
        if(combined.contains(","+letter+",")){
            queriedSet.add(letter); 
            //so that if any letters after this letter is also in word list, then I can return true for that query
            return true;
        }
        
        Pattern pattern = Pattern.compile("[a-z]+"+letter+",?"); 
        //this would find pattern of some characters before letter and after that letter maybe "," or nothing
        //no char after letter means that word is last in the list
        Matcher matcher = pattern.matcher(combined);
        queriedSet.add(letter); //add the letter to queriedSet
        
        if (matcher.find()) { //if any pattern is found then check the characters are queried before or not
            boolean flag = true;
            char[] temp = combined.substring(matcher.start(), matcher.end()-1).toCharArray();
            for(char c: temp) {
                if(!queriedSet.contains(c)){
                    flag = false;
                    break;
                }
            }
            
            if(flag){ //means all characters before letter is queried before so return true
                return true;
            }
        }
        return false;
    }
}
