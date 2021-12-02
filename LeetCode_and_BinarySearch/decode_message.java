import java.util.HashMap;

class DecodeMessage {
  // Problem Question: https://binarysearch.com/problems/Decode-Message
  public static int solve(String message) {
    int len = message.length();
    if(len == 0){
      return 0; // if length is 0 then 0 is the answer
    }

    return helper(message, new HashMap<>());
  }

  public static int helper(String message, HashMap<String, Integer> map) {
    /*
    Logic:

    Core logic is to check 1 character and if it is valid (in range of 1 to 9), we recur for rest.
    Then check 2 characters at a time and if it is valid (in range of 10 to 26), we recur for rest.

    At last when we are left with 0 characters, it is sure that all were valid. So return 1 from there.
    In recursion, we add the results we got from single char recursion and double char recursion.

    For optimising and not calculating again, we memoize the results and keep it in hashmap.
    */
    
    int len = message.length();
    if(len == 0){
      return 1; // if length is 0, its due to the fact that previous string was 2 characters long and valid.
    }

    int oneChar = Integer.parseInt("" + message.charAt(0));
    int singleCharProb = 0, doubleCharProb = 0;

    if(oneChar >= 1 && oneChar <= 9){
      singleCharProb = 1;
    }

    if(len < 2){
      return singleCharProb;
    }

    int twoChars = Integer.parseInt("" + message.charAt(0) + message.charAt(1));
    if(twoChars >= 10 && twoChars <= 26){
      doubleCharProb = 1;
    }

    int resSingleChar = 0, resDoubleChar = 0;

    if(singleCharProb != 0){
      String temp = message.substring(1);
      if(temp.length() > 0 && map.containsKey(temp)){
        resSingleChar = map.get(temp);
      }
      else{
        resSingleChar = helper(temp, map);
        map.put(temp, resSingleChar);
      }
    }

    if(doubleCharProb != 0){
      String temp = message.substring(2);
      if(temp.length() > 0 && map.containsKey(temp)){
        resDoubleChar = map.get(temp);
      }
      else{
        resDoubleChar = helper(temp, map);
        map.put(temp, resDoubleChar);
      }
    }
    
    return resSingleChar + resDoubleChar;
  }

  public static void main(String[] args) {
    // Test cases
    System.out.println(solve("11"));
    System.out.println(solve("1110"));
    System.out.println(solve("602011"));
    System.out.println(solve("10115201"));
    System.out.println(solve("31542"));
    System.out.println(solve("31216"));
    System.out.println(solve("1241273"));
    System.out.println(solve("010029"));
    System.out.println(solve("0012"));
    System.out.println(solve("60"));
    System.out.println(solve(""));
  }
}
