// 431. Encode N-ary Tree to Binary Tree
// DescriptionHintsSubmissionsDiscussSolution
//
// Design an algorithm to encode an N-ary tree into a binary tree and decode the binary tree to get the original N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. Similarly, a binary tree is a rooted tree in which each node has no more than 2 children. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that an N-ary tree can be encoded to a binary tree and this binary tree can be decoded to the original N-nary tree structure.
//
// Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See following example).
//
// For example, you may encode the following 3-ary tree to a binary tree in this way:
//
// Input: root = [1,null,3,2,4,null,5,6]
//
// Note that the above is just an example which might or might not work. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
//
//
//
// Constraints:
//
//     The height of the n-ary tree is less than or equal to 1000
//     The total number of nodes is between [0, 10^4]
//     Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
//
// Seen this question in a real interview before?
//
//     Difficulty:Hard


/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
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
class Codec {

    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        // 第一个 child 作为 left child， 剩下的 siblings 作为第一个 child 的右子树
        if (root == null) {
            return null;
        }
        TreeNode head = new TreeNode(root.val);
        if (root.children.size() > 0) {
            head.left = encode(root.children.get(0)); // children 还会有 children
        }
        TreeNode left = head.left;
        // 剩下的 children 全放在作为 left child 的第一个 child 的右子树上
        for (int i = 1; i < root.children.size(); i++) {
            left.right = encode(root.children.get(i));
            left = left.right;
        }
        return head;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        Node head = new Node(root.val, new ArrayList<Node>());
        TreeNode left = root.left; // 从 left 开始是当前 head 的 children， 一路往右走就是 siblings
        while (left != null) {
            head.children.add(decode(left)); // 对于 head 的 children 的 children， 用 recursion 完成
            left = left.right;
        }
        return head;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(root));
