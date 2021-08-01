class MedianSortedArrays{
  // Problem Question: https://leetcode.com/problems/median-of-two-sorted-arrays/
  public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    /*
    Logic:
    Take two pointers i and j on two arrays nums1 and nums2 respectively.
    Now, only add the smaller of the two element pointed by i and j. 
    If we got smaller element in nums1, then increment i else if we got smaller in nums2, increment j.

    Now, loop through left over elements of nums1 and nums2 and add them in finalArr.
    We get the merged sorted array.

    Now, the median is middlemost element or average of two middle most elements.

    Using ArrayList could increase time for adding and getting. Doing (m+n) multiple times may increase time.
    Use k instead which is already (m+n) after all manipulations and finalArr creation.
    */

    int i=0,j=0, m = nums1.length, n = nums2.length, k=0;
    int[] finalArr = new int[m+n];

    while(i < m && j < n){
      if(nums1[i] < nums2[j]){
        finalArr[k++] = nums1[i++];
      }else{
        finalArr[k++] = nums2[j++];
      }
    }

    while(i < m){
      // left overs of nums1 to add in finalArr
      finalArr[k++] = nums1[i++];
    }

    while(j < n){
      // left overs of nums2 to add in finalArr
      finalArr[k++] = nums2[j++];
    }

    if(k%2 == 0){
      // even number of elements
      // converting either one of division operands to double as it was int before
      // Division of int and int will always result in int, and we want floating values.
      return ((double)finalArr[k/2]+finalArr[k/2 - 1])/2;
    }

    return finalArr[k/2]; // if number of elements is odd
  }

  public static void main(String[] args) {
    System.out.println(findMedianSortedArrays(new int[]{1,2,3}, new int[]{2,2,3,4,4}));
  }
}