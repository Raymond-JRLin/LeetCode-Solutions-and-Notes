// 82. Remove Duplicates from Sorted List II
// DescriptionHintsSubmissionsDiscussSolution
// Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
//
// Example 1:
//
// Input: 1->2->3->3->4->4->5
// Output: 1->2->5
// Example 2:
//
// Input: 1->1->1->2->3
// Output: 2->3


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

        // return method1(head);

        return method2(head);
    }

    private ListNode method2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy; // start from dummy, check next and next.next
        while (curr.next != null && curr.next.next != null) {
            if (curr.next.val == curr.next.next.val) {
                int dup = curr.next.val; // duplicate value
                while (curr.next != null && curr.next.val == dup) {
                    // remove duplicates
                    curr.next = curr.next.next;
                }
            } else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    private ListNode method1(ListNode head) {
        // ref: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/discuss/28335/My-accepted-Java-code
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (head != null) {
            while (head.next != null && head.val == head.next.val) {
                // move to the last duplicates
                head = head.next;
            }
            if (prev.next == head) {
                // there's only 1 "duplicate" and it's just next of prev => no duplicates, move forward
                prev = prev.next;
                head = head.next;
            } else {
                // there're duplicates and head is the last one, omit duplicates including head
                prev.next = head.next; // omit head itself too
                head = head.next;
            }
        }
        return dummy.next;
    }
}
