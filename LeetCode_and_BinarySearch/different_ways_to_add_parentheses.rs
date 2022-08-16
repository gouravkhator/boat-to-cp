/**
 * Problem Question: https://leetcode.com/problems/different-ways-to-add-parentheses/
 *
 * ----Analogy-----:
 *
 * I first thought of trying out with taking first operand and recurring for rest,
 * and then taking first two operands and recurring for rest.
 * So, this approach would use two recursion, but it gave a wrong answer.
 * This can be explained in the below example, when the approach can break:
 *
 * Example expression: "2*3-4*5"
 *
 * With my previous approach, it would be "2*(rest of expression to be recurred)" or "(2*3)-(rest of expression)"
 * But then, the approach missed below expressions:
 * "((2*3)-4)*5"
 * "(2*(3-4))*5"
 *
 * Analysis from these missed expressions tells that we need to recur not based on operands,
 * but based on operators.
 *
 * So, when we get the operator, we calculate the results vector
 * from the left part of the expression, as well as from the right part of the expression.
 *
 * And then the results from the left part and the results from right part,
 * are taken into consideration in a nested loop,
 * and we take the final result from doing operation on those two, with the current operator.
 *
 * The above analogy was inspired from the youtube video,
 * as I was unable to find exact patterns from taking the examples.
 *
 * ----Approach----:
 *
 * Steps:
 * --------
 * 1. Loop through the expression, and once we encounter the operator, we follow below steps.
 * 2. Take the slice to the left part of the expression (that is before the operator).
 * 3. Take the slice to the right part of the expression (that is after the operator).
 * 4. We get the results vector from the left part and right part.
 * 5. If the results_from_left is [2,-1], and results_from_right is [-9,5,1], and the operator is let's say '+',
 * then, the final results vector for the current expression as a whole will be:
 * [(2+(-9)), ((-1)+(-9)), (2+5), (-1+5), (2+1), (-1+1)] = [-7, -10, 7, 4, 3, 0]
 * 6. We do memoizations of the expressions,
 * so that we don't have to again calculate the results vector for those expressions.
 * This memoization is done using HashMap.
 *
 * Example if applicable:
 * ---------------------
 *
 * For expression: "2-1-1", we do the recursions for two of the minus operators.
 *
 * For the 1st operator, we have results_from_left as [2], and results_from_right as [0].
 * So, we do 2-0 that is 2.
 *
 * For the 2nd operator, we have results_from_left as [(2-1)] = [1], and results_from_right as [1],
 * so we do 1-1 that is 0.
 *
 * So, finally answer is [2, 0].
 *
 * ----Time Complexity & Space Complexity-------:
 *
 * If expression is of length `n` and has totally `k` operators:
 * Time Complexity: O(n*k)
 * Space Complexity: O(size_of_hashmap_used + internally_created_vectors_size_in_recur_function)
 *
 * Explanation of the above complexities if applicable:
 * --------------------------------------------------------
 * - For the Time Complexity, without memoizations, we would have a recursion like below:
 *
 * For expression: "2-1-1"
 *       ________"2-1-1"________
 *            -            -
 *          /   \        /   \
 *         2   1-1     2-1    1
 *            /   \   /  \   
 *           1     1 2    1
 *
 * Here, total operators were 2, and there were 2 levels, if we exclude the root level.
 * And at each level, we have at max `n` length expression to be evaluated.
 * So, without memoization, it is O(n^k), as in above example, it would take n*n for those 2 levels.
 *
 * With memoization, it is approximately O(n*k),
 * as for each operator, we have to evaluate the results vector for the whole `n` length expression once.
 * This time complexity cannot be calculated in a generic way, but we can calculate it from above perspective.
 *
 * - For the Space Complexity, we are using hashmap for memoizing,
 * and internally we are also creating some memory for the vectors in each recur function call.
 *
 * So, Space Complexity: O(size_of_hashmap_used + internally_created_vectors_size_in_recur_function)
 */
pub struct Solution {}

// -----submission codes start here-----

use std::collections::HashMap;

impl Solution {
    fn recur<'a>(expression: &'a str, map: &'a mut HashMap<String, Vec<i32>>) -> Vec<i32> {
        /*
        As multiple references in the function parameters, require lifetime annotations,
        so we annotate both `expression` and `map` with same lifetime annotation..
        (as their main memory lives with the same lifetime at the very least)

        Why we store strings and vectors directly in the HashMaps instead of references?

        Ans> To store strings and vectors in HashMaps, I thought of giving their references itself there too..

        For vectors:
        ---------------
        If we store the reference of the vector, which gets created in the `recur` function only,
        then we have a dangling reference, which is unacceptable..

        As `results` vector gets created inside the scope of `recur` function,
        so we cannot return the reference to that memory from this function..
        The memory automatically gets destroyed when the function returns, and we have a dangling reference.

        For strings:
        ---------------
        For the strings in the hashmaps, we see that it can have a completely different lifetime,
        as the string slices are created inside the nested loop scope in `recur` function.
        As we cannot determine the exact lifetime for the strings, so we use an owned string.
        */
        let len: usize = expression.len();

        if len == 0 {
            return vec![];
        }

        // `contains_key` and `get` methods require passing the reference to the strings, so `expression` works fine here..
        if map.contains_key(expression) {
            return map.get(expression).unwrap().to_vec();
        }

        let mut i: usize = 0;
        let mut results: Vec<i32> = vec![];

        while i < len {
            let curr_char: &str = &expression[i..(i + 1)];

            if curr_char.eq("*") || curr_char.eq("+") || curr_char.eq("-") {
                let left_str: &str = &expression[0..i];
                let right_str: &str = &expression[(i + 1)..];

                let results_from_left: Vec<i32> = Self::recur(left_str, map);
                let results_from_right: Vec<i32> = Self::recur(right_str, map);

                for result_left in &results_from_left {
                    for result_right in &results_from_right {
                        let temp_result: i32 = match curr_char {
                            "*" => result_left * result_right,
                            "+" => result_left + result_right,
                            "-" => result_left - result_right,
                            _ => 0,
                        };

                        results.push(temp_result);
                    }
                }
            }

            i += 1;
        }

        if results.len() == 0 {
            // means no operator was present, so push the expression itself, which only contains the number
            results.push(expression.parse::<i32>().unwrap());
        }

        map.insert(expression.to_string(), results); // memoize the results for this expression

        /*
        Here, we cannot use `results` as it is already moved in the above line of `insert` method invocation
        */
        map.get(expression).unwrap().to_vec()
    }

    pub fn diff_ways_to_compute(expression: String) -> Vec<i32> {
        let mut map = HashMap::new();

        /*
        as the map will be mutated, so it is better to have mutable reference to map,
        rather than transferring ownership
        */
        Self::recur(&expression, &mut map)
    }
}

// -----submission codes end here-----

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_problem() {}
}
