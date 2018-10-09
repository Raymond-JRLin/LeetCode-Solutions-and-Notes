// 143. Reorder List
// DescriptionHintsSubmissionsDiscussSolution
// Given a singly linked list L: L0→L1→…→Ln-1→Ln,
// reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
//
// You may not modify the values in the list's nodes, only nodes itself may be changed.
//
// Example 1:
//
// Given 1->2->3->4, reorder it to 1->4->2->3.
// Example 2:
//
// Given 1->2->3->4->5, reorder it to 1->5->2->4->3.


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        mytry(head);
    }

    private void mytry(ListNode head) {
        // I would think seperate into 2 list, and then reverse latter one, and merge 2 lists
        // find the middle
        ListNode mid = findMid(head);
        // seperate 2 lists
        ListNode second = mid.next;
        mid.next = null;
        // reverse latter list
        second = reverse(second);
        // merge 2 lists
        merge(head, second);
    }
    private void merge(ListNode head, ListNode second) {
        ListNode dummy = new ListNode(0);
        while (head != null || second != null) {
            if (head != null) {
                dummy.next = head;
                dummy = head;
                head = head.next;
            }
            if (second != null) {
                dummy.next = second;
                dummy = second;
                second = second.next;
            }
        }
    }
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }
    private ListNode findMid(ListNode head) {
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return prev;
    }
}
