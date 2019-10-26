// 25. Reverse Nodes in k-Group
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
//
// k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
//
// Example:
//
// Given this linked list: 1->2->3->4->5
//
// For k = 2, you should return: 2->1->4->3->5
//
// For k = 3, you should return: 3->2->1->4->5
//
// Note:
//
//     Only constant extra memory is allowed.
//     You may not alter the values in the list's nodes, only nodes itself may be changed.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {

        return mytry(head, k);
    }

    private ListNode mytry(ListNode head, int k) {
        ListNode curr = head;
        // 找到最后一个要翻转的
        int count = 1;
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }
        // 不足 k 个， 不翻转， 直接返回
        // 因为这里我初始化 count 为 1， 所以有可能 == k 的时候正好走到了 null， 那其实 valid 的 nodes 不足 k 个
        if (curr == null) {
            return head;
        }
        // 余下的拿去 recursion， 返回的是余下的翻转之后的 head
        ListNode next = mytry(curr.next, k);
        // 翻转链表 从 head 到 curr
        // 有点不同的是， 不能有 null 的 prev 指针， 因为最后 head 翻转到最后一个的时候， 下一个是 recursion 回来的 next
        while (count > 0) {
            ListNode temp = head.next;
            // 每次 swap 一个
            head.next = next;
            next = head;
            head = temp;
            count--;
        }
        return curr;
    }
}
