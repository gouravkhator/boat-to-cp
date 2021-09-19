import java.io.*;
import java.util.StringTokenizer;

// Problem Question: https://codeforces.com/problemset/problem/884/A
public class BookReading{
	static class FastReader{
        BufferedReader br;
        StringTokenizer st;
       
        public FastReader(){
            br=new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s)throws FileNotFoundException{
           br=new BufferedReader(new FileReader(new File(s)));
        }
       
        String next(){
           while(st==null || !st.hasMoreElements()){
               try{
                    st=new StringTokenizer(br.readLine());
               }
               catch(IOException e){
                   e.printStackTrace();
               }
           }
           return st.nextToken();
       }

       int nextInt(){
           return Integer.parseInt(next());
       }

       long nextLong(){
           return Long.parseLong(next());
       }

       Double nextDouble(){
           return Double.parseDouble(next());
       }

       String nextLine(){
           String str="";
           try{
               str=br.readLine();
           }
           catch(IOException e){
               e.printStackTrace();
           }
           return str;
       }   
   }

    public static int solve(int n, int t, int[] arr) {
        // Logic: Normal logic as was said in question itself, direct answer
        int i=0;
        for(i=0; i<n && t>0; i++){
            t -= arr[i];
        }

        return i;
    }

    public static void main(String[] args) {
        FastReader in = new FastReader();

        String[] firstLine = in.nextLine().split("\\s");
        int n = Integer.parseInt(firstLine[0]);
        int t = Integer.parseInt(firstLine[1]);

        String[] secondLine = in.nextLine().split("\\s");
        int[] arr = new int[n];

        for (int i=0; i<n; i++) {
            arr[i] = 86400 - Integer.parseInt(secondLine[i]);
        }

        System.out.println(solve(n, t, arr));
    }
}
