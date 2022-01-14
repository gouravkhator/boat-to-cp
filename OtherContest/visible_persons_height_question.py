"""
The below question and solutions are well explained in https://youtu.be/oOSNGAChtfw

This problem is a Relevel Test Sample Question.
Problem Question:

There are N people standing in a queue. They are numbered 1 to N from left to right.
You have been given an array A of size N, where A[i] denotes the height of ith person.
A person i can see another person j (which is not equal to i), when j follows the following rules:

* i < j
* The height of every person between i and j should be less than height of jth person i.e. for every k (i < k < j), A[k] < A[j].

You have to print N integers, where i-th integer will denote the number of people i-th person can see.

Test case: 
heights: [11, 6, 7, 5, 11, 11]
result: [3, 2, 2, 1, 1, 0]
"""

"""
Logic:

As it is told that i<j and heights[k] < heights[j] for i < k < j.
This means that for ith element, we can see (i+1)th element and from i+1, we need a strictly increasing subsequence of heights..

So, we know that for ith element, result will be atleast 1 (if that ith element is not the last element).
(As it will see its next element for sure).

From i+1 index, we need to check how many elements are there in this strictly increasing subsequence which starts at index i+1.
That means, if we land at i+1 index somehow, then the result for element from which we landed at i+1 index, will be:

1 (to go to immediate next element) + (count of elements from immediate next element, forming this strictly increasing subsequence)

So for each index, we can store the counts of elements which will be forming that subsequence, when we land at this element.
This storing will be a dp.

For checking the subsequence, we require to find next higher elements for each element in that subsequence.
For this too, we can store that in another list, which will store next higher element's index.

So, for [11, 6, 7, 5, 11, 11], next higher elements will be:
[-1, 2, 4, 4, -1, -1]

-1 is written just to denote that for that index, we don't have any next higher element.

This next higher elements list can be made using a stack. Stack will store the indices, so that we can store those in next_higher_elements list.

Stack will be a python list, storing top of stack as the end element of the list.
So, when stack is empty, we can add the current element's index to it.
Else, we will check if top of stack is higher than current element, then next higher element for current element is that top of stack.
We will save its index as next higher element for current element.

If top of stack is lesser than or equal to current element, then we know that top most element is not the next higher element for current element.
So, we pop that element out, and then check again for current element.

This way, we find the next higher element for each of the elements in the list.

Once that is done, we will traverse from backwards, and find the counts list which will be as follows:

counts_next_higher[i] = counts_next_higher[next_higher_elements[i]] + 1

Meaning, if we land at i, number of next higher elements after current element in :
1 (to go to its next higher element) + number of next higher elements after current's next higher element.

If next higher element is -1, that means not present, then we just keep count as 0.

This counts the next higher element and then subsequent higher elements from that next element.

For the above example, counts_next_higher will be [0, 2, 1, 1, 0, 0]

Now, from this count, we know that if we land at an index, then we can get the number of elements of that subsequence.
So, we just add +1 to that count.

result[i] = 1 (to include the immediate next element, as it will always be visible) + counts_next_higher_elements[i + 1]

That means, include the immediate next element and from that next element, include the number of elements who are subsequently strictly increasing after this.
"""


def solve(heights):
    n = len(heights)
    res = [0] * n

    # store next higher elements indices as 0-based indexing
    next_higher_elements = [-1] * n  # -1 means no next higher element

    # number of next higher elements if we land at current element
    counts_next_higher = [0] * n

    stack = []  # stack to store indices for next higher element

    # stack's top most element will be at the end of the list.

    current_index = n - 1

    while current_index >= 1:
        if len(stack) > 0:
            if heights[stack[-1]] > heights[current_index]:
                next_higher_elements[current_index] = stack[-1]
                stack.append(current_index)
                current_index -= 1
            else:
                stack.pop()
        else:
            next_higher_elements[current_index] = -1
            stack.append(current_index)
            current_index -= 1

    for i in range(n - 2, -1, -1):
        if next_higher_elements[i] != -1:
            counts_next_higher[i] = counts_next_higher[next_higher_elements[i]] + 1

    # lastly, calculate the result from the count of elements higher than (i+1)th element
    for i in range(n - 2, -1, -1):
        res[i] = counts_next_higher[i + 1] + 1

    return res


if __name__ == "__main__":
    heights = list(map(int, input().split()))
    print(solve(heights))
