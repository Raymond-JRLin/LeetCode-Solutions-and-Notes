// 428. Serialize and Deserialize N-ary Tree
// DescriptionHintsSubmissionsDiscussSolution
//
// Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
//
// Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.
//
// For example, you may serialize the following 3-ary tree
//
//
//
//
//
// as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
//
//
//
// Note:
//
//     N is in the range of [1, 1000]
//     Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
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

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Codec {

    // 因为 children node 是另一个 list， 所以没有办法像 binary tree 一样放置一个不同的符号占据 null 的位置， 因为不知道是几 x-ary
    // 所以 root 后面放一个 int value， 来表示 children node 的个数

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        if (sb.length() == 0) {
            return "";
        }
        return sb.toString().substring(0, sb.length() - 1);
    }
    private void buildString(Node root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val).append(",");
        sb.append(root.children.size()).append(",");
        for (Node child : root.children) {
            buildString(child, sb);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] arr = data.split(",");
        Queue<Integer> queue = new LinkedList<>();
        for (String s : arr) {
            queue.offer(Integer.valueOf(s));
        }
        return buildTree(queue);

    }
    private Node buildTree(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        int val = queue.poll();
        int count = queue.poll();
        Node root = new Node();
        root.val = val;
        List<Node> list = new ArrayList<>();
        while (count > 0) {
            list.add(buildTree(queue));
            count--;
        }
        root.children = list;
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
