/*
    Problem: https://www.geeksforgeeks.org/count-possible-combinations-of-pairs-with-adjacent-elements-from-first-n-numbers/
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Pair {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());
        if (length <= 0)
            System.out.println("Not Defined");
        else
            System.out.println(compute(length));
        br.close();
    }

    // 0 for number alone
    // 1 for pair
    static int compute(int length) {
        if (length == 1 || length == 0) {
            return 1; // if length is 1 or 0 then count is incremented here
        }
        int count = 1; // as taken all 0 initially for that length
        int temp[] = new int[length];
        for (int i = 0; i <= length - 2; i++) {
            temp[i] = temp[i + 1] = 1;
            count += compute(length - (i + 2)); // as left numbers are length-(i+2)
            Arrays.fill(temp, 0); // set all to 0 again
        }
        return count;
    }
}