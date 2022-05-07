class ContainerWithMostWater {
  // Problem Question: https://leetcode.com/problems/container-with-most-water/
  public int maxArea(int[] height) { 
    /*
    Approach:

    As we knew from the problem, that we need to find the max area meaning the maximum height we can take farthest from each other..
    Those heights may not the maximum of all elements, but they will contribute to the maximum area.

    For this, we need 2 pointer approach.
    Now, for the two pointer approach, we would check whose height is lesser now, amongst the left and right,
    and then just that element will be excluded for further iterations.

    (Why we check for lesser heights? It is because if some height is less, then that will probably not give maximum area)
    (So, we just calculate their area now, and then exclude them for further iterations,
    meaning if left's height is smaller, then left pointer is incremented by 1,
    else if right's height is smaller, then right pointer is decremented by 1)
    */
      int maxArea = -1, currentArea = 0;
      int left = 0, right = height.length - 1, dist = 0, minHeight=0;

      while(left <= right){
          dist = right - left; // distance calculation

          if(height[left] < height[right]){
              minHeight = height[left];
              left++;
          }else{
              minHeight = height[right];
              right--;
          }

          currentArea = dist * minHeight;
          if(currentArea > maxArea)
              maxArea = currentArea;
      }

      return maxArea;
  }
}