import java.util.ArrayList;
import java.util.List;

class SansaAndXor{
    // Problem question: https://www.hackerrank.com/challenges/sansa-and-xor/problem
    public static int sansaXor(List<Integer> arr) {
        /*
        Logic:

        Ex- [1,2,3]

        For subarrays starting with 1, that is: {{1}, {1,2}, {1,2,3}}

        We have xor for them as: {1,3,0}
        Total xor till now is 1^3^0 = 2
        Now, for subarrays starting with 2 that is: {{2}, {2,3}}

        Now for calculating the xor of this subarray, we can remove 1 from the xor by again xoring previous {1,3,0} each with 1.
        {1,3,0} element wise xor {1,1,1} will lead to {0,2,1} and as the first xor is not needed.
        (as in the current subarray, we have {2} and {2,3} so, xor of this subarray is {2,1})

        Calculating xor with each of the previous xor, 3 times means calculating xor of previous xors and with previous element 1, once.
        (as xor of 1 and 1 means 0 and xor of 0 and 1 means 1 again)

        We check how many elements were there in previous round of xor.
        And if the previous round had odd number of elements,
        then xor previous element with previous xor result and that's the xor for current subarray starting with 2.

        So, current subarray's total xor is (1^3^0) ^ 1 [as previous iteration had odd number of elements in xor array].

        This is equal to 3.
        Similarly, we continue.
        */
        
        int resXor=0, j=0, len = arr.size();
        
        int currentXor = 0;
        // calculate xor of first set of subarrays
        for(j=0; j<len; j++){
            currentXor = currentXor ^ arr.get(j);
            resXor ^= currentXor;
        }

        currentXor = resXor; // reset temp variable 
        
        // calculate xor of next sets of subarrays
        for(j=1; j<len; j++){
            // if len of previous iteration is odd, means len of current iteration is previous len - 1 so, it's even.
            // So, xor the previous xor with previous element, to remove it from current set of subarray, from counting it in the current xor.
            if(((len - j) % 2) == 0){
                currentXor ^= arr.get(j-1);
            }
            
            resXor ^= currentXor; // then xor the current xor with the total xor
        }
        
        return resXor;
    }
    
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        arr.add(4);
        arr.add(5);
        arr.add(7);
        arr.add(5);
        System.out.println(sansaXor(arr));
    }
}