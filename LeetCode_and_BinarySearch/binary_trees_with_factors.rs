/**
 * Problem Question: https://leetcode.com/problems/binary-trees-with-factors/
 *
 * ----Analogy-----:
 *
 * The elements are given as unique.
 * Each element will for sure be a root of a binary tree with one element only, that is itself.
 *
 * Example:
 * -----------
 *
 * For example: if the arr = [2, 4, 5, 8, 10, 16]
 *
 * The number of binary trees formed from element 2 as the root will be just 1, that is tree with root as 2.
 * The number of binary trees formed from element 4 as the root will be 2.
 * One of the tree will have one element 4 as the root.
 * 2nd binary tree will have 3 elements, with 4 as the root, and 2 in left and right children.
 * As both the children are same, so we cannot have other unique binary trees.
 *
 * Number of binary trees formed from element 5 will be 1 only, with 5 as the root.
 *
 * Number of binary trees formed from element 8 will be 3.
 * One of the tree will have 8 as the root and no other elements.
 * One of the tree will have 8 as root, 4 in left and 2 in right child. Other tree will have 8 as the root, 2 in left and 4 in right child.
 *
 * Number of binary trees formed from element 10 will be 3.
 * One of the tree will have 10 as the root and no other elements.
 * One of the tree will have 10 as root, 5 in left and 2 in right child. Other tree will have 10 as the root, 2 in left and 5 in right child.
 *
 * Number of binary trees formed from element 16 will be 3.
 * One of the tree will have 16 as the root and no other elements.
 * One of the tree will have 16 as root, and 4 on both left and right and each 4 can have different possibilities like each can then have no children, or they can have children as 2.
 * Other trees will have 16 as root, and 2 on left and 8 on right child, and each of the children namely, 2 and 8 can have different possibilities like each can then have no children, or they can have their own number of possibilities.
 * And other cases can have 16 as root, 8 on left and 2 on right child.
 *
 * ----Approach----:
 *
 * Notes we can get from the above example:
 * ---------------------------------------------
 * > We have to lookup for number of trees formed from the factors, so we can use a dp.
 * > Also, atleast one tree will obviously be formed from every element, even if it has no factors.
 * > If we can sort the elements in ascending order, then it will be better for using the dp array for looking up number of trees required for the factors.
 * > If the factors are same, then number of trees from that factor pair is (number of trees formed from the factor) * (number of trees formed from the factor)
 * > If the factors are different, then number of trees from that factor pair is (number of trees formed from the factor 1) * (number of trees formed from the factor 2) * 2.
 * This is bcoz, those two trees will be different in structure, as one tree will have factor1 on left, another tree will have factor1 on right child.
 * > As the answer can be long, it is better to store number of trees dp and other variables related to number of trees as i64, and also do the Modulo every time we add or multiply.
 * > Also, note that we don't go till all the factors before the current number, instead we go till the floor valur of the square root of the number, and then we can find the other divisor easily, and check if it exists in the sorted_set or not..
 * This is a much faster approach..
 *
 * Steps:
 * --------
 * 1. Sort the array, so that we can make use of the number of trees formed from lesser numbers,
 * which might be factors of current number.
 * 2. Find one divisor of the current number, which is there in the sorted vector.
 * 3. Check if the other divisor exists in the vector or not.. (check it using binary search, as the vector is sorted)
 * 4. Then, calculate the number of trees, as explained in the analogy above.
 * 5. Then, sum those number of trees in the total_trees count.
 * 6. Also, save this number of trees count in the dp for further lookups.
 * 7. Do modulo division whenever we do multiplication or addition.
 * Also save the count of trees in i64 and then cast it to i32 when returning the answer.
 *
 * Example if applicable:
 * ---------------------
 * Example is mentioned in the analogy section.
 *
 * ----Time Complexity & Space Complexity-------:
 *
 * Note: n is the number of elements of the inputted array/vector.
 *  
 * Time Complexity: O((n log n) + (n * sqrt(nums[i]) * log n)) ~ O((n * sqrt(nums[i]) * log n))
 * Space Complexity: O(n)
 *
 * Explanation of the above complexities if applicable:
 * --------------------------------------------------------
 * - For the Time Complexity, O(n log n) is used for sorting the vector.
 * The next part of time complexity has 3 parts:
 *  > O(n) for traversing the n elements.
 *  > For each element nums[i], we check one divisor, by traversing till at max sqrt(nums[i]).
 *  > For each of that first divisor, we check the other divisor by doing binary search which takes O(log n).
 *
 * So, overall it takes O((n * sqrt(nums[i]) * log n)).
 *
 * - For the Space Complexity, space of O(n) is used for the dp.
*/
pub struct Solution {}

// -----submission codes start here-----

impl Solution {
    fn binarysearch(sorted_list: &Vec<i32>, val: i32) -> usize {
        let len: usize = sorted_list.len();

        let (mut left, mut right, mut mid): (isize, isize, isize) = (0, (len as isize) - 1, 0);

        while left <= right {
            mid = left + (right - left) / 2;

            if sorted_list[mid as usize] == val {
                return (mid as usize);
            } else if sorted_list[mid as usize] < val {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        (len + 1) // not returning -1, as it should return positive numbers only, so returning the (len + 1)
    }

    pub fn num_factored_binary_trees(arr: Vec<i32>) -> i32 {
        const MOD: i64 = i64::pow(10, 9) + 7;

        let len: usize = arr.len();
        let mut sorted_list: Vec<i32> = vec![];

        for elem in &arr {
            sorted_list.push(*elem);
        }

        sorted_list.sort_unstable(); // sorting the elements

        // keeping the number of trees in an i64 format, so that we can keep larger numbers, and then do modulo
        let mut number_of_trees_dp: Vec<i64> = vec![1; len];

        // i is initially set to 1, as number of trees for the 1st (aka smallest) element in this sorted_list will have no factors which are present in this list..
        // that is why, total_trees is also set to 1 initially, as we count the number of trees for 1st element here itself, and don't count it afterwards..
        let (mut i, mut j, mut total_trees): (usize, usize, i64) = (1, 0, 1);

        while i < len {
            // take the first number which divides sorted_list[i]

            let sqrt_num: i32 = (f64::sqrt(sorted_list[i] as f64)).floor() as i32;
            j = 0;

            while sorted_list[j] <= sqrt_num {
                if sorted_list[j] == 0 {
                    j += 1;
                    continue;
                }

                if sorted_list[i] % sorted_list[j] == 0 {
                    // now check if the other divisor exists or not
                    let other_divisor: i32 = sorted_list[i] / sorted_list[j];

                    if sorted_list[j] == other_divisor {
                        // same factors
                        let dp_1: i64 = number_of_trees_dp[j];
                        number_of_trees_dp[i] += (dp_1 * dp_1) % MOD;
                    } else {
                        if sorted_list.contains(&other_divisor) {
                            // other divisor should also exist in the sorted_list, then only we can proceed further
                            let dp_1: i64 = number_of_trees_dp[j];

                            // do binarysearch to find the index where this other_divisor is present in the sorted array, so that we can get its dp value
                            let dp_2: i64 =
                                number_of_trees_dp[Self::binarysearch(&sorted_list, other_divisor)];

                            number_of_trees_dp[i] += (((dp_1 * dp_2) % MOD) * 2) % MOD;
                        }
                    }
                }

                j += 1;
            }

            total_trees += (number_of_trees_dp[i] % MOD);
            i += 1;
        }

        (total_trees % MOD) as i32
    }
}

// -----submission codes end here-----

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_problem() {}
}
