class MakeTwoArrEqualByReversingSubarr {
  // Problem Question: https://leetcode.com/problems/make-two-arrays-equal-by-reversing-sub-arrays/
  public boolean canBeEqual(int[] target, int[] arr) {
    /*
    Question was boiled down to "Check if two arrays are having same elements with the same number of occurrences".
    
    We need to improve this with much better approach.
    */
    HashMap<Integer, Integer> targetElemFreq = new HashMap<>();
    HashMap<Integer, Integer> arrElemFreq = new HashMap<>();
    
    for(int elem: target){
      if(targetElemFreq.containsKey(elem)){
        targetElemFreq.put(elem, targetElemFreq.get(elem) + 1);
      }else{
        targetElemFreq.put(elem, 1);
      }
    }
    
    for(int elem: arr){      
      if(!targetElemFreq.containsKey(elem)){
        // if target element does not contain this elem in arr, then return false
        return false;
      }
      
      // if the element is contained in target as well, then calculate the frequencies in arr as well
      if(arrElemFreq.containsKey(elem)){
        arrElemFreq.put(elem, arrElemFreq.get(elem) + 1);
      }else{
        arrElemFreq.put(elem, 1);
      }
    }
    
    // compare the freqencies of both hashmaps
    for(Map.Entry<Integer, Integer> entry: arrElemFreq.entrySet()){
      int elem = entry.getKey(), freq = entry.getValue();
      
      // no need to check for containsKey, as that is checked before and false is returned if any value of arr is not present in target array.
      if(targetElemFreq.get(elem) != freq){
        return false;
      }
    }
    
    return true;
  }
}