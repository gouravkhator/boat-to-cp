class RotateImage {
  public int[] getResultantCell(int row, int col, int len){
    int[] cellPosition = new int[2];
    
    cellPosition[0] = col;
    cellPosition[1] = len - 1 - row;
    
    return cellPosition;
  }
  
  public void rotateRecur(int[][] matrix, int[] cellPos, int visitedCornerCount){
    if(visitedCornerCount == 4){
      // when I again reach the starting point of this recursion, after visiting 4 corners fully with visitedCornerCount initially as 0, then I get visitedCornerCount as 4.
      return;
    }
    
    int len = matrix.length;
    int row = cellPos[0], col = cellPos[1];
    int currentVal = matrix[row][col];
    
    // get the next cell position, where this currentVal should be put after the recursion is backtracked.
    int[] nextCellPos = getResultantCell(row, col, len);
    
    rotateRecur(matrix, nextCellPos, visitedCornerCount + 1);
    
    matrix[nextCellPos[0]][nextCellPos[1]] = currentVal;
  }
  
  public void printMatrix(int[][] matrix){
    int len = matrix.length;
    for(int k=0; k<len; k++){
      for(int l=0; l<len; l++){
        System.out.print(matrix[k][l] + ", ");
      }

      System.out.println();
    }

    System.out.println();
  }
  
  // Problem Question: https://leetcode.com/problems/rotate-image/
  public void rotate(int[][] matrix) {
    /*
Test case:
[[1,2],[3,4]]
[[1,2,3],[4,5,6],[7,8,9]]
[[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
[[2,29,20,26,16,28],[12,27,9,25,13,21],[32,33,32,2,28,14],[13,14,32,27,22,26],[33,1,20,7,21,7],[4,24,1,6,32,34]]
[[32,2],[32,27]]
    */
    
    /*
    Approach:
    ---------
    
    We could use another 2-D array and then copy the elemnents in their perfect rotated position, and then copy the elements of that 2-D array to this matrix variable.
    
    But we are told not to use another 2-D array.
    
    We see some patterns, as to where each element will be dumped into.
    For example for matrix = 
    1, 2, 3, 4
    5, 6, 7, 8
    9, 10, 11, 12
    13, 14, 15, 16
    
    Here, we have to put element 1 to position of element 4, but before that overwriting, we have to put element 4 to position of element 16, and before that, we have to put element 16 in position of 13, and before that put 13 in position of 1.
    
    And this way, we also keep track of what values to put in the next cell.
    
    Once, we visit the 4 corners, we say that this recursive call is done.
    Now, we try to rotate the next elements.
    
    From my pattern observation, I noticed that we have to recursively rotate for certain elements only, and if we do recursive rotations for all elements, then we reach the same state as the matrix.
    
    ---------------------------------------------------
    
    TC: O((N^2) * 4) (as worst case algorithmic complexity is approximated to be N^2, and 4 is for rotateRecur method, visiting 4 corners only).
    
    SC: O(1)
    */
    int len = matrix.length;

    for(int i=0; i<=(len/2) - 1; i++){
      for(int j=i; j<=(len - 2 - i); j++){
        // visited corner count is initially taken as 0, with the first corner cell visited just now as the start of this recursion
        rotateRecur(matrix, new int[]{i,j}, 0);        
      }
    }
  }
}