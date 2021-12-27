# [Guess Number Higher or Lower II](https://leetcode.com/problems/guess-number-higher-or-lower-ii/)

## Approach

Choose a number at the start itself which can lead to min amount.

Maybe loop through the starting number from 1 to n.

## Code | Not Submitted

```java
class GuessNumHigherLowerII {
    // if the tree is balanced or not
    public static boolean isBalanced(Tree root) {
        if(root == null) return true;
        int ld = depth(root.left);
        int rd = depth(root.right);

        int absDiff = Math.abs(ld - rd);
        if(absDiff > 1){
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }
    
    // calculate depth of a tree
    public static int depth(Tree root) {
        if(root == null) return 0;

        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    static class Tree{
        int val;
        Tree left, right;
    }
    
    // create a bst tree
    public static void createTree(Tree root, int min, int max){
        if(root == null) return;
        
        if(min >= max){
            return;
        }
        
        root.left = new Tree();
        root.left.val = (min + root.val - 1)/2;
        
        if(root.left.val > max || root.left.val < min){
            root.left = null;
        }
        
        root.right = new Tree();
        root.right.val = (root.val + 1 + max)/2;
        
        if(root.right.val > max || root.right.val < min){
            root.right = null;
        }
        
        createTree(root.left, min, root.val - 1);
        createTree(root.right, root.val + 1, max);
    }
    
    // maximum sum of root to leaf excluding the leaf node
    public static int maxSumToLeaf(Tree root){
        if(root == null) return 0;
        if(root.right == null && root.left==null) return 0;
        
        return root.val + Math.max(maxSumToLeaf(root.left), maxSumToLeaf(root.right));
    }
    
    public static void preorder(Tree root){
        if(root == null) return;
        
        preorder(root.left);
        System.out.print(root.val + " ");
        preorder(root.right);
    }

    // Problem Question: https://leetcode.com/problems/guess-number-higher-or-lower-ii/
    public static int getMoneyAmount(int n) {
        if(n==1){
            return 0;
        }
        
        int i=0, minAmt = Integer.MAX_VALUE;
        
        for(i=1; i<=n; i++){
            Tree root = new Tree();
            root.val = i;

            createTree(root, 1, n); // a bst tree, with left from 1 to i-1 and right from i+1 to n

            // System.out.println(root.val);
            // preorder(root);
            // System.out.println();
            int tempAmt = maxSumToLeaf(root);
            // System.out.println("Amt: "+tempAmt);
            if(minAmt > tempAmt){
                minAmt = tempAmt;
            }
        }
        
        return minAmt;
    }

    public static void main(String[] args) {
        System.out.println(getMoneyAmount(12));
        System.out.println(getMoneyAmount(8));
        System.out.println(getMoneyAmount(9));
        System.out.println(getMoneyAmount(10));
    }
}
```