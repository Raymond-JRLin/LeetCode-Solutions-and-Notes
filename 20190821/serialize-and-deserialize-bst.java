// 449. Serialize and Deserialize BST
// DescriptionHintsSubmissionsDiscussSolution
//
// Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
//
// Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
//
// The encoded string should be as compact as possible.
//
// Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
// Seen this question in a real interview before?
//
//     Difficulty:Medium


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // 这题和 serialize binary tree 不同的在于要求 The encoded string should be as compact as possible.
    // 每个 node 的 val 是不可少的， 所以考虑的应该是 null， 即如何不用额外一个 char 来代替 null
    // 那么就要用到 BST 的特性， 所以可以把 val 的上下限往下传， 来判断是 left child or right child

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        recursion(root, sb);
        return sb.toString().trim();
    }
    private void recursion(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val).append(",");
        recursion(root.left, sb);
        recursion(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] arr = data.split(",");
        Queue<Integer> queue = new LinkedList<>();
        for (String s : arr) {
            queue.offer(Integer.parseInt(s));
        }
        return decode(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private TreeNode decode(Queue<Integer> queue, int min, int max) {
        if (queue.isEmpty()) {
            return null;
        }
        // 这个地方要先判断一下， 比如说此时 peek 是　right， 那么就不同 poll 出来去走下一层， 而应该把 val 留给 right
        int val = queue.peek();
        if (val < min || val > max) {
            return null;
        }
        queue.poll();
        TreeNode root = new TreeNode(val);
        root.left = decode(queue, min, val);
        root.right = decode(queue, val, max);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
