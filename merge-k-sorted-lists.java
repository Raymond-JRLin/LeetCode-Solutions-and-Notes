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
            return null;
        }

        // return mytry(lists);

        // return method2(lists);

        return method3(lists);
    }

    private ListNode method3(ListNode[] lists) {
        // recursion, like merge K sorted lists
        // ref: https://leetcode.com/problems/merge-k-sorted-lists/discuss/10522/My-simple-java-Solution-use-recursion
        return mergeKList(lists, 0, lists.length - 1);
    }
    private ListNode mergeKList(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        } else if (start > end) {
            return null;
        } else {
            int mid = start + (end - start) / 2;
            ListNode left = mergeKList(lists, start, mid);
            ListNode right = mergeKList(lists, mid + 1, end);
            // return merge(left, right);
            return mergeTwoList(left, right);
        }
    }
    private ListNode mergeTwoList(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode root = dummy;
        while (left != null || right != null) {
            if (left == null) {
                root.next = right;
                right = right.next;
            } else if (right == null) {
                root.next = left;
                left = left.next;
            } else {
                if (left.val < right.val) {
                    root.next = left;
                    left = left.next;
                } else {
                    root.next = right;
                    right = right.next;
                }
            }
            root = root.next;
        }
        return dummy.next;
    }
    private ListNode merge(ListNode left, ListNode right) {
        // recursion way to merge 2 sorted lists
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            if (left.val < right.val) {
                left.next = merge(left.next, right);
                return left;
            } else {
                right.next = merge(left, right.next);
                return right;
            }
        }
    }

    private ListNode method2(ListNode[] lists) {
        // at least we need to get every ListNode in array, so O(N), where N is the total # ListNode in array, can be improved. Thus, we can think about how can we improve the process to choose the smallest node in heads -> PQ, O(NlogK) time and O(K) space, where K is the length of lists array
        ListNode dummy = new ListNode(0);
        ListNode root = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });
        for (ListNode node : lists) {
            // careful about NULL head, e.g. [[]]
            if (node != null) {
                pq.offer(node);
            }
        }
        while (!pq.isEmpty()) {
            ListNode curr = pq.poll();
            root.next = curr;
            root = root.next;
            if (curr.next != null) {
                pq.offer(curr.next);
            }
        }
        return dummy.next;
    }

    private ListNode mytry(ListNode[] lists) {
        // brute force: everytime we compare K heads to choose the smallest one, O(K * N) time, where K is the length of array, N is the total # ListNode in array
        int n = lists.length;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (true) {
            int index = 0;
            int min = Integer.MAX_VALUE;
            // get the smallest one from current head
            for (int i = 0; i < n; i++) {
                if (lists[i] == null) {
                    continue;
                }
                if (lists[i].val < min) {
                    min = lists[i].val;
                    index = i;
                }
            }
            curr.next = lists[index]; // get the next node
            curr = curr.next; // move to next
            // all node are catched, reach the tail
            if (curr == null) {
                break;
            }
            lists[index] = lists[index].next; // move the chosen node to its next
        }
        return dummy.next;
    }
}
