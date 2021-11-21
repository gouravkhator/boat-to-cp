# Question of remove islands was in Clement video: https://youtu.be/4tYoVx0QoN0
# Google Mock interview he took with Ben Awad..

'''
Question:

Given a matrix of any order, with 1 representing the rock and 0 as the water.
An island will be the series of 1's disconnected from any boundaries..

Remove islands by setting 1's to 0's there.

Ex- Original matrix:

1 0 0 1 1
0 1 0 1 0
0 1 1 0 0
1 0 1 0 1
1 0 1 1 0

Answer:

1 0 0 1 1 
0 0 0 1 0 
0 0 1 0 0 
1 0 1 0 1 
1 0 1 1 0

Here, you can notice we set 1 at (1,1) index to 0. And set 1 at (2,1) to 0.
Modify the existing matrix, don't return the new matrix. 
'''

'''
Logic: (Fully approached and thought and coded by me)

If rows is 0, then we just return. If rows <= 2 or cols <= 2, then also return. 
It's bcoz all cells are boundary cells, so no islands are there initially, so we don't change any cell.

For checking if a '1' is disconnected from boundaries, we need to calculate prefix sums:
Downwards, upwards, leftwards, rightwards.

For that, we make 4 matrices and we set their dimensions same as original ones (for easy calculation).
(Note: we don't need to process boundaries)

For downwards matrix, we take sums for consecutive ones, and if the count for that cell is > its index from top, then we set that as 0.
It means that the cell is connected to boundaries and so we don't remove them.

Similarly do for all sides matrices.

Now, we check for cells which are not boundary cells.

If the cell val in original matrix is 1,
and downside, upside, leftside, and rightside matrices respective cell vals are > 0,
then we set that 1 in original matrix to 0.

This means, if all sides are checked and the count given there was disconnected from all sides,
then we remove those islands..
'''

# for printing matrix in readable format
def matrix_print(title, matrix):
    print('----------------{} Matrix-----------------'.format(title))
    for l in matrix:
        for cell in l:
            print(cell, end = ' ')
        print('')

# original function for removing islands
def removeIslands(matrix):
    # remove islands should remove islands in place and return nothing. matrix will only get changed.
    rows = len(matrix)

    if rows == 0:
        return

    cols = len(matrix[0])
    
    if rows <= 2 or cols <= 2:
        # as every cell will be boundary cells themselves, so no need to remove any islands there..
        return
        
    # ignore outer boundaries, so create the same length of matrix but would not process them..
    downside_mat = [[0 for _ in range(cols)] for _ in range(rows)]
    upside_mat = [row[:] for row in downside_mat] # cloning downside_mat
    leftside_mat = [row[:] for row in downside_mat]
    rightside_mat = [row[:] for row in downside_mat]

    count = 0

    # setting 0 to a cell means we don't want to remove them
    for j in range(1, cols - 1):
        # exclude processing the boundaries
        count = 0

        for i in range(rows - 1):
            # exclude processing the boundaries except the 1st cell from top, as we need to count downwards

            if matrix[i][j] == 0:
                count = 0
            else:
                count += 1
            
            if count <= i:
                downside_mat[i][j] = count
            else:
                downside_mat[i][j] = 0
    
    for j in range(1, cols - 1):
        count = 0

        for i in range(rows - 1, 0, -1):
            # exclude processing the boundaries except the 1st cell from bottom, as we need to count upwards
            if matrix[i][j] == 0:
                count = 0
            else:
                count += 1
            
            index_from_bottom = rows - 1 - i

            if count <= index_from_bottom:
                upside_mat[i][j] = count
            else:
                upside_mat[i][j] = 0
    
    for i in range(1, rows - 1):
        for j in range(cols - 1, 0, -1):
            # exclude processing the boundaries except the 1st cell from right, as we need to count leftwards
            if matrix[i][j] == 0:
                count = 0
            else:
                count += 1
            
            index_from_right = cols - 1 - j

            if count <= index_from_right:
                leftside_mat[i][j] = count
            else:
                leftside_mat[i][j] = 0

    for i in range(1, rows - 1):
        for j in range(cols - 1):
            # exclude processing the boundaries except the 1st cell from left, as we need to count rightward
            if matrix[i][j] == 0:
                count = 0
            else:
                count += 1

            if count <= j:
                rightside_mat[i][j] = count
            else:
                rightside_mat[i][j] = 0

    # matrix_print('Downside', downside_mat)
    # matrix_print('Upside', upside_mat)
    # matrix_print('Leftside', leftside_mat)
    # matrix_print('Rightside', rightside_mat)

    for i in range(1, rows - 1):
        for j in range(1, cols - 1):
            if matrix[i][j] == 1:
                if downside_mat[i][j] > 0 and upside_mat[i][j] > 0 and leftside_mat[i][j] > 0 and rightside_mat[i][j] > 0:
                    matrix[i][j] = 0 # removing islands here by setting value to 0

if __name__ == '__main__':
    rows = int(input('Enter number of rows: '))

    print('Enter the matrix (separated by space in each row): ')
    matrix = []

    for i in range(rows):
        matrix.append(list(map(int, input().split())))

    matrix_print('Original', matrix)
    removeIslands(matrix)
    matrix_print('Answer', matrix)
