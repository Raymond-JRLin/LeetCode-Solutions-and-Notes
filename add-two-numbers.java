// 2. Add Two Numbers
// DescriptionHintsSubmissionsDiscussSolution
// You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
//
// You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
// Example:
//
// Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
// Output: 7 -> 0 -> 8
// Explanation: 342 + 465 = 807.


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // return my_try(l1, l2);

        return method2(l1, l2);
    }

    private ListNode method2(ListNode l1, ListNode l2) {
        // more concise
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int remain = 0;
        while (l1 != null || l2 != null || remain > 0) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + remain;
            curr.next = new ListNode(sum % 10);
            remain = sum / 10;
            l1 = (l1 == null ? l1 : l1.next);
            l2 = (l2 == null ? l2 : l2.next);
            curr = curr.next;
        }
        return dummy.next;
    }

    public ListNode my_try(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int remain = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + remain;
            remain = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int sum = l1.val + remain;
            remain = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = l2.val + remain;
            remain = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            l2 = l2.next;
        }
        if (remain != 0) {
            curr.next = new ListNode(remain);
        }
        return dummy.next;
    }
}
