// 37. Sudoku Solver
// DescriptionHintsSubmissionsDiscussSolution
// Write a program to solve a Sudoku puzzle by filling the empty cells.
//
// A sudoku solution must satisfy all of the following rules:
//
// Each of the digits 1-9 must occur exactly once in each row.
// Each of the digits 1-9 must occur exactly once in each column.
// Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
// Empty cells are indicated by the character '.'.
//
//
// A sudoku puzzle...
//
//
// ...and its solution numbers marked in red.
//
// Note:
//
// The given board contain only digits 1-9 and the character '.'.
// You may assume that the given Sudoku puzzle will have a single unique solution.
// The given board size is always 9x9.


class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        dfs(board);
    }

    private boolean dfs(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (dfs(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    // 答案是唯一的， 只要从第一个位置开始就好， 因为 dfs 会填第一个要填的位置， 剩下的位置又 recursion 去完成
                    // 如果这个位置 0 - 9 都不行的话， 那就直接返回 false
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isValid(char[][] board, int x, int y, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[i][y] == c) {
                return false;
            }
        }
        for (int j = 0; j < 9; j++) {
            if (board[x][j] == c) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            char curr = board[3 * (x / 3) + i / 3][3 * (y / 3) + i % 3];
            if (curr != '.' && curr == c) {
                return false;
            }
        }
        return true;
    }
}
