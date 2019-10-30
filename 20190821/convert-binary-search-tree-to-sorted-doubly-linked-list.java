// 426. Convert Binary Search Tree to Sorted Doubly Linked List
// DescriptionHintsSubmissionsDiscussSolution
//
// Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.
//
// Let's take the following BST as an example, it may help you understand the problem better:
//
//
//
//
// We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
//
// The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.
//
//
//
//
// Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.
//
// The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.
//
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return root;
        }

        // return method1(root);

        return method2(root);
    }

    // 我看到这个题目， 要是 sorted 的连起来， 那么可以使用 inorder 的 recursion traversal
    // 要求是： left -> predecessor, right -> successor
    // 也就是说， left 要指向它的前一位 （更小的）， right 要指向它的后一位（更大的）

    private Node method2(Node root) {
        Node head = null;
        Node prev = null;
        // 1. inorder iteration traversal
        Stack<Node> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (head == null) {
                head = root; // find the smallest Node as head
            }
            if (prev != null) {
                // prev 是前一个， 更小的一个
                root.left = prev;
                prev.right = root;
            }
            prev = root;
            root = root.right;
        }
        // 2. connect head and tail
        // prev 是最后一位， 因为 root 是 null 了
        head.left = prev;
        prev.right = head;
        return head;
    }

    private Node method1(Node root) {
        // 1. 先把给的 tree 按要求变化串起来
        // 2. 再把首尾连起来变成 circular doubly linked list
        Node dummy = new Node();
        prev = dummy;
        recursion(root);
        // prev 此时是最后一位， 即最大的
        prev.right = dummy.right; // right 指向后一位， 即是 tree 的第一个
        dummy.right.left = prev; // tree 第一个的 left 指向前一位， 即 tree 的最后一个， 是 prev
        return dummy.right;
    }
    Node prev;
    private void recursion(Node root) {
        if (root == null) {
            return;
        }
        recursion(root.left);
        prev.right = root;
        root.left = prev;
        prev = root;
        recursion(root.right);
    }
}
