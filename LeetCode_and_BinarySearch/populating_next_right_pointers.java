class PopulatingNextRightPointers {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
    
        public Node() {}
        
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public void recur(Node root){
        /*
        Logic:
        
        If root's left is null, return.
        (As it means root is a leaf node, as perfect binary tree has both child or no child)

        Link left child with right child.
        This link establishes the internal link for root's direct children, 
        but for root's direct children and root's next node direct children, we have to link them as well.

        So, if root's next is present, and root's right is present, and root's next left is present,
        Then link the right child of root to left child of root's next.

        (Note: And root's next is already linked before these recursive calls.)

        Recur for leftChild of current root.
        Recur for rightChild of current root.
        */

        if(root == null || root.left == null) return;
        
        Node leftChild = root.left;
        Node rightChild = root.right;
        
        leftChild.next = rightChild;
        if(root.next != null && root.right != null && root.next.left != null){
            root.right.next = root.next.left;
        }
        
        recur(leftChild);
        recur(rightChild);
    }
    
    // Problem Question: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
    public Node connect(Node root) {        
        recur(root);
        return root;
    }
}
