class BitwiseAndNumbersRange {
    public static void main(String[] args) {
        // Test Cases
        System.out.println(rangeBitwiseAnd(5, 7));
        System.out.println(rangeBitwiseAnd(10, 15));
        System.out.println(rangeBitwiseAnd(15, 22));
        System.out.println(rangeBitwiseAnd(0, 23));
        System.out.println(rangeBitwiseAnd(0,0));
        System.out.println(rangeBitwiseAnd(1, 22654895));
        System.out.println(rangeBitwiseAnd(1, 2147483647));
        System.out.println(rangeBitwiseAnd(2147483647, 2147483647));
        System.out.println(rangeBitwiseAnd(2147483646, 2147483647));
        System.out.println(rangeBitwiseAnd(2147483645, 2147483647));
    }

    public static boolean checkOriginalAnswer(int left, int right, int res) {
        // to check from left to right using naive method,
        // check if res is same as the answer of naive method or not..
        int ans = Integer.MAX_VALUE;

        for(int i=left; i<=right; i++){
            ans &= i;
        }

        return ans == res;
    }

    public static boolean isUnset(int left, int right, int i, int choice) {
        // the ith index is unset from range of (2*n)*(2^i) to (2^i)*(2*n + 1) - 1
        // where n can be any whole number
        int tempPow = (int)Math.pow(2, i+1);

        // left = (2*n)*(2^i) so find n from this equation
        // choice can be right, meaning some other even multiple can also exist, as right number is inclusive in original range.
        // so similar equation but with right into consideration
        int n = choice / tempPow;
        
        int unsetLeft = n * tempPow;
        int unsetRight = (int) Math.pow(2, i) * (2 * n + 1) - 1;
        
        if(unsetLeft < 0 || unsetRight < 0){
            // it may happen that any ends of the unset range is negative, then return false..
            // it means for that index i, all range numbers' indices at position i are set.
            return false;
        }

        return (left >= unsetLeft && left <= unsetRight) 
            || (right >= unsetLeft && right <= unsetRight)
            || (unsetLeft >= left && unsetLeft <= right)
            || (unsetRight >= left && unsetRight <= right);
    }

    // Problem Question: https://leetcode.com/problems/bitwise-and-of-numbers-range/
    public static int rangeBitwiseAnd(int left, int right) {
        /*
        Logic:

        For finding AND of numbers from left to right, we need to check the maximum number of bits required to represent right.
        Then, from 0 to maxBits, we check for each position:
        Whether for that index i, any number from left to right has 0 in that index.
        If anyone has 0, it means result will have 0 in that index.

        For checking if for index i, any of the numbers from left to right have 0 set or not:
        1. I checked from pattern that for index 0, number 0, 2, 4 etc. have that index 0 unset.
        For index 1, numbers 0 to 1, numbers 4 to 5 etc. have that index 1 unset.
        And so on.

        For index i, the range for index i unset numbers will be:
        From even multiples of 2^i as starting,
        To ((Even multiples of 2^i) +  2^i - 1) as ending.

        Even multiples mean: 2*n where n can be any whole number.
        So, unset numbers range for index i will be:
        (2*n)(2^i) to (2*n)(2^i) + 2^i - 1
        
        Simplified to:
        n*(2^(i+1)) to (2^i)*(2n + 1) - 1

        If original left is between this range then there is atleast one number which is unset for index i.
        Similar check for original right to be between this range. 
        
        Also, if unset range start or end comes in between left and right, then also atleast one number is unset for index i.
        So, for that index i, if any of above conditions are true, then that index result is 0.

        And we add 2^i to res when all numbers' index i are set.
        */

        if(left == 0 || right == 0){
            // if any of left or right is 0, the whole AND will be 0
            return 0;
        }
        
        int maxBits = Integer.SIZE - Integer.numberOfLeadingZeros(right);
        int res = 0;
        
        for(int i=0; i<maxBits; i++){
            // check if anything is unset or not
            boolean unsetFlag = isUnset(left, right, i, left) || isUnset(left, right, i, right);
            
            if(unsetFlag == false){
                // index should be set
                res += (int) Math.pow(2, i);
            }
        }
        
        // System.out.println(checkOriginalAnswer(left, right, res));
        return res;
    }
}
