// 206. Reverse Linked List
// DescriptionHintsSubmissionsDiscussSolution
//
// Reverse a singly linked list.
//
// Example:
//
// Input: 1->2->3->4->5->NULL
// Output: 5->4->3->2->1->NULL
//
// Follow up:
//
// A linked list can be reversed either iteratively or recursively. Could you implement both?
// Seen this question in a real interview before?
//
//     Difficulty:Easy


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }

        // return mytry(head);

        // return mytry2(head);

        return method3(head);
    }

    private ListNode method3(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = head;
        head = head.next;
        while (head != null) {
            tail.next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = tail.next;
        }
        return dummy.next;
    }

    private ListNode mytry2(ListNode head) {
        ListNode prev = null;
        return recursion(head, prev);
    }
    private ListNode recursion(ListNode head, ListNode prev) {
        if (head == null) {
            return prev;
        }
        ListNode next = head.next;
        head.next = prev;
        return recursion(next, head);
    }

    private ListNode mytry(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
