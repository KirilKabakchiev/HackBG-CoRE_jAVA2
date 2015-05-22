package week2.bracketsMatch;

import java.util.Stack;

public class Brackets {
    public static boolean CheckParentesis(String str) {
        if (str.isEmpty())
            return true;

        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);

            if (current == '{' || current == '(' || current == '[') {
                stack.push(current);
            }

            else {
                return false;
            }

            if (current == '}' || current == ')' || current == ']') {
                if (stack.isEmpty())
                    return false;

                char last = stack.peek();
                if (current == '}' && last == '{' || current == ')' && last == '(' || current == ']' && last == '[')
                    stack.pop();
                else
                    return false;
            }

        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String expression = "()()())))((())(";
        String expression2 = "())(()";
       

        System.out.println(Brackets.CheckParentesis(expression));
        System.out.println(Brackets.CheckParentesis(expression2));
    }

}
