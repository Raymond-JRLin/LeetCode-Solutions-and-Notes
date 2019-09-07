// 787. Cheapest Flights Within K Stops
// DescriptionHintsSubmissionsDiscussSolution
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
// Note:
//
// The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
// The size of flights will be in range [0, n * (n - 1) / 2].
// The format of each flight will be (src, dst, price).
// The price of each flight will be in the range [1, 10000].
// k is in the range of [0, n - 1].
// There will not be any duplicated flights or self cycles.


class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        // return mytry(n, flights, src, dst, K);

        return method2(n, flights, src, dst, K);
    }

    private int method2(int n, int[][] flights, int src, int des, int k) {
        int[][] prices = new int[n][n];
        for (int[] flight : flights) {
            prices[flight[0]][flight[1]] = flight[2];
        }
        // BFS: because we are allowed to use only K stops to get destination, so we can use BFS to do K rounds at most
        // 思路和 DFS 略有不同： DFS 是一路下去找， 在 K stop 内能到达 destination 的花费全放进 PQ 中， 然后找最小
        // 而BFS是： 用一个 costs 数组来记录到达 i 的最小花费， 然后在 K stop 内， 去走所有能走的路线并更新最小花费， 最后查看 costs[des] 是否有最小值
        int[] costs = new int[n]; // record the min cost to i node
        for (int i = 0; i < n; i++) {
            costs[i] = Integer.MAX_VALUE;
        }
        costs[src] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        while (k >= 0 && !queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                for (int j = 0; j < prices[curr].length; j++) {
                    if (prices[curr][j] != 0) {
                        if (costs[curr] + prices[curr][j] <= costs[j]) {
                            // = 也要放进来， 因为后面可能路线会更 cheap
                            costs[j] = costs[curr] + prices[curr][j];
                            queue.offer(j);
                        }
                    }
                }
            }
            k--;
        }
        if (costs[des] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return costs[des];
        }
    }


    private int mytry(int n, int[][] flights, int src, int des, int k) {
        // if use Map<from, List<tos>> and Map<Pair<from, to>, price> would be very annoying. So we have n - # flight, so we can use 2D array to record [from][to] = price
        int[][] prices = new int[n][n];
        for (int[] flight : flights) {
            prices[flight[0]][flight[1]] = flight[2];
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        dfs(n, prices, src, des, k, pq, 0, 0);

        if (pq.isEmpty()) {
            return -1;
        } else {
            return pq.poll();
        }
    }
    private void dfs(int n, int[][] prices, int src, int des, int k, PriorityQueue<Integer> pq, int stop, int sum) {
        if (stop > k) {
            return;
        }
        for (int i = 0; i < prices[src].length; i++) {
            if (prices[src][i] != 0) {
                if (i == des) {
                    pq.offer(prices[src][i] + sum);
                } else {
                    if (!pq.isEmpty() && sum >= pq.peek()) {
                        // pruning, otherwise TLE
                        break;
                    }
                    dfs(n, prices, i, des, k, pq, stop + 1, sum + prices[src][i]);
                }
            }
        }
    }
}
