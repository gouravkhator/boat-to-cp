import java.util.*;

class KthLargestElemArr {
  // Problem Question: https://leetcode.com/problems/kth-largest-element-in-an-array/
  public int findKthLargest(int[] nums, int k) {
    /**
     * Kth largest element means the minimum in the k largest elements.
     * 
     * Approach:
     * 
     * Make a minheap of capacity k.
     * Add first k elements to the minHeap.
     * 
     * While adding next elements, we check if this element can contribute to making k largest elements in min heap.
     * So, check if current minimum (in the min heap) is less than this current number,
     * if yes, then remove the minimum element and add this element.
     * 
     * At last, we peek the minimum element in the k largest elements, to get the Kth largest element. 
     * --------------------------------------------------------------------------
     * 
     * Time complexity: O(n log k), as we are totally adding n elements to the min heap of size k,
     * and each insertion/removal in min heap takes (log k).
     * Space complexity: O(k), for the priority queue, aka the min heap.
     */
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

    // no checks needed for k, as given already that k <= nums.length
    for (int i = 0; i < k; i++) {
      minHeap.add(nums[i]);
    }

    for (int i = k; i < nums.length; i++) {
      int currentMin = minHeap.peek();

      if (nums[i] > currentMin) {
        // then it means that we should add nums[i] to contribute to the largest elements
        minHeap.poll();

        minHeap.add(nums[i]);
      }
    }

    return minHeap.peek();
  }
}