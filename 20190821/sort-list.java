// 148. Sort List
// DescriptionHintsSubmissionsDiscussSolution
//
// Sort a linked list in O(n log n) time using constant space complexity.
//
// Example 1:
//
// Input: 4->2->1->3
// Output: 1->2->3->4
//
// Example 2:
//
// Input: -1->5->3->4->0
// Output: -1->0->3->4->5
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


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

        // return mytry(head);

        return method2(head);
    }

    private ListNode method2(ListNode head) {
        // iteration merge sort
        int n = 0; // total # nodes
        ListNode curr = head;
        while (curr != null) {
            n++;
            curr = curr.next;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        for (int i = 1; i < n; i <<= 1) {
            // 每次个数翻倍去模拟
            ListNode prev = dummy;
            curr = dummy.next;
            while (curr != null) {
                ListNode left = curr;
                // split() 会从 left 开始 (inclusive) 保留 i 个， 然后返回下一个
                ListNode right = split(left, i);
                // curr 是下一段
                curr = split(right, i);
                // prev 是前一段的最后一个
                prev = merge(left, right, prev);
                // System.out.println("left/right = " + (left == null ? " null " : left.val) + " / "+ (right == null ? " null " :  right.val) + " , after merge: curr/prev = " + (curr == null ? " null " : curr.val) + " / " + (prev == null ? " null " : prev.val));
            }
        }
        return dummy.next;
    }
    private ListNode merge(ListNode head1, ListNode head2, ListNode prev) {
        ListNode curr = prev;
        while (head1 != null || head2 != null) {
            int val1 = head1 == null ? Integer.MAX_VALUE : head1.val;
            int val2 = head2 == null ? Integer.MAX_VALUE : head2.val;
            if (val1 <= val2) {
                curr.next = head1;
                head1 = head1.next;
            } else {
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;
        }
        while (curr.next != null) {
            curr = curr.next;
        }
        return curr;
    }
    private ListNode split(ListNode head, int len) {
        // split head nodes (inclusive) with number of len
        if (head == null) {
            return head;
        }
        // len 是个数
        for (int i = 1; i < len; i++) {
            if (head.next == null) {
                break;
            }
            head = head.next;
        }
        ListNode right = head.next; // return latter half
        head.next = null; // split
        return right;
    }

    private ListNode mytry(ListNode head) {
        // same to merge sort
        // 严格来讲 recursion 的 merge sort 还是需要 O(logN) 的 stack
        return mergeList(head);
    }
    private ListNode mergeList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        ListNode head1 = mergeList(head);
        ListNode head2 = mergeList(slow);
        return mergeListNode(head1, head2);
    }
    private ListNode mergeListNode(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (head1 != null || head2 != null) {
            int val1 = head1 == null ? Integer.MAX_VALUE : head1.val;
            int val2 = head2 == null ? Integer.MAX_VALUE : head2.val;
            if (val1 <= val2) {
                curr.next = head1;
                head1 = head1.next;
            } else {
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;
        }
        return dummy.next;
    }
}
