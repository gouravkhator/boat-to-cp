# Travelling Salesman Problem

## Code (in C):

```c
#include<stdio.h>

int tsp(int mask,int pos,int n,int dist[][n],int dp[][n]){
  int VISITED_ALL = (1 << n) - 1;

  if(mask==VISITED_ALL){
    return dist[pos][source];
  }

  if(dp[mask][pos]!=-1){
    return dp[mask][pos];
  }

  int min = 99999, city;

  for(city=0; city<n; city++){
    if((mask&(1<<city))==0){
      int newAns = dist[pos][city] + tsp(mask|(1<<city),city,n,dist,dp);
      if(min>newAns){
        min = newAns;
      }
    }
  }

  return dp[mask][pos] = min;
}

int main(){
  int n=4,i,j, dp[16][4];

  int dist[4][4] = {
    {0,20,42,25},
    {20,0,30,34},
    {42,30,0,10},
    {25,34,10,0}
  };

  for(i=0; i<(1<<n); i++){
    // (2^n) * n matrix for memoization
    for(j=0; j<n; j++){
      dp[i][j] = -1;
    }
  }

  int mask = 1;
  printf("\nMin weight is %d\n", tsp(mask, 0, n, dist, dp));

  return 0;
}
```

## Notes

- (2^n) for 0000 to 1111 for mask(4 vertices) so 2^n for n vertices and pos is from 0 to n-1 ( for each of 2^n, n vertices are in each row).

- For saving distinct states of mask we need dp.

- The above approach has atmost n\*(2^n) subproblems and each subproblem is saved in dp, and for calculating that each recursive call also has a for loop for all cities which is O(n).

  - So time complexity is `O((n^2)*(2^n))`.

- Check [this link](https://www.geeksforgeeks.org/travelling-salesman-problem-set-1/) of gfg article for more details:

- Youtube Video for more explanation on the approach is [here](https://youtu.be/JE0JE8ce1V0).
