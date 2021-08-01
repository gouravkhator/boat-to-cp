class MinPathSum{
  // Problem Question: https://leetcode.com/problems/minimum-path-sum/ 
  public static int minPathSum(int[][] grid){
    int m = grid.length;
    int n = grid[0].length;

    int i=0,j=0;
    /*
    Logic:
    grid is the given matrix. Don't take another matrix to save space and manipulate this grid matrix only.
    After all manipulations, a value at (x,y) in grid tells minimum path sum to reach (x,y) from (1,1).

    Ex-
    grid is initially given as:
    1 3 1
    1 5 1
    4 2 1

    After manipulations, grid becomes:
    1 4 5
    2 7 6
    6 8 7

    As only down and right directions are allowed, so to reach i and j:
    Minimum path sum for current cell = Minimum of modified cell's value of up and left cells of (i,j) + the current cell value.

    Means, to reach (i,j), take minimum of its up and left cell minimum path sum 
    and then add the current value to that minimum.

    For i=0 (first row), take the (min path sum of left cell value) + current cell value
    For j=0 (first column), take the (min path sum of up cell value) + current cell value

    */
    for(i=0; i<m; i++){
      for(j=0; j<n; j++){
        if(i==0 && j!=0){
          grid[i][j] += grid[i][j-1];
        }else if(i!=0 && j==0){
          grid[i][j] += grid[i-1][j];
        }else if(i!=0 && j!=0){
          grid[i][j] += (grid[i][j-1] < grid[i-1][j] ? grid[i][j-1]: grid[i-1][j]);
        }
      }
    }
    
    // return the bottom right value as the min. path sum to reach bottom right from top left
    return grid[m-1][n-1];
  }

  public static void main(String[] args) {
    int[][] grid= {{1,3,1}, {1,5,1},{4,2,1}};
    System.out.println(minPathSum(grid));
  }
}