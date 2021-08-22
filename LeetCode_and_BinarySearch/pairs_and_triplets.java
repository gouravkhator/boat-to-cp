import java.util.HashMap;

class PairsAndTriplets {
    // Problem Question: https://binarysearch.com/problems/Pair-and-Triples
    public static boolean solve(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);

            if(map.containsKey(c)){
                map.put(c, map.get(c) + 1);
            }else
                map.put(c, 1);
        }

        int triplets = 0, pairs = 0;

        for(Integer count: map.values()){
          if(count < 2){
            return false; // if count < 2, no triplets and no pairs possible as it will be left alone
          }

          /*
          If count >= 2, then first check if triplets can be possible, if yes then increment triplets.
          If triplets not possible, then decrease count by 2 as we want to take pairs from that and then check for remaining if triplets possible
          If triplets possible here, then increase triplets accordingly, else check for more pairs.

          Lastly, if number of pairs is 1 and triplets are >=0, then its true else not.
          */
          if(count >= 2){
            if(count%3==0){
              triplets+=count/3;
            }else{
              count -= 2;
              pairs+=1;
              if(count%3==0){
                triplets+=count/3;
              }else{
                pairs+=count/2;
              }
            }
          }
        }

        return (pairs == 1) && (triplets >=0);
    }

    public static void main(String[] args) {
      System.out.println(solve("1111"));
      System.out.println(solve("323"));
      System.out.println(solve("11111"));
      System.out.println(solve("111222"));
      System.out.println(solve("55"));
      System.out.println(solve("11122"));
      System.out.println(solve("1144"));
    }
}
