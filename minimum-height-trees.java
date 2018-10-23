// 310. Minimum Height Trees
// DescriptionHintsSubmissionsDiscussSolution
// For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.
//
// Format
// The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).
//
// You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
//
// Example 1 :
//
// Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
//
//         0
//         |
//         1
//        / \
//       2   3
//
// Output: [1]
// Example 2 :
//
// Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
//
//      0  1  2
//       \ | /
//         3
//         |
//         4
//         |
//         5
//
// Output: [3, 4]
// Note:
//
// According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
// The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.


class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n < 0 || edges == null) {
            return Collections.emptyList();
        }
        if (n == 1) {
            List<Integer> result = new ArrayList<>();
            result.add(0);
            return result;
        }

        // return mytry(n, edges);

        // return method1(n, edges);

        return method2(n, edges);
    }

    private List<Integer> method2(int n, int[][] edges) {
        // 答案的 root 一定是这棵树直径的中点， 有可能是 1 个， 也有可能是 2 个， 因为 height 是 root 到 deepest node 的高度， 直径是最长的路径， 所以最终的结果会在直径的中间
        // 思路其实和 method1 一样， 不用 indegree 来记录， 直接 list remove 就可以了
        // BFS topological sorting,  也可以叫 2 pointers ?
        List<List<Integer>> adj = new ArrayList<>(); // adjacent list
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < edges.length; i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }
        List<Integer> leaf = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (adj.get(i).size() == 1) {
                leaf.add(i);
            }
        }
        while (n > 2) {
            n -= leaf.size();
            List<Integer> newLeaf = new ArrayList<>();
            for (int curr : leaf) {
                for (int next : adj.get(curr)) {
                    adj.get(next).remove((Integer) curr); // remove this value (Object)
                    if (adj.get(next).size() == 1) {
                        newLeaf.add(next);
                    }
                }
            }
            leaf = newLeaf;
        }
        return leaf;
    }

    private List<Integer> method1(int n, int[][] edges) {
        // 不妨从 leaf 开始走， 像剥洋葱一样， 一层一层向里， 最后剩下的就是要找的根结点
        List<List<Integer>> adj = new ArrayList<>(); // adjacent list
        int[] indegree = new int[n]; // get the in-degree
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < edges.length; i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
            // undirected graph
            indegree[edges[i][0]]++;
            indegree[edges[i][1]]++;
        }
        // topological BFS
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 1) {
                // 不是为 0 的， 无向图至少会有 1 个连接（入度）
                queue.offer(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            result = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                result.add(curr);
                indegree[curr]--; // 对方到自身的连接断开 （对 curr 来说）
                for (int next : adj.get(curr)) {
                    indegree[next]--; // 断开 curr 到 next 的连接 （对 next 来说）
                    if (indegree[next] == 1) {
                        queue.offer(next);
                    }
                }
            }
        }
        return result;

    }

    private List<Integer> mytry(int n, int[][] edges) {
        // BFS: O(N ^ 2) time, TLE, 还要注意一个问题： 树的 height 是最大的那个深度， 就是对于 specific 的一棵树来说， 高度是要能够走到所有的节点的， 也就是到达 deepest 的 leaf 的长度
        // get adjacent list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < edges.length; i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }

        List<Integer> result = new ArrayList<>();
        int minHeight = Integer.MAX_VALUE;
        // BFS
        for (int i = 0; i < n; i++) {
            int h = bfs(adj, i, n, minHeight);
            if (h > minHeight) {
                continue;
            }
            if (h < minHeight) {
                minHeight = h;
                result = new ArrayList<>();
            }
            result.add(i);
        }
        return result;
    }
    private int bfs(List<List<Integer>> adj, int root, int n, int minHeight) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        boolean[] visited = new boolean[n];
        int h = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                for (int next : adj.get(curr)) {
                    if (visited[next]) {
                        continue;
                    }
                    queue.offer(next);

                }
                visited[curr] = true;
            }
            h++;
            // pruning
            if (h > minHeight) {
                return h;
            }
        }
        return h;
    }
}
