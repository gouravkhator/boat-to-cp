class LargestDiffNodeDescendent {
    class Tree{
      int val;
      Tree left, right;
    }

    public int helper(Tree root, int min, int max){
        /*
        Logic:

        Always have minimum element and maximum element at that node and return the largest absolute difference from there.

        Ex-
              0
            /   \
           4     2
                / \
               1   7
        In this tree, we set (min=0, max=0) at node of value 0. And, recur for left and right.
        
        In left, we get min as 0 and max as 0 from parent, but then we check min and max with 4 and set accordingly.
        At 4, min is 0 and max is 4. Goto left and right of 4. 
        As nothing is there, return diff as max - min = 4-0 = 4.

        Goto 2 with min as 0 and max as 0. At 2, set min to 0 and max to 2.
        Goto its left node 1 with min as 0 and max as 2.
        At 1, min is checked and remains 0. max is also checked but remains 2.
        Return max - min which is 2-0 = 2.
        Goto 7 with min as 0 and max as 2. At 7, we check min and max and set max to 7 and min to 0.

        As 7 has no left and right, return diff as 7-0 = 7.
        At 2, we get diffs as 2 (from its own node), 2(from left node 1) and 7(from right node 7)
        Return maximum of these diffs which is 7.
        At 0, we get 0, 4, and 7 as diffs. Return maximum which is 7.
        */
        if(root == null) return 0;

        if(min > root.val){
            min = root.val;
        }

        if(max < root.val){
            max = root.val;
        }
        
        int diff = Math.abs(max - min); // absolute difference

        if(root.left != null){
            diff = Math.max(diff, helper(root.left, min, max));
            // as helper function returns absolute diff and the current diff is already absolute here,
            // so no need to do Math.abs  
        }

        if(root.right != null){
            diff = Math.max(diff, helper(root.right, min, max));
        }

        return Math.abs(diff); // absolute difference
    }

    // Problem Question: https://binarysearch.com/problems/Largest-Difference-Between-Node-and-a-Descendant
    public int solve(Tree root) {
        return helper(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }
}
