public class pro7{
	public static void main(String[] args) {
		int n=10001,count=0,i;
		for(i=2;count<n;i++){
			if(isPrime(i)){
				count++;
			}
		}
		System.out.println(i-1);
	}
	static boolean isPrime(int n){
		for(int i=2;i<=n/2;i++){
			if(n%i==0){
				return false;
			}
		}
		return true;
	}
}