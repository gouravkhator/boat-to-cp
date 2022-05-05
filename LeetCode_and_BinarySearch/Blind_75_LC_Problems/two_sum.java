import java.util.HashMap;
import java.util.Map;

class TwoSum {
    // Problem Question: https://leetcode.com/problems/two-sum/
    public int[] twoSum(int[] nums, int target) {
        /**
         * Approach:
         * 
         * Hashmap is used to save the number and their index.
         * 
         * We just check by the key, if the key is the complement of this current
         * number, then we know that the number and its complement both have been
         * present in this array, and we just return their index.
         * 
         * Else we just save the number and its respective index.
         */
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int rem = target - nums[i];
            if (map.containsKey(rem)) {
                return new int[] { map.get(rem), i };
            }

            map.put(nums[i], i);
        }

        return new int[] {};
    }
}