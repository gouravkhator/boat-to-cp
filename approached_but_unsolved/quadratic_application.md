# [Quadratic Application](https://binarysearch.com/problems/Quadratic-Application)

## Code | WA solution

```py
class Solution:
    # WA for test case: 
    '''
    nums = [-6, -2, 3, 5, 9]
    a = 1
    b = -3
    c = 2
    '''
    def solve(self, nums, a, b, c):
        left = 0
        right = len(nums)

        res = []

        while left <= right:
            leftres = a * (nums[left] ** 2) + b * nums[left]
            rightres = a * (nums[right] ** 2) + b * nums[right]

            if leftres < rightres:
                left += 1
                res.append(leftres)
            else:
                right -= 1
                res.append(rightres)

        return res
```
