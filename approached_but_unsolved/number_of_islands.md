# [Number of Islands](https://leetcode.com/problems/number-of-islands/)

## Approach

Check how many groups of connected 1's are there. A group can be said when 1's are connected horizontally or vertically.

Count number of connected components in the graph represented by the matrix rows and columns. The matrix given is adjacency matrix.

Or,

Take the count of components as 0 initially. If a cell is 1, then check its upper and left cell value, and take min of both values and if min. is 0 then set its cell to (count of components + 1) else set its cell to that min. If a cell is 0 then leave it 0.

Modify in that matrix itself.
Take maximum value as answer of the whole modified matrix.

## Code with a completely different approach but same question on [binarysearch](https://binarysearch.com/problems/Number-of-Islands)

```py
class Solution:
    # test case 1: [[1,1,0,1,0,0,1,0,1,0],[1,0,1,0,0,0,1,1,1,1],[0,1,0,1,0,1,1,1,0,1],[0,1,1,1,0,1,0,1,0,1],[0,1,1,1,1,0,1,0,0,1],[0,1,1,1,1,0,0,1,1,1],[0,1,1,0,1,0,0,0,1,0],[1,1,0,1,0,0,0,1,0,1],[0,0,0,0,1,0,1,1,0,0],[0,1,1,1,1,1,0,0,0,0]]

    # test case 2: [[0,0,1,0,0,0,0,1,1,1],[0,0,0,1,0,1,0,1,0,1],[1,0,0,1,0,0,1,0,0,0],[0,0,0,1,0,0,0,1,0,1],[0,1,1,0,1,1,1,1,1,0],[1,1,1,0,0,1,1,1,0,1],[0,1,1,1,1,1,1,0,0,0],[0,0,1,1,0,0,1,0,0,1],[1,0,0,1,0,1,1,1,1,1],[0,0,0,1,0,0,1,1,0,0]]

    def solve(self, matrix):
        rows = len(matrix)
        if rows == 0:
            return 0
        
        cols = len(matrix[0])

        def checkAllSides(tempGroupNum, i, j):
            if i >= 1:
                # check in upward cell
                if matrix[i-1][j] > 1:
                    tempGroupNum = min(tempGroupNum, matrix[i-1][j])
            
            if j >= 1:
                # check in leftward cell
                if matrix[i][j - 1] > 1:
                    tempGroupNum = min(tempGroupNum, matrix[i][j - 1])

            if i < (rows - 1):
                # check in downward cell
                if matrix[i + 1][j] > 1:
                    tempGroupNum = min(tempGroupNum, matrix[i+1][j])
            
            if j < (cols - 1):
                # check in rightward cell
                if matrix[i][j + 1] > 1:
                    tempGroupNum = min(tempGroupNum, matrix[i][j + 1])
            
            return tempGroupNum

        groupNumber = 1
        for i in range(rows):
            for j in range(cols):
                if matrix[i][j] == 0:
                    continue
                
                tempGroupNum = checkAllSides(rows*cols, i, j)
                if tempGroupNum != (rows*cols):
                    matrix[i][j] = tempGroupNum
                else:
                    groupNumber += 1
                    matrix[i][j] = groupNumber

        maxGroupNum = 0
        groupDict = {}

        for i in range(rows):
            for j in range(cols):
                if matrix[i][j] == 0:
                    continue

                matrix[i][j] = checkAllSides(matrix[i][j], i, j)
        
        for i in range(rows-1, -1, -1):
            for j in range(cols-1, -1, -1):
                if matrix[i][j] == 0:
                    continue

                matrix[i][j] = checkAllSides(matrix[i][j], i, j)
        
        for j in range(cols):
            for i in range(rows):
                if matrix[i][j] == 0:
                    continue

                matrix[i][j] = checkAllSides(matrix[i][j], i, j)
        
        for j in range(cols - 1, -1, -1):
            for i in range(rows-1, -1, -1):
                if matrix[i][j] == 0:
                    continue

                matrix[i][j] = checkAllSides(matrix[i][j], i, j)
                groupDict[matrix[i][j]] = 1
                
        print(groupDict)
        for i in range(rows):
            for j in range(cols):
                print(matrix[i][j], end=" ")
            print("")
        return len(groupDict.keys())
```