class Solution:
    def isValid(self, s: str) -> bool:
        stack = []
        for c in s: 
            if c == '(' or c == '[' or c=='{' :
                stack.append( c )
            else :
                if len(stack) == 0 :
                    return False
                op = stack.pop()
                if not ( op == '(' and c == ')' or 
                         op == '[' and c == ']' or 
                         op == '{' and c == '}' ):
                    return False
        return len(stack) == 0
        