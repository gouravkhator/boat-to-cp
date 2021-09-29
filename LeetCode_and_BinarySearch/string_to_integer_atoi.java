class StringToIntegerAtoi {
    // Problem Question: https://leetcode.com/problems/string-to-integer-atoi/
    public int myAtoi(String s) {
        /*
        Logic:
        
        First trim the string for any leading or trailing whitespaces.
        
        If length of string is 0, then return 0. 
        If 1st character is -, then the number should be negative.
        If 1st character is +, the number should be positive.
        
        Also, if 1st character is - or +, then sign given is true.
        So, startIndex (i.e., index to start from after the sign, if there is any) will be 1.
        
        Otherwise startIndex is 0.
        Just, scan the string, and now just the digits should be read.
        If at any point the non-digit is read, stop the scan.
        
        endIndex is a variable to know, till where we got the digits.
        Now, just do substring of string from startIndex to endIndex, including character at endIndex.
        
        That substring only contains the digits without sign.
        But it may happen, that the number exceeds long range too and cannot be parsed by parseLong method.
        So, do a try and catch and in catch, if the number's sign was negative, then return min value of integer, else max value of integer.
        
        After the parsing of the substring, we get a long typed value.
        Now check if the numLong is < minimum integer, if yes, return minimum integer.
        If numLong is > maximum integer, return maximum integer value.
        
        Else, if numLong is in the integer range, just explicitly cast that to int and return it.
        
        Why we used long?
        It's because we can store the larger range and then check if that number is in the integer range or not.
        */
        
        long numLong = 0;
        int i = 0, startIndex = 0;
        boolean isPositive = true;
        
        s = s.trim();
        int len = s.length(), endIndex = -1;
        
        if(len == 0){
            return 0;
        }
        
        if(s.charAt(0) == '-'){ // the length is already > 0, as we checked for 0 length before
            isPositive = false;
            startIndex = 1;
        }else if(s.charAt(0) == '+'){
            startIndex = 1;
        }
        
        for(i=startIndex; i<len; i++){
            char c = s.charAt(i);
            
            if(c >= 48 && c<=57){
                // digits
                endIndex = i;
            }else{
                // whitespace or non-digit
                break;
            }
        }
        
        if(endIndex == -1){
            return 0;    
        }
        
        try{
            numLong = Long.parseLong(s.substring(startIndex, endIndex + 1));
        }catch(Exception e){
            if(isPositive == false){
                return Integer.MIN_VALUE;
            }else
                return Integer.MAX_VALUE;
        }
        
        if(isPositive == false){
            numLong = -numLong;
        }
        
        if(numLong < Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }
        
        if(numLong > Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        
        if(numLong >= Integer.MIN_VALUE && numLong <= Integer.MAX_VALUE){
            return (int)numLong;
        }
        
        return 0;
    }
}
