// 86. Partition List
// DescriptionHintsSubmissionsDiscussSolution
// Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
//
// You should preserve the original relative order of the nodes in each of the two partitions.
//
// Example:
//
// Input: head = 1->4->3->2->5->2, x = 3
// Output: 1->2->2->4->3->5


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        // return mytry(head, x);

        return method2(head, x);
    }

    private ListNode method2(ListNode head, int x) {
        // use 2 pointers, one to smaller list seperately, and another to rest list
        // ref: https://leetcode.com/problems/partition-list/discuss/29183/Concise-java-code-with-explanation-one-pass
        ListNode smallerDummy = new ListNode(0); // smaller list dummy
        ListNode dummy = new ListNode(0); // rest list (bigger or equal one) dummy
        ListNode smallerRoot = smallerDummy; // smaller list head
        ListNode root = dummy;
        while (head != null) {
            if (head.val < x) {
                // partition
                smallerRoot.next = head;
                smallerRoot = smallerRoot.next;
            } else {
                // otherwise, normal moving forward
                root.next = head;
                root = root.next;
            }
            head = head.next;
        }
        // connect smaller list ahead of rest list
        smallerRoot.next = dummy.next;
        // make last node to null, otherwise there would be cycle list
        // 因为在 mytry 中做了 prev.next = next 就会连到最后的 null， 而上面都只是连到 head
        root.next = null;
        return smallerDummy.next;
    }

    private ListNode mytry(ListNode head, int x) {
        // partition smaller list seperately, and point to head of rest list
        ListNode smaller = new ListNode(0); // smaller list dummy
        ListNode root = smaller; // smaller list head
        ListNode dummy = new ListNode(0); // rest list (bigger or equal one) dummy
        dummy.next = head;
        ListNode prev = dummy; // prev pointer
        while (head != null) {
            if (head.val < x) {
                // partition
                ListNode next = head.next;
                prev.next = next; // cut this smaller node
                root.next = head; // connect to smaller list

                root = root.next; // move to next for smaller list
                head = next; // move to next for rest list
            } else {
                // otherwise, normal moving forward
                head = head.next;
                prev = prev.next;
            }
        }
        // connect smaller list ahead of rest list
        root.next = dummy.next;
        return smaller.next;
    }
}
