import java.util.ArrayList;
import java.util.List;

class BinaryTreeLevelTraversalII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
        }
    }
    
    public void recur(List<List<TreeNode>> res, List<TreeNode> parents, List<TreeNode> children){
        for(TreeNode root: parents){
            if(root.left != null){
                children.add(root.left);
            }

            if(root.right != null){
                children.add(root.right);
            }
        }
        
        if(children.size() == 0){
            // go through each lists in list and get integer vals in reverse order of list
            return;
        }else{
            // go through the list of children and make a new list of children and save all to them
            res.add(children); // the reference of children is added to res, now if children points to some other location, then also we have the old list address saved
            // so no cloning is required
            recur(res, children, new ArrayList<>());
        }
    }
    
    // Problem Question: https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        /*
        Logic:

        First, add all parents which is initially root.
        Then for each parents, add its children. 
        Then add those children to res list, and make those children the parent for next recursive call.

        Then we have res as List of list of TreeNode. We need to take all TreeNode by traversing list in reverse order in the outer list.

        And, then add the val of those TreeNode to List<List<Integer>>.
        */
        if(root == null) return new ArrayList<>();
        
        List<TreeNode> parents = new ArrayList<>();
        parents.add(root);
        
        List<TreeNode> children = new ArrayList<>();
        List<List<TreeNode>> res = new ArrayList<>();
        
        res.add(parents);
        recur(res, parents, children);
        
        int len = res.size(), i = 0, j=0;
        List<List<Integer>> finalRes = new ArrayList<>();
        
        for(i=len - 1; i>=0; i--){
            List<TreeNode> temp = res.get(i);
            int levelListLen = temp.size();
            
            if(levelListLen == 0){
                continue;
            }
            
            List<Integer> intList = new ArrayList<>();
            
            for(j=0; j<levelListLen; j++){
                intList.add(temp.get(j).val);
            }

            finalRes.add(intList);
        }
        
        return finalRes;
    }
}
