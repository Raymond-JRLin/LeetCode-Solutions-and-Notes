// 369. Plus One Linked List
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
//
// You may assume the integer do not contain any leading zero, except the number 0 itself.
//
// The digits are stored such that the most significant digit is at the head of the list.
//
// Example :
//
// Input: [1,2,3]
// Output: [1,2,4]


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode plusOne(ListNode head) {

        return method1(head);
    }

    private ListNode method1(ListNode head) {
        // 记得和一题 string 加 1 很像， 主要要找到最后那个不是 9 的 node， 让它加 1 就好， 后面的全设为 0
        // 2 pointers
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        while (first.next != null) {
            first = first.next;
            if (first.val != 9) {
                second = first;
            }
        }
        // now second pointer is the last non-9 digit
        second.val++; // plus 1
        // mode afterward nodes 0
        second = second.next;
        while (second != null) {
            second.val = 0;
            second = second.next;
        }

        return dummy.val == 1 ? dummy : dummy.next;
    }
    private void makeZero(ListNode curr) {
        while (curr != null) {

            curr = curr.next;
        }
    }
}
