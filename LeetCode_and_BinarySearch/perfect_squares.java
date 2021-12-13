import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class PerfectSquares {
  public int minSquares(int n, HashMap<Integer, Integer> map, ArrayList<Integer> squares, int squaresCount) {
    /*
    Logic:

    If the min no. of squares is saved before for getting sum=n, then return that number of squares from the map.

    Else, first get the location at which n is gonna be placed in squares list.
    Then, from that index to 0, we first count a square, and then recur this function for remaining number.
    
    Then, put the count of min no. of squares to get remaining number in the map.
    Current count is 1 + min no. of squares to get that remaining number.
    If this count is minimum, then set minCount to current count.
    */
    if(map.containsKey(n)){
      return map.get(n);
    }

    int index = Collections.binarySearch(squares, n);
    if(index < 0){
      index = -index - 2;
    }

    int i=0, temp = 0, count=0, minCount = Integer.MAX_VALUE;

    for(i=index; i>=0; i--){
      temp = minSquares(n - squares.get(i), map, squares, squaresCount);
      map.put(n - squares.get(i), temp);

      count = 1 + temp;
      if(count < minCount){
        minCount = count;
      }
    }

    map.put(n, minCount); // set minCount as the min no. of squares to get n
    return minCount;
  }

  // Question: https://binarysearch.com/problems/Perfect-Squares
  public int solve(int n) {
    ArrayList<Integer> squares = new ArrayList<>();
    HashMap<Integer, Integer> map = new HashMap<>();

    // squares <= n are saved
    for (int i = 1; i * i <= n; i++) {
      if (i * i == n) {
        // if n is itself a square
        return 1;
      }

      squares.add(i * i);
      map.put(i*i, 1); // for each square, we can make them using 1 element only (that is the square itself)
    }

    return minSquares(n, map, squares, squares.size());
  }
}
