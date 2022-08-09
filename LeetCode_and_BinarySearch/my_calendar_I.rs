/**
 * Problem Question: https://leetcode.com/problems/my-calendar-i/
 *
 * ----Analogy-----:
 *
 * `start` of a given event will always be saved as a unique element,
 * as we avoid conflicting events..
 *
 * And if the events are sorted based on `start`,
 * then we can easily check the new event if it conflicts with its neighbouring events.
 *
 * ----Approach----:
 *
 * We keep a vector which maintains the `start` of every events, in a sorted order.
 * And we also store the hashmap where `start` is the key, and its value is `end` - 1.
 *
 * As per the question, end is exclusive, so in our code,
 * we store `end - 1` in the value, to keep the calculations simple.
 *
 * Note: We store the `start` as the key, as we don't store any conflicting events,
 * so start is always unique.
 *
 * Steps:
 * ---------
 * 1. Get the index, where we should insert the start of the event (i.e., 15) in the sorted vector.
 *  - We can get the index, using binarysearch.
 * 2. If the index is in either ends, we check with the first element of the array,
 * or the last element of the array respectively.
 * 3. If that index is somewhat in between, then we check the conflicting conditions for new event,
 * with the events on the left as well as with the events on the right of this probable new event,
 * which will be inserted between those two events when in sorted sequence.
 *
 * Example if needed:
 * ---------------------
 *
 * So, if the sorted order of events' start was: [10, 16, 23, 25]
 * Events hashmap was: [{10: 14}, {16: 21}, {23: 23}, {25: 30}]
 *
 * And if the new event is [15, 18] (end exclusive), then we check the index where to insert 15 in this array.
 *
 * new end is [15, 17], as end was exclusive before.
 *
 * The index required is 1.
 * Now, we check the event [15, 17] with the event [10, 14] for if they are conflicting or not.
 * We also check the event [15, 17] with the event [16, 21] for if they are conflicting or not.
 *
 * It may happen that the start of the new event is already there in the sorted array, then also we get the index where it is present.
 * And we check with that index, if those two events are conflicting or not.
 * This case is already handled in a generic way.
 *
 * ----Time Complexity & Space Complexity-------:
 *
 * Each call to `book` method requires below complexity:
 *
 * Time Complexity: O((log n) + n) ~ O(n)
 * Space Complexity: O(n)
 *
 * Explanation of the above complexities if applicable:
 * --------------------------------------------------------
 * - For the Time Complexity, (log n) is for the binary search,
 * and n is for the insertion of the events' start in the sorted vector.
 * - For the Space Complexity, n is for storing the events' start in sorted_arr,
 * and the events in the hashmap.
 *
 */
// -----submission codes start here-----
use std::collections::HashMap;

struct MyCalendar {
    sorted_arr: Vec<i32>,
    events: HashMap<i32, i32>,
}

// my custom helper functions
impl MyCalendar {
    fn get_index_to_insert_at(self: &Self, key: i32) -> usize {
        let len: usize = self.sorted_arr.len();

        /*
         * len is usize, so it is safer to first convert it to isize then subtract,
         * or else if len is 0, then 0 - 1 will overflow, and not be fit in usize
         */
        let (mut left, mut right): (isize, isize) = (0, (len as isize) - 1);

        while left <= right {
            let mid: isize = left + (right - left) / 2;

            if self.sorted_arr[mid as usize] == key {
                return mid as usize;
            } else if self.sorted_arr[mid as usize] < key {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        (right + 1) as usize
    }

    fn are_events_conflicting(new_event: (i32, i32), existing_event: (i32, i32)) -> bool {
        if new_event.0 >= existing_event.0 && new_event.0 <= existing_event.1 {
            return true;
        }

        if new_event.1 >= existing_event.0 && new_event.1 <= existing_event.1 {
            return true;
        }

        if existing_event.0 >= new_event.0 && existing_event.0 <= new_event.1 {
            return true;
        }

        if existing_event.1 >= new_event.0 && existing_event.1 <= new_event.1 {
            return true;
        }

        return false;
    }
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MyCalendar {
    fn new() -> Self {
        Self {
            sorted_arr: vec![],
            events: HashMap::new(),
        }
    }

    fn book(self: &mut Self, start: i32, end: i32) -> bool {
        let end_mod = end - 1;
        let total_len: usize = self.sorted_arr.len();

        if total_len == 0 {
            // base case when the total length till now is still 0, so we can insert the new event as it is..
            self.sorted_arr.push(start);
            self.events.insert(start, end_mod);
            return true;
        }

        let index_inserted_at: usize = self.get_index_to_insert_at(start);

        let mut can_be_inserted: bool = false;

        // index_inserted_at is the index at which we want the new event to be inserted..
        if index_inserted_at >= total_len {
            // check it with the last cell's range only
            can_be_inserted = !Self::are_events_conflicting(
                (start, end_mod),
                (
                    self.sorted_arr[total_len - 1],
                    self.events[&self.sorted_arr[total_len - 1]],
                ),
            );
        } else if index_inserted_at == 0 {
            // check it with the first cell's range only
            can_be_inserted = !Self::are_events_conflicting(
                (start, end_mod),
                (self.sorted_arr[0], self.events[&self.sorted_arr[0]]),
            );
        } else {
            // check with the current and prev cell's range
            let mut is_conflicting = Self::are_events_conflicting(
                (start, end_mod),
                (
                    self.sorted_arr[index_inserted_at],
                    self.events[&self.sorted_arr[index_inserted_at]],
                ),
            );

            is_conflicting = is_conflicting
                || Self::are_events_conflicting(
                    (start, end_mod),
                    (
                        self.sorted_arr[(index_inserted_at - 1)],
                        self.events[&self.sorted_arr[(index_inserted_at - 1)]],
                    ),
                );

            can_be_inserted = !is_conflicting;
        }

        if can_be_inserted == true {
            // insert this new event in both the sorted_arr and the events hashmap
            self.sorted_arr.insert(index_inserted_at, start);
            self.events.insert(start, end_mod);
        }

        can_be_inserted
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * let obj = MyCalendar::new();
 * let ret_1: bool = obj.book(start, end);
 */

// -----submission codes end here-----

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_problem() {
        let mut obj = MyCalendar::new();
        let ret_1: bool = obj.book(10, 20);
        let ret_2: bool = obj.book(15, 25);
        let ret_3: bool = obj.book(20, 30);

        assert_eq!(ret_1, true);
        assert_eq!(ret_2, false);
        assert_eq!(ret_3, true);
    }
}

fn main() {
    let mut obj = MyCalendar::new();
    let ret_1: bool = obj.book(10, 20);
    let ret_2: bool = obj.book(15, 25);
    let ret_3: bool = obj.book(20, 30);

    assert_eq!(ret_1, true);
    assert_eq!(ret_2, false);
    assert_eq!(ret_3, true);
}
