# [Swapping Socks](https://binarysearch.com/problems/Swapping-Socks)

## Approach

In below code, there is a catch:

If current even number does not have +1 number near it, then return current index + 1.
And swap current index + 1 with the number we want there. And continue this.

## Code | Not Submitted

```java
import java.util.*;

class SwappingSocks {
    public int invalidSockIndex(int[] row, int len){
        int i=0;
        for(i=0; i<len; i++){
            if(row[i] % 2 == 0){
                if(i < len - 1 && row[i+1] != row[i] + 1){
                    return i;
                }else if(i > 0 && row[i-1] != row[i] + 1){
                    return i;
                }
            }
        }

        return -1;
    }

    public int solve(int[] row) {
        int index=0, len = row.length, count = 0;

        while((index = invalidSockIndex(row, len)) != -1){
            int otherMemberIndex = Arrays.binarySearch(row, row[index] + 1);

            if(otherMemberIndex > 0){
                int temp = row[otherMemberIndex - 1];
                row[otherMemberIndex - 1] = row[index];
                row[index] = temp;
                count++;
            }else if(otherMemberIndex == 0){
                int temp = row[otherMemberIndex + 1];
                row[otherMemberIndex + 1] = row[index];
                row[index] = temp;
                count++;
            }
        }

        return count;
    }
}

```
