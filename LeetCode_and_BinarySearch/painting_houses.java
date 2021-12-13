class PaintingHouses {
  // Problem Question: https://binarysearch.com/problems/Painting-Houses
  public int solve(int[][] matrix) {
    /*
     * Got a TLE from below old logic..
     * Old Logic:
     * 
     * Visiting a color for this fence, and then recursively checking and taking the
     * minimum cost from subsequent fences.
     * The main point is previous selected color should not be taken now.
     * 
     * Whether I take minimum costs also, then it will be greedy, and we will get
     * wrong answer.
     * We need to traverse every path,
     * as it may happen that the path may have lesser costs in further fences, which
     * minifies the global cost.
     */

    /*
     * Main Logic that worked:
     * 
     * We want to get minimum of the costs to color each one of the fences.
     * As per the question, we know that we need to exclude the same color for
     * previous fence, if we want to strictly paint current fence with that color.
     * 
     * It is similar to the question of "Max. sum taking non-adjacent numbers".
     * fences in the rows and colors in the columns..
     * Ex- If number of colors is 3, then let's say it is r, g and b. Fences are
     * named as f1 to f5 for example.
     *    r g b
     * f1 1 2 3
     * f2 4 1 8
     * f3 2 3 4
     * f4 3 3 1
     * f5 4 2 3
     * 
     * The processing we have done (as will be explained below):
     *    r g b
     * f1 1 2 3
     * f2 6 2 9
     * f3 4 9 6
     * f4 9 7 5
     * f5 9 7 10
     * 
     * Answer is min(row of fence 5) that is min(9,7,10) that is 7.
     * 
     * For f1, we can color it red in cost 1. Similarly we can do that for all
     * colors for f1.
     * For f2, to paint it only red (strictly), the cost is (paint f2 with red) +
     * (paint fences till f1, with fence f1 painted with colors other than red)
     * So, min. cost for painting other fences till f1, with f1 not painted with red
     * = 2 (minimum of matrix[f1][g] and matrix[f1][b])
     * 
     * So, for f2 red, min cost is 4 + 2 = 6
     * (it means painting f2 strictly with red and other fences before f2, with the
     * rule that adjacent ones should not be painted with same color)
     * 
     * For f2 green, min cost is 1 + (cost of painting f1 with red or blue) = 1 + 1
     * = 2
     * Similarly, fill other cells.
     * 
     * For f3 green, it means cost is the cost of painting f3 with green + cost of
     * painting previous fences, following the rule mentioned.
     * 
     * Finally, at the last fence, we get the costs of painting that fence strictly
     * with the respective colors.
     * And now, we take minimum cost of them as the answer.
     * 
     * Intuition is that till this fence, we need to paint this fence with
     * respective color, so we should exclude this color for previous fence.
     * Each updated cell represents the cost of painting a fence with respective
     * color + costs of painting previous fences following the adjacent different
     * color rule).
     */

    if (matrix.length == 0) {
      return 0;
    }

    int currentMin = Integer.MAX_VALUE, prevMin = 0;
    int colorsCount = matrix[0].length, fencesCount = matrix.length;

    for (int i = 0; i < fencesCount; i++) {
      currentMin = Integer.MAX_VALUE;

      for (int j = 0; j < colorsCount; j++) {

        if (i > 0) {
          prevMin = Integer.MAX_VALUE;

          for (int k = 0; k < colorsCount; k++) {
            if (k != j) {
              // previous color to be different from current color 
              prevMin = prevMin > matrix[i - 1][k] ? matrix[i - 1][k] : prevMin;
            }
          }

          matrix[i][j] += prevMin;
        }

        currentMin = currentMin > matrix[i][j] ? matrix[i][j] : currentMin;
      }
    }

    return currentMin;
  }
}
