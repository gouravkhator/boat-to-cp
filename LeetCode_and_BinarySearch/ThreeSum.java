//problem question : https://leetcode.com/problems/3sum/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSum{

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); //sorting as binary search is used here
        int len = nums.length;
        Set<List<Integer>> resultSet = new HashSet<List<Integer>>();
        
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                int first = nums[i];
                int second = nums[j];
                int index = Arrays.binarySearch(nums, -1*(nums[i]+nums[j]));
                //finding the third element in the array
                
                if(index >= 0 && index!=i && index!=j){
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(first);
                    temp.add(second);
                    temp.add(nums[index]);
                    Collections.sort(temp); 
                    //sorting the list so that set of lists will check element by element and remove duplicate lists
                    resultSet.add(temp);
                }
            }
        }
        
        //making lists of lists from set of lists
        return new ArrayList<>(resultSet);
    }

	public static void main(String[] args) {
		System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
	}
}