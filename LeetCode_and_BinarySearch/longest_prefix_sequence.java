import java.util.*;

class LongestPrefixSeq {
    public static void main(String[] args) {
      System.out.println(solve(new String[]{"abc", "ab", "x", "xy", "abcd"}));
      System.out.println(solve(new String[]{"abc", "", "x", "xy", "abcd"}));
      System.out.println(solve(new String[]{"abc", "ab", "x", "xyz", "abcd"}));
      System.out.println(solve(new String[]{"aca","acb","ac","a"}));
      System.out.println(solve(new String[]{"aaa","aa","ac","a"}));
    }

    public static int search(String[] words, String key, int len) {
      // As words are sorted by length and then by alphabetical sorting, so check till that length only.
      for(int i=0; i<words.length && words[i].length() <= len; i++){
        if(words[i].equals(key)){
          return i;
        }
      }

      return -1;
    }

    // Problem Question: https://binarysearch.com/problems/Longest-Prefix-Sequence
    public static int solve(String[] words) {
        /*
        Logic:
        Sort the words array first by length ascending and if length is same, then by alphabetical ascending sorting.

        Start traversing from last of arr as it is the longest word.
        If current word is empty (""), then check unvisited and if its present, jump to that index.

        Remove a letter from current word's last, then search for remaining word.
        If the remaining word exists in words arr, then jump to that index.
        (While jumping, check if the next location is 1 index before current index,
        If that's the case, then unvisited index will not be set as we did not skip any element in between.
        
        If jumping will skip some elements in between save that index as unvisited index.)

        If remaining word was absent in words arr, check unvisited index and jump accordingly,
        and also check count with maxCount and then reset count to 0.
        */

        Arrays.sort(words, new java.util.Comparator<String>() {
          @Override
          public int compare(String s1, String s2) {
            int l1 = s1.length();
            int l2 = s2.length();

            if(l1 != l2){
              return l1 - l2;
            }else{
              return s1.compareTo(s2);
            }
          }
        });

        int len = words.length;

        boolean[] visited = new boolean[len];
        int count = 0, maxCount = 0, visitedCount = 0, i=len - 1, unvisited = -1;

        while(visitedCount != len){
            if(i < 0){
              // if we get -ve index, then continue, as we may encounter -ve index in some test case
              // we don't want to break
              continue;
            }

            visited[i] = true; // set visited to true
            visitedCount++; // increment visited count by 1
            count++; // this count is the count of current sequence

            int tempLen = words[i].length();

            if(tempLen == 0){
              // if the current word is already "", then just jump to unvisited index if any.
              // also check count of current sequence with maxCount of longest sequence.
              // reset count after this checking 
              if(unvisited >= 0){
                i = unvisited;
                unvisited = -1;
              }else{
                  i--;
              }

              if(count > maxCount){
                  maxCount = count;
              }

              count = 0;
              continue;
            }

            String tempStr = words[i].substring(0, tempLen - 1);

            int index = search(words, tempStr, tempLen - 1);

            // if the remaining word was found, and its not visited yet
            if(index >= 0 && visited[index] != true){
                // if we skipped some index in between, and that was unvisited, then set unvisited index to that skipped index.
                if(index != (i-1) && unvisited == -1 && i>0 && visited[i-1] != true)
                    unvisited = i - 1;

                i = index; // jump to the next sequential word
            }else{
                // if word was not found and unvisited is set, then jump to unvisited
                if(unvisited >= 0){
                    i = unvisited;
                    unvisited = -1; // reset unvisited
                }else{
                    i--;
                }

                if(count > maxCount){
                    maxCount = count;
                }

                count = 0;
            }
        }

        return maxCount;
    }
}
