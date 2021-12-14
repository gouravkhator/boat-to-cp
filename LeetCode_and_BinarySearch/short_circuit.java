class ShortCircuit {
  // below approach is a TLE
  // Problem Question: https://binarysearch.com/problems/Short-Circuit
  public boolean solve(String[] words) {
    // we would do hamiltonian cycle problem here
    // below we did a modified version of tsp problem..
    
    int nodesCount = words.length;

    boolean[] isVisitedList = new boolean[nodesCount];
    isVisitedList[0] = true;

    return recursion(isVisitedList, 0, nodesCount, words);
  }

  public boolean isAdjacent(String start, String end){
    return start.charAt(start.length() - 1) == end.charAt(0);
  }

  public boolean recursion(boolean[] isVisitedList, int startNode, int unvisitedCount, String[] words) {
    if(unvisitedCount == 1){
      return isAdjacent(words[startNode], words[0]);
    }

    for(int i=0; i<words.length; i++){
      if(i!=startNode && isAdjacent(words[startNode], words[i]) && isVisitedList[i] == false){
        // current word is adjacent to words[i]
        isVisitedList[i] = true;
        boolean temp = recursion(isVisitedList, i, unvisitedCount - 1, words);

        if(temp == true){
          return true;
        }

        isVisitedList[i] = false;
      }
    }

    return false;
  }
}
