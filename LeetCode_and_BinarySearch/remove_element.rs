/**
 * ----Analogy-----:
 *
 * No analogy applicable here.
 *
 * ----Approach----:
 *
 * When I am traversing the array, if the current value is not same as `val`,
 * then, I increment the size of my window, and then copy this current value to the end of my window.
 *
 * I am tracking the end of this window, by keeping track of last index namely `index`.
 *
 * ----Time Complexity & Space Complexity-------:
 *
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
pub struct Solution {}

// Problem Question: https://leetcode.com/problems/remove-element/

// -----submission codes start here-----

impl Solution {
    pub fn remove_element(nums: &mut Vec<i32>, val: i32) -> i32 {
        let len: usize = nums.len();
        let mut index: i32 = -1;
        // keeping index as -1, so that we can also check for value at 0th index, and not just have to check for 0th index explicitly

        for i in 0..len {
            if nums[i] != val {
                index += 1;
                nums[index as usize] = nums[i];
            }
        }

        (index + 1)
    }
}

// -----submission codes end here-----

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_problem() {}
}
