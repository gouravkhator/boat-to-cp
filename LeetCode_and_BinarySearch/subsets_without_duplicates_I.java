import java.util.ArrayList;
import java.util.List;

class Subsets_I{
  // Problem Question: https://leetcode.com/problems/subsets/description
  public static List<List<Integer>> subsets(int nums[]){
    int length = nums.length;

        List<List<Integer>> powerset = new ArrayList<>();

        int totalPowerSets = (int)Math.pow(2, length);
        int[][] binary = new int[totalPowerSets][length];
        /*
        binary for length=3 would contain:
        Indices are as follows:
        012
        binary array:
        000
        100
        010
        110
        001
        101
        011
        111
        
        Then for each 1 in binary array, print that nums element at that index (same as the column's index)

        This logic is used as creating power set means, each element can occur once or cannot occur at all.
        (and all elements in nums array are unique)
        */

        for(int col=0; col<length; col++){
          int consecutiveZerosCount = (int)Math.pow(2, col);
          int row = 0;
          while(row < totalPowerSets){

            for(int i=0; i<consecutiveZerosCount && row < totalPowerSets; i++, row++){
              binary[row][col] = 0;
            }

            for(int i=0; i<consecutiveZerosCount && row < totalPowerSets; i++, row++){
              binary[row][col] = 1;
            }
            
          }
        }
        
        for(int i=0;i<totalPowerSets;i++){
          List<Integer> currentList = new ArrayList<>();
          for(int j=0;j<length;j++){
            if(binary[i][j] == 1){
              currentList.add(Integer.valueOf(nums[j]));
            }
          }

          powerset.add(currentList);
        }
        return powerset;
  }

  public static void main(String[] args) {
    System.out.println(subsets(new int[]{1,5,9,6}));
  }
}