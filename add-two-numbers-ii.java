// 445. Add Two Numbers II
// DescriptionHintsSubmissionsDiscussSolution
// You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
//
// You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
// Follow up:
// What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
//
// Example:
//
// Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
// Output: 7 -> 8 -> 0 -> 7


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 第一个想到的就是 reverse， 然后相加进位， 再 reverse 即可
        // return my_try(l1, l2);

        // follow up: reversing the lists is not allowed
        // one way is to use a data structure to store those values, like Stack
        return method2_stack(l1, l2);
    }
    private ListNode my_try(ListNode l1, ListNode l2) {
        ListNode root1 = reverse(l1);
        ListNode root2 = reverse(l2);
        ListNode root = add(root1, root2);
        return reverse(root);
    }
    private ListNode reverse(ListNode root) {
        ListNode prev = null;
        while (root != null) {
            ListNode temp = root.next;
            root.next = prev;
            prev = root;
            root = temp;
        }
        return prev;
    }
    private ListNode add(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode root = dummy;
        int remainder = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + remainder;
            remainder = sum / 10;
            sum %= 10;
            ListNode curr = new ListNode(sum);
            root.next = curr;
            root = curr;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int sum = l1.val + remainder;
            remainder = sum / 10;
            sum %= 10;
            ListNode curr = new ListNode(sum);
            root.next = curr;
            root = curr;
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = l2.val + remainder;
            remainder = sum / 10;
            sum %= 10;
            ListNode curr = new ListNode(sum);
            root.next = curr;
            root = curr;
            l2 = l2.next;
        }
        // don't forget we may have one more remainder
        if (remainder != 0) {
            ListNode curr = new ListNode(remainder);
            root.next = curr;
        }
        return dummy.next;
    }
    private ListNode method2_stack(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        // 这里参考了一个答案的做法， 边累加， 边 reverse， 不用 dummy node
        ListNode head = new ListNode(0);
        int sum = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (!stack1.isEmpty()) {
                sum += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                sum += stack2.pop();
            }
            head.val = sum % 10; // less significant digit after pointing
            sum /= 10; // 得到进位的 remainder， 在下一轮会在上面的两次 pop 中 +=
            ListNode curr = new ListNode(sum); // current node with higher significant digit
            curr.next = head; // reversed pointing
            head = curr; // move to next
        }
        if (head.val == 0) {
            return head.next;
        } else {
            return head;
        }
    }
}
