class LargestPlusSign {
    // Problem Question: https://leetcode.com/problems/largest-plus-sign/
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        /*
        If n=1, and we know that the mines arr length is min 1. 
        So, the only element in 1*1 matrix is set 0. So, plus sign order is 0.

        If n=2, then if the mines length is 4, then 0 is the order, else 1.

        If mines length is n*n, we have nothing but 0 as the order size.
        Now, make a matrix for other cases. Fill the mines with 1.
        (Its just that already matrix is filled with 0, so inverse all the logic of question by filling the mines with 1 rather than 0).
        
        Now, traverse rows and columns from index 1 to n-2.
        For each cell, if the cell itself is 1 (mine is there), then skip this iteration.
        Else, if cell is 0 (mine is not there), then :

        minArmLen will hold the minimum arm length excluding the centre cell for plus sign.
        Go left and update minArmLen, then goto right and only till that minArmLen.
        Goto up, and down and do similar things.

        Go till we have 0 in the plus and as soon as 1 is encountered, we stop that path.
        Then maximum plus sign order is: minArmLen + 1 is its greater than max.
        (+1 is for including the centre cell too).
        */
        if(n==1){
            return 0;
        }
        
        if(n==2){
            if(mines.length == 4)
                return 0;
            
            return 1;
        }
        
        if(mines.length == n*n){
            return 0;
        }
        
        int[][] mat = new int[n][n];
        
        for(int i=0; i<mines.length; i++){
            mat[mines[i][0]][mines[i][1]] = 1;
        }
        
        int max = 1;
        
        for(int i=1; i<=(n-2); i++){ // rows
            for(int j=1; j<=(n-2); j++){ // columns
                
                if(mat[i][j] == 1){
                    continue;
                }
                                
                int minArmLen = n+1, count=0;
                // initialised minArmLen with unfeasible arm len like n+1 
                
                // left
                for(int k=j-1; k>=0; k--){
                    if(mat[i][k] == 1){
                        break;
                    }
                    
                    count++;
                }
                
                if(count < minArmLen){
                    minArmLen = count;
                }
                count=0;
                
                // right
                for(int k=j+1; k<=(j+minArmLen) && k<n; k++){
                    if(mat[i][k] == 1){
                        break;
                    }
                    
                    count++;
                }
                
                if(count < minArmLen){
                    minArmLen = count;
                }
                count=0;
                
                // up
                for(int k=i-1; k>=(i-minArmLen) && k>=0; k--){
                    if(mat[k][j] == 1){
                        break;
                    }
                    
                    count++;
                }
                
                if(count < minArmLen){
                    minArmLen = count;
                }
                count=0;
                
                // down
                for(int k=i+1; k<=(i+minArmLen) && k<n; k++){
                    if(mat[k][j] == 1){
                        break;
                    }
                    
                    count++;
                }
                
                if(count < minArmLen){
                    minArmLen = count;
                }
                count=0;
                
                max = max < (minArmLen+1)? (minArmLen+1) : max;
            }
        }
        
        return max;
    }
}
