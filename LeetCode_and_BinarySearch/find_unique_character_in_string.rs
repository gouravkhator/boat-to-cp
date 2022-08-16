/**
 * Problem Question: https://leetcode.com/problems/first-unique-character-in-a-string/
 *
 * ----Analogy-----:
 *
 * For checking the first unique character's index in the string, one approach is to do nested loop.
 * But, a better way is to save the index of a character in a 26-character long array, containing the indices.
 *
 * If for a character, the array element is -1, it means we have not encountered this character before.
 * We then save the current index to this array index, for this character.
 *
 * If the array element for this character is not -1, it means we have encountered this character before,
 * so we just add `len` to the existing index, which will make the index >= len.
 *
 * For every unique characters present in the string,
 * the array values will be the index of that character, which is always in the range of 0 to (len - 1).
 *
 * We then iterate through the array elements, and if the element is not -1, as well as it is less than len,
 * then we know the character is present, and it is unique.
 * So, we take that index into consideration, and track the minimum of those indices.
 *
 * This means that the minimum index will be the one which contains the first unique character.
 *
 * ----Approach----:
 *
 * Steps:
 * --------
 * Steps explained in the analogy itself.
 *
 * ----Time Complexity & Space Complexity-------:
 *
 * Here, n is the length of the input string.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(26)
 *
 * Explanation of the above complexities if applicable:
 * --------------------------------------------------------
 * - For the Time Complexity, explanation is not needed..
 * - For the Space Complexity, O(26) is used for 26-length array,
 * to save the index for the 26 characters in English alphabets.
 *
 */
pub struct Solution {}

// -----submission codes start here-----

use std::cmp::min;

impl Solution {
    pub fn first_uniq_char(s: String) -> i32 {
        let len: usize = s.len();
        let mut i: usize = len;
        let mut arr: [i32; 26] = [-1; 26];

        for (i, ch) in s.chars().enumerate() {
            if arr[(ch as usize) - 97] == -1 {
                arr[(ch as usize) - 97] = (i as i32);
            } else {
                arr[(ch as usize) - 97] += (len as i32);
            }
        }

        // considering min_index to be len initially, and then if it is len after all checks, then we return -1
        let mut min_index: usize = len;

        for i in 0..26 {
            if arr[i] != -1 && arr[i] < (len as i32) {
                min_index = min(min_index, arr[i] as usize);
            }
        }

        if min_index == len {
            return -1;
        } else {
            return (min_index as i32);
        }
    }
}

// -----submission codes end here-----

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_problem() {}
}
