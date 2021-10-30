class PowXN:
    # Problem Question: https://leetcode.com/problems/powx-n/
    def myPow(self, x: float, n: int) -> float:
        '''
        I tried multiplying x, n times with checking if n is negative or not.
        That approach gave me TLE.
        
        I then tried multiplying x*x*x, n//3 times with checking as before.
        Extra power left after n//3, if n is 10 then we did x*x*x and multiplied that x*x*x 10//3 times that is 3 times. So we got x**9 in total.
        
        As 10 is not divisible by 3, so rest over power is 1. Multiply x 1 more time.

        This was giving some wrong answer, maybe logical error.
        So, shifted back to x**n approach.
        '''
        
        return x**n
