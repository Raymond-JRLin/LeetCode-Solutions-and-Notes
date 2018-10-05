// 21. Merge Two Sorted Lists
// DescriptionHintsSubmissionsDiscussSolution
// Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
//
// Example:
//
// Input: 1->2->4, 1->3->4
// Output: 1->1->2->3->4->4


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }


        // iteration way
        // return my_try(l1, l2);

        // recursion
        return method2_recursion(l1, l2);
    }

    private ListNode method2_recursion(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = method2_recursion(l1.next, l2);
            return l1;
        } else {
            l2.next = method2_recursion(l1, l2.next);
            return l2;
        }
    }

    private ListNode my_try(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                head.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                head.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            head = head.next;
        }
        while (l1 != null) {
            head.next = new ListNode(l1.val);
            l1 = l1.next;
            head = head.next;
        }
        while (l2 != null) {
            head.next = new ListNode(l2.val);
            l2 = l2.next;
            head = head.next;
        }
        return dummy.next;
    }
}
