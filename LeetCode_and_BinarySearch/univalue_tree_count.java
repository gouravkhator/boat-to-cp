class UnivalTreeCount {
    /*
    Test cases for binarysearch platform Tree view :

    [0, [0, [1, null, null], [0, null, null]], null]
    [0, [0, [1, null, null], [0, null, null]], [0, [0, [0, null, null], [0, null, null]], [0, [0, null, null], [1, null, null]]]]
    [0, [0, [0, null, null], [0, null, null]], [0, [0, null, null], [1, null, null]]]
    */

    public class Tree{
        int val;
        Tree left, right;
    }

    class IntMod{
        int count = 0;
    }

    public boolean helper(Tree root, int parentVal, IntMod im){
        /*
        Logic:

        Take the parent's val being passed from current root's predecessors.

        If current val is same as their val, then recur for left and right child of current root.

        If the left and right child says true, then count this current root.

        Returning true means all nodes from current root to it's successors are same valued as the parent val.
        Returning false means some nodes are different valued.
        
        If current val is different from parent val, just recur for left and right child of current root as before.
        But now, the parentVal param will be reset to current root's val.
        */

        if(root == null){
            return true;
        }

        if(root.val == parentVal){

            boolean flag = helper(root.left, parentVal, im);
            flag = helper(root.right, parentVal, im) && flag;

            // if I did logical AND in the same line, the next recursion for right child might not occur, 
            // in cases where the left child would return false (as false && something is always false and java would not compute the next expression).

            if(flag == true){
                // if flag is true from both subtrees, then count this node too
                im.count++;
            }

            return flag;
            // if flag is true, then return true else false
            // as left and right subtrees also matter in whether the predecessors of current root are counted in unival or not.
        }

        boolean flag = helper(root.left, root.val, im);
        flag = helper(root.right, root.val, im) && flag;

        if(flag == true){
            im.count++;
        }

        return false;
        // return false, as the current root is different valued from parentVal, so the predecessor will always not be counted in this case.
    }

    // Problem Question: https://binarysearch.com/problems/Univalue-Tree-Count
    public int solve(Tree root) {
        if(root == null){
            return 0;
        }

        IntMod im = new IntMod(); // for count variable to be modifiable in the method calls, I used a wrapper class for int.
        // class Integer was not working well here, maybe can try with some approach of Integer pre-defined wrapper class.

        helper(root, root.val, im);

        return im.count;
    }
}