//problem question : https://leetcode.com/problems/combination-sum/

//In leetcode, its TLE for the given input in main function here
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombinationsSum {

	public static void main(String[] args) {
		int[] arr = new int[] {5,10,8,4,3,12,9};
		int target = 27;
		combinationSum(arr, target);
	}

	static List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		
        int len = candidates.length;
        Map<Integer, Integer> timesMap = new HashMap<Integer, Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> values = new ArrayList<Integer>();
        for(int i=0;i<len;i++){
        	int timesNum = target/candidates[i];
        	
        	if(timesNum >= 1)
        		timesMap.put(candidates[i], timesNum);
        	else
				timesMap.put(candidates[i], 0);
        }
        
        for(Map.Entry<Integer, Integer> entry: timesMap.entrySet()) {
        	int key = entry.getKey();
        	int value = entry.getValue();
        	for(int i=0;i<value;i++) {
        		list.add(key);
        	}
        }
        findSubsets(target, list, list.size(), values, result);
        
        return result;
    }
	
	static void findSubsets(int sum, ArrayList<Integer> arr, int n, ArrayList<Integer> values, List<List<Integer>> result) {
		if(sum == 0) {
			if(!result.contains(values))
				result.add(values);
		}
		if(n==0) {
			return;
		}
		findSubsets(sum, arr, n-1, values, result);
		ArrayList<Integer> v1 = new ArrayList<Integer>(values);
		v1.add(arr.get(n-1));
		//copy the list and add value because values.add will have the reference in the backtrack also
		findSubsets(sum - arr.get(n-1), arr, n-1, v1, result);
	}
}
