class NimGame {
    // Problem Question: https://leetcode.com/problems/nim-game/
    public boolean canWinNim(int n) {
        /*
        Logic:

        Main question said this: 
        Let's say n=5:
        then I would take 1 stone, the other guy can take at max 3 stones, and the remainining stones will be taken by me.
        So, I always win.

        I found a pattern that if n is divisible by 4, it's false, else true.
        */

        return (n%4) != 0;
    }
}
