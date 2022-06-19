import java.util.*;

class NumberOfProvinces {
  // performs BFS from a particular src..
  public void bfs(int[][] isConnected, boolean[] visitedArr, int src) {
    int n = isConnected.length;
    Queue<Integer> queue = new LinkedList<>();

    queue.add(src); // city 'src' is added, taking into account 0-based-indexing

    while (!queue.isEmpty()) {
      int city = queue.poll();
      visitedArr[city] = true; // once we remove the city from the queue, we mark them as visited

      for (int i = 0; i < n; i++) {
        if (city == i) {
          continue;
        }

        if (isConnected[city][i] == 1 && visitedArr[i] == false) {
          // if the connection is there, and it is not visited, then add that to queue
          queue.add(i);
        }
      }
    }
  }

  // Problem Question: https://leetcode.com/problems/number-of-provinces/
  public int findCircleNum(int[][] isConnected) {
    /*
    Time complexity will be O(V+E),
    as we visit all the nodes exactly once, and maybe some have extra edges, so depends on number of edges too..
    We do loops and BFS, but overall the nodes are visited exactly once, and then they are marked as visited..
    
    Space complexity: O(V) where V is the number of vertices.
    */
    int n = isConnected.length, provincesCount = 0;
    boolean[] visitedArr = new boolean[n];

    for (int i = 0; i < n; i++) {
      /*
      check for all nodes, and if the node is not visited yet, it means no BFS could visit this.
      So, it is completely different, and thus a province..
      Now, for this, do the BFS, and also count that as one province..
      */
      if (visitedArr[i] == false) {
        provincesCount++;
        bfs(isConnected, visitedArr, i);
      }
    }

    return provincesCount;
  }
}