// 234. Palindrome Linked List
// DescriptionHintsSubmissionsDiscussSolution
// Given a singly linked list, determine if it is a palindrome.
//
// Example 1:
//
// Input: 1->2
// Output: false
// Example 2:
//
// Input: 1->2->2->1
// Output: true
// Follow up:
// Could you do it in O(n) time and O(1) space?
//


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        return my_try(head);
    }

    private boolean my_try(ListNode head) {
        // reverse this linked list, and compare
        // 但是要特别注意的是： 不能 reverse 整条链， 因为本身 reverse 是会更改链的结构的
        // 所以我们要 reverse 后半部分， 来比较前半部分
        // step 1: find the middle of linked list
        ListNode middle = findMiddle(head);
        // step 2: reverse the 2nd part of linked list
        ListNode last = reverse(middle);
        // step 3: compare the 1st and 2nd part
        while (last != null) {
            if (head.val != last.val) {
                return false;
            }
            head = head.next;
            last = last.next;
        }
        return true;
    }
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null) {
            // there are even number of node
            return slow;
        } else {
            // there are ood number of node, jump the exact middle one
            return slow.next;
        }
    }
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }
}
