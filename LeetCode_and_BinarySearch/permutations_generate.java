import java.util.*;
import java.util.stream.Collectors;

class PermutationsGenerator {
    // binarySearchDesc returns the index which is just higher than the element,
    // in nums array (from index left to index right).
    // Given that, from left to right index, nums is in descending order..
    public int binarySearchDesc(int[] nums, int left, int right, int elem){
        while(left <= right){
            int mid = left + (right - left)/2;
            
            if(nums[mid] > elem){
                // if mid element is greater than elem, then search in right side, so edit left pointer
                left = mid + 1;
            }else if(nums[mid] < elem){
                // if mid element is less than elem, then search in left side, so edit right pointer
                right = mid - 1;
            }
        }
        
        return right; // by dry run, got that right pointer is the index where there is that just-higher element.
    }
    
    public void reverse(int[] nums, int start, int end){
        int mid = start + (end - start)/2;
        int temp = 0, i = 0;
        
        for(i=start; i<=mid; i++){
            temp = nums[i];
            nums[i] = nums[end + start - i];
            nums[end + start - i] = temp;
        }
    }
    
    public boolean nextPermutation(int[] nums) {
        int i=0, temp = 0, len = nums.length;
                
        for(i=len - 2; i>=0; i--){
            if(nums[i] < nums[i+1]){
                // search for the element which is just higher than nums[i]
                int indexToSwap = binarySearchDesc(nums, i+1, len - 1, nums[i]);
                
                temp = nums[i];
                nums[i] = nums[indexToSwap];
                nums[indexToSwap] = temp;
                
                // reverse the remaining array, to get them in ascending order.
                /*
                 if after swapping from [1,3,2] we got [2,3,1].

                 Now, why reverse helps and sorting is not necessary?
                 > It's bcoz we got a breakpoint at 1 and this means 1 was smaller than all elements after it.
                 And after 1, all were in descending order.

                 So, if we swap 2 with 1, then also 1 is smaller than 3 and as [3,2] was already in descending order,
                 So, after swapping also, [3,1] will be in descending order.
                 
                 So, reversing them will make it ascending order.
                 We get [2,1,3] at last as the next permutation.
                */

                reverse(nums, i+1, len - 1);
                return true;
            }
        }
        
        // if dirty is false, the elements were already sorted in non-ascending order
        // and we stop at this point, as all permutations were generated.
        return false;
    }
    
    public List<Integer> toList(int[] nums){
        // converts nums int array to List of Integer
        return Arrays.stream(nums).boxed().collect(Collectors.toList());
    }
    
    // Problem Question: https://leetcode.com/problems/permutations/
    public List<List<Integer>> permute(int[] nums) {
        /*
        Logic:

        Sort the array in ascending order, and then do next permutations on that till we get to an array,
        which is totally in non-ascending order.
        */

        List<List<Integer>> res = new ArrayList<>();
                
        Arrays.sort(nums); // to get a starting point, we take a sorted array
        res.add(toList(nums));
        
        while(true){
            boolean flag = nextPermutation(nums);
            
            // when nums was in non-ascending order that means we have added that to res before.
            // and when we pass it to nextPermutation, it will return false, and then we break from the loop.
            if(flag == false){
                break;
            }
            
            res.add(toList(nums));
        }
        
        return res;
    }
}
