class KNumGreaterEqualsK {
    // Problem Question: https://binarysearch.com/problems/K-Numbers-Greater-Than-or-Equal-to-K
    public int solve(int[] nums) {
        /*
        Logic:

        The nums can contain duplicates too.
        So, we use two arrays, one for counting frequency and another for dp.

        For making arrays, calculate max number to have a maximum length of the arrays.
        Calculate freq for each element and store in freq array.

        Now, dp[i] means how much elements are greater than or equals i and also present in nums.
        (this will be dp[i-1] - freq[i-1] and this formula was generalized by looking at various patterns)

        dp[0] will be number of elements in nums array.
        (as all elements are either greater than 0 or equals 0)

        dp[1] will be number of elements greater than or equals 1 and also present in nums array.
        (means dp[1] = dp[0] - freq[0])

        If dp[i] == i, it means there are exactly i number of elements which are greater than of equal to i, so return i.
        */
        
        int maxNum = 0, len = nums.length;

        for(int elem: nums){
            if(elem > maxNum){
                maxNum = elem;
            }
        }

        int freq[] = new int[maxNum+1];
        int dp[] = new int[maxNum + 1];

        for(int elem: nums){
            freq[elem]++;
        }

        int i=0;
        dp[0] = len;

        for(i=1; i<=maxNum; i++){
            dp[i] = dp[i-1] - freq[i-1];

            if(dp[i] == i){
                return i;
            }
        }

        return -1;
    }
}
