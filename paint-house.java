// 256. Paint House
// DescriptionHintsSubmissionsDiscussSolution
// There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
//
// The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
//
// Note:
// All costs are positive integers.
//
// Example:
//
// Input: [[17,2,17],[16,16,5],[14,3,19]]
// Output: 10
// Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
//              Minimum cost: 2 + 5 + 3 = 10.


class Solution {
    public int minCost(int[][] costs) {
        if  (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        // return mytry(costs);

        // return mytry2(costs);

        return method2(costs);
    }

    private int method2(int[][] costs) {
        int n = costs.length;
        // definition: f[i][j] = cost of i house with color j
        int globalRed = costs[0][0];
        int globalBlue = costs[0][1];
        int globalGreen = costs[0][2];
        // initialization
        // DP
        for (int i = 1; i < n; i++) {
            int localRed = costs[i][0] + Math.min(globalBlue, globalGreen);
            int localBlue = costs[i][1] + Math.min(globalRed, globalGreen);
            int localGreen = costs[i][2] + Math.min(globalRed, globalBlue);
            globalRed = localRed;
            globalBlue = localBlue;
            globalGreen = localGreen;
        }
        // result
        return Math.min(globalRed, Math.min(globalBlue, globalGreen));
    }

    private int mytry2(int[][] costs) {
        int n = costs.length;
        // definition: f[i][j] = cost of i house with color j
        int[][] f = new int[n][3];
        // initialization
        for (int j = 0; j < 3; j++) {
            f[0][j] = costs[0][j];
        }
        // DP
        for (int i = 1; i < n; i++) {
            f[i][0] = costs[i][0] + Math.min(f[i - 1][1], f[i - 1][2]);
            f[i][1] = costs[i][1] + Math.min(f[i - 1][0], f[i - 1][2]);
            f[i][2] = costs[i][2] + Math.min(f[i - 1][0], f[i - 1][1]);
        }
        // result
        return Math.min(f[n - 1][0], Math.min(f[n - 1][1], f[n - 1][2]));
    }

    private int mytry(int[][] costs) {
        // recursion DP: Wrong, 有个问题是: 剩某个房子的时候, 是有约束条件的, 并不能直接说是最少的是多少
        int n = costs.length;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        recursion(costs, memo, n, n, -1);
        for(int i =0; i < n + 1; i++) {
            System.out.print(memo[i] + " ");
        }
        return memo[n];
    }
    private int recursion(int[][] costs, int[] memo, int n, int i, int color) {

        if (i == 0) {
            return 0;
        }
        // we cannot say already got here, since even though we alreday had a answer, we still need to get smaller one
        // if (memo[i] != Integer.MAX_VALUE) {
        //     System.out.println("already got");
        //     return memo[i];
        // }
        System.out.println("now check "+ i);
        for (int j = 0; j < 3; j++) {
            if (j == color) {
                continue;
            }
            System.out.print(i + " has color: " + j + " , costs is " + costs[n -i][j]);
            memo[i] = Math.min(memo[i], costs[n - i][j] + recursion(costs, memo, n, i - 1, j));
            System.out.println(",memo is " + memo[i]);
        }
        return memo[i];
    }
}
