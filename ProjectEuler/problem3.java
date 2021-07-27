public class problem3{
	public static void main(String[] args) {
		long n=600851475143L;
		long t= (long)(Math.sqrt(n));
		//if even number, to divide it by 2 continuously while even
		for(long i=3;i<=t;i+=2){
			while(n%i==0){
				n/=i;
				System.out.println(i);
			}
		}
		// even after removing all factors if it is not 1 then print it thus saying it is a prime number
		// as all factors less than root n are removed.
		System.out.println(n);
	}
}