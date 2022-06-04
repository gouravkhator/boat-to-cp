class LetterTilePossibilities {  
  public void possibilitiesRecur(String tiles, int index, String prevStr, Set<Integer> visitedIndices, Set<String> possibilities){
    /*
    Approach:
    ---------
    
    For example: tiles = "AAB"
                .
            A  ;  A should be there, but already processed ;  B
          A;B  |  ----------------------------------------- | A; another A cannot be processed in this level, as already processed in path "BA"
          B;A  | ----------------------------------------- | A; 

    And at every step, we have to count the string processed till then.
    
    That means count "A", "B" at first level.
    Count "AA", "AB" at second level which started at "A". Count "BA" at second level.
    
    Also note that if a string is already processed, we don't process it or its path, and this optimizes so much.
    
    But, then also we may have multiple identical possibilities at the end, and so keeping those in set is better, which removes duplicates.
    
    We keep track of visited indices, and just visit the unvisited ones in further paths.
    When we backtrack, we remove the indices from visited set, one by one.
    
    Lastly, the count of possibilities is the number of elements in the set "possibilities".
    */
    
    if(possibilities.contains(prevStr)){
      // don't visit the already visited strings/possibilities
      // this optimizes so much, to not revisit the same paths again.
      return;
    }
    
    possibilities.add(prevStr); // this will also add "" (empty string) to the possibilities, so while returning the answer, subtract it by 1
    
    int tilesCount = tiles.length();
    
    for(int i=0; i<tilesCount; i++){
      if(visitedIndices.contains(i)){
        // ignore visited indices
        continue;
      }
      
      visitedIndices.add(i); // visit 'i' index
      possibilitiesRecur(tiles, i, prevStr + tiles.charAt(i), visitedIndices, possibilities); // recur
      visitedIndices.remove(i); // unvisit the 'i' index
    }
  }
  
  // Problem Question: https://leetcode.com/problems/letter-tile-possibilities/
  public int numTilePossibilities(String tiles) {
    Set<String> possibilities = new HashSet<>();
    
    possibilitiesRecur(tiles, 0, "", new HashSet<>(), possibilities);
    
    return possibilities.size() - 1; // as we have added empty string into the set possibilities in the recursion
  }
}