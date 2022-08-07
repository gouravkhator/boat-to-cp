/**
 * ----Analogy-----:
 *
 * We keep track of our unique elements window, and if the current element is a new element,
 * then we add that to our unique elements window.
 *
 * ----Approach----:
 *
 * A pointer `index` is kept for tracking the unique elements' last point.
 * And when we traverse the array, we check the current element with the element at that `index`,
 * and if it is the same, then we know it is just a duplicate of that unique element already considered.
 *
 * If it is different, then we increment that index pointer by 1, and copy this new different element to that new position.
 * This way, we consider new elements and copy them to our considered unique elements window.
 *
 * ----Time Complexity & Space Complexity-------:
 *
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
pub struct Solution {}

// Problem Question: https://leetcode.com/problems/remove-duplicates-from-sorted-array/

// -----submission codes start here-----

impl Solution {
    pub fn remove_duplicates(nums: &mut Vec<i32>) -> i32 {
        let len: usize = nums.len();
        let mut index: usize = 0;

        for i in 1..len {
            if nums[index] != nums[i] {
                index += 1;
                nums[index] = nums[i];
            }
        }

        // `index` is the last point, till which we get the unique elements, so (index+1) is the number of unique elements
        (index + 1) as i32
    }
}

// -----submission codes end here-----

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_problem() {}
}
