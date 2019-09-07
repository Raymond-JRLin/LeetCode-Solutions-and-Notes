// 863. All Nodes Distance K in Binary Tree
// DescriptionHintsSubmissionsDiscussSolution
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
// Note:
//
// The given tree is non-empty.
// Each node in the tree has unique values 0 <= node.val <= 500.
// The target node is a node in the tree.
// 0 <= K <= 1000.



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
        if (root == null) {
            return Collections.emptyList();
        }
        if (K == 0) {
            return Arrays.asList(target.val);
        }

        // return mytry(root, target, K);

        // return method1(root, target, K);

        // return method2(root, target, K);

        return method3(root, target, K);
    }

    private List<Integer> method3(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, Integer> map = new HashMap<>();
        // store the value of distance in hashamp from the all nodes in that path to target
        find(root, target, map); // 注意只记录了从 root 到达 target 的 path 上的 node 及其距离 target 的距离
        for (TreeNode node : map.keySet()) {
            System.out.println(node.val + " " + map.get(node));
        }
        List<Integer> res = new LinkedList<>();
        dfs(root, target, k, map, map.get(root), res);
        return res;
    }
    // find target node first and store the distance in that path that we could use it later directly
    private int find(TreeNode root, TreeNode target, Map<TreeNode, Integer> map) {
        if (root == null) return -1;
        if (root == target) {
            map.put(root, 0);
            return 0;
        }
        int left = find(root.left, target, map);
        if (left >= 0) {
            map.put(root, left + 1);
            return left + 1;
        }
        int right = find(root.right, target, map);
        if (right >= 0) {
            map.put(root, right + 1);
            return right + 1;
        }
        return -1;
    }
    private void dfs(TreeNode root, TreeNode target, int K, Map<TreeNode, Integer> map, int length, List<Integer> res) {
        if (root == null) return;
        // 遇到了记录过的点， 就更新一下
        if (map.containsKey(root)) length = map.get(root);
        if (length == K) res.add(root.val);
        dfs(root.left, target, K, map, length + 1, res);
        dfs(root.right, target, K, map, length + 1, res);
    }

    private Node targetNode = null;
    private List<Integer> method2(TreeNode root, TreeNode target, int k) {
        // 类似 method1， 不用 map 而是创造新的 node 数据结构， 把 parent 的信息存入
        Node rootNode = buildNodes(root, null, target);
        if (targetNode == null) {
            return Collections.emptyList();
        }
        return nodeBFS(targetNode, k);
    }
    private List<Integer> nodeBFS(Node target, int k) {
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(target);
        visited.add(target);
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (k == 0) {
                // 把这一层都加进来
                while (!queue.isEmpty()) {
                    result.add(queue.poll().node.val);
                }
                return result;
            }
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                // 分别查看 parent， left 和 right 是否都存在并且没查看过
                if (curr.parent != null && !visited.contains(curr.parent)) {
                    queue.offer(curr.parent);
                    visited.add(curr.parent);
                }
                if (curr.left != null && !visited.contains(curr.left)) {
                    queue.offer(curr.left);
                    visited.add(curr.left);
                }
                if (curr.right != null && !visited.contains(curr.right)) {
                    queue.offer(curr.right);
                    visited.add(curr.right);
                }

            }
            k--;
        }
        return result;
    }
    private Node buildNodes(TreeNode root, Node parent, TreeNode target) {
        if (root == null) {
            return null;
        }
        Node curr = new Node(root);
        if (root == target) {
            targetNode = curr;
        }
        curr.parent = parent;
        curr.left = buildNodes(root.left, curr, target);
        curr.right = buildNodes(root.right, curr, target);
        return curr;
    }
    private class Node {
        private TreeNode node;
        private Node left, right, parent;

        public Node(TreeNode node) {
            this.node = node;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    private List<Integer> method1(TreeNode root, TreeNode target, int k) {
        // transform the tree structure to undirected graph, then BFS
        // O(N) time and O(N) space
        Map<TreeNode, List<TreeNode>> map = new HashMap<>();
        buildMap(map, root, null);
        if (!map.containsKey(target)) {
            return Collections.emptyList();
        }
        return bfs(map, target, k);
    }
    private List<Integer> bfs(Map<TreeNode, List<TreeNode>> map, TreeNode target, int k) {
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        visited.add(target);
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 注意是距离刚好为 k 的这一些
            if (k == 0) {
                // 把这一层都加进来
                while (!queue.isEmpty()) {
                    result.add(queue.poll().val);
                }
                return result;
            }
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                // 现在已经是 undirected graph 的结构了
                for (TreeNode next : map.get(curr)) {
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
    private void buildMap(Map<TreeNode, List<TreeNode>> map, TreeNode curr, TreeNode parent) {
        if (curr == null) {
            return;
        }
        map.put(curr, new ArrayList<TreeNode>());
        if (parent != null) {
            map.get(curr).add(parent);
            map.get(parent).add(curr);
        }
        buildMap(map, curr.left, curr);
        buildMap(map, curr.right, curr);
    }

//     private List<Integer> mytry(TreeNode root, TreeNode target, int k) {
//         // wrong: 其实不知道 target 在 root 的哪一边， 就挺麻烦的
//         List<Integer> result = new ArrayList<>();
//         // 1: from target, get k distance
//         result.addAll(bfs(target, k));
//         // 2: find the distance from root to target, and record the nodes with distance from root between them
//         List<List<Integer>> fromRoot = new ArrayList<>();
//         Queue<TreeNode> queue = new LinkedList<>();
//         queue.offer(root.left);
//         int dist = 1;
//         boolean found = false;
//         while (!queue.isEmpty()) {
//             int size = queue.size();
//             List<Integer> list = new ArrayList<>();
//             for (int i = 0; i < size; i++) {
//                 TreeNode curr = queue.poll();
//                 if (curr == null) {
//                     continue;
//                 }
//                 list.add(curr.val);
//                 if (curr.val == target.val) {
//                     found = true;
//                 }
//                 if (curr.left != null) {
//                     queue.offer(curr.left);
//                 }
//                 if (curr.right != null) {
//                     queue.offer(curr.right);
//                 }
//             }
//             if (found) {
//                 break;
//             }
//             dist++;
//             fromRoot.add(list);
//         }
//         // System.out.println("dist is " + dist);
//         if (dist == k) {
//             result.add(root.val);
//         } else if (dist > k) {
//             result.addAll(fromRoot.get(fromRoot.size() - k));
//         } else {
//             // 3: get rest (k - dist) from root right subtree
//             result.addAll(bfs(root.right, k - dist - 1));
//         }

//         return result;
//     }
//     private List<Integer> bfs(TreeNode root, int k) {
//         List<Integer> list = new ArrayList<>();
//         Queue<TreeNode> queue = new LinkedList<>();
//         queue.offer(root);
//         int dist = 0;
//         while (!queue.isEmpty() && dist < k) {
//             int size = queue.size();
//             for (int i = 0; i < size; i++) {
//                 TreeNode curr = queue.poll();
//                 if (curr == null) {
//                     continue;
//                 }
//                 if (curr.left != null) {
//                     queue.offer(curr.left);
//                 }
//                 if (curr.right != null) {
//                     queue.offer(curr.right);
//                 }
//             }
//             dist++;
//         }
//         while (!queue.isEmpty()) {
//             TreeNode curr = queue.poll();
//             if (curr != null) {
//                 list.add(curr.val);
//             }
//         }
//         return list;
//     }
}
