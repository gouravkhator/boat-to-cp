class ToeplitzMatrix {
  // Problem Question: https://binarysearch.com/problems/Toeplitz-Matrix
  public boolean solve(int[][] matrix) {
    /*
     * Logic:
     * 
     * Main logic is to fix the starting position for a diagonal and traverse towards bottom-right for each diagonal.
     * We use the first row's elements and the first column's elements as starting position for the diagonals.
     * 
     * As we know that the top rightmost element is a single element diagonal, and similar is for bottom leftmost element.
     * So, we skip checking for those.
     * 
     * If the element in the diagonal does not match the starting element of that diagonal, we return false from the whole function.
     * If we reach the end of the diagonal, we break from that inner while loop, so that we can traverse other diagonals.
     */

    int rows = matrix.length;

    if (rows == 0) {
      return true;
    }

    int cols = matrix[0].length, k = 0;

    for (k = cols - 2; k >= 0; k--) {
      // traverse first row and skip the top-rightmost element (present in the first row itself)
      int elem = matrix[0][k]; // starting element of the diagonal

      int tempi = 0, tempj = k;

      // check in bottom-right side
      while (true) {
        // incrementing i and j will mean going bottom-right
        tempi++;
        tempj++;

        if (tempi < rows && tempj < cols && matrix[tempi][tempj] != elem) {
          // if the diagonal's element does not match with the diagonal starting element, we return false
          return false;
        }

        // if we reached the end of the diagonal, we break this inner while loop, and we goto another diagonal.
        if (tempi == (rows - 1) || tempj == (cols - 1)) {
          break;
        }
      }
    }

    for (k = 1; k <= rows - 2; k++) {
      // traverse first column and skip the bottom-leftmost element (present in the first column itself)
      int elem = matrix[k][0];

      int tempi = k, tempj = 0;

      // check in bottom-right side
      while (true) {
        tempi++;
        tempj++;

        if (tempi < rows && tempj < cols && matrix[tempi][tempj] != elem) {
          return false;
        }

        if (tempi == (rows - 1) || tempj == (cols - 1)) {
          break;
        }
      }
    }

    return true;
  }
}
