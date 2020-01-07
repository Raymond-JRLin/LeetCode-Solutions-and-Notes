// 743. Network Delay Time
// DescriptionHintsSubmissionsDiscussSolution
//
// There are N network nodes, labelled 1 to N.
//
// Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
//
// Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
//
//
//
// Example 1:
//
// Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
// Output: 2
//
//
//
// Note:
//
//     N will be in the range [1, 100].
//     K will be in the range [1, N].
//     The length of times will be in the range [1, 6000].
//     All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {

        // return mytry(times, N, K);

        return method2(times, N, K);

        // return method3(times, N, K);
    }

    private int method3(int[][] times, int n, int k) {
        // Floyd
        // O(N ^ 3) time and O(N ^ 2) space
        int[][] dist = new int[n + 1][n + 1];
        for (int[] d : dist) {
            Arrays.fill(d, 600000);
        }
        for (int i = 1; i <= n; i++) {
            dist[i][i] = 0;
        }
        for (int[] time : times) {
            dist[time[0]][time[1]] = time[2];
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][l] + dist[l][j]);
                }
            }
        }


        int result = 0;
        for (int i = 1; i < n + 1; i++) {
            // System.out.print(dist[i] + " ");
            result = Math.max(result, dist[k][i]);
        }
        return result == 600000 ? -1 : result;
    }

    private int method2(int[][] times, int n, int k) {
        // similar to mytry, just go through times[][] directly without using a queue
        // Bellman-Ford
        // O(N * E) time  and O(N) space
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 600000);
        dist[k] = 0;
        for (int i = 1; i <= n; i++) {
            for (int[] time : times) {
                int from = time[0];
                int to = time[1];
                int t = time[2];
                dist[to] = Math.min(dist[to], dist[from] + t);
            }
        }

        int result = 0;
        for (int i = 1; i < n + 1; i++) {
            // System.out.print(dist[i] + " ");
            result = Math.max(result, dist[i]);
        }
        return result == 600000 ? -1 : result;
    }

    private int mytry(int[][] times, int n, int k) {
        int[][] adj = new int[n + 1][n + 1];
        for (int[] a : adj) {
            Arrays.fill(a, -1);
        }
        for (int[] time : times) {
            adj[time[0]][time[1]] = time[2];
        }
        int[] result = new int[n + 1];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[k] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(k);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                for (int next = 1; next < adj[curr].length; next++) {
                    if (next == curr) {
                        continue;
                    }
                    if (adj[curr][next] == -1) {
                        continue;
                    }
                    if (result[curr] + adj[curr][next] < result[next]) {
                        result[next] = result[curr] + adj[curr][next];
                        queue.offer(next);
                    }
                }
            }
        }
        int t = 0;
        for (int i = 1; i < n + 1; i++) {
            // System.out.print(result[i] + " ");
            t = Math.max(t, result[i]);
        }
        return t == Integer.MAX_VALUE ? -1 : t;
    }
}
