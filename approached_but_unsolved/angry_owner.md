# [Angry Owner](https://binarysearch.com/problems/Angry-Owner)

## Code

```java
class AngryOwner {
    // Problem Question: https://binarysearch.com/problems/Angry-Owner
    public static int solve(int[] customers, int[] mood, int k) {
        // check a sublist by 2-pointer approach of length k which has sum maximum and maximum number of 0's in mood
        int i=0, start = 0, end = k - 1, finalStart = -1, finalEnd = -1, len = customers.length, 
          maxSum = 0, currentSum = 0, zerosCount = 0, maxZeros = 0, finalSum = 0;

        for(i=start; i<=end && i<len; i++){
          currentSum += customers[i];
          
          if(mood[i] == 0)
              zerosCount += 1;
        }

        while(true){
            if(maxSum <= currentSum && maxZeros <= zerosCount){
                finalStart = start;
                finalEnd = end;
                maxSum = currentSum;
                maxZeros = zerosCount;
            }

            start++;
            if(start >= len){
              break;
            }

            if(mood[start - 1] == 0){
              zerosCount--;
            }

            end++;
            if(end >= len){
              break;
            }

            if(mood[end] == 0){
              zerosCount++;
            }

            currentSum += customers[end] - customers[start - 1];
        }

        for(i=finalStart; i<=finalEnd; i++){
          mood[i] = 1;
        }

        for(i=0; i<len; i++){
          if(mood[i] == 1)
              finalSum += customers[i];
        }

        return finalSum;
    }

    public static void main(String[] args) {
      System.out.println(solve(new int[]{1,2,5,5,2,9,4,1,2}, new int[]{1,1,0,0,0,0,1,0,0}, 2));
      System.out.println(solve(new int[]{5,9,2,3,11,7}, new int[]{0,1,0,0,1,0}, 3));
      System.out.println(solve(new int[]{5,9,2,3,11,7,4}, new int[]{0,1,0,1,1,0,1}, 3));

      System.out.println(solve(new int[]{2,1}, new int[]{1,0}, 1)); // expected: 3, output: 2
    }
}
```