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

        In compare, String a is always the one that's present after String b in numsStr array.
        If a starts with b or b starts with a, that means we need to check what will be the order to merge them

        s1 contains first a then b. s2 contains first b then a.

        If s1 int value is greater than s2 int value, that means:

        a should be placed before b. And as a is always placed after b, we would swap them.
        (swapping means returning negative number, so return -1)

        If s2 int value is greater than or equal to s1 int value, that means:
        b should be placed before a. And as b is already placed before a, we don't swap them.
        (no swap means returning positive number, so return 1).

        If neither b starts with a nor a starts with b,
        Then, if b > a lexicographically, then return +ve number (means no swap).
        If a > b lexicographically, then return -ve number (means swap).

        This return is controlled by compareTo method.
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
