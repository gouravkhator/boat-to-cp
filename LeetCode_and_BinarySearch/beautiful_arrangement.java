import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

class BeautifulArrangement {
    public int recur(List<List<Integer>> applicable, Set<Integer> visited, int index) {
        if (index >= applicable.size()) {
            return 1;
        }

        List<Integer> currentApplicable = applicable.get(index);

        int count = 0;

        for (Integer elem : currentApplicable) {
            if (!visited.contains(elem)) {
                visited.add(elem);
                count += recur(applicable, visited, index + 1);

                visited.remove(elem);
            }
        }

        return count;
    }

    // Problem Question: https://leetcode.com/problems/beautiful-arrangement/
    public int countArrangement(int n) {
        /*
        Logic:
        
        For each places from 1 to n, there can be only some of the numbers applicable for that place.
        Ex- 
        For n = 3
        _ _ _ 
        
        1st Place can contain {1,2,3}. 2nd Place can contain {1,2}. 3rd Place can contain {1,3}.
        
        Now, we don't want to visit the same number again in that permutation. So, we will recur.
        
        Recur will get the index we are at right now, and the applicable numbers at that index.
        
        If we have not visited that element before in our current traversal, we visit that and recur for next place.
        After recurring, we remove that from our visited list.
        
        Also, we increment the count of what we get from recursion.
        And at last index, we return 1 (as it's a possible set we got).
        
        This counts all possible sets which can be made from applicable numbers.
        
        Also, constraint for n is 1 to 15. So, this is much efficient for max 15 numbers.
        */

        List<List<Integer>> applicable = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            List<Integer> temp = new ArrayList<>();

            for (int j = 1; j <= n; j++) {
                if (j % i == 0 || i % j == 0) {
                    temp.add(j);
                }
            }

            applicable.add(temp);
        }

        return recur(applicable, new HashSet<>(), 0);
    }
}
