class TreeSum:
    class Tree:
        def __init__(self, val, left=None, right=None):
            self.val = val
            self.left = left
            self.right = right

    # Problem Question: https://binarysearch.com/problems/Tree-Sum
    def solve(self, root):
        '''
        Logic:

        Sum will include current node's value and then recur for left and right child.
        '''
        if root == None:
            return 0
        return root.val + self.solve(root.left) + self.solve(root.right)