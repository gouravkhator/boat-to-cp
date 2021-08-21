import java.util.LinkedList;
import java.util.ArrayList;

class DiagonalTreeTraversal {
    public class Tree {
      int val;
      Tree left;
      Tree right;
    }

    // Problem Question: https://binarysearch.com/problems/Diagonal-Tree-Traversal
    public int[] solve(Tree root) {
        /*
        Logic:

        If root's left is present, save it to nextLL (linkedlist for next diagonal elements).
        If root's right is present, just add root's val to currentSum and goto root's right.

        If root's right is absent, just check if currentLL contains something.
        (as in same diagonal may have more elements but they are not in the direct right of current node).
        If that's the case, set root to the first node of currentLL and remove that node from currentLL.

        If root's right is absent and currentLL has nothing, it means this diagonal is fully traversed.

        So, add current node's value to currentSum, then add currentSum to sums array, then reset currentSum to 0.
        Now, set currentLL to point to nextLL. And reset nextLL.
        This means currentLL should contain elements to be traversed for current diagonal,
        and nextLL should contain left childrens of current diagonal.
        */
        if(root == null) return null;

        int currentSum = 0;
        ArrayList<Integer> sums = new ArrayList<>();
        LinkedList<Tree> currentLL = new LinkedList<>();
        LinkedList<Tree> nextLL = new LinkedList<>();

        while(true){
            if(root == null) break;

            if(root.left != null){
                nextLL.add(root.left);
            }

            if(root.right != null){
                currentSum += root.val;
                root = root.right;
            }else if(currentLL.size() != 0){
                // root's right is null and  current ll contains some nodes
                currentSum += root.val;
                root = currentLL.pollFirst();
            }else{
                // root's right is null and current ll has no elements
                currentSum += root.val;
                currentLL = nextLL; // setting reference of currentLL to point to nextLL's reference
                nextLL = new LinkedList<>(); // clearing nextLL by just resetting its reference
                sums.add(currentSum);
                currentSum = 0;
                root = currentLL.pollFirst(); // set root to currentLL's first node
            }
        }

        int len = sums.size();
        int[] sumsArr = new int[len];
        int k = 0;

        for(Integer elem: sums){
            sumsArr[k++] = elem;
        }

        return sumsArr;
    }
}
