import java.util.List;
import java.util.ArrayList;

class ClimbingLeaderboard{
    static class Range{
        int low, high, rank;
        Range(int l, int h, int r){
            low = l;
            high = h;
            rank = r;
        }
    }
    
    // Problem Question: https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem
    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        /*
        Logic:

        Given: ranked list is in descending order, and player is in ascending order.
        Make a List of ranges each which will have low, high and rank.
        (low means the range's minimum value inclusive, high means range's maximum value inclusive, rank means the rank in leaderboard).

        Ex- ranked: [100, 90, 90, 80] 
        player: [70, 80, 105]

        First, 100 to integer max value is added to range with rank 1.
        Then, loop through other values in ranked. If the value is equal to its previous value, don't include that.

        After the loop, just add 0 to last value of ranked - 1. Also, make sure if last value is 0, then don't add this range.

        After setting up ranges, just loop through player list.
        Start from last range, and starting player score.

        If score is in range then save the rank to ranks and move to next score.
        If score is not in range, move to previous range (or the range larger than and saved before current ones).
        
        Also, the range which we moved from, will not be required later, as the player's score is also in ascending order.

        Now, again check the current player's score with current range, and continue with this.
        */
        int i=0, rankedLen = ranked.size(), scoresLen = player.size();
        List<Range> ranges = new ArrayList<>();
        List<Integer> ranks = new ArrayList<>();
        
        int currentRank = 1;
        ranges.add(new Range(ranked.get(0), Integer.MAX_VALUE, currentRank));
        
        for(i=1; i<rankedLen; i++){
            if(ranked.get(i).equals(ranked.get(i-1))){
                continue;
            }
            
            int low = ranked.get(i);
            int high = ranked.get(i-1) - 1;
            
            currentRank++;
            Range temp = new Range(low, high, currentRank);
            ranges.add(temp);
        }
        
        if(ranked.get(rankedLen - 1) != 0){
            ranges.add(new Range(0, ranked.get(rankedLen - 1) - 1, currentRank + 1));
        }
        
        int k=ranges.size() - 1;
        for(i=0; i<scoresLen;){
            int currentScore = player.get(i);
            
            Range range = ranges.get(k);
            if(currentScore >= range.low && currentScore <= range.high){
                ranks.add(range.rank);
                i++;
            }else{
                k--;
            }
        }
        
        return ranks;
    }

    public static void main(String[] args) {
        List<Integer> ranked = new ArrayList<>();
        List<Integer> player = new ArrayList<>();

        ranked.add(200);
        ranked.add(150);
        ranked.add(150);
        ranked.add(120);
        ranked.add(90);
        ranked.add(20);
        ranked.add(0);

        player.add(0);
        player.add(11);
        player.add(99);
        player.add(152);
        player.add(210);
        player.add(250);

        System.out.println(climbingLeaderboard(ranked, player));
    }
}