class RemoveStonesMinimizeTotal {
  // Problem Question: https://leetcode.com/problems/remove-stones-to-minimize-the-total/
  public int minStoneSum(int[] piles, int k) {
    /*
    Approach:
    -----------

    As we need to minimize the total, so we remove the floor halves from the larger numbers every time.
    And it will happen that the larger numbers will change, as we remove the larger numbers and then add their floored-halves.

    ------------------------------------------------------------------------

    Time complexity: O((n+k) log n)
    (As we build a max heap of size n, and each insertion/removal takes O(log n))
    
    Space complexity: O(n) // max heap of size n
    */

    int n = piles.length;
    
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(n, Collections.reverseOrder()); // max heap of size n

    int sum = 0, sumRemoved = 0, largestElem, temp;
    
    // building the heap by adding each element to it
    for(int i=0; i<n; i++){
      sum += piles[i]; // tracking total sum
      maxHeap.add(piles[i]);
    }
    
    for(int i=0; i<k; i++){
      largestElem = maxHeap.poll();
      
      temp = (int)Math.floor(largestElem/2.0);
      sum -= temp; // removing the elements from the sum
      
      maxHeap.add(largestElem - temp); // adding back the largest element - temp, which will be added in max heapified way..
    }
    
    return sum;
  }
}