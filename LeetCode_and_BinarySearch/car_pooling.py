class CarPooling:
    # Problem Question: https://leetcode.com/problems/car-pooling/
    def carPooling(self, trips, capacity: int) -> bool:
        '''
        Test case:

        Trips [PassengersCount,From,To]:
        [3,1,5], [2,3,8], [4,5,9], [2,3,7], [1,10,15], [1,9,15], [2,2,5]

        stops: 0 1 2 3 4  5  6  7  8  9 10 11 12 13 14 15
        in/de: 0 3 2 4   -2    -2 -2 -3  1             -1
        incar: 0 3 5 9 9  7  7  5  3  0  1  1  1  1  1  0  
        
        Logic:

        As it is told that from_location can start from 0, so we should keep arrays from 0 to max_end.

        Main logic is at every stop, either some will be picked up or some will be dropped off.
        We need to keep track of how many are picked up or dropped off at that stop.

        When we traverse the trips array, we get the from_loc and to_loc..
        At from_loc index, we should have the passengers picked up. At to_loc index, we need to drop them off.

        Picking up means adding that many passengers to existing inc/dec count for that index (index is here also called as the stop).
        Dropping off means subtracting that many passengers from existing inc/dec count for that index.

        When we get the whole array of inc_dec, then we know how many were either picked up or dropped off in resultant, at that stop.

        And now, just check the in_car, which means how many are in the car at that stop.
        in_car at ith stop means in_car passengers at (i-1)th stop + the count of passengers either picked up/dropped off at ith index.

        For 0th stop, we need to calculate before the loop, as i-1 would then refer to -1 th index.
        (and python's -1th index means the last element)..

        If at any stop, in_car passengers count is greater than capacity, we return False. 
        '''

        max_end = 0
        
        for i in trips:
            _,_,end = i
            if max_end < end:
                max_end = end
        
        inc_dec = [0]*(max_end + 1)
        in_car = [0]*(max_end + 1)
        
        for i in trips:
            passengers, from_loc, to_loc = i
            
            inc_dec[from_loc] += passengers
            inc_dec[to_loc] -= passengers
        
        in_car[0] = inc_dec[0]

        if in_car[0] > capacity:
            return False

        for i in range(1, max_end + 1):
            in_car[i] = in_car[i - 1] + inc_dec[i]

            if in_car[i] > capacity:
                return False
        
        return True
