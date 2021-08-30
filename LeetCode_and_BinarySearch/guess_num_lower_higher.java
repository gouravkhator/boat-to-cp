class GuessNumLowerHigher {
    // Its a function which is already in the api of the question
    public int guess(int num) {
        return 0;
    }

    // Problem Question: https://leetcode.com/problems/guess-number-higher-or-lower/
    public int guessNumber(int n) {
        int min = 1, max = n, res = 0;
        
        int num = 0;
        /*
        Logic:

        It's similar to binary search.
        Check with current num, if the guess is right, break the loop.

        If guess return -1, then the num is on left side of current num, so set max to num - 1.
        If guess return 1, then the num is on right side of current num, so set min to num + 1.
        */
        while(true){
            num = min + (max - min)/2;
            res = guess(num);
            if(res == 0){
                break;
            }else if(res < 0){
                max = num - 1;
            }else {
                min = num + 1;
            }
        }
        
        return num;
    }
}
