import java.io.BufferedReader;
import java.io.InputStreamReader;

//problem statement : https://www.geeksforgeeks.org/minimize-cost-to-empty-a-given-string-by-removing-characters-alphabetically/

public class MinCostStringChar {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int len = s.length();
		boolean deleted[] = new boolean[len];
		int rem = len, smallestIndex = 0, totalCost = 0, nonDeletedCount = 0, originalMinIndex = 0;
		//originalMinIndex is for original string's index of character to be deleted
		//smallestIndex is the left over string's index of character to be deleted
		//nonDeletedCount is the count of non-deleted characters till current index
		
		int minCharacter = 125; //a number greater than each character(alphabet)
		
		while(rem > 0) {
			//find non-deleted and smallest character and its index (ignoring deleted)
			minCharacter = 125;
			smallestIndex = 0;
			originalMinIndex = 0;
			nonDeletedCount = 0; //counting again the count of non-deleted characters from start to the current
			for(int i=0;i<len;i++) {
				if(deleted[i] == false) {
					nonDeletedCount ++;
					if(s.charAt(i) < minCharacter) {
						minCharacter = s.charAt(i);
						smallestIndex = nonDeletedCount; //for cost add-up
						originalMinIndex = i; //for flagging it as deleted
					}
				}
			}
			
			//flag that as deleted
			deleted[originalMinIndex] = true;
			totalCost += smallestIndex;
			rem --;
		}
		
		System.out.println(totalCost);
	}

}
