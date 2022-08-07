# Must Check CP Problems for Interviews

> I have grouped the problems based on similar approaches to those questions.

I will also hyperlink the solutions in the below table itself, once I commit and push the solutions to this remote git repo.

| Problem Name | Approach Used (Optimal) | Other Non-Optimal Approaches | Extra Notes | 
| ------------ | ----------------------- | ---------------------------- | ----------- |
| [2-Sum](https://github.com/gouravkhator/boat-to-cp/blob/main/LeetCode_and_BinarySearch/Blind_75_LC_Problems/two_sum.java)        | HashMap storage and lookup | Naive Approach
| [3-Sum](https://github.com/gouravkhator/boat-to-cp/blob/main/LeetCode_and_BinarySearch/Blind_75_LC_Problems/3_sum.java); [4-Sum](https://github.com/gouravkhator/boat-to-cp/blob/main/LeetCode_and_BinarySearch/4_sum.java) | Two Pointers | Using nested loops in the outside, and the innermost loop will be a binary search (worse time complexity)
| Longest Common Subsequence; Longest Palindromic Subsequence; Longest Common Substring | 2-D Array DP | Recursion (so many overlapping subproblems) 
| Longest Palindromic Substring | Two pointer like approach | 2-D Array DP (takes up extra space); Recursion (maybe exponential)
| Course Schedule | DFS | Recursion
| Course Schedule II | DFS followed by Topological Sort | Recursion
| Jump Game; Jump Game II | Linear DP | Recursion
| Merge Intervals | Normal Analogy but a good problem with no specific algo/DS | No non-optimal approach thought
| Container with Most Water | Two Pointer Approach | Nested Iteration
| Search Element in Rotated Sorted Array | Binary Search | Linear Search
| Combination Sum | Recursion with somewhat optimised approach | No non-optimal approach thought | This problem sum helped in understanding how to do recursion in a optimised way, for inclusion and exclusion problem sums
| Merge K sorted Lists | Merge sort like approach on K Lists | Any sort on the merged list of K lists (takes up extra space of O(n*k))
| Travelling Salesman Problem (TSP) | | 
| Number of Islands | DFS | No non-optimal approach thought
| Missing Number in Unsorted Array | Uses the same array for manipulating the element by the index | Uses extra boolean array and then fills them with true if it exists, or false if it is not, and then lookup that array 
| Climbing Stairs | Fibonacci Series (Normal Loop) | Linear DP
| Trapping Rain Water | | 
| Partition to K equal Sum Subsets | 
| Flatten Binary Tree to Linked List | |
| Kth Largest Element in an Array | |
| Longest Valid Parentheses | | 
| Validate BST | |
| Trie Implementation | | 
| Subsets With Duplicates I/II | |
| Spiral Matrix | |
| Next permutation | |
| Find Kth Bit in Nth Binary String | |
| Permutations I & II | |
| Range Sum Query 2-D Immutable | | 
| N Queens All Valid Layouts Print | |
| Rotate Image | | 
| Triangle Min Path Sum | | 
| Decode Ways | | 
| Binary Tree Maximum Path Sum | | 
| Angry Owner | | 
| Furthest Building you can reach | | 
| Interleaving String | | 
| Matchsticks To Square | | 
| Out of Boundary Paths | |

## Other Problem Sums that I might not have solved in this repo, but feel important for coding interviews

- [Cutting a rod](https://www.geeksforgeeks.org/java-program-for-cutting-a-rod-dp-13/)

- [Sort Colors](https://leetcode.com/problems/sort-colors/) or [Sort an array of 0s, 1s and 2s](https://geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/)

- [Find Common elements in 3 sorted arrays](https://www.geeksforgeeks.org/find-common-elements-three-sorted-arrays/)

  - My approach says that take 3 pointers to each of the array start. And check at each time, if all 3 pointers' elements are same then print that element and increment all 3 index by 1.
  - If they are not same, we increment the index of that array by 1 to point to next element in that array. This is done till we reach end of any one of the arrays.

- [Sort elements by frequency | GFG Question Set 2](https://geeksforgeeks.org/sort-elements-by-frequency-set-2/)

- [Sum of k largest elements in BST](http://geeksforgeeks.org/sum-of-k-largest-elements-in-bst/)

- [Check if a queue can be sorted into another queue using a stack](https://www.geeksforgeeks.org/check-queue-can-sorted-another-queue-using-stack/)

- [Print middle level of perfect binary tree without finding height](https://www.geeksforgeeks.org/print-middle-level-perfect-binary-tree-without-finding-height/)

- [Heap sort Convert Min Heap to Max Heap](https://www.geeksforgeeks.org/convert-min-heap-to-max-heap/)

- [Huffman Coding Problem (Greedy Algorithm)](https://www.geeksforgeeks.org/greedy-algorithms-set-3-huffman-coding/)

- [Find pair in sorted array whose sum is closest to a number x](https://www.geeksforgeeks.org/given-sorted-array-number-x-find-pair-array-whose-sum-closest-x/)

- [Find closest pair from 2 sorted arrays](https://www.geeksforgeeks.org/given-two-sorted-arrays-number-x-find-pair-whose-sum-closest-x/)

- [Watch `""The Decryption Problem" on Google Codejam 2019"` on YouTube](https://youtu.be/ne8vB6FAzl0)

- [GCD of two numbers when one of them can be very large](https://geeksforgeeks.org/gcd-of-two-numbers-when-one-of-them-can-be-very-large-2/)

- [Execute if and else block simultaneously](https://www.geeksforgeeks.org/execute-else-statements-cc-simultaneously)

