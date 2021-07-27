class LPS_LCS {
      public String longestCommonSubstr(String s1, String s2){
        int len1 = s1.length();
        int len2 = s2.length();

        int[][] dp = new int[len1][len2];

        for(int i=0;i<len1;i++){
          for(int j=0;j<len2;){
            if(s1.charAt(i) == s2.charAt(j)){
              dp[i][j] = 1;

              j++;
              for(int k=i+1; j<len2 && k<len1 && s1.charAt(k) == s2.charAt(j); k++, j++){
                dp[i][j] = dp[i][j-1] + 1;
              }

            }else{
              j++;
            }
          }
        }

        int maxVal = -1, finalJ=0;
        for(int i=0;i<len1;i++){
          for(int j=0;j<len2; j++){
            if(dp[i][j] > maxVal){
                String longestSubstr = s2.substring(Math.max(0, j+1-dp[i][j]), j+1);
              // check if longest substring got is actually palindrome or not
              // ex- aacabdkacaa this can have aaca as longest common in reversed and original string
              // aaca is not palindrome at all

              if(longestSubstr.equals(new StringBuffer(longestSubstr).reverse().toString())){
                maxVal = dp[i][j];
                finalJ = j;
              }
            }
          }
        }

        return s2.substring(Math.max(finalJ+1 - maxVal, 0), finalJ+1);
    }

    public String longestPalindrome(String s) {        
        int len = s.length();
        if(len==1){
            return s;
        }

        String reversed = (new StringBuffer(s)).reverse().toString();

        if(s.equals(reversed)){
          return s;
        }

        return longestCommonSubstr(s, reversed);
    }
}
