import java.util.ArrayList;
import java.util.Collections;

class MedianSortedArrays{
  // Problem Question: https://leetcode.com/problems/median-of-two-sorted-arrays/
  // Problem Constraint mentioned in question is: O(log(m+n)) where m is nums1.length and n is nums2.length
  // My solution below takes O((n log(m+n)) + m) but it is submitted successfully
  // ? Some improvements can be made
  public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    ArrayList<Integer> finalArr = new ArrayList<>();

    /*
    Logic:
    First add all elements of nums1 in arraylist, then binary search for elements of nums2 in nums1 one by one

    If the search is found, just insert that element of nums2 at that index in arraylist.
    If search is not found, then that negative index returned, tells us where that element can be inserted.

    Insert that element at that index in arraylist.
    */
    for(int x: nums1){
      finalArr.add(x);
    }

    for (int elem: nums2) {
      int index = Collections.binarySearch(finalArr, elem);
      if(index < 0){
        // if index is negative, then index is -(insertionIndex) - 1
        index = -index - 1; // final insertion index to insert is -index-1
      }
      finalArr.add(index, elem);
    }

    int totalLen = finalArr.size();
    if(totalLen%2 == 0){
      // even number of elements
      // converting either one of division operands to double as it was int before
      // Division of int and int will always result in int, and we want floating values.
      return ((double)finalArr.get(totalLen/2)+finalArr.get(totalLen/2 - 1))/2;
    }

    return finalArr.get(totalLen/2); // if number of elements is odd
  }

  public static void main(String[] args) {
    System.out.println(findMedianSortedArrays(new int[]{1,2,3}, new int[]{2,2,3,4,4}));
  }
}