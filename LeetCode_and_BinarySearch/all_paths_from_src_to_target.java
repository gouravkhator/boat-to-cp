import java.util.*;

class AllPathsSrcToTarget {
  public void pathRecur(int[][] graph, int currentNode, List<Integer> currPath, List<List<Integer>> resPaths){
    /*
    Add the next child to the current path, and then recur for that child.
    If I get to the target node, then add the current path to the paths list.
    And after backtracking, I remove the taken node and then take another node in the current path.
    
    This is the most optimal code, we can find anywhere.
    */
    
    int targetNode = graph.length - 1;
    
    if(currentNode == targetNode){
      resPaths.add(new ArrayList<>(currPath));
      return;
    }
        
    for(int i=0; i < graph[currentNode].length; i++){
      currPath.add(graph[currentNode][i]);
      pathRecur(graph, graph[currentNode][i], currPath, resPaths);
      currPath.remove(Integer.valueOf(graph[currentNode][i]));
    }
  }
  
  // Problem Question: https://leetcode.com/problems/all-paths-from-source-to-target/
  public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    List<List<Integer>> resPaths = new ArrayList<>();
    
    List<Integer> currPath = new ArrayList<>();
    currPath.add(0);
    
    pathRecur(graph, 0, currPath, resPaths);
    
    return resPaths;
  }
}