import java.util.*;

// Code done by me, but approach was done from checking solution, after analysing for 1 hour by my own.
// Problem Question: https://binarysearch.com/problems/Stock-Span
class StockSpan {
    class Pair{
        int first, second;

        Pair(int f, int s){
            first = f;
            second = s;
        }
    }

    Stack<Pair> stack;
    int size = 0;

    public StockSpan() {
        stack = new Stack<>();
    }

    public int next(int price) {
        /*
        Logic:

        Ex-
        [1,3,6,2,4]

        For 1, push it to stack with index 0.
        For 3, first pop all elements from stack which are smaller than or equals 3, and till we don't get a number larger than 3.

        So, popped 1.
        Now, if stack is empty, its sure that all elements were smaller than or equals 3, which appeared before.
        So answer is size which means current size or number of elements came till now.

        Push 3 with its current index.
        Now comes 6. Pop 3.

        Now, push 6 to stack with its current index.
        Now comes 2.
        As no elements are smaller than 2 which are in stack right now,
        so get top of stack and its index and get number of elements between 2's index and top of stack's index.

        These are the elements which are less than or equals 2, in consecutive fashion.
        Push 2 with its current index.
        Now comes 4. 4 will pop 2. 4 will then check if stack is empty.

        As stack is not empty, 4 will check index of top of stack which is 6 and its index is 2.
        4's index is 4 and so, number of elements till 4 which are smaller or equals 4 (consecutively), is 2.
        */
        size++;
        while(!stack.empty() && stack.peek().first <= price){
            stack.pop();
        }

        int ans = 0;
        if(stack.empty()){
            ans = size;
        }else{
            ans = size - 1 - stack.peek().second;
        }

        stack.push(new Pair(price, size - 1));
        return ans;
    }
}
