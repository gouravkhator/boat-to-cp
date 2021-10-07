import java.util.Stack;

class ValidParentheses {
    // Problem Question: https://leetcode.com/problems/valid-parentheses/
    public boolean isValid(String s){
        /*
        Logic:

        Use stack. For each open braces, push it to stack.
        For each closing braces, check if the respective open brace is there and stack is not empty, then pop that from stack.
        If the stack is empty when closing brace came, or if the respective open brace didn't match, just return false.

        Lastly, when all characters are processed, if the stack if empty return true, else false.
        (As if the open characters were left in the stack, even after the string is fully processed,
        then the stack will not be empty, and the string is thus invalid).
        */

        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            
            if(c == '(' || c=='{' || c=='['){
                // c is the opening brace
                stack.push(c);
            }else{
                // c is the closing brace
                char openBrace = ' ';
                
                switch(c){
                    case ')':
                        openBrace = '(';
                        break;
                    case '}':
                        openBrace = '{';
                        break;
                    case ']':
                        openBrace = '[';
                        break;
                }
                
                if(!stack.empty() && stack.peek() == openBrace)
                    stack.pop();
                else{
                    return false;
                }
            }
        }
        
        return stack.empty();
    }
}
