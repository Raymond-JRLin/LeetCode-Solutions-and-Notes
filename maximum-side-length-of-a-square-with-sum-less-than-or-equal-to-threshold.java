// 1292. Maximum Side Length of a Square with Sum Less than or Equal to Threshold
//
//     User Accepted: 1056
//     User Tried: 1633
//     Total Accepted: 1068
//     Total Submissions: 3374
//     Difficulty: Medium
//
// Given a m x n matrix mat and an integer threshold. Return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.
//
//
//
// Example 1:
//
// Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
// Output: 2
// Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
//
// Example 2:
//
// Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
// Output: 0
//
// Example 3:
//
// Input: mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
// Output: 3
//
// Example 4:
//
// Input: mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
// Output: 2
//
//
//
// Constraints:
//
//     1 <= m, n <= 300
//     m == mat.length
//     n == mat[i].length
//     0 <= mat[i][j] <= 10000
//     0 <= threshold <= 10^5
//


class Solution {
    public int maxSideLength(int[][] mat, int threshold) {

        // return method1(mat, threshold);

        return method2(mat, threshold);
    }

    private int method2(int[][] mat, int thr) {
        int n = mat.length;
        int m = mat[0].length;
        // 类似 method1， 也是用 prefix sum， 但是是另一种计算方式
        int[][] prefix = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                prefix[i][j] = mat[i - 1][j - 1] + prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
            }
        }

        // 然后在求 max len 的时候， 用 BS
        int start = 0;
        int end = Math.min(n, m);
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isValid(prefix, n + 1, m + 1, mid, thr)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        if (isValid(prefix, n + 1, m + 1, end, thr)) {
            return end;
        } else if (isValid(prefix, n + 1, m + 1, start, thr)) {
            return start;
        } else {
            return 0;
        }
    }
    private boolean isValid(int[][] prefix, int n, int m, int len, int thr) {
        for (int i = len; i < n; i++) {
            for (int j = len; j < m; j++) {
                if (prefix[i][j] - prefix[i - len][j] - prefix[i][j - len] + prefix[i - len][j - len] <= thr) {
                    return true;
                }
            }
        }
        return false;
    }

    private int method1(int[][] mat, int thr) {
        int n = mat.length;
        int m = mat[0].length;
        // contest 时候 prefix sum 搞错了， 一定要记住既然是叫 prefix sum， 也就是从一开始一直到当下的 sum
        // 那么 2D 的 prefix sum 也就是从 [0, 0] 到 [i, j] 的总和, 一行一行不断加过来
        int[][] prefix = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++) {
            int sum = 0;
            for (int j = 1; j < m + 1; j++) {
                sum += mat[i - 1][j - 1];
                prefix[i][j] = sum + prefix[i - 1][j]; // 加上上一行的
            }
        }

        int len = Math.min(n, m);
        for (int k = len; k > 0; k--) {
            for (int i = 1; i < n + 1 - k; i++) {
                for (int j = 1; j < m + 1 - k; j++) {
                    int curr = prefix[i + k][j + k] - prefix[i - 1][j + k] - prefix[i + k][j - 1] + prefix[i - 1][j - 1];
                    if (curr <= thr) {
                        return k + 1;
                    }
                }
            }
        }
        return 0;
    }

    /*
    private int mytry(int[][] mat, int thr) {
        // wrong
        int n = mat.length;
        int m = mat[0].length;
        int[][] prefix = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++) {
            prefix[i][1] = prefix[i - 1][1] + mat[i - 1][0];
        }
        for (int j = 1; j < m + 1; j++) {
            prefix[1][j] = prefix[1][j - 1] + mat[0][j - 1];
        }
        for (int i = 2; i < n + 1; i++) {
            for (int j = 2; j < m + 1; j++) {
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
            }
        }
        int len = Math.min(n, m);
        int result = 0;
        while (len > 0) {
            for (int i = 0; i < n + 1 - len; i++) {
                for (int j = 0; j < m + 1 - len; j++) {
                    int curr = prefix[i + len][j + len] - prefix[i][j];
                    if (curr > thr) {
                        continue;
                    }
                    result = Math.max(result, len);
                }
            }
            len--;
        }
        return result;
    }
    */
}
