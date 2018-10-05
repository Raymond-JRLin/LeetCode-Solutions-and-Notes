// 160. Intersection of Two Linked Lists
// DescriptionHintsSubmissionsDiscussSolution
// Write a program to find the node at which the intersection of two singly linked lists begins.
//
//
// For example, the following two linked lists:
//
// A:          a1 → a2
//                    ↘
//                      c1 → c2 → c3
//                    ↗
// B:     b1 → b2 → b3
// begin to intersect at node c1.
//
//
// Notes:
//
// If the two linked lists have no intersection at all, return null.
// The linked lists must retain their original structure after the function returns.
// You may assume there are no cycles anywhere in the entire linked structure.
// Your code should preferably run in O(n) time and use only O(1) memory.


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        // return my_try(headA, headB);

        // return mytry(headA, headB);

        return method2(headA, headB);
    }
    private ListNode method2(ListNode headA, ListNode headB) {
        // 非常巧的一种方法， a／b 走到头了， 就换到对方的起点， 如果有交集则 a + b + m = b + a + m， 如果没有， 长度相等时 a = b (i.e. a) 走到 null， 长度不等时 a + b = b + a
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = (a == null ? headB : a.next);
            b = (b == null ? headA : b.next);
        }
        return a;
    }

    private ListNode my_try(ListNode headA, ListNode headB) {
        // connect 2 lists and check if there's a cycle
        ListNode tailA = headA;
        while (tailA.next != null) {
            tailA = tailA.next;
        }
        tailA.next = headB; // connected A'tail to B'head
        ListNode slow = headA;
        ListNode fast = headA;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode head = headA;
                while (head != fast) {
                    head = head.next;
                    fast = fast.next;
                }
                tailA.next = null;
                return head;
            }
        }
        // remove the connection between A'tail and B'head
        tailA.next = null;
        // no intersection
        return null;
    }

    private ListNode mytry(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }
}
