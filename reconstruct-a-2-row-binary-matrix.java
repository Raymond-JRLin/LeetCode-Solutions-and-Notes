// 1253. Reconstruct a 2-Row Binary Matrix
//
//     User Accepted: 2029
//     User Tried: 2364
//     Total Accepted: 2081
//     Total Submissions: 6207
//     Difficulty: Medium
//
// Given the following details of a matrix with n columns and 2 rows :
//
//     The matrix is a binary matrix, which means each element in the matrix can be 0 or 1.
//     The sum of elements of the 0-th(upper) row is given as upper.
//     The sum of elements of the 1-st(lower) row is given as lower.
//     The sum of elements in the i-th column(0-indexed) is colsum[i], where colsum is given as an integer array with length n.
//
// Your task is to reconstruct the matrix with upper, lower and colsum.
//
// Return it as a 2-D integer array.
//
// If there are more than one valid solution, any of them will be accepted.
//
// If no valid solution exists, return an empty 2-D array.
//
//
//
// Example 1:
//
// Input: upper = 2, lower = 1, colsum = [1,1,1]
// Output: [[1,1,0],[0,0,1]]
// Explanation: [[1,0,1],[0,1,0]], and [[0,1,1],[1,0,0]] are also correct answers.
//
// Example 2:
//
// Input: upper = 2, lower = 3, colsum = [2,2,1,1]
// Output: []
//
// Example 3:
//
// Input: upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
// Output: [[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
//
//
//
// Constraints:
//
//     1 <= colsum.length <= 10^5
//     0 <= upper, lower <= colsum.length
//     0 <= colsum[i] <= 2
//


class Solution {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {

        return mytry(upper, lower, colsum);
    }

    private List<List<Integer>> mytry(int upper, int lower, int[] colsum) {
        // 我的想法是只有 0 和 1
        // 那么 colsum 是 0 的就是两个都是 0， colsum 是 2 的就是两个都是 1
        // 然后把剩下的拿去填就好了
        int m = colsum.length;
        int[][] arr = new int[2][m];
        for (int[] a : arr) {
            Arrays.fill(a, -1);
        }
        List<Integer> cols = new ArrayList<>();
        for (int j = 0; j < m; j++) {
            if (colsum[j] == 0) {
                arr[0][j] = 0;
                arr[1][j] = 0;
            } else if (colsum[j] == 2) {
                arr[0][j] = 1;
                upper--;
                arr[1][j] = 1;
                lower--;
            } else {
                cols.add(j);
            }
        }
        // invalid， upper 或者 lower 不够
        if (upper < 0 || lower < 0) {
            return Collections.emptyList();
        }
        for (int i = 0; i < cols.size(); i++) {
            int j = cols.get(i);
            if (upper > 0) {
                arr[0][j] = 1;
                arr[1][j] = 0;
                upper--;
            } else if (lower > 0) {
                arr[0][j] = 0;
                arr[1][j] = 1;
                lower--;
            } else {
                return Collections.emptyList();
            }
        }
        // 还有剩
        if (upper > 0 || lower > 0) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<Integer> list = new ArrayList<>();
            for (int num : arr[i]) {
                list.add(num);
            }
            result.add(list);
        }
        return result;
    }
}
