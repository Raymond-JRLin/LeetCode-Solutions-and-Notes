// 1252. Cells with Odd Values in a Matrix
//
//     User Accepted: 2823
//     User Tried: 2910
//     Total Accepted: 2876
//     Total Submissions: 3592
//     Difficulty: Easy
//
// Given n and m which are the dimensions of a matrix initialized by zeros and given an array indices where indices[i] = [ri, ci]. For each pair of [ri, ci] you have to increment all cells in row ri and column ci by 1.
//
// Return the number of cells with odd values in the matrix after applying the increment to all indices.
//
//
//
// Example 1:
//
// Input: n = 2, m = 3, indices = [[0,1],[1,1]]
// Output: 6
// Explanation: Initial matrix = [[0,0,0],[0,0,0]].
// After applying first increment it becomes [[1,2,1],[0,1,0]].
// The final matrix will be [[1,3,1],[1,3,1]] which contains 6 odd numbers.
//
// Example 2:
//
// Input: n = 2, m = 2, indices = [[1,1],[0,0]]
// Output: 0
// Explanation: Final matrix = [[2,2],[2,2]]. There is no odd number in the final matrix.
//
//
//
// Constraints:
//
//     1 <= n <= 50
//     1 <= m <= 50
//     1 <= indices.length <= 100
//     0 <= indices[i][0] < n
//     0 <= indices[i][1] < m
//


class Solution {
    public int oddCells(int n, int m, int[][] indices) {

        // return mytry(n, m, indices);

        return method2(n, m, indices);
    }

    private int method2(int n, int m, int[][] indices) {
        // 巧妙一点， 最后不去 loop 整个 row 和 col， 分别得到 row/col 中 odd 的来计算
        // O(N + M + K) time complexity, where K is the number of index in indices[]
        // O(N + M) space complexity
        boolean[] row = new boolean[n]; // true 为 odd
        boolean[] col = new boolean[m];
        for (int[] index : indices) {
            row[index[0]] ^= true;
            col[index[1]] ^= true;
        }
        int oddRow = 0;
        int oddCol = 0;
        for (boolean r : row) {
            oddRow += r ? 1 : 0;
        }
        for (boolean c : col) {
            oddCol += c ? 1 : 0;
        }
        // 有 oddCol 个 column 是奇数， 乘以总 row 数 n， 就是假设这些个 column 全是， 然后减去那些 row 和 col 都是奇数个的 cell
        // row 同理
        // return (n * oddCol - oddRow * oddCol) + (m * oddRow - oddRow * oddCol);
        // 或者 (奇数的 row 乘以非奇数的 col) + (奇数的 col 乘以非奇数的 row)
        return (n - oddRow) * oddCol + (m - oddCol) * oddRow;
    }

    private int mytry(int n, int m, int[][] indices) {
        // O(N * M + K) time complexity, where K is the number of index in indices[]
        // O(N + M) space complexity
        int[] row = new int[n];
        int[] col = new int[m];
        for (int[] index : indices) {
            row[index[0]]++;
            col[index[1]]++;
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int sum = row[i] + col[j];
                if (sum % 2 == 1) {
                    result++;
                }
            }
        }
        return result;
    }
}
