package Wk2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class LongestValidParentheses {

    private int position = 0;
    private int longest = 0;

    // Operates in O(n^2) time, with the worst case being a string of all left parentheses, i.e. "((((((((((((".
    private int isValidParentheses(String s){
        Stack<String> stack = new Stack<>();
        HashSet<String> left = new HashSet<>(Arrays.asList("{", "(", "["));
        int increase = position;
        int size = 0;
        int toAdd = 0;
        for(int i = increase; i < s.length(); i++){

            // Gets the current parenthesis and pushes it to the stack,
            String cur = Character.toString(s.charAt(i));
            if(left.contains(cur)){
                increase = i + 1;
                stack.push(cur);
            }

            // If we have a right parenthesis and the stack is empty, this means the parenthesis is not valid.
            else if(stack.isEmpty()){
                increase = i + 1;
                position = increase;
                return size;
            }

            else{

                // Checking to ensure the right parenthesis matches the one that is popped.
                String toMatch = stack.pop();
                if((cur.equals("}") && toMatch.equals("{")) ||
                        (cur.equals("]") && toMatch.equals("[")) ||
                        (cur.equals(")") && toMatch.equals("(")) ){

                    // Only increase the size of valid parentheses when the stack is empty.
                    toAdd += 2;
                    increase = i + 1;
                    if(stack.isEmpty()){
                        size += toAdd;
                        toAdd = 0;
                    }
                }
                else{

                    // We decide the next position based on if our stack is empty.
                    // We either move by one element,
                    // or we move the number of by the size of valid parentheses.
                    increase = i + 1;
                    if(!stack.isEmpty()){
                        position += Math.max(size, 1);
                    }
                    else{
                        position = increase;
                    }
                    return size;
                }
            }
        }
        if(!stack.isEmpty()){
            position += Math.max(size, 1);
        }
        else{
            position = increase;
        }
        return size;
    }

    // Iterates through the string checking each spot for valid parentheses.
    public int findLongestValidParentheses(String s){
        while(position < s.length()){
            int newSize = isValidParentheses(s);
            if(newSize > longest){
                longest = newSize;
            }
        }
        return longest;
    }

    public void reset(){
        position = 0;
        longest = 0;
    }

    public static void main(String[] args) {
        LongestValidParentheses test = new LongestValidParentheses();
        System.out.println("Longest parentheses in '[](((()())(' (expect 6): " + test.findLongestValidParentheses("[](((()())("));
        test.reset();
        System.out.println("Longest parentheses in '[]()[[{}{}]]]' (expect 12): " + test.findLongestValidParentheses("[]()[[{}{}]]]"));
        test.reset();
        System.out.println("Longest parentheses in '[]())((((' (expect 4): " + test.findLongestValidParentheses("[]())(((("));
        test.reset();
        System.out.println("Longest parentheses in '[)]]][' (expect 0): " + test.findLongestValidParentheses("[)]]]["));
        test.reset();
        System.out.println("Longest parentheses in '[](({)({}][){)()(' (expect 2): " + test.findLongestValidParentheses("[](({)({}][){)()("));

    }

}