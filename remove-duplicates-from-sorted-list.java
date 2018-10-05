// 83. Remove Duplicates from Sorted List
// DescriptionHintsSubmissionsDiscussSolution
// Given a sorted linked list, delete all duplicates such that each element appear only once.
//
// Example 1:
//
// Input: 1->1->2
// Output: 1->2
// Example 2:
//
// Input: 1->1->2->3->3
// Output: 1->2->3


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // return mytry(head);

        // return method2(head);

        return method3(head);
    }

    private ListNode method3(ListNode head) {
        ListNode curr = head;
        while (curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    private ListNode method2(ListNode head) {
        // recursion/ D&C
        if (head == null || head.next == null) {
            return head;
        }
        head.next = method2(head.next);
        return head.val == head.next.val ? head.next : head;
    }

    private ListNode mytry(ListNode head) {
        ListNode prev = head;
        ListNode curr = head.next;
        while (curr != null) {
            if (prev.val == curr.val) {
                prev.next = curr.next;
                curr = curr.next;
            } else {
                prev = prev.next;
                curr = curr.next;
            }

        }
        return head;
    }
}
