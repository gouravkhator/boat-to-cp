# [Maximum Width of Binary Tree](https://leetcode.com/problems/maximum-width-of-binary-tree/)

## Approach

Take bfs of the tree and store each node to a list in bfs order (level 0 first, then level 1 then level 2 and so on). For null values add null values also.

Then loop through the list and for each level find max width just by finding first non null node and last non null node of that level.

## Code | WA Solution

```java
import java.util.ArrayList;

class MaxWidthBinaryTree {
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

    public ArrayList<TreeNode> bfs(TreeNode root){
        if(root == null) return new ArrayList<>();
        
        ArrayList<TreeNode> list = new ArrayList<>();
        list.add(root);
        int index = 0;
        TreeNode temp = null;
        boolean hasChild = false;
        
        while(true){
            if(list.size() > index){
                temp = list.get(index);
                
                if(temp != null && temp.left == null && temp.right == null){
                    hasChild = false;
                    index++;
                    list.add(temp.left);
                    list.add(temp.right);
                    continue;
                }
                
                if(temp == null){
                    if(hasChild == false){
                        index++;
                        continue;
                    }
                    
                    temp = new TreeNode();
                    temp.left = null;
                    temp.right = null;
                }
                
                hasChild = true;
                list.add(temp.left);
                list.add(temp.right);
                index++;
            }else{
                break;
            }
        }
        
        return list;
    }
    
    // Problem Question: https://leetcode.com/problems/maximum-width-of-binary-tree/
    public int widthOfBinaryTree(TreeNode root) {
        ArrayList<TreeNode> list = bfs(root);
        
        System.out.println("New one: ");
        for(TreeNode node: list){
            if(node == null)
                System.out.print("b ");
            else
                System.out.print(node.val+" ");
        }
        
        int i=0, factor = 1, max = -1, currentNum = 1, firstValidIndex = -1, size = list.size(), lastValidIndex = -1;
        while(true){
            
            for(; i<currentNum && i<size; i++){
                TreeNode temp = list.get(i);
                if(temp != null && firstValidIndex == -1){
                    firstValidIndex = i;
                }
                
                if(temp != null){
                    lastValidIndex = i;
                }
            }
            
            if(firstValidIndex == -1){
                firstValidIndex = 0;
            }
            
            currentNum = i+factor*2;
            factor = factor * 2;
            
            max = Math.max(max, lastValidIndex - firstValidIndex + 1);
            firstValidIndex = -1;
            lastValidIndex = -1;
            
            if(i >= size)
                break;
        }
        
        return max;
    }
}
```
