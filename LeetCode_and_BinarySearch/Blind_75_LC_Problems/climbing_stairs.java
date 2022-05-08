class ClimbingStairs {
    // Problem Question: https://leetcode.com/problems/climbing-stairs/
    public int climbStairs(int n) {
        /*
        Logic:
        
        From the pattern, we notice that for nth stair, we can reach nth from (n-1)th or (n-2)th stair.
        
        So, add the number of ways to reach (n-1)th and (n-2)th to get answer for nth stair.
        
        For n=5,
        Number of ways: 1 1 2 3 5 8
        Stair Number:   0 1 2 3 4 5
        
        Starting with stair number 0 and assuming number of ways to be 1 for that stair 0, we get a fibonacci series.
        
        And as we want the nth value, so using array is not efficient, just add and get c.
        Start with a, b and c as 1. Decrement n by 2 as a and b are already defined.
        
        Now, do fibonacci and decrement n till n >= 0.
        For n = 1, the while loop will not work and the answer returns 1 which is correct.
        For n = 2, loop runs once and return c as 2.
        Similarly for all n. 
        */

        int a = 1, b = 1, c = 1;

        n -= 2;

        while (n >= 0) {
            c = a + b;
            a = b;
            b = c;
            n--;
        }

        return c;
    }
}
