# [Angry Owner](https://binarysearch.com/problems/Angry-Owner)

## Code | WA solution

```py
class Solution:
    ''' 

    customers = [1, 2, 5, 5, 2]
    mood = [0, 0, 1, 0, 1]
    k = 3
    Expected: 14

    customers = [1, 2, 5, 5, 2]
    mood = [0, 0, 0, 0, 1]
    k = 3
    Expected: 14

    customers = [2,1]
    mood = [1,0]
    k = 1
    Expected: 3

    customers = [1, 1, 0, 0]
    mood = [0, 1,0,0]
    k = 2
    Expected: 2

    Problem question: https://binarysearch.com/problems/Angry-Owner
    '''
    def solve(self, customers, mood, k):
        # whenever the mood is 0, we just calc max sum and update that, and reset the current sum

        # if number of numbers taken now is exceeding k, then update max sum and update the pointer of the start of the list and update the previous sum to include new one and exclude the previous pointer's sum

        n = len(mood)

        if n == 0:
            return 0
    
        global_left, global_right, left_pointer, global_zeros = -1, -1, 0, 0
        current_sum, maximum, current_zeros_count = 0, 0, 0

        for i in range(0, n):
            if (i - left_pointer + 1) <= k:
                current_sum += customers[i]

                if mood[i] == 0:
                    current_zeros_count += 1
            else:
                # update maximum, global_left, global_right, and current_sum
                if current_zeros_count > global_zeros and current_sum > 0:
                    maximum = current_sum
                    global_left = left_pointer
                    global_right = i - 1
                    global_zeros = current_zeros_count
                    print(maximum)
                elif current_zeros_count == global_zeros and current_sum > maximum:
                    maximum = current_sum
                    global_left = left_pointer
                    global_right = i - 1
                    global_zeros = current_zeros_count
                    print(maximum)
                elif current_zeros_count < global_zeros and current_sum >= maximum and current_zeros_count > 0:
                    # if sum is greater, then that subset should have atleast zeros_count > 0
                    maximum = current_sum
                    global_left = left_pointer
                    global_right = i - 1
                    global_zeros = current_zeros_count
                    print(maximum)

                current_sum -= customers[left_pointer]
                current_zeros_count -= 1 if mood[left_pointer] == 0 else 0
                current_zeros_count += 1 if mood[i] == 0 else 0
                current_sum += customers[i]
                left_pointer += 1

        if current_zeros_count > global_zeros and current_sum > 0:
            maximum = current_sum
            global_left = left_pointer
            global_right = n - 1
            global_zeros = current_zeros_count
            print(maximum)
        elif current_zeros_count == global_zeros and current_sum > maximum:
            maximum = current_sum
            global_left = left_pointer
            global_right = n - 1
            global_zeros = current_zeros_count
            print(maximum)
        elif current_zeros_count < global_zeros and current_sum >= maximum and current_zeros_count > 0:
            # if sum is greater, then that subset should have atleast zeros_count > 0
            maximum = current_sum
            global_left = left_pointer
            global_right = n - 1
            global_zeros = current_zeros_count
            print(maximum)

        for i in range(n):
            if i >= global_left and i <= global_right:
                continue
            
            if mood[i] == 1:
                maximum += customers[i]

        return maximum


```