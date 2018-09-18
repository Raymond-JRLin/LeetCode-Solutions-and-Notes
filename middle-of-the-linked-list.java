// 876. Middle of the Linked List
// User Accepted: 2201
// User Tried: 2221
// Total Accepted: 2237
// Total Submissions: 3038
// Difficulty: Easy
// Given a non-empty, singly linked list with head node head, return a middle node of linked list.
//
// If there are two middle nodes, return the second middle node.
//
//
//
// Example 1:
//
// Input: [1,2,3,4,5]
// Output: Node 3 from this list (Serialization: [3,4,5])
// The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
// Note that we returned a ListNode object ans, such that:
// ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.
// Example 2:
//
// Input: [1,2,3,4,5,6]
// Output: Node 4 from this list (Serialization: [4,5,6])
// Since the list has two middle nodes with values 3 and 4, we return the second one.
//
//
// Note:
//
// The number of nodes in the given list will be between 1 and 100.


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return head;
        }

        // return mytry(head);

        return method2(head);
    }

    private ListNode method2(ListNode head) {
        // 2 pointers
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode mytry(ListNode head) {
        // brute force
        // get the length first
        int len = 0;
        ListNode root = head;
        while (root != null) {
            len++;
            root = root.next;
        }
        // get the middle place, attention when there are two middle nodes, return the second middle node.
        int pos = (len + 1) / 2 + (len % 2 == 0 ? 1 : 0);
        while (pos > 1) {
            pos--;
            head = head.next;

        }
        return head;
    }
}
