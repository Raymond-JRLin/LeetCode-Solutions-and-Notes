// 430. Flatten a Multilevel Doubly Linked List
// DescriptionHintsSubmissionsDiscussSolution
//
// You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.
//
// Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.
//
//
//
// Example 1:
//
// Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
// Output: [1,2,3,7,8,11,12,9,10,4,5,6]
// Explanation:
//
// The multilevel linked list in the input is as follows:
//
//
//
// After flattening the multilevel linked list it becomes:
//
//
// Example 2:
//
// Input: head = [1,2,null,3]
// Output: [1,3,2]
// Explanation:
//
// The input multilevel linked list is as follows:
//
//   1---2---NULL
//   |
//   3---NULL
//
// Example 3:
//
// Input: head = []
// Output: []
//
//
//
// How multilevel linked list is represented in test case:
//
// We use the multilevel linked list from Example 1 above:
//
//  1---2---3---4---5---6--NULL
//          |
//          7---8---9---10--NULL
//              |
//              11--12--NULL
//
// The serialization of each level is as follows:
//
// [1,2,3,4,5,6,null]
// [7,8,9,10,null]
// [11,12,null]
//
// To serialize all levels together we will add nulls in each level to signify no node connects to the upper node of the previous level. The serialization becomes:
//
// [1,2,3,4,5,6,null]
// [null,null,7,8,9,10,null]
// [null,11,12,null]
//
// Merging the serialization of each level and removing trailing nulls we obtain:
//
// [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
//
//
//
// Constraints:
//
//     Number of Nodes will not exceed 1000.
//     1 <= Node.val <= 10^5
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/
class Solution {
    public Node flatten(Node head) {

        // return method1(head);

        // return method2(head);

        return method3(head);
    }

    private Node method3(Node head) {
        // 类似 method2， iteration
        Node curr = head;
        while (curr != null) {
            if (curr.child == null) {
                curr = curr.next;
                continue;
            }

            Node child = curr.child;
            // get child tail
            while (child.next != null) {
                child = child.next;
            }
            // connect child tail and net
            if (curr.next != null) {
                child.next = curr.next;
                curr.next.prev = child;
            }
            // connect child to next position
            curr.next = curr.child;
            curr.child.prev = curr;
            curr.child = null;

            curr = curr.next;
        }
        return head;
    }

    private Node method2(Node head) {
        // 或者返回的时候返回最后一个 tail， 这样可以直接往后相连
        flattenToTail(head);
        return head;
    }
    private Node flattenToTail(Node head) {
        if (head == null) {
            return head;
        }

        if (head.child != null) {
            // 先记录一下
            Node child = head.child;
            head.child = null;
            Node next = head.next;
            // 把 child 放到 next 上
            head.next = child;
            child.prev = head;
            // flatten child 这条
            Node childTail = flattenToTail(child);
            // 如果原 next 还有， 连在 child tail 后面， 并 flatten
            if (next != null) {
                childTail.next = next;
                next.prev = childTail;
                return flattenToTail(next);
            }
            return childTail;
        }
        if (head.next != null) {
            return flattenToTail(head.next);
        } else {
            return head;
        }
    }

    private Node method1(Node head) {
        Node prev = null;
        return dfs(head);
    }
    Node prev;
    private Node dfs(Node head) {
        if (head == null) {
            return head;
        }
        if (prev != null) {
            prev.next = head;
            head.prev = prev;
        }
        // 注意这里我们把 child 连在 next 上， 如果直接传参 prev 去做的话， 原 next 的 dfs 就会覆盖前面更新 child 到 next 的结果
        // 所以用一个全局变量， 这样 prev 就会成为 dfs 中最后一个 head， 也就是 flatten 好的 tail
        prev = head;
        Node next = head.next;
        // 更新 child 到 next
        dfs(head.child);
        head.child = null;
        // 继续原 next
        dfs(next);
        return head;
    }
}
