# Boat To CP

The Competitive Programming questions link with solved Java/Python solutions are written in this one single repository.

**\*NEW\* stuffs in this repo:**

* **Systems Design** - It includes problem statement, approach, hyperlinks, and much more. 

    The whole Systems Design will be in **.md** file with proper formatting and so much fun.

* Approached CP Problems but not yet solved - It includes approach and code in the form of **.md** files for each question.

## CP Platforms Covered

* [Leetcode](https://leetcode.com/problemset/all/)
    * [Blind 75 Problemset](https://leetcode.com/discuss/general-discussion/460599/blind-75-leetcode-questions)
* [Binary Search](https://binarysearch.com/)
* [Project Euler](https://projecteuler.net/) - The filenames may be given as problemN where N is the problem number in project euler website.
* [GeeksForGeeks](https://www.geeksforgeeks.org/)
* [Codeforces](https://codeforces.com/problemset/)
* [HackerRank](https://www.hackerrank.com/)
* OtherContest - It has questions given in comments of the code itself, and in filename too. 
It also includes **Google Kickstart** problem sums, either solved fully by me, or inspired by others' approaches..

## Important Pre-Interview CP Questions To Prepare

> I have grouped the problems based on similar approaches to those questions.

I will link to their solution in the below table itself, once I have committed and pushed them to remote git repo.

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
| Number of Islands | DFS | No non-optimal approach thought
| Missing Number in Unsorted Array | Uses the same array for manipulating the element by the index | Uses extra boolean array and then fills them with true if it exists, or false if it is not, and then lookup that array 
| Climbing Stairs | Fibonacci Series (Normal Loop) | Linear DP

## Assumptions

Not all CP questions are uploaded here. I got some of the major platforms here, on which I code and keep a local copy of my solutions.

**Note: All the solutions are written in Java/Python.**

Some solutions were solved for some exams which I couldn't share.
