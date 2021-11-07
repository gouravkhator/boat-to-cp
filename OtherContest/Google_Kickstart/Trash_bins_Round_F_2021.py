# Problem Question: https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435bae/0000000000887c32#problem

t = int(input())

for case in range(t):
    '''
    Logic:

    Taking string here as 1-indexed in my code..

    Traverse from left to right in string, and if a 1 comes, just update latest_one_index to that index.
    If 0 comes, then we just set its closestDist value to abs(current index - latest_one_index)

    It means, the closest distance for that 0 will be distance between it..
    and the index where 1 just arrived, closest to this current index, on its left.

    Now, we know for each index, what is the distance from closest 1, if checked from left..

    Now, we check from right side.. And update latest_one_index accordingly, when 1 comes..
    If 0 comes, then we check if its previous value was 0, it means it was not set before..
    That means no 1 was present before that 0, and its closestDist value can be set from right side 1 only..
    And as we know whole string has atleast one 1, so right side 1 will always be present..

    Set closestDist val to abs(current index - right side 1's latest one index)..

    If previously, value was not zero, it means we need to check min of left side and right side distance..
    Also, now we can sum this value of closest distance..
    '''

    n = int(input())
    s = " "+input() # for making it 1-indexed..

    sum = 0
    closestDist = [0]*n
    latest_one_index = 0

    for i in range(1, n+1):
        if s[i] == '1':
            latest_one_index = i
        else:
            if latest_one_index > 0:
                # latest_one_index must be > 0, as we are assuming 1-indexing
                closestDist[i - 1] = abs(i - latest_one_index)
    
    latest_one_index = 0

    for i in range(n, 0, -1):
        if s[i] == '1':
            latest_one_index = i
        else:
            if latest_one_index > 0:
                if closestDist[i - 1] == 0:
                    closestDist[i - 1] = abs(i - latest_one_index)
                else:
                    closestDist[i - 1] = min(closestDist[i - 1], abs(i - latest_one_index))
            
            sum += closestDist[i - 1]
        
    print("Case #{}: {}".format(case + 1,sum))
