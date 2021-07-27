/*
    Problem: https://www.geeksforgeeks.org/find-minimum-number-of-steps-to-reach-the-end-of-string/
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MinDP {
    static int dp[];

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str= br.readLine();
        int k = Integer.parseInt(br.readLine());
        dp = new int[str.length()-1]; //dp is array to store min step to go to i index in str
        int count = minSteps(str, k, 0);
        if(count == Integer.MAX_VALUE || count<=0)
            System.out.println("No possible path");
        else
            System.out.println(count);
    }
    static int minSteps(String str,int k, int i){
        int count=0;
        if(i==str.length()-1){
            return 0;
        }
        if(i > str.length()-1){
            return Integer.MAX_VALUE;
            //if this path is not feasible then it returns max value so that if some path is feasible and has less value than this
            //then that path has min steps and check in main method if min steps is Integer.MAX_VALUE if yes then print no possible path
        }
        if(dp[i]!=0){
            return dp[i];
        }
        int count1,count2,countk;
        count1 = count2 = countk = Integer.MAX_VALUE;
        if(i+1<= str.length()-1 && str.charAt(i+1)=='1'){
            count1 = 1 + minSteps(str, k, i+1);
        }
        if(i+2<= str.length()-1 && str.charAt(i+2)=='1'){
            count2 = 1 + minSteps(str, k, i+2);
        }
        if(i+k<= str.length()-1 && str.charAt(i+k)=='1'){
            countk = 1 + minSteps(str, k, i+k);
        }
        count = Math.min(Math.min(count1,count2),countk);
        dp[i] = count;
        return count;
    }
}