class StringAddition {
    // Similar to LinkedList addition of numbers, just that it is with Strings
    // Problem Question: https://binarysearch.com/problems/String-Addition
    public String solve(String a, String b) {
        StringBuilder sba = new StringBuilder(a);
        StringBuilder sbb = new StringBuilder(b);

        String a_reversed = sba.reverse().toString();
        String b_reversed = sbb.reverse().toString();

        int p1 = 0, p2 = 0;
        int alen = a_reversed.length(), blen = b_reversed.length();

        int carry = 0;
        String res = "";

        while(p1 < alen && p2 < blen){
            int first = (a_reversed.charAt(p1) - 48);
            int second = (b_reversed.charAt(p2) - 48);

            res = (first + second + carry) % 10 + res;
            carry = (first + second + carry) / 10; 

            p1++;
            p2++;
        }

        while(p1 < alen){
            int first = (a_reversed.charAt(p1) - 48);

            res = (first + carry) % 10 + res;
            carry = (first + carry) / 10; 
            p1++;
        }

        while(p2 < blen){
            int first = (b_reversed.charAt(p2) - 48);

            res = (first + carry) % 10 + res;
            carry = (first + carry) / 10; 
            p2++;
        }

        if(carry > 0){
            res = carry%10 + res;
        }

        return res;
    }
}