// 133. Clone Graph
// DescriptionHintsSubmissionsDiscussSolution
// Given the head of a graph, return a deep copy (clone) of the graph. Each node in the graph contains a label (int) and a list (List[UndirectedGraphNode]) of its neighbors. There is an edge between the given node and each of the nodes in its neighbors.
//
//
// OJ's undirected graph serialization (so you can understand error output):
// Nodes are labeled uniquely.
//
// We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
//
//
// As an example, consider the serialized graph {0,1,2#1,2#2,2}.
//
// The graph has a total of three nodes, and therefore contains three parts as separated by #.
//
// First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
// Second node is labeled as 1. Connect node 1 to node 2.
// Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
//
//
// Visually, the graph looks like the following:
//
//        1
//       / \
//      /   \
//     0 --- 2
//          / \
//          \_/
// Note: The information about the tree serialization is only meant so that you can understand error output if you get a wrong answer. You don't need to understand the serialization to solve the problem.
//


/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        if (node.neighbors.size() == 0) {
            return new UndirectedGraphNode(node.label);
        }

        // return method1_1(node);

        // return method1_2(node);

        return method2(node);
    }

    private UndirectedGraphNode method2(UndirectedGraphNode root) {
        // DFS
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        return dfs(root, map);
    }
    private UndirectedGraphNode dfs(UndirectedGraphNode root, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
        if (map.containsKey(root)) {
            return map.get(root);
        }

        UndirectedGraphNode copy = new UndirectedGraphNode(root.label);
        map.put(root, copy);
        for (UndirectedGraphNode nei : root.neighbors) {
            copy.neighbors.add(dfs(nei, map));
        }
        return copy;
    }

    private UndirectedGraphNode method1_2(UndirectedGraphNode head) {
        // another BFS, I think it's similar to Copy List with Random Pointer, use a map to record original node and copied node
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        map.put(head, new UndirectedGraphNode(head.label));
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(head);
        // BFS
        while (!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.poll();
            for (UndirectedGraphNode nei : curr.neighbors) {
                if (!map.containsKey(nei)) {
                    // 把新出现的 node 都 new 出来， 并放入 queue 中以待后面找它的 neighbors
                    map.put(nei, new UndirectedGraphNode(nei.label));
                    queue.offer(nei);
                }
                // 把这些 neighbors 都加到对应的 copied curr node 的 neighbors 里
                map.get(curr).neighbors.add(map.get(nei));
            }
        }
        return map.get(head);
    }

    private UndirectedGraphNode method1_1(UndirectedGraphNode head) {
        UndirectedGraphNode root = new UndirectedGraphNode(head.label);
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        map.put(root.label, root);
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(head);
        // BFS
        while (!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.poll();
            for (UndirectedGraphNode nei : curr.neighbors) {
                if (!map.containsKey(nei.label)) {
                    // 把新出现的 node 都 new 出来， 并放入 queue 中以待后面找它的 neighbors
                    map.put(nei.label, new UndirectedGraphNode(nei.label));
                    queue.offer(nei);
                }
                // 把这些 neighbors 都加到对应的 copied curr node 的 neighbors 里
                map.get(curr.label).neighbors.add(map.get(nei.label));
            }
        }
        return root;
    }
}
