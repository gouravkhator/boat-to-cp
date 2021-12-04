class MovingStonesTillConsecutive {
  // Problem Question: https://leetcode.com/problems/moving-stones-until-consecutive/
  public int[] numMovesStones(int a, int b, int c) {
    /*
    Logic:
    First sort a, b and c to make x, y and z.

    Max. moves is move stone at z to position (y+1),
    and stone at x to position (y-1), irrespective of difference between x, y, z

    Min moves is tricky.
    First get difference between y and x as diffOne and that between z and y as diffTwo.

    If diffOne is 2 and diffTwo >= 2 then min moves can be done by moving stone at z to position between x and y.
    So, min. moves is 1 here.
    Similarly for diffOne >= 2 and diffTwo = 2, then move stone at x to position between y and z.

    If diffOne != 1, and not of above conditions, min moves increments by 1.
    (it means if x is not y-1 then, we can move stone at x to position y-1 in 1 jump)

    If diffTwo != 1, min moves increments by 1.
    (it means if z is not y+1 then, we can move stone at z to position y+1 in 1 jump)

    This way, if both differences are > 2, then min moves becomes 2 (incremented twice as told above).
    */
    
    int[] answer = new int[2];
    
    int x = Math.min(Math.min(a,b),c);
    int z = Math.max(Math.max(a,b),c);
    int y = (a+b+c) - (x+z);
    
    int diffOne = (y - x);
    int diffTwo = (z - y);
    answer[1] = diffOne + diffTwo - 2;
    
    if(diffOne != 1) answer[0] += 1;
    if(diffTwo != 1) answer[0] += 1;
    
    // If below situations are applicable, then reset minimum moves to 1
    if(diffOne >= 2 && diffTwo == 2)
      answer[0] = 1;
    else if(diffOne == 2 && diffTwo >= 2)
      answer[0] = 1;

    return answer;
  }
}
