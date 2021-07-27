public class pro4{
	public static void main(String[] args) {
		tempM();
	}
	static boolean isPalindrome(long a){
		String s= String.valueOf(a);
		StringBuilder sb = new StringBuilder(s);
		if((sb.reverse()).equals(s)){
			return true;
		}
		else
			return false;
	}


	static void tempM(){
		for(int i=999;i>=100;i--){
			for(int j=i;j>=100;j--){
				long temp=i*j;
				if(isPalindrome(temp)==true){
					System.out.println(temp);
				}
				
			}
		}
	}
}