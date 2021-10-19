import java.util.*;

class CombinationSum {
    // Problem Question: https://leetcode.com/problems/combination-sum/
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        recur(target, candidates, 0, new ArrayList<>(), result);
        return result;
    }
	
	static void recur(int remainingSum, int[] nums, int startIndex, ArrayList<Integer> list, List<List<Integer>> result) {
        /*
        Logic:

        If remaining sum is negative, just return.

        If remaining sum is 0, that means create a new list out of that temp list (as that temp list will get modified afterwards too..)
        Add that new list to result.

        As we need duplications of same element, we start from 0 to full length and take every number.
        For each number, we take that number into account in the list, and then recur from same index in which that number is present.

        If we were to take each number at most once, we would have recurred with startIndex as i+1.
        This means, now recur from next element.
        
        Then, we remove that number from list, which means we exclude that occurrence of that number.
        Thus, we get duplicate occurrences of same number.
        */

        if(remainingSum < 0){
            return;
        }
        
        if(remainingSum == 0){
            result.add(new ArrayList<>(list));
            list = new ArrayList<>();
            return;
        }
        
		for(int i=startIndex; i<nums.length; i++){
            list.add(nums[i]);
            recur(remainingSum - nums[i], nums, i, list, result);
            list.remove(list.size() - 1);
        }
	}
}
