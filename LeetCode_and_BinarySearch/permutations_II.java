class PermutationsII {
  public void permutationRecur(int[] nums, List<Integer> visitedIndices, Set<List<Integer>> result){
    /*
    Approach:
    ---------
    
    Just have the visitedIndices list, and then take the 1st index and then recur for rest.
    Then, take the second index in a different permutation path, and recur for unvisited indices, and so on.
    We take the result in a set of list, so that duplicate permutations can be avoided.
    visitedIndices are taken as list, so that we can just make the list of values out of it, and then add that list to the result.
    
    This approach works fine but we are also told that there are duplicates in the array. 
    So, this means that at the same recursion level, we might have to traverse the same paths more than once, and that is of no use.
    ---------------------------------------------------------------------------------
    
    Meaning, for example: nums = [1,1,2]
    
    We take 1st index with value 1, then we recur for unvisited indices with values 1 and 2.
    Now, once this recursion is done, we backtrack and then start with index 2 with value 1 again.
    And as we can see, if we start with same value, we end up with same path. So, we should avoid traversing same path again.
    
    We cannot ignore those duplicate values overall, as those values will be used in making the permutation in the whole recursion process.
    
    At same recursion level, we can track the currentVisitedVals, and then if this value is already visited in this level, then we don't visit them again.
    */
    
    int len = nums.length;
    
    if(visitedIndices.size() == len){
      List<Integer> valuesList = new ArrayList<>();
      
      for(Integer index: visitedIndices){
        valuesList.add(nums[index]);
      }
      
      result.add(valuesList);
      return;
    }
    
    Set<Integer> currentVisitedVals = new HashSet<>();
    
    for(int i=0; i<len; i++){
      if(currentVisitedVals.contains(nums[i]) || visitedIndices.contains(i)){
        /*
        currentVisitedVals tracks the values at one level of recursion, 
        meaning if this value is already visited from the array elements in this path, then we don't visit it again.
        -----------------------------------------------------------------------------
        visitedIndices tracks the actual path and only allow the unvisited indices to be added in the path to make the permutation happen.
        */
        continue;
      }
      
      currentVisitedVals.add(nums[i]); // to not go through same value paths again
      
      visitedIndices.add(i);
      permutationRecur(nums, visitedIndices, result);
      visitedIndices.remove(Integer.valueOf(i));
    }
  }
  
  // Problem Question: https://leetcode.com/problems/permutations-ii/
  public List<List<Integer>> permuteUnique(int[] nums) {
    Set<List<Integer>> permutationsList = new HashSet<>();
    
    permutationRecur(nums, new ArrayList<>(), permutationsList);
    return new ArrayList<>(permutationsList);
  }
}