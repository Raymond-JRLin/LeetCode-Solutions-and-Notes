// 120. Triangle
// DescriptionHintsSubmissionsDiscussSolution
// Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
//
// For example, given the following triangle
//
// [
//      [2],
//     [3,4],
//    [6,5,7],
//   [4,1,8,3]
// ]
// The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
//
// Note:
//
// Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.


class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        // return mytry(triangle);

        // return method2(triangle);

        // return method3(triangle);

        // return method4(triangle);

        return method5(triangle);
    }

    private int method5(List<List<Integer>> triangle) {
        // optimized 1D top-down DP
        int n = triangle.size();
        // // definition: f[j] = the min path sum at point [j] of current layer
        int[] f = new int[n];
        // initialization
        f[0] = triangle.get(0).get(0); // the 1st layer
        // DP
        for (int i = 1; i < triangle.size(); i++) {
            // 这里需要注意： 我们用到了 j - 1 的值， 所以需要倒过来求， 以免更新了 j - 1， 然后在同一层下一个 j 用了最新更新的这一行的值
            for (int j = triangle.get(i).size() - 1; j >= 0; j--) {
                if (j == 0) {
                    f[j] = f[j] + triangle.get(i).get(j);
                } else if (j == triangle.get(i).size() - 1) {
                    f[j] = f[j - 1] + triangle.get(i).get(j);
                } else {
                    // left up and up one, be careful we should use j and j - 1
                    f[j] = triangle.get(i).get(j) + Math.min(f[j], f[j - 1]);
                }
            }
        }
        // result
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            result = Math.min(result, f[j]);
        }
        return result;
    }

    private int method4(List<List<Integer>> triangle) {
        // 2D top-down DP
        int n = triangle.size();
        // definition: f[i][j] = the min path sum at point [i, j]
        int[][] f = new int[n][n];
        // initialization
        f[0][0] = triangle.get(0).get(0); // the 1st layer
        // DP
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j == 0) {
                    f[i][j] = f[i - 1][j] + triangle.get(i).get(j);
                } else if (j == triangle.get(i).size() - 1) {
                    f[i][j] = f[i - 1][j - 1] + triangle.get(i).get(j);
                } else {
                    // left up and up one, be careful we should use j and j - 1
                    f[i][j] = triangle.get(i).get(j) + Math.min(f[i - 1][j], f[i - 1][j - 1]);
                }
            }
        }
        // result: we cannot update with our DP process, since we may get a smaller result in non-last layer
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            result = Math.min(result, f[n - 1][j]);
        }
        return result;
    }

    private int method3(List<List<Integer>> triangle) {
        // optimized 1D bottom-up DP
        int n = triangle.size();
        // // definition: f[j] = the min path sum at point [j] of current layer
        int[] f = new int[n];
        // initialization
        for (int j = 0; j < n; j++) {
            f[j] = triangle.get(n - 1).get(j);
        }
        // DP
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                f[j] = triangle.get(i).get(j) + Math.min(f[j], f[j + 1]);
            }
        }
        // result
        return f[0];
    }

    private int method2(List<List<Integer>> triangle) {
        // 2D bottom-up DP
        int n = triangle.size();
        // definition: f[i][j] = the min path sum at point [i, j]
        int[][] f = new int[n][n];
        // initialization
        for (int j = 0; j < n; j++) {
            f[n - 1][j] = triangle.get(n - 1).get(j);
        }
        // DP
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                f[i][j] = triangle.get(i).get(j) + Math.min(f[i + 1][j], f[i + 1][j + 1]);
            }
        }
        // result
        return f[0][0];
    }

    private int mytry(List<List<Integer>> triangle) {
        // recursion
        int n = triangle.size();
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return recursion(memo, triangle, 0, 0);
    }
    private int recursion(int[][] memo, List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size() - 1) {
            return memo[i][j] = triangle.get(i).get(j);
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int left = recursion(memo, triangle, i + 1, j);
        int right = recursion(memo, triangle, i + 1, j + 1);
        return memo[i][j] = triangle.get(i).get(j) + Math.min(left, right);
    }
}
