class LISOptimal {
  /*
  Test cases:
  
  [10,9,2,5,3,7,101,18]
  [10,9,2,5,3,7,101,6]
  [6,5,7,3]
  [2,9,1,10,6,7,3,2,18,5]
  [5]
  [4,5]
  [-1,0,5,-7,9,10,0]
  */
  public int getNextGreaterElemIndex(int[] dp, int startIndex, int endIndex, int target) {
    int left = startIndex, right = endIndex;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (dp[mid] == target) {
        // if we get the target element in the dp array before too, we just return that index, so that we again overwrite that index with the same element
        return mid;
      } else if (dp[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    /*
    the index for the next greater element will be at (right + 1), 
    but if (right + 1) is not in this dp range, then we return -1
    */
    return ((right + 1) <= endIndex) ? (right + 1) : -1;
  }

  // Problem Question: https://leetcode.com/problems/longest-increasing-subsequence/
  public int lengthOfLIS(int[] nums) {
    /*
    The analogy for this best optimal approach was actually tried firstly by me, 
    but then it didn't hit, so I looked up in videos, 
    and just once I got the smallest hint of how this can be done, I tried myself.
    
    Analogy:
    
    What we want is the longest increasing subsequence, so once we get any number, we just place that number in the next greater element's index.
    
    Meaning: for nums = [6,4,7,3,5,9]
    We get element 6, we just save that number.
    Now, we get element 4, so to get the longest increasing subsequence, if we just ignore 4, then in future if element 5 comes, we won't have the chance to put 5 after existing element 6.
    
    So, we place 4 in place of element 6.
    
    Now, we get 7, and as 7 is in the increasing way of 4, we can just put 7 after 4.
    
    Now, we get element 3, and as 3 is not in the increasing sequence, but we cannot ignore 3.
    If 3 is ignored now, then and we get 4 in future too, then we cannot place 4 again, after existing element 4.
    Meaning we won't have the dp as : [4,4,7] bcoz, this is not stricly increasing.
    So, we put 3 in place of 4 now, to give another 4 a chance in future if it comes.
    
    dp becomes: [3, 7]
    
    Element 5 comes, and for this element we should put it in place of 7, to give elements between 5 and 7, a chance in this sequence, so as to make this sequence the longest.
    
    Then we have element 9, and we can just put after element 5.
    
    Final dp becomes [3,5,9]
    
    Now, one might say that for some examples, the dp might be having wrong sequence order like for example when nums=[10,9,2,5,3,7,101,18,6]
    
    Here, the dp becomes: [2,3,6,18], and in original nums the 6 was after 18, and here it is placed before 18, which is invalid longets increasing sequence.
    
    But, we should note that the problem is to find the length of the longest increasing subsequence, and not the longest increasing subsequence.
    
    And, while making the dp like: [2,3,6,18], we were also getting dp like: [2,3,7,101] or [2,3,7,18] and so on, which are actually valid longest increasing subsequence.
    
    The point we can make is here for every new element to be added to the dp, we have to make sure we give chances for the next numbers, and always the dp will be in sorted order, as we are inserting the new elements in the place of the next greater element. 
    
    Q: Why we don't shift the numbers, rather place them in the place of next greater element?
    > For the example: nums=[6,4,7,3,5,9], shifting numbers in the dp means making the final dp like:
    dp=[3,4,5,6,7,9]
    
    And it is not the increasing sequence we want, rather shifting leads to the sorting of nums array, which is not our actual goal, and would lead to wrong answers.
    
    Approach:
    
    I used dp to track the actual chance giving increasing subsequence, and dpActualLen to track till how many elements are actually filled now.
    
    This dpActualLen was used bcoz we used an array of fixed length n.
    
    We can use arraylist for dynamic insertions, which will not require dpActualLen for tracking. Then, the actual length will be arraylist.size().
    
    And as the dp is always in sorted order, we can find the next greater element's index using binary search, which would search for all the elements till the index the dp is actually filled.
    
    If we find the next greater element index in that dp filled elements itself, then we return that index, else we return -1,
    which means if index is -1, we will place the new element after the last filled element in the dp array.
    
    ---------------------------------------------------------------------------
    
    Time complexity: O(n log n)
    (As for every element in nums array, we just do binary search and find the next greater element's index,
    so it is (log n) for the binary search, and it is done for all elements, so it is (n log n))
    
    Space complexity: O(n)
    (as we use the dp array and it can have max length of n, menaing the longest increasing subsequence can be of length n)
    
    (Also, note that to simplify my approach, I used the array of fixed length, so that fixed length was taken to be n. So, actually in my approach, space complexity is O(n) literally.
    
    We can use arraylist as such, to just have at max n elements, and not literally n elements, which may save space in most test cases).
    */
    int len = nums.length;
    if (len == 0) {
      return 0;
    }

    int[] dp = new int[len]; // we can have maximum of len longest subsequence, so we took dp of the capacity of len
    int dpActualLen = 0; // to track the actual len, so that we can insert after that

    dp[0] = nums[0];
    dpActualLen++;

    for (int i = 1; i < len; i++) {
      int nextGreaterElemIndex = getNextGreaterElemIndex(dp, 0, dpActualLen - 1, nums[i]);

      if (nextGreaterElemIndex == -1) {
        dp[dpActualLen] = nums[i];
        dpActualLen++;
      } else {
        dp[nextGreaterElemIndex] = nums[i]; // we just assign, and not shift the numbers
        // shifting numbers will keep the nums array in full sorted order, thus leading to wrong results
      }
    }

    return dpActualLen;
  }
}

class LISLessOptimal {
  public int lengthOfLISLessBetter(int[] nums) {
    /*
    This is a Less better approach.
    
    Analogy behind this approach:
    
    Each element is considered the start of the stair or the part of the longest stair yet. After all the elements are processed, we return the length of the longest stair formed.
    
    Example:
    So, if the nums array is like [10,9,2,5,3,7,101,18]
    
    Element 10 is the start of the stair, as no elements are there before 10.
    Element 9 is also the start of the stair, as no element that are before element 9, are less than 9.
    Similarly element 2 is the start of the stair.
    Element 5 is the part of the stair, which started at element 2.
    [Why so? It is bcoz, element 2 is less than element 5, and so we get a stair of length 2 till now.]
    
    Element 3 is the part of the stair, which started at element 2, making the stair as [2,3]
    Element 7 is the part of the stair, which started at element 2, and continued to element 5 or element 3 (any one is good), making the stair like [2,5,7] or [2,3,7]
    
    Element 101 is the part of the stair [2,5,7,101] or [2,3,7,101]
    Element 18 is the part of the stair [2,5,7,18] or [2,3,7,18]
    
    If we go over all the lengths of the stairs formed, we see that the longest stair is [2,5,7,101] or some other similar lengths stairs.
    So, the longest length of the stair is 4.
    
    Approach:
    
    We keep the increasing length dp to keep track of the stairs.
    
    For nums=[6,4,7,3,5,9], dp will become [1,1,2,1,2,3]
    
    And highest length in the dp array is 3, which is the answer.
    
    For element 6, stair length is 1.
    For element 4, just find the elements which are less than 4 (to make it strictly increasing), and then find the maximum length stair and then add 1 to that length to include 4 in that stair path.
    Here, we get no elements less than 4, so its dp value is 1.
    
    For element 7, just find the elements which are less than 7 (to make it strictly increasing), and then find the maximum length stair and then add 1 to that length to include 7 in that stair path.
    Here, element 6 is less than 7 and thus the dp for element 7 is having 2. ((dp for element 6) + 1 = 1+1 = 2)
    
    Thus we do for other elements too, and it may happen the longets stair is not at the end of the dp but maybe before that even, so we loop through dp to find the max length.
    
    ------------------------------------------------------------------------------------
    
    Time complexity: O(n^2) (as we use a nested loop with traversing n element at max in the inner loop).
    Space complexity: O(n) (as we use extra space of n for the dp array)
    */
    int len = nums.length;
    if (len == 0) {
      return 0;
    }

    int[] increasingLenDP = new int[len];

    increasingLenDP[0] = 1;

    for (int i = 1; i < len; i++) {
      increasingLenDP[i] = 1; // it is initialised to 1 length for sure..

      for (int j = i - 1; j >= 0; j--) {
        if (nums[j] < nums[i]) {
          // as we require a strictly increasing subsequence, so we don't include th equality check in this condition
          increasingLenDP[i] = Math.max(increasingLenDP[i], increasingLenDP[j] + 1);
        }
      }
    }

    int longestLen = 0;

    for (int i = 0; i < len; i++) {
      longestLen = Math.max(longestLen, increasingLenDP[i]);
    }

    return longestLen;
  }
}