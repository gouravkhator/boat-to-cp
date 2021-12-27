# [Cluster Management](https://binarysearch.com/problems/Cluster-Management)

## Approach

Example:

Cores: 9, 10

Tasks: 4,5,6,2,1

The tasks actually fit in any number of cores, maybe it does not fill the cores fully. But if the tasks can be fit in any number of servers then true else false.

Generate all possibilities for each core, like:

For core 9, I can have a set of tasks which have multiple tasks other than single task: 
((4,5), (4,2), (1,2),(5,2),(6,2) etc.)

For core 10, take similar possibility.

Then take all those possibility of them and check if the union of those tasks in sets of each core is the overall tasks or not. If yes then true else false.

## Code | WA Solution

```java
import java.util.*;

class ClusterManagement {
    public static Integer[] sortPrimitiveReverse(int[] arr){
        int len = arr.length, i=0;
        Integer[] converted = new Integer[len];

        for(i=0; i<len; i++){
            converted[i] = arr[i];
        }

        Arrays.sort(converted, Collections.reverseOrder());

        return converted;
    }

    // Problem Question: https://binarysearch.com/problems/Cluster-Management
    public static boolean solve(int[] cores, int[] tasks) {
        int coresLen = cores.length, i=0, minRem=0, minRemIndex=-1;
        Integer[] mainCores = sortPrimitiveReverse(cores);
        Integer[] mainTasks = sortPrimitiveReverse(tasks);

        Integer[] remaining = mainCores.clone();

        for(int elem: mainTasks){
            minRem = Integer.MAX_VALUE;
            minRemIndex = -1;

            for(i=0; i<coresLen; i++){
                if(remaining[i] >= elem && remaining[i] < minRem){
                    minRemIndex = i;
                }
            }

            System.out.println(Arrays.toString(remaining));
            if(minRemIndex != -1)
                remaining[minRemIndex] -= elem;
            else
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(solve(new int[]{8,10}, new int[]{2,3,3,3,7}));
    }
}
```
