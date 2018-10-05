// 363. Max Sum of Rectangle No Larger Than K
// DescriptionHintsSubmissionsDiscussSolution
// Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.
//
// Example:
//
// Input: matrix = [[1,0,1],[0,-2,3]], k = 2
// Output: 2
// Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
//              and 2 is the max number no larger than k (k = 2).
// Note:
//
// The rectangle inside the matrix must have an area > 0.
// What if the number of rows is much larger than the number of columns?


class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        // return method1(matrix, k);

        return method2(matrix, k);
    }

    private int method2(int[][] matrix, int k) {
        // maximum subarray sum & TreeSet， 按照列来取范围， 当前范围内找 max subarray sum, TreeSet O(NlogN) 来找小于 K 的最大值
        // 我这里是假设 row is much larger then col, 所以取列来做 subarray, O[m ^ 2 * n * log(n)], space O(n)
        // ref: https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/discuss/83599/Accepted-C++-codes-with-explanation-and-references
        // ref https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/discuss/83618/2-Accepted-Java-Solution
        int n = matrix.length;
        int m = matrix[0].length;
        int result = Integer.MIN_VALUE;
        for (int left = 0; left < m; left++) {
            // 截取 col 范围
            int[] col = new int[n];
            for (int right = left; right < m; right++) {
                // 一列一列加
                for (int i = 0; i < n; i++) {
                    col[i] += matrix[i][right];
                }
                // System.out.println("now check " + right + " column");
                // 在这一列中找 maximum subarray sum
                int subMaxSum = getSubSum(col, k);
                // 返回的已经是当前这一 col 比 k 小的最大的了， 直接取最大值即可
                result = Math.max(result, subMaxSum);
                // System.out.println("subMax and result are " + subMaxSum + " " + result);
            }
        }
        return result;

    }
    private int getSubSum(int[] nums, int k) {
        // 这里并非找最大， 而是所有的 subSum， 从而得到最接近 k 的那个， 也就是走到当前 prefix 的时候， 看看前面哪个 prev 最接近 prefix - k => TreeSet
        int[] prefix = new int[nums.length + 1];
        TreeSet<Integer> set = new TreeSet<>();
        // 注意事先给个 padding， 因为在下面 for 中， set.add() 是最后执行的， 所以加个 0 的 padding， 这样对于最后一列就不需要在 for 外面再测一次 ceiling 或者遗漏答案
        set.add(0);
        int result = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length + 1; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
            Integer ceiling = set.ceiling(prefix[i] - k);
            if (ceiling != null) {
                result = Math.max(result, prefix[i] - ceiling);
            }
            set.add(prefix[i]);
        }
        return result;
    }

    private int method1(int[][] matrix, int k) {
        // brute force: find area of every rectangle, O(N ^ 4)
        int n = matrix.length;
        int m = matrix[0].length;
        // areas[i][j] = area of [0, 0] to [i, j]
        int[][] areas = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int area = matrix[i][j];
                // 加上方的 row
                if (i - 1 >= 0) {
                    area += areas[i - 1][j];
                }
                // 加左边的 col
                if (j - 1 >= 0) {
                    area += areas[i][j - 1];
                }
                // 减掉重复计算的左上角
                if (i - 1 >= 0 && j - 1 >= 0) {
                    area -= areas[i - 1][j - 1];
                }
                areas[i][j] = area;
            }
        }
        int result = Integer.MIN_VALUE;
        // 去找 [i, j] 到 [right, down] 的 area
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int right = i; right < n; right++) {
                    for (int down = j; down < m; down++) {
                        int area = areas[right][down];
                        // 减掉上方的 row
                        if (i - 1 >= 0) {
                            area -= areas[i - 1][down];
                        }
                        //减掉左边的 col
                        if (j - 1 >= 0) {
                            area -= areas[right][j - 1];
                        }
                        // 补左上角
                        if (i - 1 >= 0 && j - 1 >= 0) {
                            area += areas[i - 1][j - 1];
                        }
                        if (area <= k) {
                            result = Math.max(result, area);
                        }
                    }
                }
            }
        }
        return result;
    }
}
