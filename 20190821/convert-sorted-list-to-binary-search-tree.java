// 109. Convert Sorted List to Binary Search Tree
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
//
// For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
//
// Example:
//
// Given the sorted linked list: [-10,-3,0,5,9],
//
// One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
//
//       0
//      / \
//    -3   9
//    /   /
//  -10  5
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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        // return mytry(head);

        // return method1(head);

        return method2(head);
    }

    private TreeNode method2(ListNode head) {
        // 每次下去的时候都快慢指针， 找到 mid
        return pointersToBST(head, null);
    }
    private TreeNode pointersToBST(ListNode head, ListNode tail) {
        if (head == tail) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        // 注意这里范围
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = pointersToBST(head, slow);
        root.right = pointersToBST(slow.next, tail);
        return root;
    }

    private TreeNode method1(ListNode head) {
        ListNode curr = head;
        int end = 0;
        while (curr != null) {
            end++;
            curr = curr.next;
        }
        node = head;
        return toBST(0, end - 1);
    }
    ListNode node;
    private TreeNode toBST(int start, int end) {
        // in-order recursion
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode left = toBST(start, mid - 1);
        TreeNode root = new TreeNode(node.val);
        root.left = left;
        node = node.next; // global variable, 不然没办法往上返回
        TreeNode right = toBST(mid + 1, end);
        root.right = right;
        return root;
    }

    private TreeNode mytry(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return sortedArrayToBST(list, 0, list.size() - 1);
    }
    private TreeNode sortedArrayToBST(List<Integer> list, int start, int end) {
        // divide & conquer
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(list.get(start));
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = sortedArrayToBST(list, start, mid - 1);
        root.right = sortedArrayToBST(list, mid + 1, end);
        return root;
    }
}
