import java.util.*;

class FindLocalPeaksSequel {
    public boolean checkSmallNearNum(int index, int[] nums, int len){
        // This method checks if there are small numbers nearby or not.
        /*
        Logic:

        If index is 0, then check for right side for smaller number after its duplicates.
        If index is len - 1, then check for left side for smaller number after its duplicates.

        If index is not 0 or len - 1, 
        then check if left side is all duplicates of current value 
        but right side has 1 number which is less than current number after duplicates.
        Or, check if right side is all duplicates of current value 
        but left side has 1 number which is less than current number after duplicates.

        Or, check both sides have 1 number on each side after duplicates of current value.
        In all above cases, return true else false.

        Optimisation can be done by not repeating similar checks in solve method,
        But not sure why but that optimisation fails for an array like:
        [1,1,1,1,1,1,1,.....1]

        The largest length with all duplicate values, then it was having TLE.
        So, keeping below approach for now.
        */

        int i=0;
        if(index == 0){
            for(i=index+1; i<len && nums[i] == nums[index]; i++);

            if(i<len && nums[i] < nums[index]){
                return true;
            }
        }else if(index == len - 1){
            for(i=index-1; i>=0 && nums[i] == nums[index]; i--);

            if(i>=0 && nums[i] < nums[index]){
                return true;
            }
        }else{
            int left=0, right = 0;
            for(left=index - 1; left>=0 && nums[left] == nums[index]; left--);

            for(right = index+1; right<len && nums[right] == nums[index]; right++);

            if(left == -1 && right < len && nums[right] < nums[index]){
                return true;
            }

            if(right == len && left >= 0 && nums[left] < nums[index]){
                return true;
            }

            if(right < len && left >=0 && nums[left] < nums[index] && nums[right] < nums[index]){
                return true;
            }
        }

        return false;
    }

    // Problem Question: https://binarysearch.com/problems/Find-Local-Peaks-Sequel
    public int[] solve(int[] nums) {
        int len = nums.length;
        if(len == 0 || len == 1) return new int[]{};

        ArrayList<Integer> indices = new ArrayList<>();
        int i=0;
        for(i=0; i<len; i++){
            if(i == 0){
                // if index is 0, check for rightside next value
                if(nums[i + 1] < nums[i]){
                    indices.add(i);
                }else if(nums[i+1] == nums[i]){
                    boolean flag = checkSmallNearNum(i, nums, len);
                    if(flag == true){
                        indices.add(i);
                    }
                }
            }else if(i == len - 1){
                // if index is len - 1, check for leftside next value
                if(nums[i - 1] < nums[i]){
                    indices.add(i);
                }else if(nums[i-1] == nums[i]){
                    boolean flag = checkSmallNearNum(i, nums, len);
                    if(flag == true){
                        indices.add(i);
                    }
                }
            }else {
                // if the element is in middle and not on ends,
                // check if both values on its sides is less than current then just add current index to valid ones.
                // else if both values are either < or = to the current value, 
                // then check if smaller value exists on either side and act accordingly
                if(nums[i + 1] < nums[i] && nums[i-1] < nums[i]){
                    indices.add(i);
                }else if(nums[i+1] <= nums[i] && nums[i-1] <= nums[i]){
                    boolean flag = checkSmallNearNum(i, nums, len);
                    if(flag == true){
                        indices.add(i);
                    }
                }
            }
        }

        int indicesLen = indices.size();
        int[] arr = new int[indicesLen];
        for(i=0; i<indicesLen; i++){
            arr[i] = indices.get(i);
        }
        return arr;
    }
}
