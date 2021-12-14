import java.util.HashSet;

class ShortCircuit {
  // below approach is a TLE
  // Problem Question: https://binarysearch.com/problems/Short-Circuit
  public boolean solve(String[] words) {
    /*
     * Logic:
     * This problem is similar to the checking of whether graph contains a hamiltonian cycle or not.
     * In the below approach, we have done a modified version of Travelling salesman problem.
     * 
     * We keep a set of unvisited nodes indices, and then we traverse each unvisited node and,
     * check if it was adjacent to previous visited node.
     * 
     * If it is adjacent, we will visit that node and recur for the rest unvisited nodes.
     * If the unvisited count is 1, we will check if the last unvisited node is adjacent to the 0th node.
     * (so that it forms a cycle)
     * 
     * For visiting and unvisiting a node while iterating through unvisited, we cannot modify the set while iteration.
     * So, keep a cloned modifiable set while iterating, and it will be passed in further recursions.
     */

    HashSet<Integer> unvisitedNodes = new HashSet<>();

    // keeping 0th index as visited one, so not adding it to unvisited nodes
    for (int i = 1; i < words.length; i++) {
      unvisitedNodes.add(i);
    }

    return recursion(unvisitedNodes, 0, words); // startnode will be 0th index node
  }

  public boolean isAdjacent(String s1, String s2) {
    // if s1's last char is equal to s2's first char then s1 is adjacent to s2
    return s1.charAt(s1.length() - 1) == s2.charAt(0);
  }

  public boolean recursion(HashSet<Integer> unvisitedNodes, int startNode, String[] words) {
    if (unvisitedNodes.size() == 1) {
      return isAdjacent(words[unvisitedNodes.iterator().next()], words[0]);
    }

    HashSet<Integer> modifiableUnvisitedSet = (HashSet<Integer>) unvisitedNodes.clone();

    for (Integer node : unvisitedNodes) {
      if (node != startNode && isAdjacent(words[startNode], words[node])) {
        // current word is adjacent to words[node]
        modifiableUnvisitedSet.remove(node); // visiting this node, so removing it from unvisited

        boolean temp = recursion(modifiableUnvisitedSet, node, words);

        if (temp == true) {
          // if result is true, meaning we found a hamiltonian cycle, we can return true
          // directly
          return true;
        }

        modifiableUnvisitedSet.add(node); // we again unvisit this node
      }
    }

    return false;
  }
}
