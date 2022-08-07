/**
 * ----Analogy-----:
 *
 * We have atmost one element to be removable to make the array strictly increasing.
 *
 * ----Approach----:
 *
 * NOTE: The below approach I checked from leetcode discuss section. But then understood the approach and implemented it myself.
 *
 * We can count the points where the array is not strictly increasing.
 *
 * And then if the number of points are 0, then it is already sorted. Return true for this.
 * If number of those points are more than 1, then we cannot remove more than 1 elements,
 * so we return false.
 * If number of those points are just 1, then we should check the edge cases and the non-edge cases,
 * of where we should remove from.
 *
 * We were tracking the `index` where we got the non-increasing point.
 * If that index is the start of the array, it means 0th element is >= 1st element. So, remove 0th element.
 * Note that index cannot be the end of the array, as we check the current element with the next element.
 * We also check if the index is (len - 2), meaning (len-2)th element is >= last element, so we can remove the last element.
 *
 * Non-edge cases:
 * -----------------
 *
 * As the non-strictly increasing point was calculated based on check between elements at index and (index+1),
 * so removing either of them will help in making the array strictly increasing.
 *
 * If we remove element at `index`, then to check the strictly increasing property,
 * elements at (index-1) and (index+1) should be following that property.
 *
 * If we remove element at `index + 1`, then to check the strictly increasing property,
 * elements at (index) and (index+2) should be following that property.
 *
 * We don't need to check other parts of the array,
 * so if I remove element at `index`, then I just need to check,
 * if the just previous element and just next element follow the strictly increasing rule.
 *
 * ----Time Complexity & Space Complexity-------:
 *
 * Time complexity: O(n) where n is the length of the vector.
 * Space complexity: O(1)
 */
pub struct Solution {}

// Problem Question: https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing/

// -----submission codes start here-----

impl Solution {
    pub fn can_be_increasing(nums: Vec<i32>) -> bool {
        let mut count: u32 = 0;
        let len: usize = nums.len();

        // keeping the index as i32, as we store -1 here
        // we can also store (len+1) here, to keep the type as usize
        let mut index: i32 = -1;

        // count the number of non-increasing points
        for i in 0..=(len - 2) {
            if nums[i] >= nums[i + 1] {
                index = i as i32;
                count += 1;
            }
        }

        if count == 0 {
            // already strictly increasing
            return true;
        } else if count > 1 {
            // cannot remove more than one element
            return false;
        } else if count == 1 {
            /*
            as the index was i32, so we cannot compare that with usize variables,
            so casting it to usize and shadowing the outer index variable
            */
            let index = index as usize;

            // edge cases for when we have only one non-increasing point
            if index == 0 || index == (len - 2) {
                return true;
            }

            // other non-edge cases
            if nums[index - 1] < nums[index + 1] {
                /*
                if we wanted to remove nums[index],
                then we check if its just prev and just next elements
                are following that strictly increasing rule
                */
                return true;
            } else if nums[index] < nums[index + 2] {
                /*
                if we wanted to remove nums[index+1],
                then we check if its just prev and just next elements
                are following that strictly increasing rule
                */
                return true;
            }
        }

        return false;
    }
}

// -----submission codes end here-----

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_problem() {}
}
