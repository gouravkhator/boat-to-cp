//problem question : https://leetcode.com/problems/integer-to-roman/

import java.util.Scanner;

public class IntToRoman {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int val = scanner.nextInt();
		System.out.println(numToRoman(val));
		scanner.close();
	}
	
	static String numToRoman(int num) {
		int[] numArr = new int[] {1000,900,500,400,100,90,50,40,10,9,5,4,1};
		String[] romanArr = new String[] {"M", "CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
		
		String res = "";
		for(int i=0;i<numArr.length && num>0;i++) {
			int q = num/numArr[i];

			for(int j=0;j<q;j++) {
				res+= romanArr[i];
			}
			//we can use romanArr[i].repeat(q) which will do same thing
			
			num = num - numArr[i]*q;
		}
		return res;
	}
}
