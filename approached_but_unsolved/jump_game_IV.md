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
