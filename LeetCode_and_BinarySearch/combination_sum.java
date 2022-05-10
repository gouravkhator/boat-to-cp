import java.util.*;

// This approach is a better one, as we have much lesser recursive calls.
class CombinationSumBetter {
    // Problem Question: https://leetcode.com/problems/combination-sum/
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        /**
         * This approach is a better one, as we have much lesser recursive calls.
         * 
         * Time complexity is O(2^n)
         * Space complexity is O(target/minimum_number) extra space.
         * 
         * --------------------------------------------------------------
         * 
         * Explanation of the time and space complexity:
         * 
         * For example: [2,3,5,1] and target is 9
         * 
         *                       [root]
         *              /       /       \   \
         *             2        3       5    1
         *          / / \ \    / / \    / \   \
         *         2  3 5  1  3  5  1  5  1    1
         *         
         * So, we go like this. For 2, we have n children. For 3, we have (n - 1)children.
         * It is because from 2, we will eventually get to all possible combinations of 2 and 3.
         * 
         * So, we don't need to check from 3 to 2 again, and so, 3 does not have the child as 2.
         * 
         * Time complexity is O(2^n) because it involves either including a number, or excluding a number.
         * 
         * Extra space is needed for storing the current visited list, taking up at max (target/min_number) size.
         * List<List<Integer>> is not considered as extra space, as we have to output that itself.
         * 
         * So, space complexity is O(target/min_number) extra space.
         */
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        recur(target, candidates, 0, new ArrayList<>(), result);
        return result;
    }

    static void recur(int remainingSum, int[] nums, int startIndex, ArrayList<Integer> list,
            List<List<Integer>> result) {
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
        
        Why the combinations come out to be unique?
        > It is bcoz, we have those unique paths created as per the state space tree. We will not encounter the same type of path twice.
        */

        if (remainingSum < 0) {
            return;
        }

        if (remainingSum == 0) {
            result.add(new ArrayList<>(list));
            list = new ArrayList<>();
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            // visit a number and recur for next occurrences of the same number
            list.add(Integer.valueOf(nums[i])); // add this number
            recur(remainingSum - nums[i], nums, i, list, result);
            list.remove(Integer.valueOf(nums[i])); // we exclude this number and try for next number
        }
    }
}

// Same time complexity as the better approach, but we do much more recursive calls and then shortlist
class CombinationSumLessBetter {
    public void combinationSumRecur(int[] candidates, int target, int currIndex, List<List<Integer>> combinations,
            List<Integer> currVisitedNums) {
        if (currIndex >= candidates.length) {
            return;
        }

        if (target < 0) {
            return;
        }

        if (target == 0) {
            List<Integer> clonedList = new ArrayList<>(currVisitedNums);
            combinations.add(clonedList);
            return;
        }

        // include this current number
        /*
        Note: adding or removing to a list is to be done as Integer and not int,
        as add and remove also takes up int in parameter,
        and by int, they will work to add and remove by index, and not by value.
        */
        currVisitedNums.add(Integer.valueOf(candidates[currIndex]));
        combinationSumRecur(candidates, target - candidates[currIndex], currIndex, combinations, currVisitedNums);

        // don't include this current number
        currVisitedNums.remove(Integer.valueOf(candidates[currIndex]));
        combinationSumRecur(candidates, target, currIndex + 1, combinations, currVisitedNums);
    }

    // Problem Question: https://leetcode.com/problems/combination-sum/
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        /**
         * Approach of this less better way of solving the combinations sum problem:
         * 
         * Time complexity is O(2^n)
         * Space complexity is O(target/minimum_number) extra space.
         * 
         * --------------------------------------------------------------------
         * 
         * Either include this current index, or don't include this current index.
         * If we include this index, and as we can have multiple occurrences of this current index, we will still give this currnet index another chance.
         * 
         * If we don't include this index, then all chances were already given till now for this path,
         * and we will not give index any more chance for this path.
         * 
         * Base cases are when the index >= array's length, or when the remaining sum is < 0, 
         * or when remaining sum is 0, meaning then we save the current visited numbers to the main list of lists.
         * 
         * So, we just increment the index by 1, to give other index the chance.
         * 
         * State space tree for the candidates = [2,3,5,1] and target as 9
         * I am representing the edge with i:number for including a number, and e:number for excluding it.
         * And I am representing the value of node for the remaining sum.
         * 
         *                                 9
         *                     i:2 /               \ e:2
         *  Remaining sum -->     7                 9
         *                  i:2  /   \ e:2     i:3 /  \ e:3
         *                     5     7
         *                i:2 /  i:3 / \ e:3
         *                   3      4    7
         *              i:2 /  i:3 / \ e:3
         *                  1     1  4
         *                      i:5 / \ e:5
         *          
         * When the sum becomes negative, we don't go forward in that path.
         * When sum becomes 0, we just add the path's elements to the main list of lists.
         * 
         * Explanation of time complexity:
         * > At each level, we have 2 decisions to make only.
         * And the maximum height of tree can be (number_of_elements + target_sum/min_number)
         * 
         * Why the height is as above?
         * > At max, we can have number of elements to be traversed in the depth. But, sometimes, some numbers are repeated too.
         * And we don't go for all multiples of every number, and we stop when sum < 0.
         *   
         * Time complexity is O(2^n) as each element can have two decisions,
         * and there are approx n levels/heights for those.
         * 
         * Space complexity is just for extra space of current visited array which is at max (target_sum/min_number)
         */
        List<List<Integer>> combinations = new ArrayList<>();

        combinationSumRecur(candidates, target, 0, combinations, new ArrayList<>());

        return combinations;
    }
}
