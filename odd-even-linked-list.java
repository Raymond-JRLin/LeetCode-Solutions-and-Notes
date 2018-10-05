// 328. Odd Even Linked List
// DescriptionHintsSubmissionsDiscussSolution
// Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
//
// You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
//
// Example 1:
//
// Input: 1->2->3->4->5->NULL
// Output: 1->3->5->2->4->NULL
// Example 2:
//
// Input: 2->1->3->5->6->4->7->NULL
// Output: 2->3->6->7->1->5->4->NULL
// Note:
//
// The relative order inside both the even and odd groups should remain as it was in the input.
// The first node is considered odd, the second node even and so on ...


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        // if we are not allowed to modify the original linkedlist, then we should use 2 pointers to connect odd and even positions in turns
        // return method1_takeTurn(head);

        // or similarily, we can take turn to move odd and even and keep a head pointer to even pointer, and connect them 2 finally
        return method2_keepHead(head);
    }
    private ListNode method1_takeTurn(ListNode head) {
        ListNode odd = head;
        ListNode even = head.next;
        while (even != null && even.next != null) {
            ListNode temp = odd.next;
            odd.next = even.next;
            even.next = even.next.next;
            odd.next.next = temp;
            odd = odd.next;
            even = even.next;
        }
        return head;
    }
    private ListNode method2_keepHead(ListNode head) {
        ListNode odd = head;
        ListNode even = head.next;
        ListNode even_head = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = even_head;
        return head;
    }
}
