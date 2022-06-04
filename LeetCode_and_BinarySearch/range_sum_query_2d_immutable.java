// Problem Question: https://leetcode.com/problems/range-sum-query-2d-immutable/
class NumMatrix {
  /*
  Test case:
["NumMatrix","sumRegion","sumRegion","sumRegion"]
[[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
["NumMatrix","sumRegion","sumRegion","sumRegion"]
[[[3,0,1],[5,6,3],[1,2,0]], [1,1,2,2], [0,1,2,1], [1,0,2,1]]
  */
  
  /*
  Approach:
  ------------
  
  As checked, we just expanded the logic of prefix sums in 1-D array to prefix sums in 2-D array.
  
  -----------------------------------------------------------------------------------------------
  
  Part 1 of the question || Calculation of Prefix Sum:
  ----------------------------------------------------
  
  For example: matrix is as follows:
  
  3,0,1
  8,14,18
  9,17,21
  
  The prefix sum should be such that sum at any cell (i,j) will have sum of elements till that row i, and column j.
  
  Prefix sum is as follows:
  3,3,4
  8,14,18
  9,17,21
  
  Logic for calculation of prefix sum:
  1. If it is the 0th row or 0th column, just take this current number and the prefix sum till previous row or previous col respectively.
  2. If it is non-zero row or column, then take this current number in the prefix sum + the prefix sum vertically above + prefix sum horizontally leftwards - prefix sum diagonally upwards
  
  Why we subtracted the prefix sum diagonally upwards?
  > As the the prefix sum vertically above + prefix sum horizontally leftwards already took care of the diagonal prefix sum twice, so we have to subtract one of the instance.
  
  It is similar to the analogy behind: P(A union B) = P(A) + P(B) - P(A intersection B)
  
  -----------------------------------------------------------------------------------------------

  Part 2 of the question || Lookup of Prefix Sum 2-D matrix and return the regional sum:
  -------------------------------------------------------------------------------------
  
  Logical steps:
  1. If starting row of the region is 0th, and we have some non-zero start column, then the sum of the region is:
      prefix sum in the cell at end_row,end_column - prefix sum at end_row,start_column - 1
  
    Analogy behind above:
    --------------------
    Starting row is 0th, and we have prefix sum at (end_row, end_col) containing sum till (end_row, end_column).
    We subtract the sum at (end_row, start_column - 1), which contains the sum from (0,0) to (end_row, start_column - 1). This way, we have sum from (0,start_column) to (end_row, end_column).
    
    Similarly, we also process for other zeroed starting row or column.
    
    The above is actually similar to what we do in prefix sum in 1-D.
    
  2. For non-zeroed starting row and column, I subtracted both the prefixSum[start_row - 1][end_col] - prefixSum[end_row][start_col - 1].
  But I also saw that one more prefix sum is subtracted twice, so I added up that prefix sum, which was prefixSum[start_row - 1][start_col - 1].
  
  ------------------------------------------------------------------------------------------------------------
  ------------------------------------------------------------------------------------------------------------
  
  TC: O(m*n), m is the number of rows and n is the number of columns of that matrix.
  (O(m*n) is used in the creation of prefix sum 2-d matrix)
  
  SC: O(m*n).
  
  [Mainly, here as the matrix was given in the params, and as we need to use the prefix sum 2-d array in the sumRegion method too, so we have to make another 2-d matrix to save the prefix sums.
  
  This is not needed if the matrix had been accessible to all the methods before, as matrix's elements once processed are not needed after we create the prefix sum.]
  */
  
  int[][] prefixSum;
  
  public NumMatrix(int[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    
    // instance variable prefixSum
    prefixSum = new int[m][n];
    
    for(int i=0; i<m; i++){
      for(int j=0; j<n; j++){
        if(i==0 && j>0){
          prefixSum[i][j] = matrix[i][j] + prefixSum[i][j-1];
        }else if(j==0 && i>0){
          prefixSum[i][j] = matrix[i][j] + prefixSum[i-1][j];
        }else if(i==0 && j==0){
          prefixSum[i][j] = matrix[i][j];
        }else {
          prefixSum[i][j] = matrix[i][j] + prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1];
        }
      }
    }
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    if(row1 == 0 && col1 == 0){
      return prefixSum[row2][col2];
    }
    
    if(row1 == 0 && col1 > 0){
      return prefixSum[row2][col2] - prefixSum[row2][col1 - 1];
    }
    
    if(row1 > 0 && col1 == 0){
      return prefixSum[row2][col2] - prefixSum[row1 - 1][col2];
    }
    
    return prefixSum[row2][col2] - prefixSum[row1 - 1][col2] - prefixSum[row2][col1 - 1] + prefixSum[row1 - 1][col1 - 1];
  }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */