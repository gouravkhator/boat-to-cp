import java.util.*;

class OutOfBoundaryPaths {

  final long MOD = (long) Math.pow(10, 9) + 7;

  class Triplet {

    private final int x, y, z;

    public Triplet(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    // it is required for hashmap internal comparisons
    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Triplet) {
        Triplet t = (Triplet) obj;

        return this.x == t.x && this.y == t.y && this.z == t.z;
      }

      return false;
    }

    @Override
    public int hashCode() {
      char[] res = (this.x + " " + this.y + " " + this.z).toCharArray();
      return Arrays.hashCode(res);
    }
  }

  public long recur(
    ArrayList<Integer> rootCell,
    int movesCount,
    HashMap<Triplet, Long> map,
    int m,
    int n,
    int maxMove
  ) {
    /*
    Approach:
    ------------
    
    As seen in class OutOfBoundaryPathsNoMemoize, where I have not memoized.
    For more details on the question, check the comments in the class OutOfBoundaryPathsNoMemoize.
    
    To use memoization for saving the count of out of boundary paths, we have to save the state for (cellRow, cellColumn, movesTillNow). And we do this with our custom Triplet.
    
    So, we do a DFS like approach, where we recur and calulcate for the children depth-wise, and not breadth-wise as we saw in OutOfBoundaryPathsNoMemoize class.
    
    This is such that, we can calculate the result of one of the children in a level, and then lookup that result for the same ask.
    
    So, if the state is (1,1,2), this means that we are at cell (1,1), and moveCount till now is 2. And we will calculate the out of boundary paths count from this to the level where we reached maxMove.
    And we will use this result, when we get the same state again, which is (1,1,2).
    */

    if (
      rootCell.get(0) < 0 ||
      rootCell.get(0) >= m ||
      rootCell.get(1) < 0 ||
      rootCell.get(1) >= n
    ) {
      return 1;
    }

    if (movesCount >= maxMove) {
      return 0;
    }

    Triplet tempTriplet = new Triplet(
      rootCell.get(0),
      rootCell.get(1),
      movesCount
    );

    if (map.containsKey(tempTriplet)) {
      return map.get(tempTriplet);
    }

    int row = rootCell.get(0), col = rootCell.get(1);

    ArrayList<Integer> rightCell = new ArrayList<>(Arrays.asList(row, col + 1));
    ArrayList<Integer> downCell = new ArrayList<>(Arrays.asList(row + 1, col));
    ArrayList<Integer> leftCell = new ArrayList<>(Arrays.asList(row, col - 1));
    ArrayList<Integer> upCell = new ArrayList<>(Arrays.asList(row - 1, col));

    // keeping the result in long format, and also doing mod after every addition..
    long result = (recur(rightCell, movesCount + 1, map, m, n, maxMove) % MOD);
    result += (recur(downCell, movesCount + 1, map, m, n, maxMove) % MOD);
    result += (recur(leftCell, movesCount + 1, map, m, n, maxMove) % MOD);
    result += (recur(upCell, movesCount + 1, map, m, n, maxMove) % MOD);

    map.put(tempTriplet, result);

    return result;
  }

  // Problem Question: https://leetcode.com/problems/out-of-boundary-paths/
  public int findPaths(
    int m,
    int n,
    int maxMove,
    int startRow,
    int startColumn
  ) {
    return (int) (
      recur(
        new ArrayList<>(Arrays.asList(startRow, startColumn)),
        0,
        new HashMap<>(),
        m,
        n,
        maxMove
      ) %
      MOD
    );
  }
}

// a non-optimal approach which does not use memoization, and this approach gives TLE for almost 50% of Leetcode hidden test cases
class OutOfBoundaryPathsNoMemoize {

  class MyInt {

    int count = 0;
  }

  // checks and updates the count of out of boundary cases, or the next level children
  public void checkNextCell(
    ArrayList<Integer> nextCell,
    int m,
    int n,
    MyInt result,
    Queue<ArrayList<Integer>> children
  ) {
    if (
      nextCell.get(0) < 0 ||
      nextCell.get(0) >= m ||
      nextCell.get(1) < 0 ||
      nextCell.get(1) >= n
    ) {
      result.count++;
    } else {
      children.add(nextCell);
    }
  }

  // Problem Question: https://leetcode.com/problems/out-of-boundary-paths/
  public int findPaths(
    int m,
    int n,
    int maxMove,
    int startRow,
    int startColumn
  ) {
    /*
    Approach:
    --------------
    
    This question told that we can move to any direction from a particular cell, irrespective of whether we have visited that before or not.
    
    We just need to count the out of boundary cases, when we are done with maxMove number of moves.
    
    So, I added the cell with start row and column, as the parent, and then looked for next cell in all four directions. Whenever I got the out of bound case, I incremented the count, or else I added that valid cell to the children.
    
    Once I completed the parents level in this imaginary tree, I made the children the new parents.
    
    And once I am done with one level, I increment the moves count, and check the moves count with maxMove. This is bcoz, I went from all parents to this level children in one move.
    
    But, as we can see that let's say, we start from (0,0) and in one move, we go to valid children (0,1) and (1,0), but from those two children, in the next move, we can go to (1,1) in two ways, once from (0,1) and another way from (1,0).
    
    We have to calculate for (1,1) with this many moves count twice, which we could have memoized and calculated for only once.
    
    The point is if we have reached (1,1) in the same number of moves, then the number of out of boundary cases after (1,1) to the maxMove level will be the same, for all the (1,1) with same number of moves till now.
  
    So, I approached it with somewhat different recursive way along with memoization, in the above class.
    */
    Queue<ArrayList<Integer>> parents = new LinkedList<>();
    Queue<ArrayList<Integer>> children = new LinkedList<>();

    MyInt result = new MyInt();
    int moveCount = 0;

    ArrayList<Integer> temp = new ArrayList<>(
      Arrays.asList(startRow, startColumn)
    );
    parents.add(temp);

    while (parents.size() > 0) {
      ArrayList<Integer> currCell = parents.poll();
      int row = currCell.get(0), col = currCell.get(1);

      ArrayList<Integer> rightCell = new ArrayList<>(
        Arrays.asList(row, col + 1)
      );
      ArrayList<Integer> downCell = new ArrayList<>(
        Arrays.asList(row + 1, col)
      );
      ArrayList<Integer> leftCell = new ArrayList<>(
        Arrays.asList(row, col - 1)
      );
      ArrayList<Integer> upCell = new ArrayList<>(Arrays.asList(row - 1, col));

      checkNextCell(rightCell, m, n, result, children);
      checkNextCell(downCell, m, n, result, children);
      checkNextCell(leftCell, m, n, result, children);
      checkNextCell(upCell, m, n, result, children);

      if (parents.size() == 0) {
        parents = children;
        children = new LinkedList<>();

        moveCount++;

        if (moveCount >= maxMove) {
          break;
        }
      }
    }

    return result.count;
  }
}
