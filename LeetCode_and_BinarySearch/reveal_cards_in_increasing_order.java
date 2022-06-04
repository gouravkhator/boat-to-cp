class RevealCardsInIncreasingOrder {
  // Problem Question: https://leetcode.com/problems/reveal-cards-in-increasing-order/
  public int[] deckRevealedIncreasing(int[] deck) {
    /*
    Assume for length n, we are taking a deck array as [1...n]
    
    Pattern:

    For length 7, order will be [1,6,2,5,3,7,4]
    For length 8, order will be [1,5,2,7,3,6,4,8]
    For length 9, order will be [1,9,2,6,3,8,4,7,5]
    For length 5, order will be [1,5,2,4,3]
    For length 4, order will be [1,3,2,4]
    
    My approach:
    -------------
    
    Just fill the available indices alternatively, and if I finish filling the available index at the last currently available index, then in next iteration, I have to leave the first available index, and start filling after that.
    
    (Just to be alternative and cyclic filling of numbers)
    
    -----------------------------------------------------------------------------
    
    Another approach saw in discuss section was using Queue: https://leetcode.com/problems/reveal-cards-in-increasing-order/discuss/2044782/Short-Simple-and-concise-or-Queue-or-C%2B%2B
    
    Extra Notes:
    -------------
    * Actually my approach was inspired from the pattern of filling the elements, and filled the way, they should be, in iterations. 
    * The approach in discuss section is based on how the question is told.
    Have the list of indices, and then fill in the 1st index in the queue, and then pop the next index and push that to the end of the queue..
    * And as we can see, I was also checking for next alternative available indices, just that I was doing it in cyclic movements, and the queue's approach did that in linear movement, enqueuing and dequeuing indices from/to the queue.
    */
    int len = deck.length;
    
    Arrays.sort(deck);
    
    int[] order = new int[len];
    int k = 0;
    
    List<Integer> availableIndices = new ArrayList<>();
    
    for(int i=0; i<len; i++){
      availableIndices.add(i);
    }
    
    List<Integer> tempAvailable = new ArrayList<>(availableIndices);
    
    int startAvlIndex = 0;

    while(availableIndices.size() > 0){
      int size = availableIndices.size();
      
      for(int i=startAvlIndex; i<size; i+=2){
        order[availableIndices.get(i)] = deck[k++];
        tempAvailable.remove(availableIndices.get(i));
      }

      if(order[availableIndices.get(size - 1)] == 0){
        // if we didn't fill the last available index now, then we can start from the first available index to fill the numbers 
        startAvlIndex = 0;
      }else{
        startAvlIndex = 1;
      }
      
      availableIndices = tempAvailable;
      tempAvailable = new ArrayList<>(availableIndices);
    }
    
    return order;
  }
}