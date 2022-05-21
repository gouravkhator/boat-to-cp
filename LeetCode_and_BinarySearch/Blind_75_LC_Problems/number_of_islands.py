class NumberOfIslands:
    # Problem Question: https://binarysearch.com/problems/Number-of-Islands
    # This question is also there in leetcode: https://leetcode.com/problems/number-of-islands/
    def solve(self, matrix):
        '''

        The approach should be much better, if we try it with the approach, same as:
        "Number of provinces: https://leetcode.com/problems/number-of-provinces/"

        Logic:

        Number of Islands means number of groups having 1's, that are in some way horizontally or vertically connected directly or indirectly.

        First check all 1's in the matrix and add their i and j as tuples in the unvisitedCellsList.

        Then do a recursion. It will go like below:
        * We use groupNum as the global variable for counting the connected groups, as that is our answer.
        * We use unvisitedCellsList from the global variable.
        * We have current i and j and the is_source parameter, which represents, whether this cell is the source of the group or not.
        [It is used to ensure we don't count a group more than once, so we fix a group's source]

        * If unvisitedCellsList has no cell left, then we return from this function, as all cells with 1's are being visited.
        * We then check in all four sides if a side is possible, and if that cell is unvisited or not.
        If that cell is unvisited too, it means that cell is 1, and also unvisited yet.

        So, we remove that cell's i and j from the unvisited (to visit it), and apply recursion for that cell.
        Note: is_source is False now for that cell, as we are going from 1 cell to another connected cell.
        (so, it comes in same group)

        At last, we check if the is_source for current cell is True,
        that means we would have traversed recursively in all sides from this source,
        and now we can apply recursion for a new cell from the unvisited.

        This also increments the groupNum by 1, as we will be traversing a new group, with the source as that popped new cell.
        '''
        
        rows = len(matrix)
        if rows == 0:
            return 0
        
        cols = len(matrix[0])

        unvisitedCellsList = []

        for i in range(rows):
            for j in range(cols):
                if matrix[i][j] == 1:
                    # only cells with 1, are of our concern
                    unvisitedCellsList.append((i, j))
        
        # recursion function
        def recur(i, j, is_source):
            # use global variable
            nonlocal groupNum
            nonlocal unvisitedCellsList

            if len(unvisitedCellsList) == 0:
                # all cells with 1's are visited
                return

            if i >= 1 and (i - 1, j) in unvisitedCellsList:
                unvisitedCellsList.remove((i - 1, j))
                recur(i-1, j, is_source=False)
            
            if j >= 1 and (i, j - 1) in unvisitedCellsList:
                unvisitedCellsList.remove((i, j - 1))
                recur(i, j - 1, is_source=False)

            if (i + 1) < rows and (i + 1, j) in unvisitedCellsList:
                unvisitedCellsList.remove((i + 1, j))
                recur(i + 1, j, is_source=False)
                
            if (j + 1) < cols and (i, j + 1) in unvisitedCellsList:
                unvisitedCellsList.remove((i, j + 1))
                recur(i, j + 1, is_source=False)

            if is_source==True and len(unvisitedCellsList) > 0:
                # as unvisited cells are remaining, 
                # so we got another cell, which can be the source of new group.
                # Increment the group number
                groupNum += 1
                # pop the first one from the list and unpack that tuple to get i and j
                recur(*unvisitedCellsList.pop(0), is_source=True)

        unvisitedlen = len(unvisitedCellsList)

        if unvisitedlen == 0:
            return 0
        
        if unvisitedlen == rows*cols:
            # if whole matrix is all 1's, then unvisited cells will be the whole matrix
            # then only one group is possible 
            return 1
        
        groupNum = 0

        if unvisitedlen > 0:
            groupNum += 1
            recur(*unvisitedCellsList.pop(0), is_source=True)

        return groupNum

obj = NumberOfIslands()
matrix = [[0,0,1,0,0,0,0,1,1,1],[0,0,0,1,0,1,0,1,0,1],[1,0,0,1,0,0,1,0,0,0],
[0,0,0,1,0,0,0,1,0,1],[0,1,1,0,1,1,1,1,1,0],[1,1,1,0,0,1,1,1,0,1],
[0,1,1,1,1,1,1,0,0,0],[0,0,1,1,0,0,1,0,0,1],[1,0,0,1,0,1,1,1,1,1],[0,0,0,1,0,0,1,1,0,0]]

print(obj.solve(matrix))
