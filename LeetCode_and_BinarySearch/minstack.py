import bisect

# Problem Question: https://leetcode.com/problems/min-stack/
class MinStack:
    '''
    Logic:

    If I push normally and pop normally, and only get minimum element from the whole list by using min(), then it is very much time complex.

    So, using minstack. It is another list.

    For push operation, I append the element at last to original stack, and
    insert the value in minstack in sorted way.

    For pop operation, I remove the element from last from the original stack, but
    I remove 1st occurrence of that element from the minstack.

    While getting min, I can return 1st element from minstack directly, as it was maintained in ascending order.

    Previous approach used O(n) to get min.
    Now, get min is constant time, and push and pop takes O(log n) mostly,
    as inserting or deleting from sorted list can be done by binary search algo internally.
    '''

    def __init__(self):
        self.stack = []
        self.minstack = []
        
    def push(self, val: int) -> None:
        self.stack.append(val)
        
        # insort method inserts the element in sorted position in minstack, so minstack is always in ascending sorted order.
        bisect.insort(self.minstack, val)

    def pop(self) -> None:
        # pop will remove the last element from original stack
        # remove method removes 1st occurrence of that element from minstack, and not all occurrences
        self.minstack.remove(self.stack.pop())

    def top(self) -> int:
        # constraint says that top is called when stack is non-empty,
        # just access -1 index without checks
        return self.stack[-1]

    def getMin(self) -> int:
        # constraint says that getMin is called when stack is non-empty,
        # so just access 0-index without checks
        return self.minstack[0]

# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(val)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()
