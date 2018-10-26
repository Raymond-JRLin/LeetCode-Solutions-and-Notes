// 323. Number of Connected Components in an Undirected Graph
// DescriptionHintsSubmissionsDiscussSolution
// Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
//
// Example 1:
//
// Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
//
//      0          3
//      |          |
//      1 --- 2    4
//
// Output: 2
// Example 2:
//
// Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
//
//      0           4
//      |           |
//      1 --- 2 --- 3
//
// Output:  1
// Note:
// You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.


class Solution {
    public int countComponents(int n, int[][] edges) {


        // return mytry(n, edges);

        // return mytry2(n, edges);

        return mytry3(n, edges);
    }

    private int mytry3(int n, int[][] edges) {
        // DFS
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        // flooding
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            dfs(adj, visited, i);
            count++;
        }
        return count;
    }
    private void dfs(List<List<Integer>> adj, boolean[] visited, int curr) {
        if (visited[curr]) {
            return;
        }
        visited[curr] = true;
        for (int next : adj.get(curr)) {
            dfs(adj, visited, next);
        }
    }

    private int mytry2(int n, int[][] edges) {
        // BFS
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        // flooding
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            bfs(adj, visited, i);
            count++;
        }
        return count;
    }
    private void bfs(List<List<Integer>> adj, boolean[] visited, int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        visited[root] = true;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : adj.get(curr)) {
                if (visited[next]) {
                    continue;
                }
                queue.offer(next);
                visited[next] = true;
            }
        }
    }

    private int mytry(int n, int[][] edges) {
        // union find
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.connect(edge[0], edge[1]);
        }
        return uf.count;
    }
    private class UnionFind {
        private int[] nums;
        private int count;
        public UnionFind(int n) {
            this.count = n;
            this.nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i;
            }
        }
        public int find(int i) {
            while (i != nums[i]) {
                nums[i] = nums[nums[i]];
                i = nums[i];
            }
            return i;
        }
        public void connect(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                nums[rootA] = rootB;
                count--;
            }
        }
    }
}
