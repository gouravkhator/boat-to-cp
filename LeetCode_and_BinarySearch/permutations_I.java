class PermutationsI {
  public void recur(int[] nums, List<Integer> visited, List<List<Integer>> res){
      // Approach is similar to combinations sum problem, or to permutations II problem.
      if(visited.size() == nums.length){
          res.add(new ArrayList<>(visited));
          return;
      }
      
      for(int i=0; i<nums.length; i++){
          if(!visited.contains(nums[i])){
              visited.add(nums[i]);
              
              recur(nums, visited, res);
              visited.remove(visited.size() - 1);
          }
      }
  }
  
  // Problem Question: https://leetcode.com/problems/permutations/
  public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> res = new ArrayList<>();
      
      recur(nums, new ArrayList<>(), res);
      return res;
  }
}