# Convert Binary Tree to Doubly Linked List

## Question

Given a Binary Tree (Bt), convert it to a Doubly Linked List(DLL).

The left and right pointers in nodes are to be used as previous and next pointers respectively in converted DLL.

The order of nodes in DLL must be the same as in Inorder for the given Binary Tree.

The first node of Inorder traversal (leftmost node in BT) must be the head node of the DLL. 

## Code | WA solution

> We get wrong solution, when printing the DLL in backward direction. The forward direction printing works fine.

```java
import java.util.Stack;

/*
Question:

Given a Binary Tree (Bt), convert it to a Doubly Linked List(DLL).
The left and right pointers in nodes are to be used as previous and next pointers respectively in converted DLL.
The order of nodes in DLL must be the same as in Inorder for the given Binary Tree. 
The first node of Inorder traversal (leftmost node in BT) must be the head node of the DLL. 
*/
class ConvertBTtoDLL {
    /**
     * MasterNode represents wrapping of node. It is done to change and pass Tree
     * nodes as if it was a global variable.
     */
    static class MasterNode {
        TreeNode node;
    }

    static class TreeNode {
        int data;
        TreeNode left, right;

        TreeNode(int data) {
            this.data = data;
        }

        TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Prints Doubly Linked List in forward direction
     * 
     * @param root
     */
    static void printDLLForwards(TreeNode root) {
        if (root == null)
            return;

        System.out.print(root.data + " <=> ");
        printDLLForwards(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(12);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(25);
        root.left.right = new TreeNode(30);
        root.right.left = new TreeNode(36);

        root = convertBTtoDLL(root); // inplace change is done and new changed root is returned
        System.out.println("Doubly linked list in forward direction");
        printDLLForwards(root);
        System.out.println();
    }

    // Main Convert BT to DLL method
    static TreeNode convertBTtoDLL(TreeNode root) {
        if (root == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        MasterNode previousNodeChecked = new MasterNode();
        previousNodeChecked.node = null; // initially previousNodeChecked is null

        MasterNode originalRoot = new MasterNode();
        originalRoot.node = root;

        helper(stack, originalRoot, previousNodeChecked);
        root = originalRoot.node; // it will be edited to point to leftmost node of the whole tree
        // as root is pointing to new reference, so we return the root
        // we can use MasterNode too
        return root;
    }

    static void helper(Stack<TreeNode> stack, MasterNode originalRoot, MasterNode previousNodeChecked) {
        /*
         * Logic:
         * 
         * We push the root to stack initially. Then in helper function, we get that
         * root and push its left and recur. Once we get to leftmost, we pop the
         * leftmost node from stack, that is the top element.
         * 
         * previousNodeChecked represents the node which was really taken into
         * consideration last time. previousNodeChecked and originalRoot are taken as
         * MasterNode, so that we can change the node's reference inside the objects and
         * pass it easily.
         * 
         * If previousNodeChecked's node is null, it means current root should be the
         * first node in doubly linked list (DLL). Then, edit originalRoot's node to
         * point to this node.
         * 
         * Then root's left should point to previousNodeChecked's node. And if
         * previousNodeChecked's node is not null, previousNodeChecked's node's right
         * should point to current node.
         * 
         * This means linking the two nodes bidirectional. Now set the current node to
         * be previousNodeChecked's node. And then check for right child.
         * 
         * If right child is not present, then current node's right will be pointing to
         * its parent/predecessor, which is present at stack's peek.
         * 
         * There might be cases when stack is empty, then current node's right will
         * point to null.
         * 
         * If originally current node's right was not null, we push the right child to
         * stack, and recur again.
         */

        int len = stack.size();

        if (len == 0)
            return;

        TreeNode root = stack.peek();

        if (root.left != null) {
            stack.push(root.left);
            helper(stack, originalRoot, previousNodeChecked);
        }

        stack.pop();

        if (previousNodeChecked.node == null) {
            // edit main root to point to current root.
            originalRoot.node = root;
        }

        root.left = previousNodeChecked.node; // current's left will be pointing to previous checked node

        if (previousNodeChecked.node != null) {
            // if previousNodeChecked is also present, then link its right to point to
            // current checked node
            previousNodeChecked.node.right = root;
        }

        previousNodeChecked.node = root; // now current node becomes previous checked node for further nodes

        if (root.right == null) {
            if (stack.size() > 0)
                root.right = stack.peek();
        } else {
            stack.push(root.right);
            helper(stack, originalRoot, previousNodeChecked);
        }
    }
}
```
