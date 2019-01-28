// 36. Valid Sudoku
// DescriptionHintsSubmissionsDiscussSolution
// Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
//
// Each row must contain the digits 1-9 without repetition.
// Each column must contain the digits 1-9 without repetition.
// Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
//
// A partially filled sudoku which is valid.
//
// The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
//
// Example 1:
//
// Input:
// [
//   ["5","3",".",".","7",".",".",".","."],
//   ["6",".",".","1","9","5",".",".","."],
//   [".","9","8",".",".",".",".","6","."],
//   ["8",".",".",".","6",".",".",".","3"],
//   ["4",".",".","8",".","3",".",".","1"],
//   ["7",".",".",".","2",".",".",".","6"],
//   [".","6",".",".",".",".","2","8","."],
//   [".",".",".","4","1","9",".",".","5"],
//   [".",".",".",".","8",".",".","7","9"]
// ]
// Output: true
// Example 2:
//
// Input:
// [
//   ["8","3",".",".","7",".",".",".","."],
//   ["6",".",".","1","9","5",".",".","."],
//   [".","9","8",".",".",".",".","6","."],
//   ["8",".",".",".","6",".",".",".","3"],
//   ["4",".",".","8",".","3",".",".","1"],
//   ["7",".",".",".","2",".",".",".","6"],
//   [".","6",".",".",".",".","2","8","."],
//   [".",".",".","4","1","9",".",".","5"],
//   [".",".",".",".","8",".",".","7","9"]
// ]
// Output: false
// Explanation: Same as Example 1, except with the 5 in the top left corner being
//     modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
// Note:
//
// A Sudoku board (partially filled) could be valid but is not necessarily solvable.
// Only the filled cells need to be validated according to the mentioned rules.
// The given board contain only digits 1-9 and the character '.'.
// The given board size is always 9x9.



class Solution {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return true;
        }

        // return mytry(board);

        // 注意如何查 9 个 小的 3 * 3 的 cell， 想要简洁一些就是 1-看看如何简化 row col 的输入, 2- set.add () 是会返回 boolean 的结果

        // return mytry_2(board);

        return method2(board);
    }

    private boolean method2(char[][] board) {
        // more concise
        // ref: https://leetcode.com/problems/valid-sudoku/discuss/15450/Shared-my-concise-Java-code
        for(int i = 0; i < 9; i++){
            HashSet<Character> rows = new HashSet<Character>();
            HashSet<Character> columns = new HashSet<Character>();
            HashSet<Character> cube = new HashSet<Character>();
            for (int j = 0; j < 9; j++){
                if (board[i][j] != '.' && !rows.add(board[i][j])) {
                    return false;
                }
                if (board[j][i] != '.' && !columns.add(board[j][i])) {
                    return false;
                }
                int RowIndex = 3 * (i / 3);
                int ColIndex = 3 * (i % 3);
                if (board[RowIndex + j / 3][ColIndex + j % 3] != '.' && !cube.add(board[RowIndex + j / 3][ColIndex + j % 3])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean mytry_2(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!isParticallyValid(board, i, 0, i, 8)) {
                return false;
            }
            if (!isParticallyValid(board, 0, i, 8, i)) {
                return false;
            }
        }
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if (!isParticallyValid(board, i * 3, j * 3, i * 3 + 2, j * 3 + 2)) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isParticallyValid(char[][] board, int x1, int y1, int x2, int y2) {
        Set<Character> singleSet = new HashSet<>();
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (board[i][j] != '.') {
                    if(!singleSet.add(board[i][j])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean mytry(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        if (n != m || n % 3 != 0 || m % 3 != 0) {
            return false;
        }
        // check each row
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0';
                if (num < 1 || num > 9) {
                    return false;
                }
                if (set.contains(num)) {
                    return false;
                }
                set.add(num);
            }
        }
        // check each column
        for (int i = 0; i < m; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if (board[j][i] == '.') {
                    continue;
                }
                int num = board[j][i] - '0';
                if (num < 1 || num > 9) {
                    return false;
                }
                if (set.contains(num)) {
                    return false;
                }
                set.add(num);
            }
        }
        // check each small 3 * 3 cube
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!isCellValid(board, i * 3, j * 3, i * 3 + 2, j * 3 + 2)) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isCellValid(char[][] board, int x1, int y1, int x2, int y2) {
        Set<Integer> set = new HashSet<>();
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0';
                if (num < 1 || num > 9) {
                    return false;
                }
                if (set.contains(num)) {
                    return false;
                }
                set.add(num);
            }
        }
        return true;
    }
}
