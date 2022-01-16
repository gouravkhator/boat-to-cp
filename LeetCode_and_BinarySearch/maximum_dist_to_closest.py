class MaxDistToClosest:
    # Problem Question: https://leetcode.com/problems/maximize-distance-to-closest-person/
    def maxDistToClosest(self, seats) -> int:
        '''
        Logic:

        First loop in forward direction, and when we encounter 1, just update that we found latest 1 at this index.
        When we encounter 0, we can update the closest_distance of that 0, to be current_index - latest_one_found_index

        If no ones were there before this 0, then just update closest as n (which is the maximum distance and cannot occur as per the question).

        We follow same policy but now in backwards direction,
        and if we find any 0, we check the minimum distance of the current closest distance and previous closest distance.
        
        Also, update the maximum as the maximum of the updated closest distances.

        The core logic is first check from left side, and update the closest distance for the 0 from left side.
        Then check from right side, and update the closest distance for the 0 from the right side.
        (Updating the closest from right means finding closer distance, as seen from left and from right sides).
        Also, find the maximum of updated closest distances. 
        '''

        n = len(seats)
        
        closest_dists = [0]*n
        latest_ones_index = -1
        
        for i in range(n):
            if seats[i] == 1:
                latest_ones_index = i
            else:
                # if latest_ones_index is -1, means we have not encountered 1 before this 0
                closest_dists[i] = (i - latest_ones_index) if (latest_ones_index != -1) else n
        
        latest_ones_index = -1
        res = 1 # minimum answer will always be 1, as there are atleast one 0 and atleast one 1 in the seats array
        
        for i in range(n-1, -1, -1):
            if seats[i] == 1:
                latest_ones_index = i
            else:
                closest_dists[i] = min(closest_dists[i], (latest_ones_index - i) if (latest_ones_index != -1) else n)
                res = max(res, closest_dists[i])
        
        return res
