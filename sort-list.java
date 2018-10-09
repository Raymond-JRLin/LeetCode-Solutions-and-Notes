// 148. Sort List
// DescriptionHintsSubmissionsDiscussSolution
// Sort a linked list in O(n log n) time using constant space complexity.
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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // return my_try(head);

        // return method2(head);

        return method3(head);
    }

    private ListNode method3(ListNode head) {
        // quick sort
        if (head == null || head.next == null) {
            return head;
        }
        // find the middle as pivot
        ListNode middle = findMiddle(head);
        // create 3 list to put <, ==, > middle
        ListNode leftDummy = new ListNode(0);
        ListNode midDummy = new ListNode(0);
        ListNode rightDummy = new ListNode(0);
        ListNode left = leftDummy;
        ListNode mid = midDummy;
        ListNode right = rightDummy;
        while (head != null) {
            if (head.val < middle.val) {
                left.next = head;
                left = left.next;
            } else if (head.val == middle.val) {
                mid.next = head;
                mid = mid.next;
            } else {
                right.next = head;
                right = right.next;
            }
            head = head.next;
        }
        // cut list separately
        left.next = null;
        mid.next = null;
        right.next = null;
        // sort < and > part
        left = method3(leftDummy.next);
        right = method3(rightDummy.next);
        // combine 3 lists together
        return combine(left, midDummy.next, right);
    }
    private ListNode combine(ListNode left, ListNode middle, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        head.next = left;
        head = getTail(head); // 因为 head next 是 left， 这里取 head 的 tail 比较好， 万一 left 是 null
        head.next = middle;
        head = getTail(head);
        head.next = right;
        return dummy.next;
    }
    private ListNode getTail(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }


    private ListNode method2(ListNode head) {
        // divide & conquer based on merge sort
        // ref: https://discuss.leetcode.com/topic/18100/java-merge-sort-solution
        // 1. exit
        if (head == null || head.next == null) {
            return head;
        }
        // 2. find the middle
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head; // don't need to be head.next because we use prev
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // cut half
        // 3: divide
        ListNode left = method2(head);
        ListNode right = method2(slow);
        // 4. conquer
        return merge(left, right);
    }


    private ListNode my_try(ListNode head) {
        // try merge sort
        if (head == null || head.next == null) {
            return head;
        }
        // find middle node first
        ListNode middle = findMiddle(head);
        // cut into 2 parts (divide)
        ListNode right = my_try(middle.next);
        middle.next = null;
        ListNode left = my_try(head);
        return merge(left, right);

    }
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next; // avoid StackOverflow for [2, 1], it may cause dead loop when do for left
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                node.next = left;
                left = left.next;
            } else {
                node.next = right;
                right = right.next;
            }
            node = node.next;
        }
        while (left != null) {
            node.next = left;
            node = node.next;
            left = left.next;
        }
        while (right != null) {
            node.next = right;
            node = node.next;
            right = right.next;
        }
        return dummy.next;
    }
}
