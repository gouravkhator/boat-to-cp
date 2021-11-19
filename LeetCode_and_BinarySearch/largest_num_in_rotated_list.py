class LargestNumRotatedList:
    # Problem Question: https://binarysearch.com/problems/Find-the-Largest-Number-in-a-Rotated-List
    def solve(self, arr):
        if len(arr) == 0:
            return None

        '''
        Logic:

        split arr in half and check maximum amongst mid, left and right.
        whoever is larger, just mark them as max and recur from that index to their right side.
        '''

        maximum = arr[0] # initialize maximum as the 1st variable
        originalEnd = len(arr) - 1

        def binaryLarger(left, right):
            if left > right:
                return

            nonlocal maximum # use global variable maximum 
            mid = left + (right - left)//2
            resLeft, resRight = left, right

            if arr[mid] > maximum:
                # if mid child is maximum, left side will be mid and right side will be right - 1
                maximum = arr[mid]
                resLeft = mid
                resRight = right - 1
            
            if arr[left] > maximum:
                # if left child is maximum, left side will be left and right side will be mid - 1
                maximum = arr[left]
                resLeft = left
                resRight = mid - 1
            
            if arr[right] > maximum:
                # if right child is maximum, we don't recur for more, as there is nothing maximum than right
                # as amongst left child right child and mid child, right child is maximum 
                maximum = arr[right]
                return
            
            if resLeft == left and resRight == right:
                resLeft+=1 # increase the left marker, as if the same markers are present, it would go in infinite recursion

            binaryLarger(resLeft, resRight) # recur with resLeft and resRight as left ad right respectively
        
        binaryLarger(0, originalEnd)
        return maximum
