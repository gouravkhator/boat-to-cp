class FindMinRotatedSortedArr {
    // Problem Question:https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/ 
    public static int findMin(int[] nums) {
        int len= nums.length;
        
        /*
        Logic:

        Check local minimum with mid, left and right element.

        If left one is minimum in this situation (amongst the three), then goto left part of the array.
        (set right to mid - 1)

        If right one is minimum in this situation (amongst the three), then goto right part of the array.
        (set left to mid + 1)

        If mid one is minimum amongst the three, then the case maybe like:
        a) Ex- [5,1,2,3,4]
        b) Ex- [5,6,1,2,3]

        In a), we get that mid is 2 which is minimum amongst 5, 2 and 4 but we have lesser element in left part.
        In b), we get that mid is the minimum amongst 5,1 and 3, so here we check the right part.
        (As in left part, we already get larger elements and right part would also not contain smaller element than mid but just to confirm go there).

        So, if mid one is minimum, go to that part where the smallest neighbour resides.
        (Meaning, in b), for mid element 1, we check for 6 and 2 whichever is minimum go to that part, here right part of array.
        Or, in a), as 1 is smaller than 3 so go to that part, here left part of array.)

        Update gmin or global minimum whenever local minimum is > gmin.
        Answer is the global minimum.
        */

        int left = 0, right = len - 1, mid = 0;
        
        int gmin = Integer.MAX_VALUE;
        int lmin = gmin, lminInd = -1;
        
        while(left <= right){
            mid = left + (right - left) / 2;
            
            // lminInd is the index where lmin is stored now
            if(lmin > nums[mid]){
                lmin = nums[mid];
                lminInd = mid;
            }
            
            if(lmin > nums[left]){
                lmin = nums[left];
                lminInd = left;
            }
            
            if(lmin > nums[right]){
                lmin = nums[right];
                lminInd = right;
            }
            
            if(lminInd == left){
                right = mid - 1;
            }else if(lminInd == right){
                left = mid + 1;
            }else if(lminInd == mid){
                
                if(mid > 0 && nums[mid - 1] < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            
            if(lmin < gmin){
                gmin = lmin;
            }
            
            lmin = Integer.MAX_VALUE;
            lminInd = -1;
        }
        
        return gmin;
    }
    
    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3,4,5,1,2}));
        System.out.println(findMin(new int[]{5,1,2,3,4}));
        System.out.println(findMin(new int[]{1,2}));
        System.out.println(findMin(new int[]{2}));
        System.out.println(findMin(new int[]{2,1}));
        System.out.println(findMin(new int[]{4,1,2,3}));
        System.out.println(findMin(new int[]{4,5,1,2}));
        System.out.println(findMin(new int[]{3,4,6,9,12,2}));
        System.out.println(findMin(new int[]{0,1,3,9,10}));
        System.out.println(findMin(new int[]{10,11,12,0,2}));
        System.out.println(findMin(new int[]{3,4,5,6,7,8,1,2}));
    }
}
