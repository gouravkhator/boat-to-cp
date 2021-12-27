# [Latin Square Solver](https://binarysearch.com/problems/Latin-Square-Solver)

## Approach

Just check if the filled number has occurred in the upward elements or the left elements, if yes then return false else continue.

If the number is 0, don't check for any up or left elements.

If number is 0, check for valid numbers on that cell. If list is empty, return false. If list has 1 element, fill it and then update all other unfilled 0's on the same row and same column and reevaluate those zeros.

0 1
2 0

1 2 3
0 1 0
0 0 2

1 3 2
0 1 3
2 0 0

1 2 3
0 0 1
0 0 2

1 0 2
0 1 0
2 0 1

## Code | WA Solution

```java
import java.util.*;

class LatinSquareSolver {
    public static void main(String[] args) {
        // int[][] matrix = new int[][]{
        //     {1, 3, 2},
        //     {2, 0, 1},
        //     {3, 1, 0}
        // };

        int[][] matrix = new int[][]{
            {0, 1},
            {2, 0}
        };

        /*
        Test cases which fail:

        [
            [1,2,3],
            [2,1,2],
            [3,1,2]
        ]

        [
            [0,0],
            [0,0]
        ]
        */

        System.out.println(solve(matrix));
    }

    static class Pair{
        int row, col;

        Pair(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    public static HashMap<Pair, Set<Integer>> sortHashMapBySetLen(HashMap<Pair, Set<Integer>> map){
        List<Map.Entry<Pair, Set<Integer>>> hmlist =
               new LinkedList<Map.Entry<Pair, Set<Integer>>>(map.entrySet());
 
        // Sort the hmlist
        Collections.sort(hmlist, new Comparator<Map.Entry<Pair, Set<Integer>>>() {
            public int compare(Map.Entry<Pair, Set<Integer>> o1,
                               Map.Entry<Pair, Set<Integer>> o2)
            {
                return (o1.getValue().size()) - (o2.getValue().size());
            }
        });
         
        // put data from sorted list to hashmap
        HashMap<Pair, Set<Integer>> temp = new LinkedHashMap<>();
        for (Map.Entry<Pair, Set<Integer>> aa : hmlist) {
            temp.put(aa.getKey(), aa.getValue());
        }

        return temp;
    }

    public static boolean solve(int[][] matrix) {
        List<Pair> zeroLocations = new ArrayList<>();
        int rows = matrix.length, cols = matrix[0].length;

        // TODO: do check if the matrix is already in good state or not

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(matrix[i][j] == 0){
                    Pair p = new Pair(i, j);
                    zeroLocations.add(p);
                }
            }
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Pair, Set<Integer>> availableNums = new HashMap<>();

        for(Pair p: zeroLocations){
            int row = p.row, col=p.col;

            map.clear();

            // traverse row wise and col wise to check the numbers available
            for(int j=0; j<cols; j++){
                if(matrix[row][j] == 0){
                    continue;
                }

                if(map.containsKey(matrix[row][j])){
                    System.out.println(row+" , "+j);
                    return false;
                }else{
                    map.put(matrix[row][j], 1);
                }
            }

            availableNums.put(p, new HashSet<>());

            for(int i=1; i<=rows; i++){
                if(!map.containsKey(i)){
                    availableNums.get(p).add(i);
                }
            }

            map.clear();

            for(int j=0; j<rows; j++){
                if(matrix[j][col] == 0){
                    continue;
                }

                if(map.containsKey(matrix[j][col])){
                    return false;
                }else{
                    map.put(matrix[j][col], 1);
                }
            }

            Set<Integer> tempSet = new HashSet<>();

            for(int i=1; i<=rows; i++){
                if(!map.containsKey(i)){
                    tempSet.add(i);
                }
            }

            availableNums.get(p).retainAll(tempSet);
            if(availableNums.get(p).size() == 0){
                return false;
            }
        }

        // sort availableNums hashmap by length of available numbers list
        sortHashMapBySetLen(availableNums);
        for (Map.Entry<Pair, Set<Integer>> en : availableNums.entrySet()) {
            // traverse through the other zero entries
            Pair p = en.getKey();

            for (Map.Entry<Pair, Set<Integer>> other : availableNums.entrySet()) {
                Pair pOther = other.getKey();

                if(pOther == p || (p.row != pOther.row && p.col != pOther.col)){
                    continue;
                }

                if(other.getValue().size() > en.getValue().size()){
                    continue;
                }

                if(pOther.row == p.row || pOther.col == p.col){
                    en.getValue().removeAll(other.getValue());
                }
            }

            if(en.getValue().size() == 0){
                return false;
            }
        }

        return true;
    }
}
```
