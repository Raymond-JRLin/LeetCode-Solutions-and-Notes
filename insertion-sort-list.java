// 147. Insertion Sort List
// DescriptionHintsSubmissionsDiscussSolution
// Sort a linked list using insertion sort.
//
//
// A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
// With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list
//
//
// Algorithm of Insertion Sort:
//
// Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
// At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
// It repeats until no input elements remain.
//
// Example 1:
//
// Input: 4->2->1->3
// Output: 1->2->3->4
// Example 2:
//
// Input: -1->5->3->4->0
// Output: -1->0->3->4->5



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        return mytry(head);
    }

    private ListNode mytry(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode root = dummy;
        while (head != null) {
            ListNode prev = getPrev(dummy, head.val); // find the index that this head should insert into
            // System.out.println(prev.val);
            ListNode temp = head.next; // keep this head next temporarily
            head.next = prev.next; // connect this inserted node to original latter part
            prev.next = head; // connect to this head
            head = temp; // move head to next one
        }
        return dummy.next;
    }
    private ListNode getPrev(ListNode dummy, int target) {
        while (dummy.next != null && dummy.next.val < target) {
            dummy = dummy.next;
        }
        return dummy;
    }
}
