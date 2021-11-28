import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class EvenTheOddsSort {
    /*
     * Question:
     * Given in thoughtworks interview coding round 1
     * 
     * Given an array of numbers.
     * From the inputed numbers, Have all even numbers in the output and have all
     * zeros at the end of the resultant list.
     * 
     * Logic:
     * 
     * Done as per the question. No optimization, nothing much.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().trim().split("\\s+");
        int zerosCount = 0, length = line.length;
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            int temp = Integer.parseInt(line[i]);
            
            if (temp == 0) {
                zerosCount++;
            } else if (temp % 2 == 0) {
                list.add(temp);
            }
        }

        Collections.sort(list);
        for (int i = 0; i < zerosCount; i++) {
            list.add(0);
        }

        System.out.println("Output:");
        System.out.println(list);
    }
}