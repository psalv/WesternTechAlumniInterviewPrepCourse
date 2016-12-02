package Wk2;

import java.util.HashSet;


// Data structure given in the question.
//class Wk2.ListNode {
//    int val;
//    Wk2.ListNode next;
//
//    Wk2.ListNode(int x) {
//        val = x;
//    }
//}


public class LinkedListCycleII {

    // O(n) time, uses additional space.
    public static ListNode findLinkedListCycleNode(ListNode list){
        HashSet<ListNode> seen = new HashSet<>();

        ListNode cur = list;

        // Searches for repeated nodes by storing seen nodes in a hashset.
        while(cur != null){
            if(seen.contains(cur)){
                return cur;
            }
            seen.add(cur);
            cur = cur.next;
        }
        return null;
    }

    public static void main(String[] args) {

        ListNode cur;
        Integer[] testList = {1, 2, 3, 4, 5, 6, 7, 8};
        ListNode test1 = new ListNode(testList[0]);
        cur = test1;
        for(int i = 1; i < testList.length; i ++){
            cur.next = new ListNode(testList[i]);
            cur = cur.next;
        }
        // Create a cycle to Wk2.ListNode with value 2.
        cur.next = test1.next;

        ListNode test2 = new ListNode(testList[0]);
        cur = test2;
        for(int i = 1; i < testList.length; i ++){
            cur.next = new ListNode(testList[i]);
            cur = cur.next;
        }

        System.out.println("1 > 2 > 3 > 4 > 5 > 6 > 7 > 8 > 2 > 3 > 4 > ... ");
        System.out.println("Cycle at: " + findLinkedListCycleNode(test1).val);
        System.out.println("\n1 > 2 > 3 > 4 > 5 > 6 > 7 > 8 : ");
        System.out.println("Cycle at: " + findLinkedListCycleNode(test2));
    }


}
