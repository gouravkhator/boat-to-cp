import java.util.ArrayList;
import java.util.List;

class SpiralMatrix {
  // Problem Question: https://leetcode.com/problems/spiral-matrix/
  public List<Integer> spiralOrder(int[][] matrix) {
    /**
     * Approach:
     * 
     * First traverse first row, then the last column, then last row, then firts
     * column.
     * 
     * But, for manipulating the row start and end and column start and end,
     * we used 4 variables for startRow, startCol, endRow, endCol.
     * 
     * And, once we traverse the first row, we increment startRow by 1.
     * Similarly for all other variables.
     * We traverse last column, so decrement endCol by 1,
     * as now, end column should not be traversed again.
     * 
     * When we increment or decrement, those col or row variables might be out of
     * their range.
     * So, just break the loop then.
     * 
     * ---------------------------------------------------------------------
     * Time complexity: O(m*n)
     * Space complexity: O(1) extra space
     */
    int rows = matrix.length, cols = matrix[0].length;
    List<Integer> spiralOrderList = new ArrayList<>();
    int startRow = 0, startCol = 0, endRow = rows - 1, endCol = cols - 1;

    int i, j;

    while (true) {
      // first row
      for (j = startCol; j <= endCol; j++) {
        spiralOrderList.add(matrix[startRow][j]);
      }

      startRow++;

      if (startRow > endRow) {
        break;
      }

      // last column
      for (i = startRow; i <= endRow; i++) {
        spiralOrderList.add(matrix[i][endCol]);
      }

      endCol--;

      if (endCol < startCol) {
        break;
      }

      // last row
      for (j = endCol; j >= startCol; j--) {
        spiralOrderList.add(matrix[endRow][j]);
      }

      endRow--;

      if (endRow < startRow) {
        break;
      }

      // start column
      for (i = endRow; i >= startRow; i--) {
        spiralOrderList.add(matrix[i][startCol]);
      }

      startCol++;

      if (startCol > endCol) {
        break;
      }
    }

    return spiralOrderList;
  }

  public static void main(String[] args) {
    SpiralMatrix obj = new SpiralMatrix();

    int[][] matrix = new int[][] {
        { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }
    };

    printList(obj.spiralOrder(matrix));
  }

  public static void printList(List<Integer> list) {
    for (int i = 0; i < list.size(); i++) {
      System.out.print(list.get(i) + ",");
    }
  }
}