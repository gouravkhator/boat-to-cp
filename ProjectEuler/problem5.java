public class pro5{
	public static void main(String[] args) {
		System.out.println(hcf(232792560,20));
		long l=1L;
		for(long i=2;i<=20;i+=1){
			l= (long)(l*i/hcf(l,i));
			System.out.println(l);
		}
		System.out.println(l);
	}

	static long hcf(long a,long b){
		if(b%a==0){
			return a;
		}
		long temp;
		while(b%a!=0){
			temp=a;
			a=b%temp;
			b=temp;
		}
		return a;
	}
}