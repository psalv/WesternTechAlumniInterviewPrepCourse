package Wk2;

import java.util.ArrayList;

/**
 * An object to store a value and a previous object of a similar type to keep track of minimum elements.
 *
 * @param <E> the value of the object; any class that extends comparable
 */
class StackObject<E extends Comparable>{

    private E value;

    // A pointer to the next smallest element in the stack.
    private StackObject previous;

    public StackObject(E value){
        this.value = value;
    }

    public E getValue(){
        return value;
    }

    public void setPrevious(StackObject previous){
        this.previous = previous;
    }

    public StackObject getPrevious(){
        return previous;
    }

    public String toString(){
        return value.toString();
    }
}


/**
 * A stack data structure that pops, pushes, and returns the min value in O(n) time.
 *
 * @param <E> the value of the object; any class that extends comparable.
 */
public class CustomStack<E extends Comparable> {

    // Chose to use a an array list since all required functions will be amortized to O(1).
    private ArrayList<StackObject<E>> stack = new ArrayList<>();
    private int front = 0;

    // Instance variable storing the current minimum element.
    private StackObject<E> min;

    public E peek(){
        return stack.get(front - 1).getValue();
    }

    public void push (E element){
        StackObject<E> toAdd = new StackObject<E>(element);
        stack.add(front++, toAdd);

        if(min == null){
            toAdd.setPrevious(min);
            min = toAdd;
        }

        // Checks if the new element is smaller than the current minimum, if so sets this element as the new minimum.
        // Stores the previous minimum so that when this element is popped it can return to being the minimum for the stack up to this point.
        else if(element.compareTo(min.getValue()) == -1){
            toAdd.setPrevious(min);
            min = toAdd;
        }
    }

    public E pop() {
        if (front == 0) {
            return null;
        }

        // Checks if the element that is being popped is the current min, if so sets it as the next lowest number (the previous min).
        if (min == stack.get(--front)) {
            min = stack.get(front).getPrevious();
        }

        return stack.remove(front).getValue();
    }

    public E min(){
        return min.getValue();
    }

    public String toString(){
        return stack.toString();
    }




    // ---- Test cases ----

    public static void main(String[] args) {
        CustomStack<Integer> test1 = new CustomStack<>();

        System.out.println("--Integer tests--");
        test1.push(4);
        System.out.println("Stack:" + test1);
        System.out.println("Min: " + test1.min());

        test1.push(7);
        test1.push(3);
        System.out.println("\nStack:" + test1);
        System.out.println("Min: " + test1.min());

        test1.pop();
        System.out.println("\nStack:" + test1);
        System.out.println("Min: " + test1.min());

        test1.push(2);
        test1.push(3);
        test1.push(1);
        System.out.println("\nStack:" + test1);
        System.out.println("Min: " + test1.min());

        test1.pop();
        System.out.println("\nStack:" + test1);
        System.out.println("Min: " + test1.min());

        CustomStack<String> test2 = new CustomStack<>();

        System.out.println("\n--String tests--");
        test2.push("C");
        System.out.println("Stack:" + test2);
        System.out.println("Min: " + test2.min());

        test2.push("B");
        test2.push("F");
        test2.push("A");
        System.out.println("\nStack:" + test2);
        System.out.println("Min: " + test2.min());

        test2.pop();
        System.out.println("\nStack:" + test2);
        System.out.println("Min: " + test2.min());
    }
}
