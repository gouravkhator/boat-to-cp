// https://leetcode.com/problems/combination-sum/
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<List<Integer>>();
        int length = candidates.length;
        Arrays.sort(candidates);
        List<Integer> temp = new ArrayList<Integer>();
        getCombinations(combinations,candidates,temp,target,length,0);
        return combinations;
        
    }
     void   getCombinations(List<List<Integer>> combinations,int[] arr,List<Integer> temp,int remaining,int n,int index)
    {
        if(remaining<0)
            return;
        if(remaining == 0)
        {
            List<Integer> res = new ArrayList<Integer>(temp);
            combinations.add(res);
            return;
        }
        for(int i=index;i<n;i++)
        {
            if(remaining<arr[i])
                break;
            temp.add(arr[i]);
            getCombinations(combinations,arr,temp,remaining-arr[i],n,i);
            temp.remove(new Integer(arr[i]));
        }
    }
}