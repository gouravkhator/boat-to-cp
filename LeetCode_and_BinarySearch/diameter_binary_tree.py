from typing import Optional

class DiameterBinaryTree:
    # Definition for a binary tree node.
    class TreeNode:
        def __init__(self, val=0, left=None, right=None):
            self.val = val
            self.left = left
            self.right = right

    def depth(self, root, depths):
        if not root:
            return 0
        
        depths[root] = 1 + max(self.depth(root.left, depths), self.depth(root.right, depths))
        return depths[root]
    
    def diameterRecur(self, root, depths):
        if not root:
            return 0
        
        currentDiam = 0
        
        # maybe some time root.left is None or root.right is None or both,
        # so first check if their depths are saved in dictionary or not, then add them to current diameter.
        if root.left in depths:
            currentDiam = depths[root.left]
        
        if root.right in depths:
            currentDiam += depths[root.right]
            
        return max(currentDiam, self.diameterRecur(root.left, depths), self.diameterRecur(root.right, depths))
    
    # Problem Question: https://leetcode.com/problems/diameter-of-binary-tree/
    def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        '''
        Test cases:
        
[1,2,3,4,5]
[1,2]
[1,2,3,4,5,null,null,6,7,8,9, 10, null, null,11, 12]
[1]
        '''

        '''
        Logic:

        Normal recursion to get depth of each node, and then traverse those nodes and get diameter will be redundant.

        We have to calculate twice.

        So, store depth in a dictionary once you traverse the tree.
        Depth calculation at a node means 1 + max(depth(left child), depth(right child))
        This means this node is at which maximum depth, if we count from the leaves, go up to that node.

        Then, after storing depth at each node, we recur for maximum diameter.
        Generally, maximum diameter will occur at the root node itself.
        (Means from largest depth in left child to the largest depth in right child of root node).

        But if some root has only 1 element or lesser elements in right and the left child is a complex subtree, then maximum diameter can occur in left child only.
        Vice versa can also happen.

        So, first check diameter at root, by checking depths of left and right child,
        and then recur for diameter in left subtree, and right subtree differently.

        And return maximum of all diameters.  
        '''
        
        if not root:
            return 0
        
        # calculate depth of all nodes and store them in dict
        depths = {}
        self.depth(root, depths)
        
        # then use the depths dict and calculate maximum diameter 
        return self.diameterRecur(root, depths)
