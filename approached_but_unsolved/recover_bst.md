# [Recover BST](https://leetcode.com/problems/recover-binary-search-tree/)

## Approach

Store min valid node and max valid node for a particular node.

Ex-
```
       3
     /   \
    1     4
         /
        2
```

For node 3, no range on min and max.

For node 1, min node is null and max node 3 (means 1 should be less than 3 and not equals 3 keeping the property of bst).

For node 4, min node is node 3 and max node is null.

For node 2, min node is node 3 and max node is node 4.

As node 2 is invalidly placed. So check which one is invalid with node 2 (max node or min node).

Min node is invalid here. So swap it with node 3.