// 1066. Campus Bikes II
// DescriptionHintsSubmissionsDiscussSolution
//
// On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.
//
// We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.
//
// The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
//
// Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.
//
//
//
// Example 1:
//
// Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
// Output: 6
// Explanation:
// We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output is 6.
//
// Example 2:
//
// Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
// Output: 4
// Explanation:
// We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2, bike 2 to worker 2 or worker 1. Both assignments lead to sum of the Manhattan distances as 4.
//
//
//
// Note:
//
//     0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
//     All worker and bike locations are distinct.
//     1 <= workers.length <= bikes.length <= 10
//
// Seen this question in a real interview before?
//
//     Difficulty:Medium


class Solution {
    public int assignBikes(int[][] workers, int[][] bikes) {

        // return mytry(workers, bikes);

        return method2(workers, bikes);
    }

    private int method2(int[][] workers, int[][] bikes) {
        // 在 mytry 中只是简单的 pruning， 并没有记录组合得到的值来去重剪枝
        // 所以可以带 memo 传下去计算
        // 记录不同的组合， 因为 bike 只有使用或不使用， 那么就是 0/1 状态， 可以使用 bit 状态压缩
        int n = workers.length;
        int m = bikes.length;
        int[] memo = new int[1 << m];
        Arrays.fill(memo, -1);
        return recursion(workers, 0, bikes, 0, memo);
    }
    private int recursion(int[][] workers, int index, int[][] bikes, int bikeStatus, int[] memo) {
        if (index == workers.length) {
            return 0;
        }
        if (memo[bikeStatus] != -1) {
            return memo[bikeStatus];
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < bikes.length; i++) {
            // 要注意这里不能去比较是否 == 1， 因为只有 i 位 bit 是 1， 其他都是 0， 所以要判断是否是 0
            if ((bikeStatus & (1 << i)) != 0) {
                continue;
            }
            bikeStatus |= (1 << i);
            res = Math.min(
                res,
                getDist(workers[index], bikes[i]) + recursion(workers, index + 1, bikes, bikeStatus, memo)
            );
            bikeStatus &= ~(1 << i);
        }
        return memo[bikeStatus] = res;
    }

    private int mytry(int[][] workers, int[][] bikes) {
        // 这题和 I 不同， I 只要每个能够匹配的距离短， 这里要求总体曼哈顿距离最小， 所以 I 中局部最优的解法不适用在这里
        // 试一下 DFS
        int n = workers.length;
        int m = bikes.length;

        result = Integer.MAX_VALUE; // value is index of bike
        dfs(workers, 0, bikes, 0, new HashSet<>());
        return result;
    }
    private int result;
    private void dfs(int[][] workers, int w, int[][] bikes, int sum, Set<Integer> set) {
        if (w == workers.length) {
            result = Math.min(result, sum);
            return;
        }
        if (sum >= result) {
            return;
        }
        int[] worker = workers[w];
        for (int i = 0; i < bikes.length; i++) {
            if (set.contains(i)) {
                continue;
            }
            set.add(i);
            dfs(workers, w + 1, bikes, sum + getDist(worker, bikes[i]), set);
            set.remove(i);
        }
    }
    private int getDist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
