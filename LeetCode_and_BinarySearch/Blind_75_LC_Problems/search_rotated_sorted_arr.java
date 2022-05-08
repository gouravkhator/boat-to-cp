class SearchRotatedSortedArr {
  public int findPivotPoint(int[] nums, int low, int high) {
    /**
     * Find pivot point is to be done in O(log n) time complexity.
     * 
     * For log n, we have to either search in left half or right half, but how to calculate that?
     * 
     * We take a mid index, and the base condition should be:
     * 1. If mid's adjacent elements are only the ones having pivot..
     *  a. If mid's element is greater than element at (mid + 1), then we return index of pivot as mid+1.
     *  b. If mid's element is lesser than element at (mid - 1), then we return index of pivot as mid.
     * 2. How to recur for halves?
     *  a. If low's element is greater than mid's element, then we know that the pivot would be in left half.
     *  b. Otherwise, the pivot will be in right half.
     * 
     * Pivot point is at the starting point of next part of the array, which is already in ascending order.
     */

    // the base case as well the edge case
    if (low >= high) {
      /**
       * either single element in nums, for which low == high,
       * or either low > high, which means the array nums was fully in ascending order, or some other case,
       * which means return pivot as 0 index of array.
      */
      return 0;
    }

    int mid = low + (high - low) / 2;

    // make sure mid+1 is a valid index in nums array
    if (mid < (nums.length - 1) && nums[mid] > nums[mid + 1]) {
      return mid + 1;
    }

    // make sure mid-1 is a valid index in nums array
    if (mid > 0 && nums[mid] < nums[mid - 1]) {
      return mid;
    }

    // this condition decides if we have to go to left half or right half
    if (nums[low] > nums[mid]) {
      return findPivotPoint(nums, low, mid - 1);
    }

    return findPivotPoint(nums, mid + 1, high);
  }

  public int binarySearch(int[] nums, int low, int high, int target) {
    if (low <= high) {
      int mid = low + (high - low) / 2;

      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] < target) {
        return binarySearch(nums, mid + 1, high, target);
      } else {
        return binarySearch(nums, low, mid - 1, target);
      }
    }

    return -1;
  }

  // Problem Question: https://leetcode.com/problems/search-in-rotated-sorted-array/
  public int search(int[] nums, int target) {
    /**
     * Approach:
     * 
     * We can binary search in left and right parts of array,
     * but we need the pivot point at which it was rotated.
     * 
     * Here, just note that the array will always be rotated,
     * as 1 <= number of rotations < len, and
     * so it will not ever become in ascending order again.
     * 
     * But to find pivot point, we have to do that in O(log n) itself, to have overall in O(log n)
     * 
     * Time complexity: O(log n)
     */

    int pivotPoint = findPivotPoint(nums, 0, nums.length - 1);

    // Pivot point is at the starting point of next part of the array, which is already in ascending order
    int targetIndex = binarySearch(nums, 0, pivotPoint - 1, target);

    // if index found is < 0 from first part, then binary search in next part of array  
    if (targetIndex < 0) {
      targetIndex = binarySearch(nums, pivotPoint, nums.length - 1, target);

      if (targetIndex < 0) {
        return -1;
      }
    }

    return targetIndex;
  }

  public static void main(String[] args) {
    SearchRotatedSortedArr obj = new SearchRotatedSortedArr();

    // Sample Test cases
    System.out.println(obj.search(new int[] { 4, 1, 3 }, 3));
    System.out.println(obj.search(new int[] { 1 }, 1));
    System.out.println(obj.search(new int[] { 2, 1 }, 3));
    System.out.println(obj.search(new int[] { 2, 1 }, 1));
    System.out.println(obj.search(new int[] { 4, 1, 3 }, 5));
    System.out.println(obj.search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 3));
    System.out.println(obj.search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 5));
    System.out.println(obj.search(new int[] { 1, 3 }, 3));
    System.out.println(obj.search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 1));
  }
}