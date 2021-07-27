class JumpGame {
    // Problem question: https://leetcode.com/problems/jump-game-ii/
    public int jump(int[] nums) {
        int len = nums.length;
        int[] jumps = new int[len];
        
        jumps[0] = 0;
        
        // check min. jumps to reach each element
        for(int i=1; i<len; i++){
            jumps[i] = Integer.MAX_VALUE; // initialise jumps needed to reach i index
            
            for(int j=0; j<i; j++){
                if(j+nums[j] >= i){
                    // if from j index, I can jump to i index also
                    jumps[i] = Math.min(jumps[j]+1, jumps[i]);
                    // jumps needed to reach jth index + 1 to reach ith index from j
                    
                    // minimum is used to take min. of all feasible jumps
                }
            }
        }
        
        return jumps[len-1];
    }
}