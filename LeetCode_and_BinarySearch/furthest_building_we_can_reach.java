import java.util.*;

class FurthestBuildingReachable {

  // Problem Question: https://leetcode.com/problems/furthest-building-you-can-reach/
  public int furthestBuilding(int[] heights, int bricks, int ladders) {
    /*
     * Analogy:
     * ---------
     *
     * To get to the furthest building, we should use the ladders in the longest jumps needed from lower height to higher height.
     * And when the ladders get exhausted, we should use the bricks if possible.
     * If neither ladders nor bricks can be used, we will return that index as the furthest we can reach.
     *
     * Approach:
     * -----------
     *
     * Let ladders be denoted here by l.
     *
     * Firstly, to track the l number of largest jumps till now, we have to use priority queue of capacity l.
     * But in java, we cannot have priority queue of capacity 0, and if l is 0, then we should check it in other block.
     *
     * If number of ladders provided is 0, then we only use the bricks if possible.
     *
     * For this case, we follow below steps:
     * 1. If height at ith index is lower than height at i+1, then we have to use the bricks here.
     * 2. If available bricks is greater than or equal to the height difference,
     * then we consider that number of bricks required and subtract it from available bricks.
     * 3. If we don't have that much available bricks, we break from the loop, and return the index i (the lower index, as we are unable to jump to higher index i+1).
     * 4. If height at ith index is greater than or equals height at i+1, we just continue, as no operation needs to be done.
     *
     * We now return the index i.
     *
     * For the case when the number of ladders is positive in number, we can use the ladders for largest jumps and bricks otherwise.
     * The steps are as follows:
     * 1. If height at ith index is greater than or equals height at i+1, we just continue, as no operation needs to be done.
     * 2. If height at ith index is lower than height at i+1, we have to follow further steps.
     * 3. We check if the min heap (for tracking l largest jumps) is not full, then we can use ladders for this jump.
     * 4. If min heap is full, then follow the substeps:
     *  a. We check if current min element (the minimum of l largest jumps till now) is really to be removed from the newly arrived height difference.
     *  b. If yes, then we have to remove this current min element from the min heap, and consider this for the bricks.
     *  c. If no, we have to consider the newly arrived height for the bricks.
     * 5. For considering anything for bricks, we check if that is less than or equals available bricks, then we count that in bricks else we break.
     * Breaking from loop means that we cannot jump to higher height now, as the bricks and ladders are all exhausted.
     *
     * We return the index i, after the loop.
     *
     * ------------------------------------------------------------------------------
     *
     * l is the number of ladders provided, and n is the length of heights array
     * Time complexity: O(n log l)
     * Space complexity: O(l), as we use the min heap for tracking l largest jumps till current index.
     */

    int len = heights.length, index = 0;

    if (ladders == 0) {
      for (index = 0; index <= (len - 2); index++) {
        if (heights[index] < heights[index + 1]) {
          int diffInHeight = heights[index + 1] - heights[index];

          // consider the diffInHeight in the bricks if possible
          if (diffInHeight <= bricks) {
            bricks -= diffInHeight;
          } else {
            break;
          }
        } else {
          continue;
        }
      }

      return index;
    }

    PriorityQueue<Integer> largestJumpsHeap = new PriorityQueue<>(ladders); // min heap of ladders number of largest elements

    for (index = 0; index <= (len - 2); index++) {
      if (heights[index] < heights[index + 1]) {
        int diffInHeight = heights[index + 1] - heights[index];

        if (largestJumpsHeap.size() < ladders) {
          // if we have not exhausted the ladders yet, we can consider the ladder itself..
          largestJumpsHeap.add(Integer.valueOf(diffInHeight));
        } else {
          int currMinElement = largestJumpsHeap.peek();

          if (currMinElement < diffInHeight) {
            /**
             * if current minimum element is less than the difference in height,
             * we consider the current minimum element for the bricks and not for the ladders..
             *
             * It is bcoz, there are larger elements than this current min element,
             * which is much more eligible for using ladders.
             */
            if (currMinElement <= bricks) {
              // consider this in bricks, and remove it from the min heap of largest jumps using ladders
              bricks -= currMinElement;
              largestJumpsHeap.remove();
              largestJumpsHeap.add(Integer.valueOf(diffInHeight));
            } else {
              // not even eligible for available bricks, then we break the loop, as we cannot jump to (index+1)
              break;
            }
          } else {
            // consider the diffInHeight in the bricks if possible
            if (diffInHeight <= bricks) {
              bricks -= diffInHeight;
            } else {
              break;
            }
          }
        }
      } else {
        continue;
      }
    }

    return index;
  }
}
