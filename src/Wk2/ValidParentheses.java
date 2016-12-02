package Wk2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class ValidParentheses {

    public static boolean isValidParentheses(String s){
        Stack<String> stack = new Stack<>();
        HashSet<String> left = new HashSet<>(Arrays.asList("{", "(", "["));

        for(int i = 0; i < s.length(); i++){

            String cur = Character.toString(s.charAt(i));
            if(left.contains(cur)){
                stack.push(cur);
            }

            else if(stack.isEmpty()){
                return false;
            }

            else{
                String toMatch = stack.pop();
                if((cur.equals("}") && toMatch.equals("{")) ||
                    (cur.equals("]") && toMatch.equals("[")) ||
                        (cur.equals(")") && toMatch.equals("(")) ){
                    continue;
                }
                else{
                    return false;
                }

            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String test1 = "[]({[]}())";
        String test2 = "[]({{[]}())";
        String test3 = "))";
        String test4 = "(()";
        String test5 = "(]";

        System.out.println(test1 + ": " + isValidParentheses(test1));
        System.out.println(test2 + ": " + isValidParentheses(test2));
        System.out.println(test3 + ": " + isValidParentheses(test3));
        System.out.println(test4 + ": " + isValidParentheses(test4));
        System.out.println(test5 + ": " + isValidParentheses(test5));
    }
}
