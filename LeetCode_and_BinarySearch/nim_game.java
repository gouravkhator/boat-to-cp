class NimGame {
    // Problem Question: https://leetcode.com/problems/nim-game/
    public boolean canWinNim(int n) {
        /*
        Logic:
        I first approached with a dp solution:
        dp[1] = dp[2] = dp[3] = true (means I will win)

        Loop from i = 4 to n
            dp[i] = !dp[i-1] || !dp[i-2] || !dp[i-3];

        Return dp[n] it means check previous and compute accordingly, but this solution led to TLE.
        While printing dp, I found that if n is divisible by 4, its false, else true.
        */
        return (n%4) != 0;
    }
}
