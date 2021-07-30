class UniquePaths{
  //Problem Question: https://leetcode.com/problems/unique-paths/
  //Pattern recognised by Vanshika Khator (My sister)
  public static int uniquePaths(int m, int n){
    int dp[][] = new int[m+1][n+1];
    int i=0,j=0;
    /*
    Logic:
    dp is a matrix of order (m+1) * (n+1) just to keep 1-based indexing intact.
    A value at (x,y) in dp tells how many unique paths are possible to reach (x,y) from (1,1).

    The below pattern has been noticed by finding no. of unique paths for various (x,y).

    To reach i and j, the number of unique paths will be sum of number of unique paths coming from its upper cell and that from its left cell.
    (As only down and right directions are allowed, so the paths can reach the current cell from the current cell's left and current cell's up).
    */
    for(i=1;i<=m;i++){
      for(j=1;j<=n;j++){
        if(i==1 || j==1){
          dp[i][j] = 1;
        }else{
          dp[i][j] = dp[i][j-1] + dp[i-1][j];
        }
      }
    }
    return dp[m][n];
  }

  public static void main(String[] args) {
    System.out.println(uniquePaths(3, 7));
    System.out.println(uniquePaths(3, 2));
    System.out.println(uniquePaths(4, 3));
    System.out.println(uniquePaths(1, 1));
    System.out.println(uniquePaths(1, 3));
  }
}