class UniqueBST {
    // Problem Question: https://leetcode.com/problems/unique-binary-search-trees/
    public int numTrees(int n) {
        /*
        Logic:
        
        By looking at various pattern for n=3 and n=4, I noticed the below.
        
        For n=1, we have 1 possibility.
        
        ---------------------------------
        For n=2, we start with root of 1 or 2.
        
        Let's start with root of 1.
        Now, number of left children possible are 0. number of right child possible is 1 that is the node 2.
        So, if any of numbers of left or right child is 0, then we take sum of the two numbers (in other words, take the non-zero number).
        
        Here, the possibility for bst trees with root 1 is 0+1 = 1.
        
        For tree with root 2, possibility is 1+0 = 1 (as left child can have 1 node that is node with value 1).
        
        For n=2, total possibility is 1+1 = 2.
        
        ---------------------------------------
        For n=3, we can start with root 1, 2 or 3.
        
        a) For root 1 start, we have 0 values on left child of 1, and 2 values on right child of 1. (following the bst logic).
        
        And, for n=2 we know that, possibility is 2. So, 2 values on right child will give 2 possibilities. On left child, 0 values are there, so no possibility.
        
        For root 1 start, 0+2 = 2 possibilities in total.
        
        b) For root 2 start, we have 1 value on left and 1 value on right.
        
        We know that n=1 will give 1 possibility.
        
        So, for root 2 start, 1*1 possibility as none of them are 0 values.
        And for each left possibility, there is a right possibility.
        So, from this logic, (left non-zero value) * (right non-zero value).
        
        For root 2 start, 1 possibility in total.
        
        c) For root 3 start, we have 2 values on left and 0 value on right.
        
        We know that n=2 will give 2 possibility.
        So, for root 3 start, 2+0 possibility.
        
        For root 3 start, 2 possibility in total.
        
        For n=3, 2+1+2 = 5 possibilities in total.
        
        Similarly for others.
        
        So, use dp for storing the previous possibilities.
        And before calculating for actual n, calculate for each of numbers before n.
        Also, for each number i, we need to loop for setting roots from 1 to i.
        So, nested for loop is used.
        */
        
        int[] dp = new int[n + 1];
        
        dp[0] = 0;
        dp[1] = 1;
        
        for(int i=2; i<=n; i++){
            for(int j=1; j<=i; j++){
                // j is the root node of bst tree containing numbers from 1 to i
                int leftChildVal = dp[j - 1]; // j - 1 is the number of left child and dp[leftchild] will give number of possibilities of bst from left subtree
                int rightChildVal = dp[i - j]; // i - j is the number of right child and dp[rightchild] will give number of possibilities of bst from right subtree
                
                if(leftChildVal == 0 || rightChildVal == 0){
                    dp[i]+= leftChildVal + rightChildVal;
                }else
                    dp[i]+= leftChildVal * rightChildVal;
            }
        }
        
        return dp[n];
    }
}
