/**
 * Problem Question: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * ----Analogy-----:
 *
 * We get the paths from the root node to those two particular nodes.
 * And when we check the paths in reverse order, we return the first element which comes in the reverse order of the paths.
 *
 * This means, the last element which is common in the paths from root to those two nodes.
 *
 * ----Approach----:
 *
 * Steps:
 * --------
 * 1. Get the addresses of all the nodes in a vector of addresses, from the root node to node p.
 * 2. Repeat step 1 but for node q too.
 * 3. Then, check start traversing the paths to node p, and check if that node is also present in path_to_q vector..
 * 4. If present, then return that as the Lowest common ancestor, else continue..
 *
 * Extra Notes:
 * --------------
 * We just save the Rc<RefCell<TreeNode>> in the vector for the paths.
 * This is important, so that we don't make copies of the TreeNode unnecessarily,
 * and just clone the addresses whenever we need to access the node.
 *
 * Also, in the function "get_path_from_root_to_node", we pass the root as an option itself,
 * as sometimes the children we recur for, can be None too, so passing Option enum makes the code simple..
 *
 * Example if applicable:
 * ---------------------
 * NA
 *
 * ----Time Complexity & Space Complexity-------:
 *
 * Note: n is the number of nodes in the BST.
 *
 * Time Complexity: O(log n + log n) ~ O(log n)
 * Space Complexity: O(log n + log n) ~ O(log n)
 *
 * Explanation of the above complexities if applicable:
 * --------------------------------------------------------
 * - For the Time Complexity, log n is required for getting the path from root to a node in BST.
 * And a vector path can have at max (log n) elements, and we need to traverse those (log n) elements,
 * and check if it is present in the other paths vector.
 * For the contains method of vectors, Vectors in rust have O(1) indexing..
 * Overall, this would also take at most O(log n).
 * In total, time complexity is O(log n)
 *
 * - For the Space Complexity, 2 path vectors are to be created, and each vectors have atmost (log n) elements..
 * So, space complexity is O(log n)
 *
 */
pub struct Solution {}

// -----submission codes start here-----

use std::cell::RefCell;
use std::rc::Rc;

// Definition for a binary tree node.
#[derive(Debug, PartialEq, Eq)]
pub struct TreeNode {
    pub val: i32,
    pub left: Option<Rc<RefCell<TreeNode>>>,
    pub right: Option<Rc<RefCell<TreeNode>>>,
}

impl TreeNode {
    #[inline]
    pub fn new(val: i32) -> Self {
        TreeNode {
            val,
            left: None,
            right: None,
        }
    }
}

impl Solution {
    fn get_path_from_root_to_node(
        root: &Option<Rc<RefCell<TreeNode>>>,
        node_val: i32,
        path: &mut Vec<Rc<RefCell<TreeNode>>>,
    ) {
        if let Some(main_root) = root {
            // Note: main_root is unwrapped from root, so it is of type &Rc<RefCell<TreeNode>>
            // main_root is auto-dereferenced to RefCell and we can use borrow here to get the immutable reference to TreeNode
            let root_val = main_root.borrow().val;

            // to copy the addresses in the vector also, we use Rc::clone
            path.push(Rc::clone(main_root));

            if root_val == node_val {
                return;
            } else if root_val < node_val {
                Self::get_path_from_root_to_node(&(main_root.borrow().right), node_val, path);
            } else {
                Self::get_path_from_root_to_node(&(main_root.borrow().left), node_val, path);
            }
        }
    }

    fn get_lca_from_paths(
        path_to_p: &Vec<Rc<RefCell<TreeNode>>>,
        path_to_q: &Vec<Rc<RefCell<TreeNode>>>,
    ) -> Option<Rc<RefCell<TreeNode>>> {
        let (len1, len2): (usize, usize) = (path_to_p.len(), path_to_q.len());

        // i and j can go beyond 0, in the negative direction, so using isize instead of usize
        let (mut i, mut j): (isize, isize) = ((len1 as isize) - 1, (len2 as isize) - 1);

        while i >= 0 {
            if path_to_q.contains(&path_to_p[i as usize]) {
                return Some(Rc::clone(&path_to_p[i as usize]));
            }

            i -= 1;
            j -= 1;
        }

        return None;
    }

    pub fn lowest_common_ancestor(
        root: Option<Rc<RefCell<TreeNode>>>,
        p: Option<Rc<RefCell<TreeNode>>>,
        q: Option<Rc<RefCell<TreeNode>>>,
    ) -> Option<Rc<RefCell<TreeNode>>> {
        let mut path_to_p: Vec<Rc<RefCell<TreeNode>>> = vec![];
        let mut path_to_q: Vec<Rc<RefCell<TreeNode>>> = vec![];

        // the p and q is always some valid node as per the question, so we just used unwrap
        let main_p: Rc<RefCell<TreeNode>> = p.unwrap();
        let main_q: Rc<RefCell<TreeNode>> = q.unwrap();

        Self::get_path_from_root_to_node(&root, main_p.borrow().val, &mut path_to_p);
        Self::get_path_from_root_to_node(&root, main_q.borrow().val, &mut path_to_q);

        Self::get_lca_from_paths(&path_to_p, &path_to_q)
    }
}

// -----submission codes end here-----

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_problem() {}
}
