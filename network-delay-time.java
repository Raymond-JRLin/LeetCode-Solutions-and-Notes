// 743. Network Delay Time
// DescriptionHintsSubmissionsDiscussSolution
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
//
//
// Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
// Output: 2
//
//
// Note:
//
// N will be in the range [1, 100].
// K will be in the range [1, N].
// The length of times will be in the range [1, 6000].
// All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
// Seen this question in a real interview before?
// Difficulty:Medium


class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        if (times == null || times.length == 0 || times[0].length == 0) {
            return -1;
        }

        // incorrect
        // return my_try(times, N, K);

        // Dijkstra's algorithm
        return method(times, N, K);
    }

    private int method(int[][] times, int N, int K) {
        int[][] grid = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                grid[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int[] time : times) {
            grid[time[0]][time[1]] = time[2]; // i -> j: cost
        }
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return distance[o1] - distance[o2];
            }
        });
        pq.offer(K);
        distance[K] = 0;
        while (!pq.isEmpty()) {
            int curr = pq.poll();
            for (int i = 1; i < N + 1; i++) {
                int cost = grid[curr][i];
                if (cost != Integer.MAX_VALUE && distance[i] > distance[curr] + cost) {
                    distance[i] = distance[curr] + cost;
                    pq.offer(i);
                }
            }
        }
        int result = -1;
        for (int i = 1; i < N + 1; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                return -1;
            }
            result = Math.max(result, distance[i]);
        }
        return result;
    }

//     private int my_try(int[][] times, int N, int K) {

//         // use BFS
//         boolean[] visited = new boolean[N + 1];
//         int mark = N;
//         int sum = 0;
//         Queue<Integer> queue = new LinkedList<>();
//         Map<Integer, List<int[]>> map = getDirections(times);
//         if (!map.containsKey(K)) {
//             return -1;
//         }
//         queue.offer(K);
//         visited[K] = true;
//         N--;
//         while (!queue.isEmpty()) {
//             int curr = queue.poll();
//             if (!map.containsKey(curr)) {
//                 continue;
//             }
//             List<int[]> trans = map.get(curr);
//             for (int[] tran : trans) {
//                 int tar = tran[0];
//                 if (visited[tar]) {
//                     continue;
//                 }
//                 int cost = tran[1];
//                 queue.offer(tar);
//                 N--;
//                 visited[tar] = true;
//                 sum += cost;
//             }
//         }
//         if (N != 0) {
//             return -1;
//         } else {
//             return sum;
//         }
//     }
//     private Map<Integer, List<int[]>> getDirections(int[][] times) {
//         Map<Integer, List<int[]>> map = new HashMap<>();
//         for (int[] time : times) {
//             int s = time[0];
//             int t = time[1];
//             int cost = time[2];
//             if (map.containsKey(s)) {
//                 List<int[]> list = map.get(s);
//                 int[] array = new int[]{t, cost};
//                 list.add(array);
//                 map.put(s, list);
//             } else {
//                 List<int[]> list = new ArrayList<>();
//                 int[] array = new int[]{t, cost};
//                 list.add(array);
//                 map.put(s, list);
//             }
//         }
//         return map;
//     }
}
