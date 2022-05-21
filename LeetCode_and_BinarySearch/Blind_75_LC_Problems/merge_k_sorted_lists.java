class MergeKSortedLists {
  public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public void printList(ListNode list) {
    if (list == null) {
      return;
    }

    System.out.print(list.val + ", ");
    printList(list.next);
  }

  // Merges two sorted lists into one list
  public ListNode mergeTwoLists(ListNode firstList, ListNode secondList) {
    if (firstList == null && secondList != null) {
      return secondList;
    }

    if (secondList == null && firstList != null) {
      return firstList;
    }

    if (firstList == null && secondList == null) {
      return null;
    }

    ListNode mergedListHead = null, mergedListTail = null, nextValidNode = null;

    while (firstList != null && secondList != null) {
      if (firstList.val < secondList.val) {
        nextValidNode = firstList;
        firstList = firstList.next;
      } else {
        nextValidNode = secondList;
        secondList = secondList.next;
      }

      if (mergedListHead == null) {
        mergedListHead = nextValidNode;
        mergedListTail = nextValidNode;
      } else {
        mergedListTail.next = nextValidNode;
        mergedListTail = mergedListTail.next;
      }
    }

    while (firstList != null) {
      nextValidNode = firstList;

      if (mergedListHead == null) {
        mergedListHead = nextValidNode;
        mergedListTail = nextValidNode;
      } else {
        mergedListTail.next = nextValidNode;
        mergedListTail = mergedListTail.next;
      }

      firstList = firstList.next;
    }

    while (secondList != null) {
      nextValidNode = secondList;

      if (mergedListHead == null) {
        mergedListHead = nextValidNode;
        mergedListTail = nextValidNode;
      } else {
        mergedListTail.next = nextValidNode;
        mergedListTail = mergedListTail.next;
      }

      secondList = secondList.next;
    }

    return mergedListHead;
  }

  /**
   * Problem Question:
   * https://leetcode.com/problems/merge-k-sorted-lists/
   */
  public ListNode mergeKListsRecur(ListNode[] lists, int low, int high) {
    /**
     * Time complexity is O(nk log k), where n is the length of each list in the
     * lists of ListNode.
     * k is the length of list of lists.
     * 
     * (log k) is because we are dividing the list of lists into two halves, and so
     * there are log k iterations.
     * For each iteration, we are mainly merging n*k elements, so we are traversing
     * those n*k elements once.
     */
    ListNode resultantMergedList = null;

    if (low < high) {
      int mid = low + (high - low) / 2;
      ListNode mergedLeftList = mergeKListsRecur(lists, low, mid);
      ListNode mergedRightList = mergeKListsRecur(lists, mid + 1, high);

      if (mergedLeftList == null && mergedRightList != null) {
        return mergedRightList;
      }

      if (mergedRightList == null && mergedLeftList != null) {
        return mergedLeftList;
      }

      if (mergedLeftList == null && mergedRightList == null) {
        return null;
      }

      // merging those two already merged lists.
      resultantMergedList = mergeTwoLists(mergedLeftList, mergedRightList);
    } else if (low == high) {
      /**
       * low is equal to high, meaning we divided the lists of lists into two, such
       * that we got to a single list now..
       */
      return lists[low];
    }

    return resultantMergedList;
  }

  public ListNode mergeKLists(ListNode[] lists) {
    return mergeKListsRecur(lists, 0, lists.length - 1);
  }
}