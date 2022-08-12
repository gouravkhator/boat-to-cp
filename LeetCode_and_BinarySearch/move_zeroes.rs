/**
 * Problem Question: https://leetcode.com/problems/move-zeroes/
 *
 * ----Analogy-----:
 *
 * Analogy #1:
 * ------------
 * Just count number of zeroes, and then copy the non-zero elements from left to the right..
 * And then overwrite the rest of the array with the total number of zeroes.
 *
 * Analogy #2: (a better one with lesser number of operations)
 * --------------------------------------------------------------
 *
 * Keep track of the latest non zero element's index,
 * and when we encounter non-zero element,
 * we swap that with the next cell of latest non-zero element.
 *
 * Every time we have the non-zero element, we should put that after the last put non-zero element.
 *
 * ----Approach----:
 *
 * Steps:
 * --------
 * 1. Initialise the latest non zero element's index (lnzi for short) with -1.
 * (as we didn't encounter any non-zero yet)
 * 2. Then we traverse the array, and check if that is non-zero..
 * 3. If the current element is non-zero, we increment the lnzi and put this non-zero element at lnzi index.
 * 4. We make sure that we are not overwriting the current element at the same location where it was there before.
 * 5. And then, we make this current element as 0.
 *
 * This way, all the non-zero elements gets put from left to right, and at their position, we mark it as 0.
 *
 * Example if applicable:
 * ---------------------
 * NA
 *
 * ----Time Complexity & Space Complexity-------:
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Explanation of the above complexities if applicable:
 * --------------------------------------------------------
 * NA
 */
pub struct Solution {}

// -----submission codes start here-----

impl Solution {
    pub fn move_zeroes(nums: &mut Vec<i32>) {
        let mut latest_non_zero_elem_index: i32 = -1;
        let mut i: usize = 0;
        let len: usize = nums.len();

        while i < len {
            if nums[i] != 0 {
                latest_non_zero_elem_index += 1;

                if i != (latest_non_zero_elem_index as usize) {
                    // this check is done, so that we don't overwrite the same location
                    nums[latest_non_zero_elem_index as usize] = nums[i];
                    nums[i] = 0;
                }
            }

            i += 1;
        }
    }
}

// -----submission codes end here-----

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_problem() {
        let mut test_cases = vec![
            vec![0, 1, 0, 3, 12],
            vec![0],
            vec![0, 1, 0, 3, 5, 12, 0, 9, 1, 0, 0, 3],
            vec![1],
            vec![1, 2],
            vec![1, 2, 0],
            vec![0, 1, 0],
        ];

        for test_case in &mut test_cases {
            let vector = test_case;
            Solution::move_zeroes(vector);
            println!("{:?}", vector);
        }
    }
}
