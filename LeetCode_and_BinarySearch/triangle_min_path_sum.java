class TriangleMinPathSum {
  // Problem Question: https://leetcode.com/problems/triangle/
  public int minimumTotal(List<List<Integer>> triangle) {
    /*
    Analogy:
    ---------
    
    We cannot go via greedy approach, as that will give a wrong answer.
    And doing backtracking is exponential and the given constraints is that the number of rows will be at max 200, so that will give TLE when we do via backtracking.
    
    What we wanted is the minimum path sum at the last level.
    So, we could save the minimum path sum till the previous level and then check from the previous level's corresponding indices (from which we can reach the current level's index).
    
    Approach:
    -----------
    
    1. Maintain a reference of previous level list of elements.
    2. We then iterate through the rows (or levels) one by one, and in each level, we try to set the current index as the current element + previous level element.
    3. The current level element at jth index, can only come from previous level elements at jth index or (j-1)th index.
    4. If the current level element is at 0th index or the last index, then it will come only from the first or last of the previous level list.
    5. If the current level element is at any index from 1 to second last index, then it will consider the minimum of the previous level elements at jth index and (j-1)th index.
    6. Lastly, we update the reference of the previous level list to the current level list.
    7. We then check the minimum path sum out of lal the path sums, from the last level of the triangle.
    
    ---------------------------------------------------------------
    
    Time complexity: O(n^2), where n is the total number of rows
    Space complexity: O(1), as we only used the prevLevelElems as the reference variable, and we didn't use extra space, just the given triangle was only used for maintaining the minimum path sum. 
    */
    
    List<Integer> prevLevelElems = triangle.get(0); // constraint tells that min. row is 1, so we can get the 0th row elements
    
    int totalRows = triangle.size();
    
    for(int i=1; i<totalRows; i++){
      List<Integer> currLevelElems = triangle.get(i);
      
      int prevRowSize = prevLevelElems.size();
      int currRowSize = prevRowSize + 1;
      
      for(int j=0; j<currRowSize; j++){
        // we can come to jth index from prevLevelElems's jth index or (j-1)th index, if they exists, and we will take the minimum of those two only
        
        if(j == 0){
          currLevelElems.set(0, currLevelElems.get(0) + prevLevelElems.get(0));
        }else if(j == (currRowSize - 1)){
          currLevelElems.set(j, currLevelElems.get(j) + prevLevelElems.get(prevRowSize - 1));
        }else{
          int min = Math.min(prevLevelElems.get(j), prevLevelElems.get(j - 1));
          
          currLevelElems.set(j, currLevelElems.get(j) + min);
        }
      }
      
      prevLevelElems = currLevelElems;
    }
    
    int minPathSum = Integer.MAX_VALUE;
    
    for(Integer pathSum: triangle.get(totalRows - 1)){
      if(pathSum < minPathSum){
        minPathSum = pathSum;
      }
    }
    
    return minPathSum;
  }
}