/*
    Problem: https://www.geeksforgeeks.org/find-distinct-integers-for-a-triplet-with-given-product/
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Triplets {
    static int count = 3;
    static int num1, num2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int res = triplet(num);
        if (res == -1) {
            System.out.println(-1);
        } else {
            System.out.println(num1 + " " + num2 + " " + (num / (num1 * num2)));
        }
    }

    static int triplet(int number) {
        if (count == 1 && number > 1) {
            return number;
        }
        for (int i = 2; i <= (int) Math.sqrt(number); i++) {
            if (number % i == 0) {
                if (i == number / i) {
                    return -1; // non-distinct numbers so -1
                }
                if (num1 == i || num2 == i) {
                    continue; // number already taken so continue for next numbers
                }
                // if any of num1 or num2 is not assigned then assign
                // else if both assigned then -1
                // as we returned that number in count = 1 case
                if (num1 == 0) {
                    num1 = i;
                } else if (num2 == 0) {
                    num2 = i;
                } else
                    return -1;
                count--; // count -- for triplet counting
                int x = triplet(number / i);
                // if called function returned -1 then its an error else number
                if (x == -1) {
                    return -1;
                } else
                    return x;
            }
        }
        return -1;
    }
}