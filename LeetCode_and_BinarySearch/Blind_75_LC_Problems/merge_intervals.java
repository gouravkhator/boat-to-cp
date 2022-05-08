import java.util.*;

class MergeIntervals {
    // Problem Question: https://leetcode.com/problems/merge-intervals/
    public int[][] merge(int[][] intervals) {
        /*
        Logic:
        
        First sort the intervals based on the first part of each range in ascending order.
        
        Then, add the first range of intervals to Linkedlist ranges.
        
        Then with each range in intervals sorted, just check it with the last element of ranges list.
        
        Let the current range be referred as current.
        
        Let last element of the ranges list be lastRange.
        
        If current's first part is in the range defined by lastRange,
        then the merged range will have first part as minimum of current's first part and lastRange's first part.
        and last part as maximum of current's 2nd part and lastRange's 2nd part.
        
        Then, remove the last element of ranges, as it's included in merged.
        Now, append the newly merged range to that linkedlist.
        
        If current's first part is not in the range defined by lastRange, just append this range to the linkedlist.
        
        After this loop, just traverse the linkedlist ranges and create a 2d array out of it.
        Return that 2d array as answer.
        
        Improvements that was seen in other's program approaches:
        
        This program could mark the intervals, which are already merged and should not be in answer.
        Then, after traversing this, just remove marked ones from answer.
        
        Also, it would help this program, to not use another LinkedList for storing.
        The storing could be done in intervals array itself.
        Just, we need another 2d array for removing the not-required ranges from answer.
        */

        int len = intervals.length;
        if (len == 1) {
            return intervals;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        LinkedList<int[]> ranges = new LinkedList<>();
        ranges.add(intervals[0]);

        for (int i = 1; i < len; i++) {
            int[] lastRange = ranges.get(ranges.size() - 1);

            if (intervals[i][0] >= lastRange[0] && intervals[i][0] <= lastRange[1]) {
                int[] temp = new int[2];

                temp[0] = Math.min(intervals[i][0], lastRange[0]);
                temp[1] = Math.max(intervals[i][1], lastRange[1]);

                ranges.removeLast();
                ranges.add(temp);
            } else {
                ranges.add(intervals[i]);
            }
        }

        int k = 0;
        int[][] res = new int[ranges.size()][2];
        for (int[] range : ranges) {
            res[k++] = range;
        }

        return res;
    }
}
