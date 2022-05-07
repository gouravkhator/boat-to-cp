class JumpGame {
  // Problem question: https://leetcode.com/problems/jump-game/
  public boolean canJump(int[] nums) {
    int len = nums.length;
    boolean[] jumps = new boolean[len];
    /*
    Logic:
    jumps is an array in which an element at index i tells if we can reach ith index or not.

    For solving this problem,
    To reach last element, we maintain a jumps array so that we know that:
    if there is a path from previous elements to last element or not.

    If I want to reach ith index, and if I can reach index j which is less than i,
    and from j, I can jump to i, then I can reach i and so set jumps[i] = true.
    */
    jumps[0] = true; // can reach 0th index from 0th index itself so set to true
    
    for(int i=1; i<len; i++){
      jumps[i] = false; // assume that we cannot reach ith index
    
      for(int j=0; j<i; j++){
        if(jumps[j] == true && j+nums[j] >= i){
            // if reaching jth index is possible and from j to i is also possible,
          // then set jumps[i] to true
          jumps[i] = true;
          break; // as if we can reach ith index from j, we don't want to check any other paths
        }
      }
    }
    
    return jumps[len-1];
  }
}