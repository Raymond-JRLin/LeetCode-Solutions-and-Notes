// 261. Graph Valid Tree
// DescriptionHintsSubmissionsDiscussSolution
// Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
//
// Example 1:
//
// Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
// Output: true
// Example 2:
//
// Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
// Output: false
// Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.


class Solution {
    public boolean validTree(int n, int[][] edges) {

        // return mytry(n, edges);

        // return method2(n, edges);

        return method3(n, edges);
    }

    private boolean method3(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            if (uf.find(edge[0]) == uf.find(edge[1])) {
                return false;
            }
            uf.connect(edge[0], edge[1]);
        }
        return uf.count == 1;
    }
    private class UnionFind {
        private int count;
        private int[] nums;
        public UnionFind(int n) {
            count = n;
            nums = new int[n];
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

    private boolean method2(int n, int[][] edges) {
        // get adjacent list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        // 同样也需要 3 个状态， 记录当前查询的 node 以及当前的 parent， 然后在 dfs 中的 loop 内查 next
        if (hasCycle(adj, 0, visited, -1)) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
    private boolean hasCycle(List<List<Integer>> adj, int root, boolean[] visited, int parent) {
        visited[root] = true;
        for (int next : adj.get(root)) {
            if (next != parent && visited[next]) {
                return true;
            }
            if (!visited[next] && hasCycle(adj, next, visited, root)) {
                return true;
            }
        }
        // 这里是要存着访问记录的， 所以不需要回溯变为 false
        return false;
    }

    private boolean mytry(int n, int[][] edges) {
        // BFS
        // get adjacent list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        // 1. 判断是否有环
        // 注意这里并不能用 boolean 型， 因为在哪里设置 visited 都不对。 关键在于我们要在某个 curr 的时候去查它所连接的 node， 查完了所有之后才可以把它设为 visited， 因为 adjList 是双向的， 一定会在倒回去查看的时候为 false， 此时就错了。 所以设置 3 种状态
        int[] visited = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = 1; // 查看当前的 loop 中
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : adj.get(curr)) {
                // 碰到一个 node 在当前 loop 中
                if (next != curr && visited[next] == 1) {
                    return false;
                }
                // 当碰到一个全新没访问过的 node 才把它加入 queue， 并且设置为 1
                if (visited[next] == 0) {
                    queue.offer(next);
                    visited[next] = 1;
                }
            }
            visited[curr] = 2; // 玩全访问过这个 node
        }
        // 2. 是否有 node 没有访问过， 即没有被连接
        for (int v : visited) {
            if (v == 0) {
                return false;
            }
        }
        return true;
    }
}
