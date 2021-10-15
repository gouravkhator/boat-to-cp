class CheckWordPlacedInCrossword {
    /* Test Cases:

[["#"," ","#"],[" "," ","#"],["#","c"," "]]
"abc"
[[" ","#","a"],[" ","#", "c"],[" ","#","a"]]
"ac"
[["#"," ","#"],[" "," ","#"],["#"," ","c"]]
"ca"
[["#"," ","#"],[" "," ","#"],["#"," "," "]]
"ca"
[[" "],["#"],["o"],[" "],["t"],["m"],["o"],[" "],["#"],[" "]]
"octmor"
[[" ","c","z"],["#","#","#"]]
"cz"
    */
    
    public static void main(String[] args) {
        CheckWordPlacedInCrossword ob = new CheckWordPlacedInCrossword();
        
        char[][] board = new char[][]{
            {' ', 'c', 'z'},
            {'#', '#', '#'},
        };

        System.out.println(ob.placeWordInCrossword(board, "cz"));
    }
    
    public boolean check(char[][] board, int i, int j, String word, boolean choice){
        /*
        Logic:

        For vertical check,

        First loop:

        For this loop to get executed, first check if start is either 0 which means no character is before start, or before start we have #, which is a block.
        
        If before start, we have some different character, it means that whole column will not get traversed by word.

        First fix j and loop row from i to (m-1).
        If character is #, then if index is word's length, it means return true, as we got the word in board.

        If the character is space, just increment index of word traversed by 1.
        If character is same as the word's current character, increment index by 1.
        If character is not same, or the index has already exceeded, then break with flag as false.

        At the end of loop, if flag is true and index is same as word's length, then return true.
        (This case is when all characters were correct, and we did come out of loop smoothly.)
        
        Second Loop:
        Similar check for second loop, if after end of this loop, we have # or this end is the end of that column.

        Also, do a backward search vertically, as maybe the characters were placed reversed order..

        Similarly, horizontal check is done too.
        */
        
        int m = board.length, n = board[0].length;
        
        int index = 0, start = 0, end = 0;
        boolean flag = true;
        
        if(choice == true){
            // horizontal
            start = j;
            end = n - 1;
        }else{
            start = i;
            end = m - 1;
        }

        boolean loop1Exec = (start == 0) || 
            (choice == true && start > 0 && board[i][start - 1] == '#') || 
            (choice == false && start > 0 && board[start - 1][j] == '#');
        
        if(loop1Exec == true){
            for(int k=start; k<=end; k++){
                char c = ' ';

                if(choice == true){
                    // horizontal
                    c = board[i][k];
                }else{
                    c = board[k][j];
                }

                if(c == '#'){
                    if(index == word.length()){
                        return true;
                    }

                    flag = false;
                    break;
                }

                if(index >= word.length()){
                    flag = false;
                    break;
                }

                if(c == ' '){
                    index++;
                }else if(c == word.charAt(index)){
                    index++;
                }else if(c != word.charAt(index)){
                    flag = false;
                    break;
                }
            }

            if(flag == true && index == word.length()){
                return true;
            }
        }
        
        index = 0;
        flag = true;
        start = 0;
        
        if(choice == true){
            // horizontal
            end = j;
        }else{
            end = i;
        }

        boolean loop2Exec = (choice == true && end == (n - 1)) || 
            (choice == false && end == (m - 1)) || 
            (choice == true && end <= (n - 2) && board[i][j + 1] == '#') || 
            (choice == false && end <= (m - 2) && board[i + 1][j] == '#');
        
        if(loop2Exec == true){
            for(int k=end; k>=start; k--){
                char c = ' ';

                if(choice == true){
                    // horizontal
                    c = board[i][k];
                }else{
                    c = board[k][j];
                }

                if(c == '#'){
                    if(index == word.length()){
                        return true;
                    }

                    flag = false;
                    break;
                }

                if(index >= word.length()){
                    flag = false;
                    break;
                }

                if(c == ' '){
                    index++;
                }else if(c == word.charAt(index)){
                    index++;
                }else if(c != word.charAt(index)){
                    flag = false;
                    break;
                }
            }

            if(flag == true && index == word.length()){
                return true;
            }
        }
        
        return false;
    }
    
    // Problem Question: https://leetcode.com/problems/check-if-word-can-be-placed-in-crossword/
    public boolean placeWordInCrossword(char[][] board, String word) {
        /*
        Logic:

        If the current cell is #, don't do any vertical or horizontal checks..
        If current cell is space or letter, do vertical and horizontal checks.
        */
        
        boolean ans = false;
        
        int i=0, j=0, m = board.length, n = board[0].length;
        
        for(i=0; i<m; i++){
            for(j=0; j<n; j++){
                if(board[i][j] == '#'){
                    continue;
                }
                
                // for horizontal check, true is passed as choice, and for vertical check, false is passed.
                boolean temp = check(board, i, j, word, true) || check(board, i, j, word, false);
                
                if(temp == true){
                    return true;
                }
            }
        }
        
        return ans;
    }
}
