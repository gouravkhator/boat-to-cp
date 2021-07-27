/**
 * Problem Description:
 * A kid needs to go from stair 0 to N. He can jump 1 step or 2 steps at a time.
 * Find number of ways of reaching stair N.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class staircase{

    static Map<Long,Long> m = new HashMap<>();  //for memoization
    //the spell is correct for memoization.
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        while(t!=0){
            long n= Long.parseLong(br.readLine());
            System.out.println(stair(0,n)); 
            t--;
        }
        br.close();
    }

    static long stair(long x,long n){
        long count;
        
        if(x==n){
            return 1; 
        }
        else if(x==(n+1)){
            return 0;
        }
        if(m.get(x)!=null){
            return m.get(x);
        }
        count=stair(x+1,n) + stair(x+2,n);
        return count;
    }
}