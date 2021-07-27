/**
 * Problem Description:
 * A thief can stole money from houses such that he doesn't steals from the adjacent houses.
 * So find the maximum amt. stolen by him.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class max_steal{
    public static void main(String[] args) throws IOException{
        List<Integer> money = new ArrayList<>();
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); 
        String line= br.readLine();
        String[] strs= line.trim().split("\\s+");
        
        for (int i = 0; i < n; i++) {
            money.add(Integer.parseInt(strs[i]));
        }

        System.out.println(max_stealing(0,n,money));
    }

    static int max_stealing(int x,int n, List<Integer> money){
        if(x>=n){
            return 0;
        }
        int max = Math.max(money.get(x)+max_stealing(x+2, n, money), max_stealing(x+1, n, money));
        return max;
    }
}