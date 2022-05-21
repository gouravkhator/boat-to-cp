class TrappingRainwater {
  // Problem Question: https://leetcode.com/problems/trapping-rain-water/

  /**
   * Analogy:
   * 
   * For each block, what is the trapped rainwater?
   * Ex- the blocks have heights as [0,4,2,1,5,3,1,2,1]
   * 
   * For block indexed 1, the height is 4, and it cannot trap water.
   * For block indexed 2, the height is 2, it can trap water till level 4, meaning
   * trapped water is (4 - heightOfBlock) = 4 - 2 = 2
   * 
   * For block indexed 3, height is 1, water level will again be 4.
   * For block indexed 4, height is 5, water cannot be trapped.
   * So on and so forth.
   * 
   * Meaning, for each block, we have to find the maximum height in left and
   * maximum height in right.
   * And then to not let water spill, we have to take the minimum of those two.
   * This will give us the water level till which we can trap.
   * 
   * -------------------------------------------------------------------
   * -------------------------------------------------------------------
   * 
   * 1st Approach: (use DP to calculate leftMax and rightMax):
   * [ This 1st approach is coded in method "trapDPApproach" ]
   * 
   * -------------------------------------------------------------------
   * 
   * 2nd Approach (optimal approach using two pointers):
   * [ This 2nd approach is coded in method "trap" ]
   */

  // 1st approach: DP approach
  public int trapDPApproach(int[] height) {
    /**
     * 
     * Mainly we need the maximum before the current block from the left.
     * Also, we need to find the right maximum for this current block, which will be
     * from the next block to the extreme right..
     * 
     * leftMaximums[i] will be maximum element till this element (including this
     * element).
     * 
     * Then while traversing from right to left, we maintain a right max variable
     * and then check the minimum of the right maximum after ith element and
     * leftMaximums[i-1]
     * 
     * This is the water level for ith element,
     * and now just subtract this water level with height[i] to get the trapped
     * rain-water.
     * -----------------------------------------------------------------------
     * 
     * Time complexity is O(2*n) (I provided exact figure for time complexity).
     * Space complexity is O(n).
     */
    int len = height.length, trappedWater = 0;

    int[] leftMaximums = new int[len];
    leftMaximums[0] = height[0];

    for (int i = 1; i < len; i++) {
      // leftMaximums store maximum till the current elem to the left
      leftMaximums[i] = Math.max(height[i], leftMaximums[i - 1]);
    }

    int rightMax = height[len - 1];

    for (int i = len - 2; i >= 1; i--) {
      // for current elem, maximum to the left will be maximum stored in just previous
      // left maximum value
      int leftMax = leftMaximums[i - 1];

      int minHeight = Math.min(leftMax, rightMax);

      if (minHeight > height[i]) {
        trappedWater += minHeight - height[i];
      }

      // right max is firstly used to store max in the right, and then we use that
      // value for current elem, and then update the right max as per current elem
      if (height[i] > rightMax) {
        rightMax = height[i];
      }
    }

    return trappedWater;
  }

  // 2nd approach: Two pointer solution with Optimised space
  public int trap(int[] height) {
    /**
     * This approach is copied from leetcode discuss section, but it is tough to
     * understand the analogy fully.
     * 
     * The issue with 1st approach was we had to calculate mostly the same maximum
     * again and again.
     * 
     * So, we can use two pointers as we know we have to traverse from left to
     * right,
     * and from right to left.
     * 
     * Here, the leftMax is always updated to contain the maximum from 0 to left
     * pointer,
     * and rightMax is always updated to contain maximum from right pointer to
     * (len-1).
     * 
     * We always take preference on lower boundary,
     * meaning for left parts of the array, we take the trapped water as checked
     * from leftmax..
     * for right parts of array, we take the trapped water as checked from rightmax
     * 
     * ------------------------------------------------------------------
     * Time complexity: O(n)
     * Space complexity: O(1)
     */

    int len = height.length, trappedWater = 0;
    int left = 0, right = len - 1;
    int leftMax = height[left], rightMax = height[right];

    while (left <= right) {
      // updating the left and right maximums
      leftMax = Math.max(leftMax, height[left]);
      rightMax = Math.max(rightMax, height[right]);

      // if one of the max is less, then that will be taken into consideration
      if (leftMax < rightMax) {
        trappedWater += leftMax - height[left];
        left++;
      } else {
        trappedWater += rightMax - height[right];
        right--;
      }
    }

    return trappedWater;
  }
}