// 92. Reverse Linked List II
// DescriptionHintsSubmissionsDiscussSolution
// Reverse a linked list from position m to n. Do it in one-pass.
//
// Note: 1 ≤ m ≤ n ≤ length of list.
//
// Example:
//
// Input: 1->2->3->4->5->NULL, m = 2, n = 4
// Output: 1->4->3->2->5->NULL


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }

        // return mytry(head, m, n);

        return method2(head, m, n);
    }

    private ListNode method2(ListNode head, int m, int n) {
        // ref: https://leetcode.com/problems/reverse-linked-list-ii/discuss/30666/Simple-Java-solution-with-clear-explanation
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 1; i < m; i++) {
            prev = prev.next;
            head = head.next;
        }
        ListNode then = head.next;
        for (int i = 0; i < n - m; i++) {
            head.next = then.next;
            then.next = prev.next;
            prev.next = then;
            then = head.next;
        }
        return dummy.next;
    }

    private ListNode mytry(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int count = 1;
        ListNode root = dummy;
        // get the starting position
        while (count < m) {
            root = root.next;
            head = head.next;
            count++;
        }
        ListNode tail = head; // record the starting, to connect it to the next of last reverse node
        // reverse list
        ListNode prev = null;
        while (count <= n) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
            count++;
        }
        root.next = prev; // connect node right before starting reverse node to the last reverse node
        tail.next = head;
        return dummy.next;
    }
}
