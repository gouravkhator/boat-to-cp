class PossibleSetsAbctoN{
    /*
    Question:

    Given N such that 2<=N<=10**6
    Find out number of possible sets (a,b,c) such that a*b+c = N. [a, b and c should be positive integers]

    Ex- N = 4
    Sets possible are:
    (1,1,3)
    (1,3,1)
    (3,1,1)
    
    N = 6
    (1,6,1)
    (6,1,1)
    (2,2,2)
    (4,1,2)
    (1,4,2)
    (3,1,3)
    (1,3,3)
    (1,2,4)
    (2,1,4)
    (1,1,5)
    */
    public static int solve(int n) {
        /*
        Logic:

        Populate arr. arr[i] means number of possible sets a,b such that a*b = i
        
        Loop through 1 to n-1 as c,
        Now remaining is n-c, which should be in form of a*b

        arr[n-c] means count of possible sets for a,b such that a*b= n-c
        or, a*b+c = n

        Total count is added in each iteration.

        For populating arr,
        Loop from 1 to n-1, and for making i as product of two positive integers, we need to travel from 1 to sqrt(i),

        And if the i is divisible by j, then check if i/j and j are same numbers, if yes count the set once.
        Else, count them twice (as a*b is different in the set counting from b*a).

        Time complexity: O(N sqrt(N))
        Space complexity: O(N)
        */

        int[] arr = new int[n];
        int count = 0;

        for(int i=1; i<=n-1; i++){
            // populate arr
            int tempCount = 0;
            for(int j=1; j<=Math.floor(Math.sqrt(i)); j++){
                if(i%j != 0){
                    continue;
                }

                if((i/j) == j){
                    tempCount++; // 1*1 is same in the set so counting once
                }else{
                    tempCount+=2; // double counting the different numbers as 1*2 is different from 2*1 in set
                }
            }

            arr[i] = tempCount;
        }

        // printing the arr
        // for (int i = 1; i < arr.length; i++) {
        //     System.out.println(i+":"+arr[i]);
        // }

        for(int i=1; i<=n-1; i++){
            int rem = n-i;
            count+=arr[rem];
        }

        return count;
    }

    public static void main(String[] args) {
        int[] testCases = {2,3,4,5,6,9, 15,19, 28, 56};

        for (int i : testCases) {
            System.out.println("N = " + i + " and solve(N) = " + solve(i));
        }
    }
}