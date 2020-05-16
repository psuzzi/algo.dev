package w1.p05;

import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        
        Stack<Character> stack = new Stack<>();
        for( char c : s.toCharArray() )
            if( c=='(' || c=='[' || c=='{')
                stack.push(c);
            else {
                if ( stack.size() == 0 )
                    return false;
                char op = stack.pop();
                if( !(op == '(' && c == ')' || 
                    op == '[' && c == ']' ||
                    op == '{' && c == '}') )
                    return false;
            }
        return stack.size()==0;
    }
}