class FirstMissingPosSeq {
  // Problem Question: https://binarysearch.com/problems/First-Missing-Positive-Sequel
  public int solve(int[] arr) {
    /*
    Logic:
    If the arr has 1 thing not in [1,n+1] count will not increase.
    */
    int i=0, count=1;
    for(i=0; i<arr.length; i++){
      if(count == arr[i]){
        count++;
      }
    }

    return count;
  }
}