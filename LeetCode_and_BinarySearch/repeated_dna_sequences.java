import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

class RepeatedDNASeq {
  // Problem Question: https://leetcode.com/problems/repeated-dna-sequences/submissions/
  public List<String> findRepeatedDnaSequences(String s) {
    int i=0, len = s.length();
    String tempStr = "";
    
    HashMap<String, Integer> map = new HashMap<>();
    List<String> repeatedSeqList = new ArrayList<>();
    
    // 10-letter substring is needed so, traverse till the letter which is 10 letters before the end
    for(i=0; i<=(len - 10); i++){
      tempStr = s.substring(i, i+10); // substring of current character to 10 chars after it.

      if(map.containsKey(tempStr)){
        map.put(tempStr, map.get(tempStr) + 1);
      }else{
        map.put(tempStr, 1);
      }
    }
        
    for (Map.Entry<String, Integer> entry: map.entrySet()){
      if(entry.getValue() > 1){
        // the count is greater than 1 for the sequence
        repeatedSeqList.add(entry.getKey());
      }
    }
    
    return repeatedSeqList;
  }
}
