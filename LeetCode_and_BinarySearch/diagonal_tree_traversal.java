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

        Core logic is to save left child of the nodes present in current diagonal.
        And if right child is present, we save left child to next diagonal list and then traverse to the right child.
        
        If right child is not present, we check the current diagonal list and if it is also empty, we push the sum to result.
        And make the next diagonal list as our current one.
        
        If right child is absent, there can be a case where current diagonal list contains some other elements.
        It is bcoz, those elements might be left child of some other parent.

        So, until and unless current diagonal list is not empty, we sum each element and act as said above.
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
