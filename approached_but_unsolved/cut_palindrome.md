# [Cut Palindrome](https://binarysearch.com/problems/Cut-Palindrome)

## Approach

At each index in string a, take the first part of a. And at that index only, take the second part of b. Then merge them and if its palindrome then true else continue with increasing the index.

## Code | TLE Solution

```java
import java.util.*;

class Solution {
    public boolean isPalin(String s){
        StringBuffer sb = new StringBuffer(s);
        return sb.reverse().toString().equals(s);
    }

    public boolean solve(String a, String b) {
        int len = a.length(), i=0;

        if(isPalin(b)){
            return true;
        }

        String temp = "";
        for(i=1; i<=len; i++){
            temp = a.substring(0, i) + b.substring(i);
            if(isPalin(temp)){
                return true;
            }
        }

        return false;
    }
}

```