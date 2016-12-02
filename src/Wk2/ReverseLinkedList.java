package Wk2;

import java.util.LinkedList;
import java.util.Stack;

public class ReverseLinkedList<E> {

    /**
     * Reverses the order of a list in O(n) time using the LinkedLists pointer to the end of the list (this uses a doubley-linked list).
     *
     * @param list a linked list
     * @return a linked list with the order of elements reversed
     */
    public static LinkedList reverseLinkedList1(LinkedList list) {
        LinkedList reversedList = new LinkedList<>();

        // Remove the last item from the input and build it into the output list
        // Continue to take the last element until the input list is empty.
        while (!list.isEmpty()) {
            reversedList.add(list.removeLast());
        }

        return reversedList;
    }

    /**
     * Reverses the order of a list in O(n) time first using a stack to order the elements.
     *
     * @param list a linked list
     * @return a linked list with the order of elements reversed
     */
    public static LinkedList reverseLinkedList2(LinkedList list){
        LinkedList reversedList = new LinkedList<>();
        Stack reversingStack = new Stack();

        // Put the elements into a stack.
        while(!list.isEmpty()){
            reversingStack.add(list.remove(0));
        }

        // Pop them out of the stack and into the linked list (inputting them in the reverse of the original order).
        while(!reversingStack.isEmpty()){
            reversedList.add(reversingStack.pop());
        }

        return reversedList;
    }

    public static void main(String[] args) {
        LinkedList<Integer> test1 = new LinkedList<>();
        LinkedList<Integer> test2 = new LinkedList<>();
        for(int i = 0; i < 10; i++){
            test1.add(i);
            test2.add(i);
        }

        System.out.println("Before rev: " + test1.toString());
        System.out.println("After rev1: " + ReverseLinkedList.reverseLinkedList1(test1).toString());
        System.out.println("After rev2: " + ReverseLinkedList.reverseLinkedList2(test2).toString());

    }
}
