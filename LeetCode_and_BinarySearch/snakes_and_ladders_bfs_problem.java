class SnakesAndLaddersBFS {
  class Node{
      int val, moves;
      
      Node(int val, int moves){
          this.val = val;
          this.moves = moves;
      }
  }
  
  /*
  Converting cell number to row and column is done here..
  
  All the forumlas are derived from patterns, derived from taking different examples of rows and cols.
  */
  public int[] convertCellToRowCol(int cellNumber, int boardLen){
      int row = (boardLen - 1) - (cellNumber - 1) / boardLen;
      
      int col = 0;
      
      /*
      For even boardLen, the row number for the start of snakes and ladder game will be (boardLen - 1) which is odd.
      For odd boardLen, the row number for the start of snakes and ladder game will be (boardLen - 1) which is even.
      
      So we cannot just check if row number is odd or even,
      rather we have to check if  start of the game that is cell number 1 had been in row 0, what we would have done.
      
      To assume it to row 0, we just do (boardLen - 1 - actual_row_number), and now we know, that 1 will always be assumed in row 0, irrespective of boardLen.
      
      So, we can check by that created row number.
      */
      
      if((boardLen - 1 - row) % 2 == 0){
          // even row number when checked from bottom, so the numbers increase from board's left to board's right
          col = (cellNumber - 1) % boardLen;
      }else {
          // odd row number when checked from bottom, so the numbers increase from board's right to board's left
          col = (boardLen - 1) - (cellNumber - 1) % boardLen;
      }

      return new int[]{row, col};
  }

  public int bfsSnakesLadders(int sourceCell, int[][] board){
      /*
      Approach:
                                    1
                      /                       /   /  \ \ \
               2 (replaced by 15)             3    4  5  6 7
              / \                   \         /     
            16  17 (replaced by 13) 18 ....   4 (4 will not come as it is visited already)
            .......................................................................

      Mainly, we can go from cell 1 to cell 2, 3 etc. to 7
      But when we land on cell 2, we will automatically go to cell 15.
      
      So, it is like a tree, where cell 1 has children as 15,3,4,5,6,7
      (12 is a child of 1, as when landing on 2, it directly goes to 15, and we don't actually land on 2 finally)
      
      From 15, we can goto 16,17,and so on to cell 21.
      But, when landing on cell 17, we directly goto cell 13. So, we don't actually land on 17.
      
      So, node 15 in this tree will have children as 16,13,18,19,20,21.
      Similarly from node 3, 4, 5, 6 and 7, we can goto different nodes, but if the node we are visiting already exists, we will not visit it again, as for example:
      
      From node 1 to 5, we visited in 1 move, but from node 1 to 4 then to 5, we can visit in 2 moves,
      then why to visit from node 4 to node 5, as it will not lead us to shortest path.
      
      As we travel all the children first and push all their children in the queue, then if we get node n^2 in the early level,
      we will not move to next levels of this tree.
      
      This is fully BFS, and BFS for unweighted or uniformly weighted grpah, will give us shortest path for sure.
      
      Why to track moves along with the nodes?
      > We save all values in queue, and we will not be able to tell if a node was in 1st level or 2nd level
      of our visually imagined tree.
      So, we save the moves taken to reach this node, which will always be minimum.
      
      Why not save moves also to the visitedSet?
      > Bcoz, in visitedSet, we only need to store the values visited,
      and we can just use contains method to check if a value is visited or not.
      
      Why BFS and not DFS, and why BFS can give shortest path?
      > The answer to this question is noted in my notes of evernote.
      */
      
      int boardLen = board.length;
      
      Queue<Node> queue = new LinkedList<>();
      
      Set<Integer> visitedSet = new HashSet<>();
      
      queue.add(new Node(sourceCell, 0));
      visitedSet.add(sourceCell);

      while(!queue.isEmpty()){
          Node cellNode = queue.remove();
          int cellValue = cellNode.val;
                    
          for(int i=1; i<=6; i++){
              int nextCell = cellValue + i;
              
              int[] positions = convertCellToRowCol(nextCell, boardLen);
              int row = positions[0], col = positions[1];
                              
              if(board[row][col] != -1){
                  nextCell = board[row][col];
              }
              
              
              if(nextCell == boardLen*boardLen){
                  // we found the last node to be reached..
                  return cellNode.moves + 1;
              }
              
              if(!visitedSet.contains(nextCell)){
                  /*
                  When we add the next cell to the queue, we add them to visited too.
                  
                  For example- 1 has children 2 and 3, and we add 2 and 3 to visited..
                  Then, when we actually goto node 2, and remove that from queue, and then add its children 3 and 4.

                  We see that 3 was already visited so we don't need to add it to visited.
                  */
                  queue.add(new Node(nextCell, cellNode.moves + 1));
                  visitedSet.add(nextCell);
              }
          }
      }
      
      return -1;
  }
  
  
  public int snakesAndLadders(int[][] board) {
      /*
      Test cases:
      
      #1:
      [[1,1,-1],[1,1,1],[-1,1,1]]
      
      #2:
      [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
      */
      
      return bfsSnakesLadders(1, board); // source cell is passed as 1
  }
}