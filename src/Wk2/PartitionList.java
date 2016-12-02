package Wk2;

import java.util.LinkedList;


// Data structure given in the question.
 class ListNode {
     int val;
     ListNode next;

     ListNode(int x) {
         val = x;
     }
 }



public class PartitionList {

    private static ListNode partitionList1(ListNode list, int x){

        // Need to keep track of pointers to the front (which will serve as the list) and the back (to add)
        ListNode firstNodeFrontList = null;
        ListNode lastNodeFrontList = null;
        ListNode firstNodeBackList = null;
        ListNode lastNodeBackList= null;
        ListNode currentNode = list;
        boolean last = false;

        while(!last){

            if(currentNode.next == null){
                last = true;
            }

            // Add to the front list if less than x
            if(currentNode.val < x){

                // If the front pointer has not been set yet we must do this to use as the return value (front of entire new list).
                if(lastNodeFrontList == null){
                    firstNodeFrontList = new ListNode(currentNode.val);
                    lastNodeFrontList = firstNodeFrontList;
                }
                else {
                    lastNodeFrontList.next = new ListNode(currentNode.val);
                    lastNodeFrontList = lastNodeFrontList.next;
                }
            }
            else{

                // If the fron pointer has not been set then we must do this so we can add this to the end of the "front list"
                if(lastNodeBackList == null){
                    firstNodeBackList = new ListNode(currentNode.val);
                    lastNodeBackList = firstNodeBackList;
                }
                else {
                    lastNodeBackList.next = new ListNode(currentNode.val);
                    lastNodeBackList = lastNodeBackList.next;

                }
            }
            currentNode = currentNode.next;
        }


        if(lastNodeFrontList == null){
            return firstNodeBackList;
        }
        else if(lastNodeBackList == null){
            return firstNodeFrontList;
        }

        // Combining the lists, placing the front of the "back list" and the back of the "front list"
        lastNodeFrontList.next = firstNodeBackList;

        return firstNodeFrontList;

    }


    // Using javas linked-list data structure.
    public static <E extends Comparable> LinkedList<E> partitionList2(LinkedList<E> list, E x){
        LinkedList<E> frontList = new LinkedList<E>();
        LinkedList<E> backList = new LinkedList<E>();

        // Separate the elements based on if they are greater or lower than the partitioning element x.
        for(E element: list){
            if(element.compareTo(x) == -1){
                frontList.add(element);
            }
            else{
                backList.add(element);
            }
        }

        if(frontList.isEmpty()){
            return backList;
        }
        else if(backList.isEmpty()){
            return frontList;
        }


        // Add the elements that were greater than the partitioning element to the end of the list lower.
        frontList.addAll(backList);

        return frontList;

    }

    // Iterates through the singley-linked list and prints each value.
    static void printAll(ListNode list){

        if(list == null){
            return;
        }

        while(list.next != null){
            System.out.print(list.val);
            list = list.next;
        }
        System.out.print(list.val);
        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("----With javas linked list data structure----");
        LinkedList<Integer> test2 = new LinkedList<>();
        test2.add(5);
        test2.add(6);
        test2.add(1);
        test2.add(3);
        test2.add(2);
        test2.add(1);
        test2.add(2);

        System.out.println("     Before partitioning: " + test2);
        System.out.println(" After partitioning on 2: " + PartitionList.partitionList2(test2, 2));
        System.out.println(" After partitioning on 0: " + PartitionList.partitionList2(test2, 0));
        System.out.println("After partitioning on 10: " + PartitionList.partitionList2(test2, 10));

        System.out.println("\n----With only the singly linked-list given----");
        ListNode test1 = new ListNode(5);
        ListNode cur = test1;
        cur.next = new ListNode(6);
        cur = cur.next;
        cur.next = new ListNode(1);
        cur = cur.next;
        cur.next = new ListNode(3);
        cur = cur.next;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(1);
        cur = cur.next;
        cur.next = new ListNode(2);

        System.out.print("     Before partitioning: ");printAll(test1);
        System.out.print(" After partitioning on 2: ");printAll(PartitionList.partitionList1(test1, 2));
        System.out.print(" After partitioning on 0: ");printAll(PartitionList.partitionList1(test1, 0));
        System.out.print("After partitioning on 10: ");printAll(PartitionList.partitionList1(test1, 10));

    }
}
