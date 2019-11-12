// 510. Inorder Successor in BST II
// DescriptionHintsSubmissionsDiscussSolution
//
// Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
//
// The successor of a node p is the node with the smallest key greater than p.val.
//
// You will have direct access to the node but not to the root of the tree. Each node will have a reference to its parent node.
//
//
//
// Example 1:
//
// Input:
// root = {"$id":"1","left":{"$id":"2","left":null,"parent":{"$ref":"1"},"right":null,"val":1},"parent":null,"right":{"$id":"3","left":null,"parent":{"$ref":"1"},"right":null,"val":3},"val":2}
// p = 1
// Output: 2
// Explanation: 1's in-order successor node is 2. Note that both p and the return value is of Node type.
//
// Example 2:
//
// Input:
// root = {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":{"$id":"4","left":null,"parent":{"$ref":"3"},"right":null,"val":1},"parent":{"$ref":"2"},"right":null,"val":2},"parent":{"$ref":"1"},"right":{"$id":"5","left":null,"parent":{"$ref":"2"},"right":null,"val":4},"val":3},"parent":null,"right":{"$id":"6","left":null,"parent":{"$ref":"1"},"right":null,"val":6},"val":5}
// p = 6
// Output: null
// Explanation: There is no in-order successor of the current node, so the answer is null.
//
// Example 3:
//
// Input:
// root = {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":{"$id":"4","left":null,"parent":{"$ref":"3"},"right":null,"val":2},"parent":{"$ref":"2"},"right":{"$id":"5","left":null,"parent":{"$ref":"3"},"right":null,"val":4},"val":3},"parent":{"$ref":"1"},"right":{"$id":"6","left":null,"parent":{"$ref":"2"},"right":{"$id":"7","left":{"$id":"8","left":null,"parent":{"$ref":"7"},"right":null,"val":9},"parent":{"$ref":"6"},"right":null,"val":13},"val":7},"val":6},"parent":null,"right":{"$id":"9","left":{"$id":"10","left":null,"parent":{"$ref":"9"},"right":null,"val":17},"parent":{"$ref":"1"},"right":{"$id":"11","left":null,"parent":{"$ref":"9"},"right":null,"val":20},"val":18},"val":15}
// p = 15
// Output: 17
//
// Example 4:
//
// Input:
// root = {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":{"$id":"4","left":null,"parent":{"$ref":"3"},"right":null,"val":2},"parent":{"$ref":"2"},"right":{"$id":"5","left":null,"parent":{"$ref":"3"},"right":null,"val":4},"val":3},"parent":{"$ref":"1"},"right":{"$id":"6","left":null,"parent":{"$ref":"2"},"right":{"$id":"7","left":{"$id":"8","left":null,"parent":{"$ref":"7"},"right":null,"val":9},"parent":{"$ref":"6"},"right":null,"val":13},"val":7},"val":6},"parent":null,"right":{"$id":"9","left":{"$id":"10","left":null,"parent":{"$ref":"9"},"right":null,"val":17},"parent":{"$ref":"1"},"right":{"$id":"11","left":null,"parent":{"$ref":"9"},"right":null,"val":20},"val":18},"val":15}
// p = 13
// Output: 15
//
//
//
// Note:
//
//     If the given node has no in-order successor in the tree, return null.
//     It's guaranteed that the values of the tree are unique.
//     Remember that we are using the Node type instead of TreeNode type so their string representation are different.
//
//
//
// Follow up:
//
// Could you solve it without looking up any of the node's values?
// Seen this question in a real interview before?
//
//     Difficulty:Medium


/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/
class Solution {
    public Node inorderSuccessor(Node x) {
        if (x == null) {
            return null;
        }

        // return method1(x);

        return method2(x);
    }

    private Node method2(Node x) {
        // 如果不去比较 value， 思路还是同样的
        // x 有 right subtree， successor 一定在 right subtree， 然后找 leftmost， 这个不需要比较 value
        if (x.right != null) {
            Node right = x.right;
            // 如果有 left， 去找最左边的， 就是比 x 大的中最小的
            while (right.left != null) {
                right = right.left;
            }
            return right;
        } else {
            // 如果 x 没有 right subtree， successor 是比 x 大的第一个 root
            // 也就是说 x 在 root 的 left subtree
            // 如果是从 right 走上去的都不是， 找到第一个从 left 走上去的 root
            while (x.parent != null) {
                if (x.parent.left == x) {
                    return x.parent;
                } else {
                    x = x.parent;
                }
            }
            return null;
        }
    }

    private Node method1(Node x) {
        // 如果 x 有 right subtree， successor 一定在 right subtree， 因为 right 整体小于比 x 大的 root
        // 且是 right subtree 中 leftmost 那个
        if (x.right != null) {
            Node right = x.right;
            // 如果有 left， 去找最左边的， 就是比 x 大的中最小的
            while (right.left != null) {
                right = right.left;
            }
            return right;
        } else {
            // 如果 x 没有 right subtree， successor 就是比 x 大的第一个 root
            Node root = x.parent;
            while (root != null && root.val <= x.val) {
                root = root.parent;
            }
            return root;
        }
    }
}
