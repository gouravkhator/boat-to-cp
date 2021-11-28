import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Test cases for both of below questions:

Test Case 1: (for question 1A and 1B)

A B C
A F F B F C C A F C E A B C

Test Case 2: (for question 1A and 1B)

C A B
C A F F B F C C A F C E A B C A A
*/

/*
Question 1A:
(My modified question)

Design a vote counting machine which can take input of valid candidates name and vote casted list,
We need to write a function to find the number of votes casted against each valid candidate in descending order of frequency.
If frequency matches, then sort by ascending order of index in valid candidates.
*/
class VoteCountingMachine1A {
    // Class to store freq and originalIndex (index as seen in candidates array)
    // so that originalIndex is also saved with data and frequency
    static class Meta {
        int originalIndex, freq;

        Meta(int originalIndex, int freq) {
            this.originalIndex = originalIndex;
            this.freq = freq;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] candidates = br.readLine().trim().split("\\s+");
        String[] votesCasted = br.readLine().trim().split("\\s+");
        
        int candidatesLen = candidates.length;

        HashMap<String, Meta> map = new HashMap<>();

        for (int i=0; i<candidatesLen; i++) {
            map.put(candidates[i], new Meta(i, 0));
        }

        for (String vote : votesCasted) {
            if(map.containsKey(vote)){
                Meta data = map.get(vote);
                data.freq++;
                map.put(vote, data);
            }
        }

        List<Map.Entry<String, Meta>> list = new ArrayList<Map.Entry<String, Meta>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Meta>>() {
            @Override
            public int compare(Map.Entry<String, Meta> o1, Map.Entry<String, Meta> o2) {
                Meta data1 = o1.getValue();
                Meta data2 = o2.getValue();

                // if freq is not same
                if(data1.freq != data2.freq){
                    // second occurring element has freq greater than first occurring one, so swap them to have second occurring one before first
                    // if we want to swap, just return positive number from here
                    return data2.freq - data1.freq;
                }

                // freq is same, so if first occurring element has index greater than second occurring one, then swap
                // meaning index should be ordered by ascending order
                return data1.originalIndex - data2.originalIndex;
            }
        });

        System.out.println("Candidates sorted by frequency desc and by order asc");

        for (Map.Entry<String,Meta> entry : list) {
            System.out.print(entry.getKey() + " ");
        }
    }
}

/*
Question 1B:
(given by Thoughtworks in interview coding round 1)

Design a vote counting machine which can take input of valid candidates name and vote casted list,
We need to write a function to find the number of votes casted against each valid candidate, 
invalid vote counts and finally the winner name.

Note: If two candidates have the same votes, then the winner can be finalized based on the order of valid candidates.
If number of invalid vote is more or if vote casted list is empty, then output Winner = N/A
*/
public class VoteCountingMachine {
    public static void main(String[] args) throws IOException{
        /*
        Logic:

        Make a list out of candidates array to have contains and indexOf methods.
        Count invalid votes casted, by checking if the votes casted for person is there in candidates list.
        Create a frequency hashmap.

        Then loop through the map and maintain max frequency and second max freq.
        If current freq > secondMaxFreq, it must become the new secondMaxFreq and also update runnerUp candidate.
        If current freq > maxFreq, it must become the new maxFreq and also update winner candidate.
        But before setting maxFreq and winner, just set the old maxFreq and winner to secondMaxFreq and runnerUp.
        As the old one will now be runnerUp.

        If maxFreq is equal to secondMaxFreq, then check indexof winner and runnerUp in candidatesList.
        And check order of frequency and finalize winner according to that.
        */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] candidates = br.readLine().trim().split("\\s+");
        String[] votesCasted = br.readLine().trim().split("\\s+");
        
        int totalVotesCount = votesCasted.length;

        if(totalVotesCount == 0){
            System.out.println("Votes count is 0");
            System.out.println("Invalid votes count is 0");
            System.out.println("Winner = N/A");
            return;
        }
        
        List<String> candidatesList = new ArrayList<>();

        for (String candidate : candidates) {
            candidatesList.add(candidate);
        }

        HashMap<String, Integer> map = new HashMap<>();
        int invalidVotesCount = 0;

        for (String vote : votesCasted) {
            if(!candidatesList.contains(vote)){
                invalidVotesCount++;
                continue;
            }

            if(map.containsKey(vote)){
                map.put(vote, map.get(vote) + 1);
            }else{
                map.put(vote, 1);
            }
        }

        System.out.println("Invalid votes: " + invalidVotesCount);
        System.out.println("Votes given to candidates : \n" + map.entrySet());

        if (invalidVotesCount > (totalVotesCount - invalidVotesCount)){
            System.out.println("Winner = N/A");
            return;
        }

        int maxFreq = 0, secondMaxFreq = 0;
        String winner = "", runnerUp = "";

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            String temp = entry.getKey();

            if(freq > secondMaxFreq){
                secondMaxFreq = freq;
                runnerUp = temp;
            }

            if(freq > maxFreq){
                secondMaxFreq = maxFreq;
                runnerUp = winner;
                maxFreq = freq;
                winner = temp;
            }
        }

        if(maxFreq == secondMaxFreq){
            System.out.println("Winner : "+ (
                candidatesList.indexOf(winner) < candidatesList.indexOf(runnerUp) ? winner: runnerUp
            ));
        }else{
            System.out.println("Winner : "+ winner);
        }
    }
}
