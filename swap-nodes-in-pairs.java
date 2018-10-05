// 24. Swap Nodes in Pairs
// DescriptionHintsSubmissionsDiscussSolution
// Given a linked list, swap every two adjacent nodes and return its head.
//
// Example:
//
// Given 1->2->3->4, you should return the list as 2->1->4->3.
// Note:
//
// Your algorithm should use only constant extra space.
// You may not modify the values in the list's nodes, only nodes itself may be changed.


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // return mytry(head);

        return method2(head);
    }

    private ListNode method2(ListNode head) {
        // recursion
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = method2(head.next.next);
        next.next = head;
        return next;
    }

    private ListNode mytry(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (head != null && head.next != null) {
            ListNode next = head.next;
            head.next = next.next;
            next.next = head;
            prev.next = next;
            prev = head;
            head = head.next;
        }
        return dummy.next;
    }
}
