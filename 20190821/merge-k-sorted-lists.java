// 23. Merge k Sorted Lists
// DescriptionHintsSubmissionsDiscussSolution
// Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
//
// Example:
//
// Input:
// [
//   1->4->5,
//   1->3->4,
//   2->6
// ]
// Output: 1->1->2->3->4->4->5->6


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return new ListNode(0).next;
        }

        // return method1(lists);

        return method2(lists);
    }

    private ListNode method2(ListNode[] lists) {
        return mergeKListNode(lists, 0, lists.length - 1);
    }
    private ListNode mergeKListNode(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode left = mergeKListNode(lists, start, mid);
        ListNode right = mergeKListNode(lists, mid + 1, end);
        return mergeTwoListNode(left, right);
    }
    private ListNode mergeTwoListNode(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (left != null || right != null) {
            int leftVal = left == null ? Integer.MAX_VALUE : left.val;
            int rightVal = right == null ? Integer.MAX_VALUE : right.val;
            if (leftVal >= rightVal) {
                head.next = right;
                right = right.next;
            } else {
                head.next = left;
                left = left.next;
            }
            head = head.next;
        }
        return dummy.next;
    }

    private ListNode method1(ListNode[] lists) {
        // use PQ
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }
        while (!pq.isEmpty()) {
            ListNode curr = pq.poll();
            head.next = curr;
            head = head.next;
            if (curr.next != null) {
                pq.offer(curr.next);
            }
        }
        return dummy.next;
    }
}
