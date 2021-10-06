import java.util.*;

class FindDuplicatesInArr {
    // Problem Question: https://leetcode.com/problems/find-all-duplicates-in-an-array/
    public List<Integer> findDuplicates(int[] nums) {
        /*
        Logic:

        Given that the array is of length n and it contains numbers from 1 to n only.

        For finding duplicates in array like below:
        [4,3,2,7,8,2,3,1]

        Indexing is taken as 1-based indexing below.

        We get element 4 at 1st index. Now, for that 4, negate the 4th index.
        If the 4th index is already negated, then 4 has occurred twice, so append this 4 to list of duplicates.

        But, it may happen that some other element has negated this 4 too.
        So, first take absolute value of 1st index and then try negating the 4th index if not done.

        Main logic is:
        As we have n elements from 1 to n only, so we can just check if 4 has occurred twice by manipulating its index's value.
        Similarly, we do for each elements.
        */

        int i=0, temp = 0, len = nums.length;
        
        List<Integer> list = new ArrayList<>();
        
        for(i=0; i<len; i++){
            temp = Math.abs(nums[i]);
            
            if(nums[temp - 1] > 0){
                nums[temp - 1] = -nums[temp - 1];
                // using "nums[temp - 1] *= -1" to negate that, will take more time than direct negating that.
            }else{
                list.add(temp);
            }
        }
        
        return list;
    }
}
