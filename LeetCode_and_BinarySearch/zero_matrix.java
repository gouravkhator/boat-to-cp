import java.util.*;

class ZeroMatrix {
  // Problem Question: https://binarysearch.com/problems/Zero-Matrix
  public int[][] solve(int[][] matrix) {
    /*
    Logic:
    If a cell is 0, then store its row in infected rows set, also store its column in infected cols set.
    Now, just traverse the matrix once again and if a cell's row or col is infected, then make that cell 0.
    */

    if (matrix.length < 1) {
      return matrix;
    }

    HashSet<Integer> infectedRows = new HashSet<>();
    HashSet<Integer> infectedCols = new HashSet<>();

    int rows = matrix.length, cols = matrix[0].length;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (matrix[i][j] == 0) {
          infectedRows.add(i);
          infectedCols.add(j);
        }
      }
    }

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (infectedRows.contains(i) || infectedCols.contains(j)) {
          matrix[i][j] = 0;
        }
      }
    }

    return matrix;
  }
}
