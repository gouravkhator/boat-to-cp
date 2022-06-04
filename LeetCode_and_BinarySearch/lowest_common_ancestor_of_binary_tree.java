/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LowestCommonAncestor {
  public void searchNode(TreeNode node, int target, List<TreeNode> tempPath, List<TreeNode> resPath){
    if(node == null){
      return;
    }
    
    if(node.val == target){
      resPath.clear();
      resPath.addAll(new ArrayList<>(tempPath));
      return;
    }
    
    if(node.left != null){
      tempPath.add(node.left);
      searchNode(node.left, target, tempPath, resPath);
      tempPath.remove(node.left);
    }
    
    if(resPath.size() == 0 && node.right != null){
      tempPath.add(node.right);
      searchNode(node.right, target, tempPath, resPath);
      tempPath.remove(node.right);
    }
  }
  
  // Problem Question: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    /*
    Current approach:
    -------------------
    Get the paths for each of the nodes, and then traverse the path1 in reverse order,
    and then find that node in the other path path2, and if it is present, then we return that node as the LCA.
    
    We have to optimize this approach, as it takes 879 ms runtime, on submission, but got accepted as faster than 5% submissions.
    */
    List<TreeNode> tempPath = new ArrayList<>();
    tempPath.add(root);
    
    List<TreeNode> resPath1 = new ArrayList<>();
    searchNode(root, p.val, tempPath, resPath1);
    
    tempPath = new ArrayList<>();
    tempPath.add(root);
    
    List<TreeNode> resPath2 = new ArrayList<>();
    searchNode(root, q.val, tempPath, resPath2);
    
    List<Integer> resPath2Int = new ArrayList<>();
    
    for(TreeNode node: resPath2){
      resPath2Int.add(node.val);
    }
    
    int sizePath1 = resPath1.size();
    
    for(int i=sizePath1 - 1; i>=0; i--){
      TreeNode node = resPath1.get(i);
      
      if(resPath2Int.indexOf(node.val) >= 0){
        return node;
      }
    }
    
    return null;
  }
}
