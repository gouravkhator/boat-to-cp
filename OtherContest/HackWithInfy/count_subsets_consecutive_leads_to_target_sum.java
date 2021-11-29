class CountSubsetsTarget{
    /*
    Question: (Was there in HackWithInfy round 2 2020 question number 3)
    (I did a different recursive approach there which is noted in Interviews and CP copy)
    (Below is a better approach which takes at max O(n^2) as O(n) for traversing and if we reverse back the pointer again then we again traverse)

    Count all possible subsets from an array of Positive Integers (so it will not contain 0 also).
    The subsets will lead to a given sum, but the constraint is that integers taken will be consecutive in the array.
    */
    public static void main(String[] args) {
        /*
        Constraint..

        "positive integers" is the constraint. So, non-positive integers including 0 are not given in inputs.
        For 0 also, it will not work, will give wrong answer.
        as whenever we get current sum equal to target, we also reset current sum to 0.

        For getting subset to count till 0, we need to not reset the current sum at non-zero.
        Also, we should start the subset again from the same start if 0 is encountered, so that we can have same sum counted twice. 

        Or, we can keep track of last subset's end point and if after that end point, we get a 0, we check and count the subset for that 0 too.
        For consecutive 0's after the possible subset, we can keep track of any non-zeros as such between the subset's end point and the current zero.
        */

        int[] arr = {1,3,7,5,2,1,12,3,1};
        System.out.println(countSubsets(arr, 15));

        int[] arr1 = {3,5};
        System.out.println(countSubsets(arr1, 7));   
        
        int[] arr2 = {1, 6, 8, 12, 7, 20, 5, 7, 8, 4, 1};
        System.out.println(countSubsets(arr2, 20));
    }

    static int countSubsets(int[] arr, int target){
        /*
        Logic:

        Use left pointer as the start of subset. i will denote the ( end of subset + 1 ).

        If current sum of the subset is > target sum, then remove left's element from current sum and increment left pointer.

        If current sum is equal to target sum, we will count that subset, and will start i again from left+1.
        It is bcoz we want to calculate sum again from the element starting after the left.
        This is bcoz, some subsets may have overlapping elements so we want to count them also.

        And set left to start at the new left that is (old left + 1).
        Set current sum to 0 as we will again start summing from elements at (left + 1).
        
        If current sum is less than target, then we normally take the ith element into consideration.

        The catch occurs at last element.
        If last element also occurs in the possible subset, then that subset might not get counted.
        It is bcoz, at last element i = len - 1, now currentSum+= arr[i] and then i becomes len.
        Now, if in for loop we keep i<len as the condition, it will be false.
        And loop terminates after i became len.
        So possible subset is not found.
        
        So, keep i <= len in for loop. And increment it whenever currentSum < target.
        And for not checking outbound, only do currentSum += arr[i] if i < len.
        */

        int len = arr.length, left = 0, i=0, count = 0, currentSum = 0;

        for(i=0; i<=len;){
            if(currentSum > target){
                currentSum -= arr[left];
                left++;
            }else if (currentSum == target){
                count++;
                
                i = left+1;
                left = i;
                currentSum = 0;
            }else{
                if(i < len){
                    currentSum += arr[i];
                }

                i++;
            }
        }
        
        return count;
    }
}