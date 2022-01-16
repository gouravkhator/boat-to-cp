class FindDuplicate:
    # Problem Question: https://leetcode.com/problems/find-the-duplicate-number/
    def findDuplicate(self, nums) -> int:
        '''
        Logic:

        As the constraint is that the numbers are from 1 to n only, in an array of (n+1) integers.
        
        For example: [1,3,4,2,2]
        Here, we get 1 as the 1st number, just negate the 1st number in the list.
        Then we get 3, just negate the 3rd number in the list.
        Then comes 4, just negate the 4th number in the list.
        Then comes 2, just negate the 2nd number.
        Then again 2, now as 2nd number is already negated, meaning some other 2 has done that negation.
        So, duplicate is 2.

        The catch is, when we check for current number, it may have been negated before by some other number,
        so we should take its absolute and check whether at that absolute number's index, is the number negated or not..
        If the number in that index is already negated, then this absolute number is our answer.
        '''

        for i in range(len(nums)):
            current = abs(nums[i])
            if nums[current - 1] < 0:
                return current
            else:
                nums[current - 1] *= -1
        
        return 0
