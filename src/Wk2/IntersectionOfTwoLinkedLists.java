package Wk2;

import java.util.Stack;

// Data structure given in the question.
//class Wk2.ListNode {
//    int val;
//    Wk2.ListNode next;
//
//    Wk2.ListNode(int x) {
//        val = x;
//    }
//}

public class IntersectionOfTwoLinkedLists {


    // Find the point of merger (or intersection) of two lists.
    // Works from the back of each list, points which should be equivalent in merged lists.
    public static ListNode findIntersectionOfTwoLinkedLists1(ListNode list1, ListNode list2){

        if(list1 == null || list2 == null){
            return null;
        }

        ListNode ln1 = list1;
        ListNode ln2 = list2;
        Stack<ListNode> l1 = new Stack();
        Stack<ListNode> l2 = new Stack();

        // Build all of the lists into stacks.
        while(ln1 != null){
            l1.push(ln1);
            ln1 = ln1.next;
        }
        while(ln2 != null){
            l2.push(ln2);
            ln2 = ln2.next;
        }

        // Keep popping elements from the back until two elements are not the same (the lists have diverged).
        ListNode intersection = null;
        while(l1.peek().val == l2.peek().val){
            intersection = l1.pop();
            l2.pop();
            if(l1.isEmpty() || l2.isEmpty()){
                break;
            }
        }

        return intersection;
    }

    // Iterates through the singley-linked list and prints each value.
    static void printAll(ListNode list){

        if(list == null){
            System.out.println("No intersection.");
            return;
        }

        System.out.println("Value at intersection: " + list.val);
        System.out.print("  Entire intersection: ");


        while(list.next != null){
            System.out.print(list.val);
            list = list.next;
        }
        System.out.print(list.val);
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] testList1 = {1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] testList2 = {1, 1, 1, 1, 1, 1, 5, 6, 7, 8};
        Integer[] testList3 = {1, 2, 3, 4, 5, 6, 7, 1};
        Integer[] testList4 = {1, 2, 3, 4, 5, 6, 7, 8};

        ListNode cur;

        ListNode test1 = new ListNode(testList1[0]);
        cur = test1;
        for(int i = 1; i < testList1.length; i ++){
            cur.next = new ListNode(testList1[i]);
            cur = cur.next;
        }
        ListNode test2 = new ListNode(testList2[0]);
        cur = test2;
        for(int i = 1; i < testList2.length; i ++){
            cur.next = new ListNode(testList2[i]);
            cur = cur.next;
        }
        ListNode test3 = new ListNode(testList3[0]);
        cur = test3;
        for(int i = 1; i < testList3.length; i ++){
            cur.next = new ListNode(testList3[i]);
            cur = cur.next;
        }
        ListNode test4 = new ListNode(testList4[0]);
        cur = test4;
        for(int i = 1; i < testList4.length; i ++){
            cur.next = new ListNode(testList4[i]);
            cur = cur.next;
        }

        System.out.println("----Intersection that lasts until the end (merging of lists)----");
        System.out.println("Intersection of {1, 2, 3, 4, 5, 6, 7, 8} and {1, 1, 1, 1, 1, 1, 5, 6, 7, 8}:");printAll(findIntersectionOfTwoLinkedLists1(test1, test2));
        System.out.println("\nIntersection of {1, 2, 3, 4, 5, 6, 7, 8} and {1, 2, 3, 4, 5, 6, 7, 1}:");printAll(findIntersectionOfTwoLinkedLists1(test1, test3));
        System.out.println("\nIntersection of {1, 2, 3, 4, 5, 6, 7, 8} and {1, 2, 3, 4, 5, 6, 7, 6}:");printAll(findIntersectionOfTwoLinkedLists1(test1, test4));

    }
}
