// 265. Paint House II
// DescriptionHintsSubmissionsDiscussSolution
//
// There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
//
// The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
//
// Note:
// All costs are positive integers.
//
// Example:
//
// Input: [[1,5,3],[2,9,4]]
// Output: 5
// Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
//              Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
//
// Follow up:
// Could you solve it in O(nk) runtime?
// Seen this question in a real interview before?
//
//     Difficulty:Hard


class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }

        return method1(costs);
    }

    private int method1(int[][] costs) {
        int prev = -1; // prev pointer to index of firstMinSum
        int firstMinSum = 0, secondMinSum = 0;
        for (int[] cost : costs) {
            int curr = -1; // current pointer to index of 1st min in current row
            int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
            // 不能去贪每个 row 的最小， 这样只是局部最优， 而非全局最优
            // 应该要带上前面得到的结果， 一路走过来看哪个最小
            for (int i = 0; i < cost.length; i++) {
                int value = (i != prev ? firstMinSum : secondMinSum) + cost[i];
                if (value < min1) {
                    min2 = min1;
                    min1 = value;
                    curr = i;
                } else if (value < min2) {
                    // 不用去记录第二小的 index， 因为我们永远取一路走来最小的
                    min2 = value;
                }
            }
            firstMinSum = min1;
            secondMinSum = min2;
            prev = curr;
        }
        return firstMinSum;
    }
}
