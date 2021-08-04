/**
 * public class Tree {
 *   int val;
 *   Tree left;
 *   Tree right;
 * }
 */
class CountNodesCBT {
    public class Tree {
        int val;
        Tree left;
        Tree right;
    }
    
    // Code logic contributed by Mohit Daga
    // Problem Question: https://binarysearch.com/problems/Count-Nodes-in-Complete-Binary-Tree
    public int solve(Tree root) {
        if(root == null){
            return 0;
        }

        /*
        Logic is to count depth of left and depth of right and add 1 to it.
        (as current node is also counted)
        */

        return 1 + solve(root.left) + solve(root.right);
    }
}