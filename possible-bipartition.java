// 886. Possible Bipartition
// User Accepted: 595
// User Tried: 1114
// Total Accepted: 597
// Total Submissions: 2379
// Difficulty: Medium
// Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
//
// Each person may dislike some other people, and they should not go into the same group.
//
// Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
//
// Return true if and only if it is possible to split everyone into two groups in this way.
//
//
//
// Example 1:
//
// Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
// Output: true
// Explanation: group1 [1,4], group2 [2,3]
// Example 2:
//
// Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
// Output: false
// Example 3:
//
// Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
// Output: false
//
//
// Note:
//
// 1 <= N <= 2000
// 0 <= dislikes.length <= 10000
// 1 <= dislikes[i][j] <= N
// dislikes[i][0] < dislikes[i][1]
// There does not exist i != j for which dislikes[i] == dislikes[j].


class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        if (N == 0 || dislikes == null || dislikes.length == 0 || dislikes[0].length == 0) {
            return true;
        }

        return mytry(N, dislikes);

        // return method2(N, dislikes);
    }

    private boolean method2(int n, int[][] dis) {
        // DFS
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < dis.length; i++) {
            adj.get(dis[i][0]).add(dis[i][1]);
            adj.get(dis[i][1]).add(dis[i][0]);
        }

        int[] colors = new int[n + 1];
        Arrays.fill(colors, -1); // not colored yet
        // color: 0 and 1, so we can use 1 - color to get opposite color
        for (int i = 1; i < n + 1; i++) {
            if (colors[i] == -1 && !dfs(adj, colors, i, 0)) {
                return false;
            }
        }
        return true;
    }
    private boolean dfs(List<List<Integer>> adj, int[] colors, int root, int color) {
        if (colors[root] != -1) {
            // if it's colored
            return colors[root] == color;
        }
        // color this pos
        colors[root] = color;
        for (int next : adj.get(root)) {
            if (!dfs(adj, colors, next, 1 - color)) {
                return false;
            }
        }
        return true;
    }

    private boolean mytry(int n, int[][] dis) {
        // flooding to 2 colors
        // BFS
        int m = dis.length;

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            adj.get(dis[i][0]).add(dis[i][1]);
            adj.get(dis[i][1]).add(dis[i][0]);
        }

        int[] visited = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                if (!bfs(adj, visited, i)) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean bfs(List<List<Integer>> adj, int[] visited, int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : adj.get(curr)) {
                if (visited[next] == visited[curr]) {
                    // collision
                    return false;
                }
                if (visited[next] == -visited[curr]) {
                    // already colored
                    continue;
                }
                // not colored yet
                visited[next] = -(visited[curr]);
                queue.offer(next);
            }
        }
        return true;
    }
}
