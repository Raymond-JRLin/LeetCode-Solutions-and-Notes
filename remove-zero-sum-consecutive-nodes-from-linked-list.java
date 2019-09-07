// 1171. Remove Zero Sum Consecutive Nodes from Linked List
// User Accepted: 961
// User Tried: 1259
// Total Accepted: 974
// Total Submissions: 2551
// Difficulty: Medium
// Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.
//
// After doing so, return the head of the final linked list.  You may return any such answer.
//
//
//
// (Note that in the examples below, all sequences are serializations of ListNode objects.)
//
// Example 1:
//
// Input: head = [1,2,-3,3,1]
// Output: [3,1]
// Note: The answer [1,2,1] would also be accepted.
// Example 2:
//
// Input: head = [1,2,3,-3,4]
// Output: [1,2,4]
// Example 3:
//
// Input: head = [1,2,3,-3,-2]
// Output: [1]
//
//
// Constraints:
//
// The given linked list will contain between 1 and 1000 nodes.
// Each node in the linked list has -1000 <= node.val <= 1000.


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        if (head == null) {
            return head;
        }

        // return mytry(head);

        return method1(head);
    }

    private ListNode method1(ListNode head) {
        // ref: https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/discuss/366319/JavaC++Python-Greedily-Skip-with-HashMap
        Map<Integer, ListNode> map = new HashMap<>(); // <prefix sum, ListNode>
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        int sum = 0;
        while (curr != null) {
            sum += curr.val;
            if (map.containsKey(sum)) {
                ListNode prev = map.get(sum).next;
                int prefix = sum + prev.val;
                while (prefix != sum) {
                    map.remove(prefix);
                    prev = prev.next;
                    prefix += prev.val;
                }
                map.get(sum).next = curr.next;
            } else {
                map.put(sum, curr);

            }
            curr = curr.next;
        }
        return dummy.next;
    }

    private ListNode mytry(ListNode head) {
        // wrong, like [0,0]
        // remove map 那里处理的不对， 删掉的那一整段中间的 prefix sum 其实都应该删除
        Map<Integer, ListNode> map = new HashMap<>(); // <prefix sum, ListNode>
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        int sum = 0;
        while (curr != null) {
            sum += curr.val;
            if (map.containsKey(sum)) {
                ListNode prev = map.get(sum);
                // System.out.println("has b4, sum is " + sum + ", node is " + prev.val);
                prev.next = curr.next;
                map.remove(sum);
            } else {
                map.put(sum, curr);

            }
            curr = curr.next;
        }
        return dummy.next;
    }
}
