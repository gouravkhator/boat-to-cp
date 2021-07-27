/*
    Problem: https://www.geeksforgeeks.org/maximum-profit-by-selling-n-items-at-two-markets/
*/
import java.util.Scanner;
public class MaxProfitMarket {

	static int pathChangeCount = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a[] = new int[n];
		int b[] = new int[n];

		for(int i=0;i<n;i++){
			a[i] = sc.nextInt();
		}
		for(int i=0;i<n;i++){
			b[i] = sc.nextInt();
		}
		int max;
		int path=0; //0 for a and 1 for b
		if(a[0]>b[0]) {
			max = a[0];
		}else {
			max = b[0];
			path = 1;
		}
		max+= maxProfit(a, b, path, 1); //as 0 is done start from 1 index
		System.out.println(max);
		sc.close();
	}

	static int maxProfit(int a[],int b[], int path,int index){
		if(index >= a.length){
			return 0;
		}
		int max=0;
		int newPath = 0; 
		if(a[index]>b[index]){
			if(path == 1){ //if path is different than current max number then increase count
				pathChangeCount++; 
				newPath = 0; //current changed path 
			}
		}else{
			if(path == 0){
				pathChangeCount++;
				newPath = 1;
			}
		}
		if(pathChangeCount>=2){ //if count is 2 or more then don't go by new path, go by old path
			if(path == 0){
				max = a[index];
			}else  max = b[index];
			max += maxProfit(a, b, path, index+1);
		}else{
			//if count is less than 2 then go by new max number path and bifurcate also to see all possible paths
			max = a[index] > b[index] ? a[index] : b[index]; 
			int first = maxProfit(a,b,newPath,index+1);
			int second = maxProfit(a,b,newPath^1,index+1);
			max+= Math.max(first,second);
		}
		return max;
	}

}
