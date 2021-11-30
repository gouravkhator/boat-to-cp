import java.util.HashMap;
import java.util.Map;

class PairsDivisibleByK{
    /*
    Question:

    Write a function that takes as its input a non-empty list of integers and a positive integer k.
    Find and print the number of pairs where i < j and list[i] + list[j] is divisible by k.

    Ex- list = [1,3,2,9,-7,8,0,15] and k = 5
    Output:
    4

    4 pairs are (1,9), (3,2), (0,15), (2,-7)
    */
    public static void main(String[] args) {
        int[] arr = {1,3,5,2,9,8,10,15};
        int[] arr1 = {1,3,2,9,-7,8,0,15};
        int k = 5;

        System.out.println(countPairsDivisibleByK(arr, k));
        System.out.println(countPairsDivisibleByK(arr1, k));
    }

    static int countPairsDivisibleByK(int[] arr, int k) {
        /*
        Logic:

        i < j is just redundant condition, as if we need to find pairs, any pair will have 1 element before another element.
        And we are said to find pairs such that list[i] + list[j] is divisible by k.

        That also infers that (list[i] % k) + (list[j] % k) leads to sum k or 0.
        If we mod 0 or numbers which are divisible by k, then we get 0.
        So, for numbers divisible by k, we need sum of mod pairs to be 0.

        If we mod positive or negative numbers by k, then we get a positive number.
        So, for numbers not divisible by k, we need sum equal to k.
        Meaning for a specific mod, we need to find (k - mod) to get the remaining mod which makes up the pair.

        So, we do the mod and store the mods and their occurrences in hashmap.
        Occurrences are required so that we can multiply the required occurrences to get the resultant count.
        
        For all mod which are zeros, we know that number of possible pairs from those numbers will be (zerosCount C 2)
        For all mod which are non-zeros, the other remaining number (k - currentMod) needs to be present in hashmap.
        Then, 2 cases arise:
        1: If (2 * currentMod) == k, that means same mod can be a pair to sum to k.
        Ex- (2,6) with k as 4. So, mods are (2,2) and 2+2 makes 4 so (2,6) is a valid pair.

        2: If currentMod < (k - currentMod), this means, one element is smaller than the remaining other element.
        It is required so that we only count the pairs once and not twice.
        Bcoz when we encounter larger element, we again check for pairs of it with smaller remaining element.
        So, it will count twice. So avoiding this case too.

        Time Complexity: O(n + k) meaning if n is larger, it is O(n) else O(k)
        Space complexity: O(k) as HashMap only contains (k-1) keys at max.
        (As we store mods in that, modulo k and we exclude 0 as we calculate count for zero mods differently).

        Note that k is positive. So k cannot be zero or negative. So we can easily do modular arithmetic.
        */
        
        int len = arr.length, count = 0, zerosCount = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<len;i++){
            arr[i] %= k;
            // as we mod all the elements so hashmap can contain keys ranging from 1 to k - 1
            // that means maximum k-1 keys are stored in hashmap, irrespective of how big the array is.

            if(arr[i] == 0){
                zerosCount++;
                continue;
            }

            if(map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i]) + 1);
            }else{
                map.put(arr[i], 1);
            }
        }

        count += (zerosCount*(zerosCount - 1))/2; // zerosCount C 2

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int freq = entry.getValue();
            
            if(!map.containsKey(k - key)){
                continue;
            }

            // map contains the remaining element too
            // if this key is smaller than the other remaining key, then we calculate the pairs possible
            // if this key would have been larger than other remaining key, we should skip it as we already calculated that when the other was smaller.
            if(key < (k-key)){
                count += freq * map.get(k - key);
            }else if(key == (k - key)){
                // key+key will sum to k
                count += (freq*(freq - 1))/2;
            }
        }

        return count;
    }
}