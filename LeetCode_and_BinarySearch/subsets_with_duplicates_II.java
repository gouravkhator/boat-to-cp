import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Subsets_II{
  // Problem Question: https://leetcode.com/problems/subsets-ii/
  public static List<List<Integer>> subsets(int nums[]){
    int length = nums.length;

    Arrays.sort(nums);
    // sorting elements so that duplicate elements appear all at once for a particular element
    // sorting is done so that while adding list to hashset, hashset can compare and remove duplicate lists.

    HashSet<List<Integer>> powerset = new HashSet<>();

    int totalPowerSets = (int)Math.pow(2, length); // number of power sets
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

    The elements can be duplicated, so nums array have been sorted first.
    As after binary array has been created, we can add the elements to hashset of lists.

    Hashset will compare and remove duplicate lists.
    (as list have been sorted way before, so duplicate removal is easy)
    
    Now, convert hashset of lists to list of lists.
    */

    /*
    To create that binary array, following logic was followed:
    Index 0 has alternative 0 and 1. Index 1 has two zeros and two ones and so on. 
    So, count how many conseuctive zeros are needed. 
    And run a loop to fill binary array up column-wise.
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
          // adding current column indexed nums element to currentList
        }
      }

      powerset.add(currentList);
    }

    return new ArrayList<>(powerset); // converting hashset of lists to list of lists
  }

  public static void main(String[] args) {
    System.out.println(subsets(new int[]{4,4,4,1,4}));
  }
}