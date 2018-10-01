// 265. Paint House II
// DescriptionHintsSubmissionsDiscussSolution
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
// Follow up:
// Could you solve it in O(nk) runtime?


class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        return method1(costs);
    }

    private int method1(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;
        int firstMin = 0;
        int secondMin = 0;
        int index = -1;
        for (int i = 0; i < n; i++) {
            int localFirst = Integer.MAX_VALUE;
            int localSecond = Integer.MAX_VALUE;
            int localPos = -1;
            for (int j = 0; j < k; j++) {
                int curr = (j == index ? secondMin : firstMin) + costs[i][j];
                if (curr < localFirst) {
                    localSecond = localFirst;
                    localFirst = curr;
                    localPos = j;
                } else if (curr < localSecond) {
                    localSecond = curr;
                }
            }
            // update global min
            firstMin = localFirst;
            secondMin = localSecond;
            index = localPos;
        }
        return firstMin;
    }
}
