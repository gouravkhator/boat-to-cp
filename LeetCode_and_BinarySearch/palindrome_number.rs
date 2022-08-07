/**
 * ----Analogy-----:
 *
 * Analogy not needed to be told..
 *
 * ----Approach----:
 *
 * Approach not needed to be told..
 *
 * ----Time Complexity & Space Complexity-------:
 *
 * Time complexity: O(number_of_digits_of_inputed_number)
 * Space complexity: O(1)
 */
pub struct Solution {}

// Problem Question: https://leetcode.com/problems/palindrome-number/

// -----submission codes start here-----

impl Solution {
    pub fn is_palindrome(x: i32) -> bool {
        if x < 0 {
            return false;
        }

        let (mut num, mut rem, mut reversed): (i32, i32, i32) = (x, 0, 0);

        while num > 0 {
            rem = num % 10;
            reversed = reversed * 10 + rem;
            num = num / 10;
        }

        return reversed == x;
    }
}

// -----submission codes end here-----

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_problem() {}
}
