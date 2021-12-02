import java.util.Arrays;
import java.util.Comparator;

class CreateLargestNumFromList {
    // Problem Question: https://binarysearch.com/problems/Create-Largest-Number-From-a-List
    public static String solve(int[] nums) {
        int i=0, len = nums.length;
        String[] numsStr = new String[len];

        for(i=0; i<len; i++){
            numsStr[i] = "" + nums[i];
        }

        /*
        Main logic lies in sorting the strings. The sort is not regular reverse sort. It's more tricky.

        There can be numbers like 1 and 10 where if we do a normal sort it will treat 10 as bigger one than 1.
        And it will keep it as 10, 1 which merges to 101 which is not the largest number formed.
        It should be 110.

        For this, we check if either b starts with a or a starts with b, then we merge them both ways.
        One string s1 will have a first, followed by b. s2 will have b first, followed by a.

        In the array, a has occurred before b.
        So, if s1 (in int form) > s2 (in int form), it means by keeping a before b, we get larger number.
        So, we don't do swap. So return negative number.

        If s1 < s2 (both are in int form), then it means by keeping b before a, we get larger number.
        So, we swap a and b. So return positive number.
        
        If neither b starts with a nor a starts with b, then it means:
        Ex- 72 and 59 here, we can just compare the strings and swap accordingly..

        If a is larger than b, and as a always comes before b in array, so we don't swap.
        If b is larger than a, we swap.
        To achieve this check easily, use compareTo. 
        b.compareTo(a) returns positive number when b is larger than a, so the sort method will swap them.
        Else will not swap.
        */

        Arrays.sort(numsStr, new Comparator<String>(){
            public int compare(String a, String b){
              if(b.startsWith(a) || a.startsWith(b)){
                  String s1 = a+b;
                  String s2 = b+a;
                  int s1i = Integer.parseInt(s1);
                  int s2i = Integer.parseInt(s2);

                  return (s1i > s2i) ? -1 : 1;
                }
                
                return b.compareTo(a);
            }
        });

        String res = "";
        for(String s: numsStr){
            res+= s;
        }
        
        return res;
    }

    public static void main(String[] args) {
       System.out.println(solve(new int[]{1, 10,7, 76, 415, 1,12}));
    }
}
