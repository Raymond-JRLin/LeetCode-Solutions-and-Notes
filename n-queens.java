// 51. N-Queens
// DescriptionHintsSubmissionsDiscussSolution
// The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
//
//
//
// Given an integer n, return all distinct solutions to the n-queens puzzle.
//
// Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
//
// Example:
//
// Input: 4
// Output: [
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
// Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.


class Solution {
    public List<List<String>> solveNQueens(int n) {
        if (n < 1) {
            Collections.emptyList();
        }

        return mytry(n);
    }

    private List<List<String>> mytry(int n) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(n, result, new ArrayList<>());
        return draw(result, n);
    }
    private void dfs(int n, List<List<Integer>> result, List<Integer> list) {
        if (list.size() == n) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!isValid(list, i)) {
                continue;
            }
            list.add(i);
            dfs(n, result, list);
            list.remove(list.size() - 1);
        }
    }
    private boolean isValid(List<Integer> list, int col) {
        int row = list.size();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == col || list.get(i) + i == row + col || list.get(i) - i == col - row) {
                return false;
            }
        }
        return true;
    }
    private List<List<String>> draw(List<List<Integer>> result, int n) {
        List<List<String>> chess = new ArrayList<>();
        for (List<Integer> list : result) {
            List<String> board = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb.append(j == list.get(i) ? "Q" : ".");
                }
                board.add(sb.toString());
            }
            chess.add(board);
        }
        return chess;
    }
}
