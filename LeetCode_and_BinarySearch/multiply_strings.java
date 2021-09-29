import java.util.*;

class MultiplyStrings {
    public static void main(String[] args) {
        System.out.println(multiply("12345997628846356902", "3432298978786498929849343943"));
    }

    /*
    My Test case:
    num1 = "1234599762884635690277668833672827490123459976288463569027766883367282749034993543984937593759375"
    num2 = "343229897878649892984934394398932123459976288463569027766883367282749023435454354978872338065478283845621"
    */

    //Problem Question: https://leetcode.com/problems/multiply-strings/
    public static String multiply(String num1, String num2) {
        /*
        Logic:

        The traditional multiplication was followed.
        If anyone of the number is 0, then return "0".

        levelCount means number of zeros to be appended to each multiplied result.
        Each level's result is stored in vals.
        maxLen means maximum length of any string in vals.

        First, we calculate each level's val.
        Ex- num1 = "123" and num2 = "456"

        123
      X 456
    ----------
        Here if we multiply, 6 with 123 and then 5 with 123 and so on.
        The values then needs to be added from right to left. This may make us to reverse each level's val and then sum up.

        So, from initial steps, we multiply in reverse order.
        Also, the way I took in program is 3 multiplies with 654 then 2 with 654 and so on. You may take other way too.

        654
      X 321
    ---------
        8631
        0219
      + 00654
    ----------
        88065
        
        The answer is 56088.
        The string is not reversed as a whole, it's just that at each character addition in last sum, first the character is added then previous string.

        For each level, first calculate digit multiplication and carry is forwarded, and at last index,
        If the multiplication+carry is a two digit number, it's added in reverse else normally that digit is added.
        
        Now, while processing each level's values, we process column wise.
        First, 0th index is taken and all are added and carry is forwarded to next index.
        Also, if a level's value has not much length as the index it's trying to access, it skips that level for that and future indices.
        It means that the value will not be used again, and it's more efficient.

        This is done using startLevel variable.
        
        Now, normal addition takes place and the carry is forwarded nicely.
        At the end index, let's say we got 53 as the sum+carry, and previous string was 64892.
        Then 5364892 will be the answer.

        (As, at each digit append, previous string is added after this digit,
        making it the original answer, so we didn't reverse at one go).
        */

        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }
        
        List<String> vals = new ArrayList<>();
        int i, j, res, resDigit, carry, digit1, digit2, len1 = num1.length(), len2 = num2.length();
        
        int levelCount = 0, maxLen = 0;
        
        for(i=len1 - 1; i>=0; i--){
            carry = 0;
            digit1 = num1.charAt(i) - 48;
            String t = new String(new char[levelCount]).replace('\0', '0') + "";
            
            for(j=len2 - 1; j>0; j--){
                digit2 = num2.charAt(j) - 48;
                
                res = (digit1*digit2 + carry);
                resDigit = res % 10;
                carry = res / 10;
                t += resDigit;
            }
            
            // add all carry to last digit and reverse the res digit
            res = (digit1 * (num2.charAt(0) - 48)) + carry;
            if(res <= 9){
                t+= res;
            } else {
                t+= (res%10) + "" + (res/10)%10; // as it will be at max of two digits
            }
            
            vals.add(t);
            maxLen = maxLen < t.length() ? t.length() : maxLen;
            levelCount++;
        }
        
        // add all strings of vals
        int valsLen = vals.size(), startLevel = 0;
        carry = 0;
        String result = "";
        
        for(j=0; j<maxLen; j++){
            int sum = carry;

            for(i=startLevel; i<valsLen; i++){
                String temp = vals.get(i);
                if(temp.length() > j){
                    int digit = temp.charAt(j) - 48;
                    sum+= digit;
                }else{
                    startLevel = i+1;
                }
            }
            
            if(j == maxLen - 1){
                result = sum + result;
                continue;
            }
            
            resDigit = sum%10;
            carry = sum/10;
            result = resDigit + result; // to get the original string, we reverse when we add digits
        }
        
        return result;
    }
}
