class DecodeMessage {
  // Problem Question: https://binarysearch.com/problems/Decode-Message
  public static int solve(String message) {
    int len = message.length();
    if(len == 0){
      return 0; // if length is 0 then 0 is the answer
    }

    return helper(message);
  }

  public static int helper(String message) {
    /*
    Logic:

    Ex- "123106"
    Take 1st character and if its valid (in range of 1 to 9), then single character probability is 1 else 0.
    Take 1st 2 characters and if its valid (in range of 10 to 26), then double character probability is 1 else 0.

    If single character probability is 1, then we can recur for rest characters that is "23106".
    If double character probability is 1, then we can recur for rest characters that is "3106".
    Add both results up.

    Edge cases:
    If length is less than 2, and I cannot take 1st two characters, then return the single character probability (0 or 1).
    If length is 0, then return 1 (1 case as explained just below)
    (length can only become 0 when we take two characters at a time and recur for rest which is "", empty).
    
    (when I have atleast 2 characters in current recursive iteration, I can take single character in that iteration, 
    and recur for rest which is obviously 1 character long, in the next iteration.

    When I have 1 character in this iteration, I will check and return from there,
    and never recur again for rest which is "", empty).
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
    if(singleCharProb != 0)
      resSingleChar = helper(message.substring(1));

    if(doubleCharProb != 0)
      resDoubleChar = helper(message.substring(2));
    
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
