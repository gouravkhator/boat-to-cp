import java.util.*;

class AngryOwner {
  // Problem Question: https://binarysearch.com/problems/Angry-Owner
  public int solve(int[] customers, int[] mood, int k) {
      /*
      Test cases:
#1:
customers = [1, 2, 5, 5, 2]
mood = [1, 1, 0, 0, 0]
k = 2

#2:
customers = [1,2,5,5,2,3,2,2,4,2,5,3]
mood = [1,0,0,1,0,0,0,0,0,1,1,0]
k = 2

#3:
customers = [5]
mood = [0]
k = 0

#4:

customers = [2,2]
mood = [0,1]
k = 1

#5:

customers = [2,0,1]
mood = [0,1,0]
k = 3
      */

      /*
      Analogy:
      ------------

      Problem question was kind of unclear, but the main question was to:
      "Find the maximum number of happy people, given that we can set the contiguous list of length k in the moods array to 1".

      This also means that we can set the cell in that list from 0 to 1, or even from 1 to 1.

      This question boils down to the "find maximum sum of length k, where the sum will only come from considering customers, where the mood is 0"

      This means keep counting all the elements irrespective of the mood, but only consider the customers[i] in the sum, if mood[i] == 0.

      Approach:
      ------------

      1. When we arrive at an element, we count that element.
      2. We also check if the start (the start of the sublist) is set or not. If not, we set that to current.
      3. Then, if the mood is unhappy, we consider this current element to the sum.
      4. If mood is happy, we don't consider this current element to the sum.
      5. And then we check if the count is k, then follow below steps:
          a) Update maxSum with the sum.
          b) Update start (start of the sublist) to its consecutive next.
          c) As we are removing the start index from the sublist, we decrement the count.
          d) We also remove the element from the sum, if it was even considered before. (Meaning if mood[start] == 0, then it was considered before and so we subtract that from the sum).

      Step 5 is mainly just shifting the sub-problem to the next sub-problem.

      Another edge case Note:
      When we are trying to consider the current customers[i] to sum, we should check if k > 0 or not.
      It may happen that k = 0, then it means that we should not consider this element.

      After the loop, if the count is still something which is less than k, then we update maxSum.

      Why we don't include the 1's in the maxSum previously?
      > It is bcoz, we need to return the maximum number of people whose mood is 1.
      So, if we count some 1's in the max sum subarray, 
      then we have to exclude them from the count of 1's from other parts of the subarray.

      So, to simplify, we don't include the 1's in the maxSum before, 
      and then after the maxSum (the sum of the at most k zeros we have set imaginarily) is checked, 
      we just count the number of ones.

      --------------------------------------------------------------------------------

      TC: O(n)
      SC: O(1)
      */

      int len = customers.length;
      int count = 0, start = -1, sum=0, maxSum = 0;

      for(int i=0; i<len; i++){
        count++;

        if(start == -1){
          start = i;
        }

        if(mood[i] == 0){
          // if the mood is currently 0

          if(k > 0){
            // if k is 0, we should not consider this customers[i] in the sum, as we are making length of k.
            sum += customers[i];
          }
        }

        if(count == k){
          maxSum = Math.max(maxSum, sum);
          sum -= (mood[start] == 1 ? 0: customers[start]);
          count--;
          start++;
        }
      }

      if(count > 0 && count < k){
        /*
        we should have atmost k length sublist, so that is why, 
        we are considering this edge case too where the count is less than k, but greater than 0
        */
        maxSum = Math.max(maxSum, sum); 
      }

      for(int i=0; i<len; i++){
        // summing up the customers with mood as 1
        if(mood[i] == 1){
            maxSum += customers[i];
        }
      }

      return maxSum;
    }
}