import java.util.Arrays;

class MatchSticksToSquare {

  public boolean canMakeSquare(int[] matchsticks, int[] leftOvers, int index) {
    if (index < 0) {
      return true;
    }

    for (int i = 0; i < leftOvers.length; i++) {
      if (leftOvers[i] >= matchsticks[index]) {
        leftOvers[i] -= matchsticks[index];

        boolean flag = canMakeSquare(matchsticks, leftOvers, index - 1);

        if (flag == true) {
          return true;
        }

        leftOvers[i] += matchsticks[index];
      }
    }

    return false;
  }

  // Problem Question: https://leetcode.com/problems/matchsticks-to-square/
  public boolean makesquare(int[] matchsticks) {
    /*
     * As checked in the other solution given below, the time complexity becomes approx. O((2^n)^4)
     * where n is the number of matchsticks.
     *
     * But, the strategy we took there was to fill each side and then recur for next side.
     * And if needed, backtrack to previously filled side, and fill it with different combination of matchsticks.
     *
     * But, to optimize this approach, we have to think of newer approach.
     *
     * I saw a small analogy from Neetcode channel, and then thought of below most-optimal approach with my optimizations added..
     *
     * Approach:
     * ------------
     *
     * If we can fill each matchstick in the sides one by one, instead of filling the sides one by one.
     *
     * This way, we can bring down the time complexity to O(4^n), where for each matchstick, we have 4 routes, which are the 4 sides.
     *
     * 1. We just maintain an array of leftOvers determining the left over lengths.
     * 2. We then check the 1st matchstick in the 4 sides wherever valid, and then recur for the next matchstick to fill in the sides.
     * 3. This approach is a completely reverse-thought-process of the one implemented below with different strategy.
     * 4. To optimize this approach, we sorted the array of matchsticks in ascending order, and tried fetching them from the end
     * (To fill the sides, starting with the larger matchsticks to the smaller matchsticks)
     * 5. Some more optimizations were to pre-check before recursion,
     * if the sum of the matchstick lengths are not divisble by 4, then it will not be a square anytime.
     *
     * ---------------------------------------------------------------------
     *
     * Time complexity: O(4^n)
     * Space complexity: O(4) ~ O(1) approx.
     *
     */
    int perimeter = 0;

    for (int stickLen : matchsticks) {
      perimeter += stickLen;
    }

    if ((perimeter % 4) != 0) {
      return false;
    }

    int sideLen = perimeter / 4, totalSticks = matchsticks.length;

    int[] leftOvers = new int[4]; // leftover lengths of the 4 sides

    for (int i = 0; i < leftOvers.length; i++) {
      leftOvers[i] = sideLen;
    }

    Arrays.sort(matchsticks); // sorting it in ascending and would fetch matchsticks in descending order, meaning from the last to the first

    return canMakeSquare(matchsticks, leftOvers, totalSticks - 1);
  }
}

// This is another perspective of the technique, we can use to solve this problem, but it gives TLE for some of the cases
class MatchsticksSquareOtherLessOptimalTechnique {

  public boolean canMakeSquare(
    int[] matchsticks,
    int remainingSides,
    int remainingLen,
    int sideLen,
    boolean[] visited
  ) {
    if (remainingSides == 1) {
      return true;
    }

    if (remainingLen == 0) {
      return canMakeSquare(
        matchsticks,
        remainingSides - 1,
        sideLen,
        sideLen,
        visited
      );
    }

    for (int i = matchsticks.length - 1; i >= 0; i--) {
      if (matchsticks[i] <= remainingLen && visited[i] == false) {
        visited[i] = true;

        boolean flag = canMakeSquare(
          matchsticks,
          remainingSides,
          remainingLen - matchsticks[i],
          sideLen,
          visited
        );

        if (flag == true) {
          return true;
        }

        visited[i] = false;
      }
    }

    return false;
  }

  // Problem Question: https://leetcode.com/problems/matchsticks-to-square/
  public boolean makesquare(int[] matchsticks) {
    /*
     * This approach gives TLE on: [5,5,5,5,16,4,4,4,4,4,3,3,3,3,4], with 171/195 test cases passed.
     *
     * But this approach just tells us to first fill the first side,
     * then fill the second side with remaining matchsticks, then the third and the fourth.
     *
     * If at any side, if we don't find any valid matchsticks combination,
     * then we backtrack to invalidate the combination used in filling previous filled side.
     *
     * One optimization I thought was to sort the array, and traverse it from descending to ascending.
     * It will be helpful, bcoz we try to fill the sides with larger matchsticks first,
     * helping us to fill more and more sides quicker in most cases.
     * Then try to fill with smaller ones.
     *
     * But here the time complexity is around O((2^n)^4) as there are 2^n combinations to be checked at the max for each side.
     * And for each combination checked, we recur for the next side, and again check around 2^n combinations.
     *
     * Obviously, some of the values will be visited, and so we don't include them in the next recursions.
     *
     * But, this time complexity is way worse.
     */
    int perimeter = 0;

    for (int stickLen : matchsticks) {
      perimeter += stickLen;
    }

    if ((perimeter % 4) != 0) {
      return false;
    }

    int sideLen = perimeter / 4, totalSticks = matchsticks.length;
    boolean[] visited = new boolean[totalSticks];

    Arrays.sort(matchsticks);

    return canMakeSquare(matchsticks, 4, sideLen, sideLen, visited);
  }
}
