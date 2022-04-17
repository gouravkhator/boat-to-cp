import java.util.*;

class GenerateParentheses {
    public void recur(int openAvl, int closedAvl, String prevStr, List<String> list) {
        /*
         * Logic:
         * 
         * By looking at pattern of n=3 and how to make a balanced parentheses string:
         * 
         * We take number of available open brackets (openAvl) and number of available
         * closed brackets (closedAvl).
         * Then, when openAvl is equal to closedAvl, it means that the string is
         * balanced yet,
         * and to let it be balanced, '(' can only be given now.
         * As ')' will make it unbalanced as opening will always come before closing
         * braces.
         * 
         * If the openAvl and closedAvl becomes 0, it means the string is balanced and
         * we used all the braces.
         * Just add that string to result list.
         * 
         * If openAvl is 0 but closedAvl is > 0, it means that opening braces have been
         * used, so just add closing braces now.
         * 
         * If openAvl is > 0 and closedAvl is 0, it means no more closing braces will
         * come,
         * and rest of opening braces need to be closed. So, this will lead to invalid
         * string. So return, and don't recur.
         * 
         * If openAvl is < closedAvl, then '(' or ')' can come next. So, recur for both
         * cases.
         * 
         * Using '(' means reducing openAvl and adding '(' to prevStr.
         * And similar for ')'.
         * 
         * -------------------------------------------------------------
         * 
         * As cases for checking if 0 or not are specific cases than checking only for
         * greater than or less than,
         * so, we will do those 0'checking cases first, then those other comparisons
         * later..
         */
        if (openAvl == 0 && closedAvl == 0) {
            list.add(prevStr);
            return;
        }

        if (openAvl == 0 && closedAvl > 0) {
            recur(openAvl, closedAvl - 1, prevStr + ")", list);
            return;
        }

        if (closedAvl == 0 && openAvl > 0) {
            return;
        }

        if (openAvl == closedAvl) {
            recur(openAvl - 1, closedAvl, prevStr + "(", list);
            return;
        }

        if (openAvl < closedAvl) {
            recur(openAvl - 1, closedAvl, prevStr + "(", list);
            recur(openAvl, closedAvl - 1, prevStr + ")", list);
            return;
        }
    }

    // Problem Question: https://leetcode.com/problems/generate-parentheses/
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        recur(n, n, "", list);
        return list;
    }
}
