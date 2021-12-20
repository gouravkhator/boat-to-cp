import java.util.*;

class BomberMan {
  // Problem Question: https://binarysearch.com/problems/Bomber-Man
  public int solve(int[][] matrix) {
    /*
    Logic:
    If a cell is 1, then store its row in danger rows set, also store its column in danger cols set.
    Now, just traverse the matrix once again and if neither cell's row nor column is danger, we count that cell as safe cell.
    */

    if (matrix.length < 1) {
      return 0;
    }

    HashSet<Integer> dangerRows = new HashSet<>();
    HashSet<Integer> dangerCols = new HashSet<>();

    int rows = matrix.length, cols = matrix[0].length;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (matrix[i][j] == 1) {
          dangerRows.add(i);
          dangerCols.add(j);
        }
      }
    }

    int count = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (!dangerRows.contains(i) && !dangerCols.contains(j)) {
          count++;
        }
      }
    }

    return count;
  }
}
