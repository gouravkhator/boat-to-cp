import java.lang.Math;
public class problem2{
	static long sum=0;
	public static void main(String[] args) {
		fibo(1,2,3);
		System.out.print(sum);
	}
	static void fibo(long a,long b,long s){
		long temp=0;
		if(b>4*Math.pow(10,6))
			return;
		if((b&1)==0)
			sum+=b;
		fibo(b,s,(b+s));
	}
}