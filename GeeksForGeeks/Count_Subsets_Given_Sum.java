//problem : https://www.geeksforgeeks.org/count-of-subsets-with-sum-equal-to-x/
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Count_Subsets_Given_Sum {

	static int recur(int sum, int[] arr, int n) {
		if(sum < 0 || n<0) {
			return 0;
		}
		
		if(sum==0) {
			return 1;
		}
		
		if(n==0) {
			return 0;
		}
		
		return recur(sum, arr, n - 1) + recur(sum - arr[n], arr, n-1);
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strings = br.readLine().split(" ");
		int sum = Integer.parseInt(br.readLine());
		int length = strings.length;
		int[] arr = new int[length];
		for(int i=0;i<length;i++) {
			arr[i]= Integer.parseInt(strings[i]); 
		}
		int count = recur(sum, arr, length-1) + 1;
		System.out.println(count);
	}

}
