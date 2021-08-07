import java.util.HashMap;

class CountSublistSameFirstLast {
    // Problem Question: https://binarysearch.com/problems/Count-of-Sublists-with-Same-First-and-Last-Values
    public int solve(int[] nums) {
        /*
        Logic:
        My Pattern says: 
        Ex- [1,2,3,2,5,1,2]
        Count of sublists with more than one values having start and end as 2: 3C2
        Here, 3 is count of occurrences of element 2
        Count of sublists with more than one values having start and end as 1: 2C2
        Here, 2 is count of occurrences of element 1
        
        NC2 = N*(N-1)/2
        So, count the element's occurrences when they occur more than once in the array.
        And, also count single element sublists which is all the elements taken.
        (that's why initialised finalCount with len).
        */
        HashMap<Integer, Integer> counts = new HashMap<>();

        int i, len = nums.length, finalCount, elem;
        finalCount = len;
        for(i=0; i<len; i++){
            elem = nums[i];
            // counting occurrences
            if(counts.containsKey(elem)){
                counts.put(elem, counts.get(elem) + 1);
            }else{
                counts.put(elem, 1);
            }
        }

        for(int count: counts.values()){
            if(count > 1){
                finalCount += count*(count - 1)/2; // nc2
            }
        }

        return finalCount;
    }
}