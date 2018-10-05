// 19. Remove Nth Node From End of List
// DescriptionHintsSubmissionsDiscussSolution
// Given a linked list, remove the n-th node from the end of list and return its head.
//
// Example:
//
// Given linked list: 1->2->3->4->5, and n = 2.
//
// After removing the second node from the end, the linked list becomes 1->2->3->5.
// Note:
//
// Given n will always be valid.
//
// Follow up:
//
// Could you do this in one pass?


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // return mytry(head, n);

        // return method2(head, n);

        return method2_2(head, n);
    }

    private ListNode method2_2(ListNode head, int n) {
        // use dummy node then don't need to handle edge case
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    private ListNode method2(ListNode head, int n) {
        // 1 pass: usually for linked list, we gonna use 2 pointers
        // |---n--->|------l - n------>|   1st pointer
        // -----------------------------   linked list
        // |------l - n------>|---n--->|   2nd pointer
        // so if the 1st pointer reached the 2nd | and then 2nd pointer start to move, they can move l - n distance when 1st pointer reaches the end
        ListNode fast = head;
        int index = 0;
        while (index < n) {
            fast = fast.next;
            index++;
        }
        // edge case: reach the end
        if (fast == null) {
            return head.next;
        }
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    private ListNode mytry(ListNode head, int n) {
        ListNode node = head;
        // get the whole list length first
        int length = 0;
        while (node.next != null) {
            node = node.next;
            length++;
        }
        length -= n; // get the count from start to end
        // edge case: remove the 1st element, since length start at 0
        if (length < 0) {
            return head.next;
        }
        node = head;
        while (length > 0) {
            node = node.next;
            length--;
        }
        // remove target node
        node.next = node.next.next;
        return head;
    }
}
