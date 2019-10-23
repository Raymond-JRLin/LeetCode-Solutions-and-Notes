// 787. Cheapest Flights Within K Stops
// DescriptionHintsSubmissionsDiscussSolution
//
// There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.
//
// Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
//
// Example 1:
// Input:
// n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
// src = 0, dst = 2, k = 1
// Output: 200
// Explanation:
// The graph looks like this:
//
//
// The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
//
// Example 2:
// Input:
// n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
// src = 0, dst = 2, k = 0
// Output: 500
// Explanation:
// The graph looks like this:
//
//
// The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
//
// Note:
//
//     The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
//     The size of flights will be in range [0, n * (n - 1) / 2].
//     The format of each flight will be (src, dst, price).
//     The price of each flight will be in the range [1, 10000].
//     k is in the range of [0, n - 1].
//     There will not be any duplicated flights or self cycles.
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (flights == null || flights.length == 0) {
            return -1;
        }

        // return method1(n, flights, src, dst, K);

        return method2(n, flights, src, dst, K);
    }

    // 这里 BFS 会有点 tricky， 不好做， 因为类似于 word search， 一个 stop 可以由几个不同的过来， 并不是只访问一遍， 这样后 poll 出来的会使用同一轮先 poll 出来的更小的值

    private int method2(int n, int[][] flights, int src, int dst, int k) {
        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[src] = 0;
        for (int i = 0; i <= k; i++) {
            // 同样地， 为了不在同一轮更新错误， 用另一个 temp[] 先存一下
            int[] temp = Arrays.copyOf(f, n);
            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                int cost = flight[2];
                if (f[from] != Integer.MAX_VALUE) {
                    temp[to] = Math.min(temp[to], f[from] + cost);
                }
            }
            f = temp;
        }
        return f[dst] == Integer.MAX_VALUE ? -1 : f[dst];
    }

    private int method1(int n, int[][] flights, int src, int dst, int k) {
        // 带权重的有向图的最短路径, 这里短意味着花费的权重最少
        // build adjacent list
        int[][] adj = new int[n][n];
        for (int[] flight : flights) {
            adj[flight[0]][flight[1]] = flight[2];
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // <min cost>
        dfs(dst, k, adj, pq, src, 0);

        return pq.isEmpty() ? -1 : pq.peek();
    }
    private void dfs(int dst, int k, int[][] adj, PriorityQueue<Integer> pq, int from, int cost) {
        // k 表示的是中间经停的 stop， 不包括 src and dst
        // 所以如果经过 k stop 到达 dst 的时候， k 一定 < 0， 所以先判定一下
        if (from == dst) {
            pq.offer(cost);
            return;
        }
        // 经过了 k stop 的下一个， 不是 dst
        if (k < 0) {
            return;
        }
        // pruning: cost 已经超过了能够到达的 min cost， 也没必要往下走了
        if (!pq.isEmpty() && cost > pq.peek()) {
            return;
        }
        for (int i = 0; i < adj[from].length; i++) {
            if (adj[from][i] == 0) {
                continue;
            }
            dfs(dst, k - 1, adj, pq, i, cost + adj[from][i]);
        }
    }
}
