// 52. N-Queens II
// DescriptionHintsSubmissionsDiscussSolution
// The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
//
//
//
// Given an integer n, return the number of distinct solutions to the n-queens puzzle.
//
// Example:
//
// Input: 4
// Output: 2
// Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
// [
//  [".Q..",  // Solution 1
//   "...Q",
//   "Q...",
//   "..Q."],
//
//  ["..Q.",  // Solution 2
//   "Q...",
//   "...Q",
//   ".Q.."]
// ]


class Solution {
    public int totalNQueens(int n) {
        if (n < 1) {
            return 0;
        }

        return mytry(n);
    }

    private int mytry(int n) {
        dfs(n, new ArrayList<>());
        return count;
    }
    private int count = 0;
    private void dfs(int n, List<Integer> list) {
        // use list to record the col numebr in each row
        if (list.size() == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!isValid(list, i)) {
                continue;
            }
            list.add(i);
            dfs(n, list);
            list.remove(list.size() - 1);
        }
    }
    private boolean isValid(List<Integer> list, int col) {
        int row = list.size();
        for (int i = 0; i < list.size(); i++) {
            // attention the order (+/-) of determination
            if (list.get(i) == col || list.get(i) + i == row + col || list.get(i) - i == col - row) {
                return false;
            }
        }
        return true;
    }
}
