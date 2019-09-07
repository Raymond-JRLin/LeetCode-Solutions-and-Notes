// 834. Sum of Distances in Tree
// DescriptionHintsSubmissionsDiscussSolution
// An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.
//
// The ith edge connects nodes edges[i][0] and edges[i][1] together.
//
// Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.
//
// Example 1:
//
// Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
// Output: [8,12,6,10,10,10]
// Explanation:
// Here is a diagram of the given tree:
//   0
//  / \
// 1   2
//    /|\
//   3 4 5
// We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
// equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.
// Note: 1 <= N <= 10000
//
// Seen this question in a real interview before?
// Difficulty:Hard


class Solution {
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        if (edges == null || edges.length == 0 || edges[0].length == 0) {
            return new int[]{0};
        }

        // return mytry(N, edges);

        return method1(N, edges);
    }

    private int[] method1(int n, int[][] edges) {
        // 2 recursive traversal of tree
        // ref: https://leetcode.com/problems/sum-of-distances-in-tree/discuss/130583/C++JavaPython-Pre-order-and-Post-order-DFS-O(N)
        // construct adjacent list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        //
        int[] count = new int[n];
        int[] dis = new int[n];
        // start with root of 0, update count[] and get distant[] at each root(which has subtrees, i.e. 0 and 2 in example)
        dfs(0, adj, count, dis, new boolean[n]);

        recursion(n, 0, adj, count, dis, new boolean[n]);

        return dis;
    }
    private void dfs(int root, List<List<Integer>> adj, int[] count, int[] dis, boolean[] visited) {
        visited[root] = true;
        for (int next : adj.get(root)) {
            if (visited[next]) {
                continue;
            }
            dfs(next, adj, count, dis, visited);
            count[root] += count[next];
            dis[root] += count[next] + dis[next];
        }
        count[root]++;
    }
    private void recursion(int n, int root, List<List<Integer>> adj, int[] count, int[] dis, boolean[] visited) {
        visited[root] = true;
        for (int next : adj.get(root)) {
            if (visited[next]) {
                continue;
            }
            dis[next] = dis[root] - count[next] + (n - count[next]);
            recursion(n, next, adj, count, dis, visited);
        }
    }

    private int[] mytry(int n, int[][] edges) {
        // TLE: brute DFS for every node
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        int[] dis = new int[n];
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            dfs(adj, dis, i, i, 0, visited);
        }
        return dis;
    }
    private void dfs(List<List<Integer>> adj, int[] dis, int root, int curr, int sum, boolean[] visited) {
        if (visited[curr]) {
            dis[root] += sum - 1;
            return;
        }
        visited[curr] = true;
        for (int next : adj.get(curr)) {
            dfs(adj, dis, root, next, sum + 1, visited);
        }
    }
}
