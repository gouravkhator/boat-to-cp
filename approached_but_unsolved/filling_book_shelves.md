# [Filling BookCase Shelves](https://leetcode.com/problems/filling-bookcase-shelves/)

## Approach

The hint in leetcode was to use dp.

dp(i) will give solution for books(i:)

If height is same add all to current shelf.

Else, first check with next books and their height, and make a temp shelf to keep them until we get different height again.

If the books are fixed in current shelf, fix the previous shelf.
