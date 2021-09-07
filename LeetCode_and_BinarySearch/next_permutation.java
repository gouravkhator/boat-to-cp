class NextPermutation {
    // sort using insertion sort, as merge sort uses extra memory and it's told not to use extra memory
    public void sort(int[] nums, int start, int end){
        for(int i=start+1; i<=end; i++){
            int j=0, temp = nums[i];
            
            for(j=i-1; j>=start && nums[j] > temp; j--){
                nums[j+1] = nums[j];
            }
            
            nums[j+1] = temp;
        }
    }
    
    // Problem Question: https://leetcode.com/problems/next-permutation/
    public void nextPermutation(int[] nums) {
        /*
        Logic:

        First check from last element, if the sequence goes non-ascending, then don't do anything and continue.

        As soon as the sequence goes ascending,
        Ex- [2,3,1]

        Here, for i=0, the sequence becomes ascending as 2 < 3
        So, swap 2 with just higher number than 2 which is present after index of element 2.

        So, 2 is swapped with 3. 
        Now, the remaining elements after index 0, is sorted in ascending order.

        Ex- [3,2,1]
        Here, the sequence was non-ascending, so dirty flag was false.

        If dirty is false, then just reverse the arr (by swapping 1st with last element and so on going till the middle element).
        */

        int i=0, j=0, temp = 0, len = nums.length, minIndex = -1, min = Integer.MAX_VALUE;
        
        boolean dirty = false;
        for(i=len - 2; i>=0; i--){
            if(nums[i] < nums[i+1]){
                minIndex = -1;
                min = Integer.MAX_VALUE;
                
                // search for number which is just larger than nums[i]
                /*
                as the numbers after index i, are in non-ascending order,
                For example:

                [1,3,1,1]
                Here, the if condition is valid for i=0 at nums[i] = 1.
                So, we need the number greater than 1 which is after index 0, to be swapped with index 0.

                We cannot search for numbers equal to 1, as then the arr will be smaller permutation or equal to current arr.
                Ex- [1,1,1,1]

                Here, arr is in non-ascending order. so the if condition does not enter actually.
                */
                for(j=i+1; j<len; j++){
                    if(nums[j] > nums[i] && min > nums[j]){
                        min = nums[j];
                        minIndex = j;
                    }
                }
                
                // swap just higher number with current number
                temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
                
                // sort the remaining in ascending order
                sort(nums, i+1, len - 1);
                
                dirty = true;
                break;
            }
        }
        
        // if dirty is false, the elements were already sorted in non-ascending order
        if(dirty == false){

            // reverse them
            for(i=0; i<(len/2);i++){
                temp = nums[i];
                nums[i] = nums[len - 1 - i];
                nums[len - 1 - i] = temp;
            }
        }
    }
}
