class PaintingHouses {
  public int helper(int[][] matrix, int prevColorIndex, int fenceNumber, int currentCost) {
    /*
    Got a TLE from below approach..
    
    Logic:

    Visiting a color for this fence, and then recursively checking and taking the minimum cost from subsequent fences.
    The main point is previous selected color should not be taken now.

    Whether I take minimum costs also, then it will be greedy, and we will get wrong answer.
    We need to traverse every path,
    as it may happen that the path may have lesser costs in further fences, which minifies the global cost.
    */

    if (fenceNumber >= matrix.length) {
      return currentCost;
    }

    int min = Integer.MAX_VALUE;
    for (int color = 0; color < matrix[0].length; color++) {
      if (color == prevColorIndex) {
        continue;
      }

      int temp = helper(matrix, color, fenceNumber + 1, currentCost + matrix[fenceNumber][color]);
      min = min > temp ? temp : min;
    }

    return min;
  }

  // Problem Question: https://binarysearch.com/problems/Painting-Houses
  public int solve(int[][] matrix) {
    if (matrix.length == 0) {
      return 0;
    }

    return helper(matrix, -1, 0, 0);
  }
}
