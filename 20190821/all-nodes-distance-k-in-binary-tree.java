// 863. All Nodes Distance K in Binary Tree
// DescriptionHintsSubmissionsDiscussSolution
//
// We are given a binary tree (with root node root), a target node, and an integer value K.
//
// Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
//
//
//
// Example 1:
//
// Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
//
// Output: [7,4,1]
//
// Explanation:
// The nodes that are a distance 2 from the target node (with value 5)
// have values 7, 4, and 1.
//
//
//
// Note that the inputs "root" and "target" are actually TreeNodes.
// The descriptions of the inputs above are just serializations of these objects.
//
//
//
// Note:
//
//     The given tree is non-empty.
//     Each node in the tree has unique values 0 <= node.val <= 500.
//     The target node is a node in the tree.
//     0 <= K <= 1000.
//
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
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

        // return method1(root, target, K);

        return method2(root, target, K);
    }

    private List<Integer> method2(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, Integer> map = new HashMap<>();
        // 这个 recursion 拿到的是从 root 到 target 这个 path 上的 node 以及相对应的离 target 的距离
        // 比如给的 example， map 存着: <<5, 0>, <3, 1>>
        recursion(root, target, map);
        // for (TreeNode node : map.keySet()) {
        //     System.out.println(node.val + " : " + map.get(node));
        // }
        // 带着这些 path 上的距离再走一遍， 因为没有这些信息， 不是 target 同一 subtree 的 node 是不知道距离的
        List<Integer> result = new ArrayList<>();
        find(root, k, map, result, map.get(root));
        return result;
    }
    private void find(TreeNode root, int k, Map<TreeNode, Integer> map, List<Integer> result, int dist) {
        if (root == null) {
            return;
        }
        // 如果当前 root 在 path 上， 要先拿到 dist 信息
        // 因为开始的时候是带着 root 的 dist 下来的， 往下走的时候如果遇到的是 path 上的 node
        // 那么此时从上往下带来的 dist 并不是这个 node 真正的 path
        if (map.containsKey(root)) {
            dist = map.get(root);
        }
        // 找到真正的 path 之后看是否为 k
        // 如果是 target， 我们存的距离是 0， 所以往下也正好
        if (dist == k) {
            result.add(root.val);
        }

        find(root.left, k, map, result, dist + 1);
        find(root.right, k, map, result, dist + 1);
    }
    private int recursion(TreeNode root, TreeNode target, Map<TreeNode, Integer> map) {
        if (root == null) {
            return -1;
        }
        // 找到 target 就立即返回， 下面的由具体第二次找答案的 recursion 去做
        if (root == target) {
            map.put(root, 0);
            return 0;
        }
        // 看看 target 在不在左子树， 一旦发现在了， 就返回， 因为我们找的就是 root 到 target 的 path
        int left = recursion(root.left, target, map);
        if (left >= 0) {
            map.put(root, left + 1);
            return left + 1;
        }
        // 左子树没有， 找右子树
        int right = recursion(root.right, target, map);
        if (right >= 0) {
            map.put(root, right + 1);
            return right + 1;
        }
        // 没找到返回 -1
        return -1;
    }

    private List<Integer> method1(TreeNode root, TreeNode target, int k) {
        // 不能从 root 开始走， 就是因为没有 parent 指针
        // 把 BST 转成 undirected graph， 从 target 来看的话， 就是 BFS 了
        Map<TreeNode, List<TreeNode>> adj = new HashMap<>();
        getAdj(root, null, adj);

        List<Integer> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        while (!queue.isEmpty() && k >= 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (k == 0) {
                    result.add(curr.val);
                }
                if (!adj.containsKey(curr)) {
                    continue;
                }
                for (TreeNode next : adj.get(curr)) {
                    if (visited.contains(next)) {
                        continue;
                    }
                    queue.offer(next);
                    visited.add(next);
                }
            }
            k--;
        }
        return result;
    }
    private void getAdj(TreeNode curr, TreeNode prev, Map<TreeNode, List<TreeNode>> adj) {
        if (curr == null) {
            return;
        }
        getAdj(curr.left, curr, adj);
        if (prev != null) {
            List<TreeNode> l1 = adj.computeIfAbsent(prev, dummy -> (new ArrayList<>()));
            List<TreeNode> l2 = adj.computeIfAbsent(curr, dummy -> (new ArrayList<>()));
            l2.add(prev);
            l1.add(curr);
        }
        getAdj(curr.right, curr, adj);
    }
}
