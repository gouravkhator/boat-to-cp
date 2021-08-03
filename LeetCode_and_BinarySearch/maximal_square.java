class MaximalSquare{
  // Problem Question: https://leetcode.com/problems/maximal-square/
  public static int maximalSquare(char[][] matrix) {
    /*
    I could have manipulated matrix 2-d array itself, 
    but if the matrix contained a square of side length 11, 
    then as matrix can only store character it will be difficult for me to store '11' in a single char.

    Also, storing 11 as just a int in the char matrix will work for given contraints,
    but leetcode told that the solution without using another matrix used more memory than using dp.

    Don't know why, as same manipulations were done there as well. So, using dp, a new matrix.

    Logic:
    Use a 2d array of int type, named dp
    Copy, matrix cell data as int in dp. 
    So, '1' in matrix[i][j] will be copied as 1 in dp[i][j].

    If (i,j) in dp is 0, then leave it as it is. (as 0 does not contribute to the square formation)
    If its the 1st row or 1st column, we leave as it is.
    (Bcoz, 1st row or 1st column itself cannot make a square of side length more than 1).

    Find maximum of the dp[i][j] value.

    If we didn't leave the cell as it is, then follow below steps:
    We check the minimum of up, left and upper-left diagonal cells, and set current dp cell value to min + 1.

    It's because the square can be formed from taking into account, upper cell, left cell and upleft diagonal cell.
    If up cell dp value is 1, left cell dp value is 2, upleft cell dp value is 1.
    then current cell will have dp value as min(1,2,1) + 1 = 1 + 1 = 2.

    This means, including current cell, we can have square of side length 2.
    Lastly, max * max is the square of the side length of maximal square.

    Example: matrix is given as below
    1 1 1 0
    1 1 1 1
    1 1 1 0

    dp formed is:
    1 1 1 0
    1 2 2 1
    1 2 3 0

    max is 3 in the whole matrix, so max*max = 9 is the square of maximal square of side length 3.
    */

    int m = matrix.length, n = matrix[0].length, i=0, j=0, max=0, tempMin = 0;
    int[][] dp = new int[m][n];

    for(i=0; i<m; i++){
      for(j=0; j<n; j++){
        dp[i][j] = matrix[i][j] - 48; // converting char to its equivalent int, stored inside single quotes

        if(dp[i][j] == 0 || i==0 || j==0){
          if(max < dp[i][j]){
            max = dp[i][j];
          }
          continue;
        }

        tempMin = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]);
        dp[i][j] = tempMin + 1;

        if(max < dp[i][j]){
          max = dp[i][j];
        }
      }
    }

    return max*max;
  }

  public static void main(String[] args) {
    // char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
    char[][] matrix = {
      {'1','1','1','0'},
      {'1','1','1','1'},
      {'1','1','1','0'},
    };
    System.out.println(maximalSquare(matrix));
  }
}