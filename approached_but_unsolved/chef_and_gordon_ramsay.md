# [Chef And Gordon Ramsay](https://www.codechef.com/problems/CHGORAM)

## Approach

Make an adjacency list.

First check the pattern and if the pattern does not have smallest vertex as the first in triplet, don't start with vertex 1 (as it won't fit in the pattern)

If pattern has only smallest as first vertex don't start with last vertex (highest one).

For each vertex ex- for vertex 1, we see that 2 is in its list. Traverse list of vertex 2. If again the visited vertices come, ignore it.

In vertex 2 list, 1 is there, 3 is there and 4 is there. So 1 outcome is 1,2,3 or 1,2,4.

Check if the triplets match the pattern of Gordon if yes then increment count.

Now start the source at vertex 2.
Do similar things until we get more triplets.

Stop when all vertices have been taken as source.

## Code | Not Submitted

```java
import java.util.ArrayList;

class ChefGordonRamsay{
  class Triplet<T>{
    T a, b, c;
    public Triplet(T a, T b, T c){
      this.a = a;
      this.b = b;
      this.c = c;
    }
  }

  public static void countTriplets(ArrayList<Integer>[] adjacencyList, int n, Triplet<Integer> pattern) {
    int count = 0, startVertex = 1, endVertex = n;

    if(pattern.a > pattern.b && pattern.a > pattern.c){
      // start with vertex 2 and not with vertex 1
      startVertex = 2;
    }else if(pattern.a < pattern.b && pattern.a < pattern.c){
      // don't start with end vertex
      endVertex = n-1;
    }

    ArrayList<Integer> visitedVertices = new ArrayList<>();

    for(int i=startVertex; i<=endVertex; i++){
      visitedVertices.add(i);
      recursivePatternMatch(i, n, pattern, visitedVertices, adjacencyList);
      visitedVertices.clear();
    }
  }

  private static void recursivePatternMatch(int source, int n, Triplet<Integer> pattern, ArrayList<Integer> visited, ArrayList<Integer>[] adjacencyList) {
    if(source > n){
      if(visited.size() == 3){
        // TODO: check pattern

        return;
      }
    }

    for (Integer elem : adjacencyList[source]) {
      int visitedCount = visited.size();
      int currentA = visited.get(0);
      if(visitedCount == 2){
        int currentB = visited.get(1);

        boolean flag = ((pattern.a > pattern.b) != (currentA > currentB));
        if(flag == true){
          visited.clear();
          continue;
        }
        
      }else if(visitedCount == 3){

      }
    }
  }

  // Problem Question: https://www.codechef.com/problems/CHGORAM
  public static void main(String[] args) {
        
  }
}
```