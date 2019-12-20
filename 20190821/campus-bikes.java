// 1057. Campus Bikes
// DescriptionHintsSubmissionsDiscussSolution
//
// On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.
//
// Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.
//
// The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
//
// Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.
//
//
//
// Example 1:
//
// Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
// Output: [1,0]
// Explanation:
// Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
//
// Example 2:
//
// Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
// Output: [0,2,1]
// Explanation:
// Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
//
//
//
// Note:
//
//     0 <= workers[i][j], bikes[i][j] < 1000
//     All worker and bike locations are distinct.
//     1 <= workers.length <= bikes.length <= 1000
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {

        // return mytry(workers, bikes);

        return method2(workers, bikes);
    }

    private int[] method2(int[][] workers, int[][] bikes) {
        // bucket sort
        // O(N * M + 2000) time and O(2000) space
        int n = workers.length;
        int m = bikes.length;

        List<Pair>[] bucket = new List[2000];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Pair 没有 comparable 也没关系， 因为这里 i/j 是有顺序的加入到 list 中
                Pair pair = new Pair(getDist(workers[i], bikes[j]), i, j);
                if (bucket[pair.dist] == null) {
                    bucket[pair.dist] = new ArrayList<>();
                }
                bucket[pair.dist].add(pair);
            }
        }

        int[] result = new int[n]; // value is index of bike
        Arrays.fill(result, -1);
        Set<Integer> set = new HashSet(); // <bike index>
        for (int i = 0; i < bucket.length; i++) {
            if (set.size() == n) {
                break;
            }
            if (bucket[i] == null) {
                continue;
            }
            for (Pair curr : bucket[i]) {
                if (result[curr.workerIndex] != -1) {
                    continue;
                }
                if (set.contains(curr.bikeIndex)) {
                    continue;
                }
                result[curr.workerIndex] = curr.bikeIndex;
                set.add(curr.bikeIndex);
            }
        }
        return result;
    }

    private int[] mytry(int[][] workers, int[][] bikes) {
        // N <= M 会有一点点麻烦， 因为不好去判断 == 的时候和小于的时候分别要怎么做
        // 所以就直接把所有的 pair 全放进去， 然后按规则取
        // O(N * M * log(NM)) time and O(N * M) space
        int n = workers.length;
        int m = bikes.length;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pq.offer(new Pair(getDist(workers[i], bikes[j]), i, j));
            }
        }
        int[] result = new int[n]; // value is index of bike
        Arrays.fill(result, -1);
        Set<Integer> set = new HashSet(); // <bike index>
        while (!pq.isEmpty() && set.size() < m) {
            Pair curr = pq.poll();
            if (result[curr.workerIndex] != -1) {
                continue;
            }
            if (set.contains(curr.bikeIndex)) {
                continue;
            }
            result[curr.workerIndex] = curr.bikeIndex;
            set.add(curr.bikeIndex);
        }
        return result;
    }
    private int getDist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
    private class Pair implements Comparable<Pair> {
        int dist;
        int workerIndex;
        int bikeIndex;

        Pair(int d, int w, int b) {
            this.dist = d;
            this.workerIndex = w;
            this.bikeIndex = b;
        }

        @Override
        public int compareTo(Pair o2) {
            if (this.dist != o2.dist) {
                return Integer.compare(this.dist, o2.dist);
            }
            if (this.workerIndex != o2.workerIndex) {
                return Integer.compare(this.workerIndex, o2.workerIndex);
            }
            return Integer.compare(this.bikeIndex, o2.bikeIndex);
        }
    }
}
