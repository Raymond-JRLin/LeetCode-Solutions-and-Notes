// 206. Reverse Linked List
// DescriptionHintsSubmissionsDiscussSolution
// Reverse a singly linked list.
//
// Example:
//
// Input: 1->2->3->4->5->NULL
// Output: 5->4->3->2->1->NULL
// Follow up:
//
// A linked list can be reversed either iteratively or recursively. Could you implement both?


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
        if (head == null || head.next == null) {
            return head;
        }

        // 写了几遍了要是还不会写就要好好反思！！！
        // return my_try(head);

        // recursion
        return method2_recursion(head);
    }
    private ListNode my_try(ListNode head) {
        // iteration
        ListNode prev = null; // 注意点1: 直接设 prev 为 null
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        // 注意点2: 返回的是 prev 因为此时结束了 loop 时 head 为 null
        return prev;
    }

    private ListNode method2_recursion(ListNode head) {
        return recursion(head, null);
    }
    private ListNode recursion(ListNode head, ListNode prev) {
        if (head == null) {
            return prev;
        }
        ListNode temp = head.next;
        head.next = prev;
        return recursion(temp, head);
    }
}
