class NQueensValidLayouts {
  public boolean isSafe(List<String> prevRowsLayout, int row, int col, int n){
    // check vertically up, before this current row
    for(int i=0; i<row; i++){
      char c = prevRowsLayout.get(i).charAt(col);
      
      if(c == 'Q'){
        return false;
      }
    }
    
    // check the upper left diagonal, before this current row
    for(int i=row - 1, j=col - 1; i>=0 && j>=0; i--, j--){
      char c = prevRowsLayout.get(i).charAt(j);
      
      if(c == 'Q'){
        return false;
      }
    }
    
    // check the upper right diagonal, before this current row
    for(int i=row - 1, j=col + 1; i>=0 && j<n; i--, j++){
      char c = prevRowsLayout.get(i).charAt(j);
      
      if(c == 'Q'){
        return false;
      }
    }
    
    return true;
  }
  
  public void placeNQueensRecur(int n, int row, List<String> currLayout, List<List<String>> resLayouts){
    /*
    Approach:
    -----------
    
    If the placed queens (denoted by currLayout) before this row, is working safe with the current to-be-placed at "row" and "i", then we can place that up and then recur for next rows.
    
    Whether or not the recursion works, we remove the currently placed queen from the currLayout list.
    
    If we go to the end row, and we have the valid layout before this row, then just add the currLayout list to the resLayouts.
    
    Then, we backtrack and we try other positions for the queen.
    
    --------------------------------------------------------------------------------
    
    T(N) = N*(T(N-1) + O(N)) [Meaning, N positions are there, and for each we have N-1 subproblems, and each subproblem also has this isSafe method which is of O(N) complexity].
    
    T(N) = N*T(N-1) + O(N*N) ~ Making a time complexity of O(N!) at the last.
    
    Amortized Time Complexity: O(N!), Since we have N choices in the first row, then N-1 choices in the second row and so on so the overall complexity become O(N!).
    Space Complexity: O(N + stack space), O(N) for the currLayout saving, which is the only extra space.
    resLayouts is not the extra space as it contains the result, we have to store in.
    */
    
    for(int i=0; i<n; i++){      
      if(isSafe(currLayout, row, i, n)){
        // checks if all the positions till this current row is valid or not.
        // currLayout only contains the layout of queens till previous row..
        
        String placedQueenStr = ".".repeat(i) + "Q" + ".".repeat(n - 1 - i);

        currLayout.add(placedQueenStr);
        
        if(row == (n - 1)){
          resLayouts.add(new ArrayList<>(currLayout));
        }else {
          placeNQueensRecur(n, row + 1, currLayout, resLayouts);
        }
        
        currLayout.remove(placedQueenStr);
      }
    }
  }
  
  // Problem Question: https://leetcode.com/problems/n-queens/
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> resLayouts = new ArrayList<>();
    
    placeNQueensRecur(n, 0, new ArrayList<>(), resLayouts);
    return resLayouts;
  }
}