class StrStr {
    // Problem Question: https://leetcode.com/problems/implement-strstr/
    public int strStr(String haystack, String needle) {
        /*
        Logic:

        In java, normal indexOf would be slower here, and the submission is faster than just 28 to 29%.
        So, if needle's length is nlen, then check every nlen in haystack string.

        This is done by using substring, and checking if that substring equals needle string, if yes then return the index.
        If needle's length is 0, return 0, which is a special case given in question.
        */

        int nlen = needle.length(), hlen = haystack.length(), i=0;
        
        if(nlen == 0) return 0;
        
        for(i=0; i<hlen; i++){
            if((i + nlen) > hlen) break;
            
            if(haystack.substring(i, i+nlen).equals(needle)) return i;
        }
        
        return -1;
    }
}
