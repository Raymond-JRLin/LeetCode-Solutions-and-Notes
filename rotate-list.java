// 61. Rotate List
// DescriptionHintsSubmissionsDiscussSolution
// Given a linked list, rotate the list to the right by k places, where k is non-negative.
//
// Example 1:
//
// Input: 1->2->3->4->5->NULL, k = 2
// Output: 4->5->1->2->3->NULL
// Explanation:
// rotate 1 steps to the right: 5->1->2->3->4->NULL
// rotate 2 steps to the right: 4->5->1->2->3->NULL
// Example 2:
//
// Input: 0->1->2->NULL, k = 4
// Output: 2->0->1->NULL
// Explanation:
// rotate 1 steps to the right: 2->0->1->NULL
// rotate 2 steps to the right: 1->2->0->NULL
// rotate 3 steps to the right: 0->1->2->NULL
// rotate 4 steps to the right: 2->0->1->NULL


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k < 1) {
            return head;
        }

        return mytry(head, k);
    }

    private ListNode mytry(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // get the list length
        int n = 1;
        while (head.next != null) {
            n++;
            head = head.next;
        }

        k %= n; // k may larger than length
        ListNode tail = dummy; // get the new tail
        for (int i = 0; i < n - k; i++) {
            tail = tail.next;
        }
        head.next = dummy.next; // connect original tail to original head
        dummy.next = tail.next; // create a new head
        tail.next = null;// create a new tail

        return dummy.next;
    }
}
