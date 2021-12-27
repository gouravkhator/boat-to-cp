# [Crush Numbers](https://binarysearch.com/problems/Crush-Numbers)

## Approach

Take those numbers which have larger differences. And then remove them and add its absolute difference.

## Code | WA Solution

```java
import java.util.*;

class CrushNumbers {
    // Problem Question: https://binarysearch.com/problems/Crush-Numbers
    public static int solve(int[] nums) {
        int len = nums.length;
        if(len == 1){
            return nums[0];
        }

        int i, currentLen, a, b, currentDiff;
        Arrays.sort(nums);

        ArrayList<Integer> list = new ArrayList<>();

        for(i=0; i<len; i++){
            list.add(nums[i]);
        }

        while(true){
            currentLen = list.size();
            if(currentLen == 0) return 0;
            if(currentLen == 1) return list.get(0);

            a = list.get(currentLen - 2);
            b = list.get(currentLen - 1);

            list.remove(list.size() - 1);
            list.remove(list.size() - 1);
            
            currentDiff = (b > a) ? (b - a): (a - b);
            if(currentDiff != 0)
                list.add(currentDiff);

            Collections.sort(list);
        }
    }

    public static void main(String[] args) {
      System.out.println(solve(new int[]{1,2,5})); // expected: 2, ours: 2
      System.out.println(solve(new int[]{2,2,2,3})); // expected: 1, ours: 1
      System.out.println(solve(new int[]{1,1,1,2,10,3,8,8,5,1})); // expected: 0, ours: 0
      System.out.println(solve(new int[]{2,2,2,3,3})); // expected: 0, ours: 2 (X)
      System.out.println(solve(new int[]{1,2,2,3,3,3})); // expected: 0, ours: 1 (X)

    }
}
```