class LatinSquareSolver:
    # Checks if the current cell (i,j) is safe or not
    # meaning, all non-zero elements in i'th row are all unique in that row
    # and all non-zero elements in j'th column are all unique in that column
    def isSafe(self, matrix, i, j):
        n = len(matrix)
        temp_set = set()

        # checks for ith row
        for k in range(n):
            if matrix[i][k] == 0:
                # if the element is 0, then we skip those
                continue

            if matrix[i][k] in temp_set:
                # if the element is there in the set, then we got a duplicate
                return False
            temp_set.add(matrix[i][k])

        temp_set = set()

        # checks for jth column
        for k in range(n):
            if matrix[k][j] == 0:
                continue

            if matrix[k][j] in temp_set:
                return False

            temp_set.add(matrix[k][j])

        return True

    # recursion function -- checks available numbers for the unfilled cells and fill that cell with available number and then recur for next unfilled
    # if anywhere we see that matrix is unsafe, we backtrack and fill that cell with other available number
    def recur(self, unfilled_squares, index, matrix):
        if index >= len(unfilled_squares):
            return True
        
        n = len(matrix)
        i, j = unfilled_squares[index]

        # check for the numbers that can be filled in this cell
        available_nums = set(range(1, n+1)) - set(matrix[i]) # numbers available for that cell in i'th row

        col_not_available = set() # numbers not available for that cell in j'th column

        for k in range(n):
            col_not_available.add(matrix[k][j]) # those numbers who are there in j'th column will not be available for that cell
        
        # intersection of both sets to check total numbers available for that cell to be filled with
        available_nums = available_nums.intersection(set(range(1, n+1)) - col_not_available)

        # check if after filling that cell, is that cell safe and can we recur for next unfilled cells
        for num in available_nums:
            matrix[i][j] = num

            if self.isSafe(matrix, i, j):
                if self.recur(unfilled_squares, index+1, matrix):
                    # if cell is safe and we can fill next unfilled cells too, then we return True
                    return True

            matrix[i][j] = 0 # else unset this cell and set other available numbers (if any)

        return False # no available numbers could satisfy the safety of matrix, then we return false

    # Problem Question: https://binarysearch.com/problems/Latin-Square-Solver 
    def solve(self, matrix):
        '''
        Logic:

        If the cell is unfilled, we save that cell's coordinates i and j in a list of tuples.
        Then, we do recursion for that list of unfilled cells.

        If cell is filled, we immediately check if it is safe or not.

        Safety of a cell means if cell's row does not have duplicate non-zero elements, 
        and cell's column does not have duplicate non-zero elements.

        Filling unfilled cells safely meaning atleast one number is present to fill that unfilled cell.

        Edge cases:
        #1: If no 0 is present in matrix, then we also check for safety of filled cells.
        #2: If some filled cell's row and column have some duplicates, yet we can fill unfilled cells safely, then the solve function should return False.
        '''

        '''
        Test cases:
        #1: [[1,2,3,4],[3,4,1,2],[2,1,4,3], [4,3,2,3]]

        #2:
        [
            [2, 2, 12, 3, 11, 4, 10, 5, 9, 6, 8, 7],
            [2, 3, 1, 4, 12, 5, 11, 6, 10, 7, 9, 8],
            [3, 4, 2, 5, 1, 6, 12, 7, 11, 8, 10, 9],
            [4, 5, 3, 6, 2, 7, 1, 8, 12, 9, 11, 10],
            [5, 6, 4, 7, 3, 8, 2, 9, 1, 10, 12, 11],
            [6, 7, 5, 8, 4, 9, 3, 10, 2, 11, 1, 12],
            [7, 8, 6, 9, 5, 10, 4, 11, 3, 12, 2, 1],
            [8, 9, 7, 10, 6, 11, 5, 12, 4, 1, 3, 2],
            [9, 10, 8, 11, 7, 12, 6, 1, 5, 2, 4, 3],
            [10, 11, 9, 12, 8, 1, 7, 2, 6, 3, 5, 4],
            [11, 12, 10, 1, 9, 2, 8, 3, 7, 4, 6, 5],
            [12, 1, 11, 2, 10, 3, 9, 4, 8, 5, 7, 0]
        ]
        '''

        n = len(matrix)

        # take the 0's and put in list of tuples
        unfilled_squares = [] 

        for i in range(n):
            for j in range(n):
                if matrix[i][j] == 0:
                    unfilled_squares.append((i,j))
                elif self.isSafe(matrix, i, j) ==  False:
                    return False

        return self.recur(unfilled_squares, 0, matrix)

ob = LatinSquareSolver()
print(ob.solve([[1,3,2], [3,0,1], [2,3,0]]))
print(ob.solve([[3,0,1], [1,2,3], [3,1,3]]))
print(ob.solve([[1,2,3], [2,0,1], [3,1,2]]))
print(ob.solve([[1,2,3,4],[3,4,1,2],[2,1,4,3], [4,3,2,3]]))
