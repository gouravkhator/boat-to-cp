# [Jump Game IV](https://leetcode.com/problems/jump-game-iv/)

## Approach

```
Ex- [100,-23,-23,404,100,23,23]

We will use dp. To reach -23 at index 1, we can reach from index 0 in 1 jump.
To reach -23 at index 2, we can reach from index 0 to index 1 to index 2, in 2 jumps.

To reach 404, we can reach via the long route from 100 -> -23 -> -23 -> 404, or just search for nearest 100 (same value as src) which is near to 404 (destination).
And then we can go backwards.

To reach 100 at index 4, when we check from index 0 to 3 as src, we can reach from index 0 directly to index 4, as nearest 100 (src) which is near to destination 100 is at the destination only.

Main catch is to find the locations of each element, and store them such that we can find the location of an element near to the current index in O(1) time.
```

## Code | Incomplete

```py
class Solution:
    # Problem Question: https://leetcode.com/problems/jump-game-iv/
    def minJumps(self, arr: List[int]) -> int:
        # test case: [100, 0, -23, 0 -23]
        
        n = len(arr)
        
        def findSimilarNear(element, nearToIndex):
            # returns the index of the element which is closer to the nearToIndex
            # the near can be the nearToIndex too..
            
            return
        
        jumps = [0]*n
        
        for i in range(1, n):
            # we need to reach a similar src (same value as the src) near the destination or a similar destination (same value as the destination) near the src..
            
            if jumps[i] > 0:
                # it is being computed before, don't recompute
                continue
            
            for j in range(i):
                # j is the src and i is the destination..
                min_jumps = jumps[j] + (i - j) # 1 by 1 step taken in forward
                
                src_1 = findSimilarNear(arr[j], i)
                src_2 = findSimilarNear(arr[i], j)
                
                jumps_src_1, jumps_src_2 = 0, 0
                # calculate jumps, we cannot use jumps[] directly, as those indices might be after i, and might not have been populated.
                if jumps[src_1] > 0:
                    jumps_src_1 = jumps[src_1]
                else:
                    jumps_src_1 = calcJumps(src_1)
                    
                if jumps[src_2] > 0:
                    jumps_src_2 = jumps[src_2]
                else:
                    jumps_src_2 = calcJumps(src_2)

                # calculate the jumps for src_1 and src_2 indices and then add abs(i - src_1) or abs(i - src_2)
                
                min_jumps = min(min_jumps, jumps_src_1 + abs(i - src_1)) # reach a similar src, which is near to the destination
                min_jumps = min(min_jumps, jumps_src_2 + abs(i - src_2)) # reach a similar dest, near the src
                jumps[i] = min(jumps[i], min_jumps)
                
        return jumps[n-1]
```
