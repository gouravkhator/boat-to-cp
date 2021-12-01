class CountSubstrAllOne {
  // Problem Question: https://binarysearch.com/problems/Count-Substrings-With-All-1s
  public int solve(String s) {
      /*
      By calculating number of substrings:
      I got a pattern, 
      First count number of 1's in a consecutive sequence. Then store it in count variable.
      In that sequence, number of substrings possible with all 1s are:
      count*(count+1)/2

      It is got from the fact that:
      Ex- If substring with consecutive 1's is of length 3
      Then, in that, 1-length substrings will be 3.
      2-length substrings will be 2. And 3-length substrings will be 1.

      For substrings with length n and consecutive 1s:
      Substrings count is (count of 1-length substrings) + (count of 2-length substrings) + ... + (count of n-length substrings)
      = n + (n-1) + ... + 1 = Sum of first n positive numbers = n*(n+1)/2

      Add this to sum.
      Now, traverse the other sequences also and add them to sum and return sum.
      While calculating, ensure to mod by 10**9 + 7 everytime we calculate count and sum.
      */
      
      final int MOD = (int)Math.pow(10, 9) + 7;
      int count=0, sum=0, n = s.length(), i=0;

      for(i=0; i<n; i++){
          if(s.charAt(i) == '0'){
              // at char 0, the sequence of 1's is broken, so calculate the no. of possible substrings and reset count
              sum += (count*(count+1)/2) % MOD;
              count = 0;
          }else{
              count++; // MOD was not needed here, the submission was successful without it also
          }
      }

      // if the count is remaining, it means ending sequence of 1's arrived
      if(count != 0)
          sum += (count*(count+1)/2) % MOD;

      return sum;
  }
}